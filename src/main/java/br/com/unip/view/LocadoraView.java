package br.com.unip.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import br.com.contmatic.controller.LocadoraController;
import br.com.unip.domain.Automovel;
import br.com.unip.domain.Endereco;
import br.com.unip.domain.Locadora;
import br.com.unip.domain.Telefone;
import br.com.unip.service.LocadoraService;

public final class LocadoraView {

	private static Locadora locadora;

	private static LocadoraController locadoraController = new LocadoraController(new LocadoraService());

	private static Object[] cadastraAutomovel = { "Sim", "Não" };

	private LocadoraView() {

	}

	public static void escolherCadastroCarroOuCadastroLocadora() {
		Object[] escolhaCadatro = { "Cadastrar Locadora", "Cadastrar Carro", "Entrar na locadora" };
		String cadastrarCarroOrEmpresa = (String) JOptionPane.showInputDialog(null,
				"Escolha o que deseja fazer, Carro ou Locadora: ", "Escolha", JOptionPane.QUESTION_MESSAGE, null,
				escolhaCadatro, null);
		switch (cadastrarCarroOrEmpresa) {
		case "Cadastrar Locadora":
			cadastrarLocadora();
			break;
		case "Cadastrar Carro":
			cadastrarAutomovel(AutomovelView.cadastrarAutomovel(locadora));
			break;
		case "Entrar na locadora":
			if (buscaLocadoraPeloId() != null) {
				JOptionPane.showMessageDialog(null, "Locadora " + locadora.getNome(),
						"Login Realizado - Dados da locadora", JOptionPane.INFORMATION_MESSAGE);
				verificaSeInsereOuMostraListaAutomoveisDaLocadora();
			}
			break;
		default:
			JOptionPane.showMessageDialog(null, "Digito invalido");
			break;
		}
	}

	private static Locadora buscaLocadoraPeloId() {
		locadora = new Locadora();
		List<Locadora> locadoras = locadoraController.retornarTodasLocadoras(locadora);
		Locadora[] locadoraDados = new Locadora[locadoras.size()];
		for (int i = 0; i < locadoras.size(); i++) {
			locadoraDados[i] = locadoras.get(i);
		}
		Locadora locadoraSelecionada = (Locadora) JOptionPane.showInputDialog(null, "Escolha a sua Locadora: ",
				"Cadastro - Inseri Locadora", JOptionPane.INFORMATION_MESSAGE, null, locadoraDados, null);

		locadora = locadoraController.retornarLocadoraPorId(locadoraSelecionada, locadoraSelecionada.getId());
		if (locadora == null) {
			JOptionPane.showMessageDialog(null, "Locadora não encontrada", "Error - Não encontrado",
					JOptionPane.ERROR_MESSAGE);
			return null;
		} else {
			return locadora;
		}
	}

