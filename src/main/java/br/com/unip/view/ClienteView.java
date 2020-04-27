package br.com.unip.view;

import java.math.BigDecimal;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.contmatic.controller.ClienteController;
import br.com.contmatic.controller.LocadoraController;
import br.com.unip.domain.Automovel;
import br.com.unip.domain.Cliente;
import br.com.unip.domain.Locadora;
import br.com.unip.service.ClienteService;
import br.com.unip.service.LocadoraService;

public class ClienteView {

	private static Cliente cliente;

	private static ClienteController clienteController = new ClienteController(new ClienteService());

	public static void verificaSePossuiLogin() {
		int resposta = JOptionPane.showConfirmDialog(null, "Você possui login ?", "Tela de Login - Possui Login",
				JOptionPane.YES_NO_OPTION, JOptionPane.NO_OPTION);
		if (resposta == JOptionPane.YES_OPTION) {
			String email = JOptionPane.showInputDialog(null, "Informe o seu Email: ", "Cadastro - Email do Cliente",
					JOptionPane.INFORMATION_MESSAGE);
			String senha = JOptionPane.showInputDialog(null, "Informe a sua Senha: ", "Cadastro - Senha da Cliente",
					JOptionPane.INFORMATION_MESSAGE);
			cliente = new Cliente();
			cliente = verificarLogin(email, senha, cliente);
			if (cliente != null) {
				perguntaSeDesejaAlugarAutomovel();
			}
		} else {
			cadastrarCliente();
		}
	}

	private static void cadastrarCliente() {
		String nome = JOptionPane.showInputDialog(null, "Informe o seu Nome: ", "Cadastro - Nome da Cliente",
				JOptionPane.INFORMATION_MESSAGE);
		String email = JOptionPane.showInputDialog(null, "Informe o seu Email: ", "Cadastro - Email do Cliente",
				JOptionPane.INFORMATION_MESSAGE);
		String senha = JOptionPane.showInputDialog(null, "Informe a sua Senha: ", "Cadastro - Senha da Cliente",
				JOptionPane.INFORMATION_MESSAGE);
		BigDecimal dinheiro = BigDecimal.valueOf(Double
				.parseDouble(JOptionPane.showInputDialog(null, "Informe o valor em Dinheiro para a sua carteira: ",
						"Cadastro - Dinheiro na carteira", JOptionPane.INFORMATION_MESSAGE)));
		cliente = new Cliente(nome, email, senha, dinheiro);
		clienteController.salvarCliente(cliente);
	}

	private static Cliente verificarLogin(String email, String senha, Cliente cliente) {
		cliente = clienteController.retornaClientePeloEmailAndSenha(email, senha, cliente, "email", "senha", email,
				senha);
		if (cliente == null) {
			JOptionPane.showMessageDialog(null, "Email ou senha informados são invalidos", "Login - Error",
					JOptionPane.ERROR_MESSAGE);
			return null;
		} else {
			JOptionPane.showMessageDialog(null, "Login feito com sucesso", "Login - Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
			return cliente;
		}
	}

	public static void perguntaSeDesejaAlugarAutomovel() {
		String[] verDadosOuAlugarAutomovel = { "Alugar Automovel", "Ver meus dados" };
		String resposta = (String) JOptionPane.showInputDialog(null, "Escolha uma opção: ",
				"Tela de escolha - Escolha", JOptionPane.QUESTION_MESSAGE, null, verDadosOuAlugarAutomovel,
				null);
		if (resposta.equalsIgnoreCase(verDadosOuAlugarAutomovel[0])) {
			Locadora locadora = buscaLocadoraPeloId();
			if (locadora != null) {
				addAutomoveisCadastradosParaMostraNaTela(locadora);
			}
		} else if (resposta.equalsIgnoreCase(verDadosOuAlugarAutomovel[1])) {
			JOptionPane.showMessageDialog(null,
					"Nome: " + cliente.getNome() + "\nEmail: " + cliente.getEmail() + "\nR$: " + cliente.getDinheiro()
							+ "\nCarro Alugado: " + cliente.getAutomovelAlugado(),
					"Dados do cliente - Informações", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private static Locadora buscaLocadoraPeloId() {
		Locadora locadora = new Locadora();
		List<Locadora> locadoras = clienteController.retornarTodasLocadoras(locadora);
		Locadora[] locadoraDados = new Locadora[locadoras.size()];
		for (int i = 0; i < locadoras.size(); i++) {
			locadoraDados[i] = locadoras.get(i);
		}
		Locadora locadoraSelecionada = (Locadora) JOptionPane.showInputDialog(null,
				"Escolha a Locadora que você deseja vê os carros: ", "Escolha - Escolher Locadora",
				JOptionPane.INFORMATION_MESSAGE, null, locadoraDados, null);

		locadora = clienteController.retornarLocadoraPorId(locadoraSelecionada, locadoraSelecionada.getId());
		if (locadora == null) {
			JOptionPane.showMessageDialog(null, "Locadora não encontrada", "Error - Não encontrado",
					JOptionPane.ERROR_MESSAGE);
			return null;
		} else {
			return locadora;
		}
	}

	private static void addAutomoveisCadastradosParaMostraNaTela(Locadora locadora) {
		LocadoraController locadoraController = new LocadoraController(new LocadoraService());
		List<Automovel> automoveis = locadoraController.retornarTodosAutomoveis(new Automovel());
		for (int i = 0; i < automoveis.size(); i++) {
			if (locadoraController.verificaAutomovelEstaCadastrado(automoveis.get(i), locadora)) {
				automoveis.remove(i);
				i--;
			}
		}
		Automovel[] automoveisDados = new Automovel[automoveis.size()];
		for (int i = 0; i < automoveis.size(); i++) {
			automoveisDados[i] = new Automovel();
			automoveisDados[i] = automoveis.get(i);
		}
		Automovel automovelSelecionado = (Automovel) JOptionPane.showInputDialog(null,
				"Esses são todos os Automoveis cadastrados pela Locadora: " + locadora.getNome().toUpperCase()
						+ "\nSelecione um Automovel para alugar: ",
				"Lista de Automoveis", JOptionPane.INFORMATION_MESSAGE, null, automoveisDados, null);
		automovelAlugado(automovelSelecionado);
	}

	private static void automovelAlugado(Automovel automovel) {
		if (cliente.getDinheiro().compareTo(automovel.getPreco()) >= 0) {
			cliente.setDinheiro(cliente.getDinheiro().subtract(automovel.getPreco()));
			cliente.setAutomovelAlugado(automovel);
			clienteController.alugaCarro(cliente);
			JOptionPane.showMessageDialog(null, "Automovel alugado com sucesso \n" + automovel,
					"Aluguel Automovel - Sucesso", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Dinheiro insulficiente para alugar \n" + automovel,
					"Aluguel Automovel - Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
