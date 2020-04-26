package br.com.unip.view;

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
			cadastrarAutomovel(AutomovelView.cadastrarAutomovel());
			break;
		case "Entrar na locadora":
			int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o número de Identificação da locadora",
					"Tela de Identificação - Busca por codigo", JOptionPane.QUESTION_MESSAGE));
		default:
			JOptionPane.showMessageDialog(null, "Digito invalido");
			break;
		}

	}

	private static void cadastrarAutomovel(Automovel automovel) {
		JOptionPane.showMessageDialog(null, locadoraController.salvarAutomovel(automovel), "Cadastro Realizado",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private static void cadastrarLocadora() {
		String nome = JOptionPane.showInputDialog(null, "Informe o nome da locadora: ", "Cadastro - Nome da Locadora",
				JOptionPane.INFORMATION_MESSAGE);
		Set<Telefone> telefones = TelefoneView.cadastrarTelefone();
		Endereco endereco = EnderecoView.cadastrarEndereco();
		locadora = new Locadora(nome, telefones, endereco);
		Object[] cadastraAutomovel = { "Sim", "Não" };
		cadastrarLocadora(locadora);
		verificaSeInsereAutomovelNaLocadora(cadastraAutomovel);
	}

	private static void verificaSeInsereAutomovelNaLocadora(Object[] cadastraAutomovel) {
		String resposta = (String) JOptionPane.showInputDialog(null, "Deseja inserir um Automovel: ",
				"Cadastro - Inseri Automovel", JOptionPane.INFORMATION_MESSAGE, null, cadastraAutomovel, null);
		if (resposta.equalsIgnoreCase("sim")) {
			retornaAutomoveisOuCadastraAutomovelCasoNaoTenha(new Automovel());
		}
	}

	private static void cadastrarLocadora(Locadora locadora) {
		JOptionPane.showMessageDialog(null, locadoraController.salvarLocadora(locadora), "Cadastro Realizado",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private static void retornaAutomoveisOuCadastraAutomovelCasoNaoTenha(Automovel automovel) {
		List<Automovel> automoveis = locadoraController.retornarAutomovel(automovel);
		if (automoveis == null || automoveis.isEmpty()) {
			Object[] cadastraAutomovel = { "Sim", "Não" };
			String resposta = (String) JOptionPane.showInputDialog(null, "Deseja cadastrar um carro: ",
					"Cadastro - Carro", JOptionPane.INFORMATION_MESSAGE, null, cadastraAutomovel, null);
			if (resposta.equalsIgnoreCase("sim")) {
				Automovel cadastrarAutomovel = AutomovelView.cadastrarAutomovel();
				cadastrarAutomovel.setLocadora(locadora);
				cadastrarAutomovel(cadastrarAutomovel);
			} else {
				cadastrarLocadora(locadora);
			}
		} else {
			verificaAutomovelEstaCadastrado(locadoraController, automoveis);
		}
	}

	private static void verificaAutomovelEstaCadastrado(LocadoraController locadoraController,
			List<Automovel> automoveis) {
		if (locadoraController.verificaAutomovelEstaCadastrado(automoveis, locadora)) {
			JOptionPane.showMessageDialog(null, "O Automovel já esta cadastrado", "Automovel cadastrado",
					JOptionPane.WARNING_MESSAGE);
		} else {
			locadora.setAutomoveis(automoveis);
			System.out.println(automoveis);
			cadastrarLocadora(locadora);
		}
	}
}