	private static void cadastrarAutomovel(Automovel automovel) {
		if (buscaLocadoraPeloId() != null) {
			automovel.setLocadora(locadora);
			List<Automovel> automoveis = new ArrayList<>();
			automoveis.add(automovel);
			locadora.setAutomoveis(automoveis);
			JOptionPane.showMessageDialog(null, locadoraController.salvarAutomovel(automovel),
					"Cadastro - Realizado com sucesso", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Falha ao cadastrar Automovel", "Cadastro - Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void cadastrarLocadora() {
		String nome = JOptionPane.showInputDialog(null, "Informe o nome da locadora: ", "Cadastro - Nome da Locadora",
				JOptionPane.INFORMATION_MESSAGE);
		Set<Telefone> telefones = TelefoneView.cadastrarTelefone();
		Endereco endereco = EnderecoView.cadastrarEndereco();
		locadora = new Locadora(nome, telefones, endereco);
		verificaSeInsereOuMostraListaAutomoveisDaLocadora();
		cadastrarLocadora(locadora);
	}

	private static void verificaSeInsereOuMostraListaAutomoveisDaLocadora() {
		Object[] escolhaVerOrInserirAutomovel = { "Ver Automoveis", "Inserir Automovel" };
		String resposta = (String) JOptionPane.showInputDialog(null, "Escolha uma opão: ", "Opções",
				JOptionPane.INFORMATION_MESSAGE, null, escolhaVerOrInserirAutomovel, null);
		if (resposta.equalsIgnoreCase("Inserir Automovel")) {
			retornaAutomoveisOuCadastraAutomovelCasoNaoTenha(new Automovel());
		} else {
			List<Automovel> automoveis = locadoraController.retornarTodosAutomoveis(new Automovel());
			
			removeAutomoveisNaoCadastradosDaLista(automoveis);
			Automovel[] automoveisDados = new Automovel[automoveis.size()];
			
			addAutomoveisCadastradosParaMostraNaTela(automoveis, automoveisDados);
			JOptionPane.showInputDialog(null, "Esses são todos os seus Automoveis cadastrados: ", "Lista de Automoveis",
					JOptionPane.INFORMATION_MESSAGE, null, automoveisDados, null);
		}
	}

	private static void addAutomoveisCadastradosParaMostraNaTela(List<Automovel> automoveis,
			Automovel[] automoveisDados) {
		for (int i = 0; i < automoveis.size(); i++) {
			if (locadoraController.verificaAutomovelEstaCadastrado(automoveis.get(i), locadora)) {
				automoveisDados[i] = automoveis.get(i);
			}
		}
	}

	private static void removeAutomoveisNaoCadastradosDaLista(List<Automovel> automoveis) {
		for (int i = 0; i < automoveis.size(); i++) {
			if (!locadoraController.verificaAutomovelEstaCadastrado(automoveis.get(i), locadora)) {
				automoveis.remove(i);
				i--;
			}
		}
	}

	private static void cadastrarLocadora(Locadora locadora) {
		JOptionPane.showMessageDialog(null, locadoraController.salvarLocadora(locadora), "Cadastro Realizado",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private static void retornaAutomoveisOuCadastraAutomovelCasoNaoTenha(Automovel automovel) {
		List<Automovel> automoveis = locadoraController.retornarTodosAutomoveis(automovel);
		if (automoveis == null || automoveis.isEmpty()) {
			String resposta = (String) JOptionPane.showInputDialog(null, "Deseja cadastrar um carro: ",
					"Cadastro - Carro", JOptionPane.INFORMATION_MESSAGE, null, cadastraAutomovel, null);
			if (resposta.equalsIgnoreCase("sim")) {
				Automovel cadastrarAutomovel = AutomovelView.cadastrarAutomovel(locadora);
				cadastrarAutomovel(cadastrarAutomovel);
			} else {
				cadastrarLocadora(locadora);
			}
		} else {
			Automovel[] automoveisDados = new Automovel[automoveis.size()];
			for (int i = 0; i < automoveis.size(); i++) {
				automoveisDados[i] = automoveis.get(i);
			}
			Automovel automovelSelecionado = (Automovel) JOptionPane.showInputDialog(null,
					"Selecione o Automovel que deseja inserir: ", "Cadastro - Inseri Automovel", JOptionPane.INFORMATION_MESSAGE,
					null, automoveisDados, null);
			verificaAutomovelEstaCadastrado(locadoraController, automovelSelecionado);
		}
	}

	private static void verificaAutomovelEstaCadastrado(LocadoraController locadoraController, Automovel automovel) {
		automovel = locadoraController.retornarAutomovelPorId(automovel, automovel.getId());
		if (locadoraController.verificaAutomovelEstaCadastrado(automovel, locadora)) {
			JOptionPane.showMessageDialog(null, "O Automovel já esta cadastrado", "Cadastro - Falha",
					JOptionPane.WARNING_MESSAGE);
		} else {
			List<Automovel> automoveis = new ArrayList<>();
			automoveis.add(automovel);
			locadora.setAutomoveis(automoveis);
			JOptionPane.showMessageDialog(null, "O Automovel foi cadastrado com sucesso", "Cadastro - Sucesso",
					JOptionPane.WARNING_MESSAGE);
		}
	}
}
