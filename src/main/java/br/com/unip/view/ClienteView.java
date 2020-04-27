package br.com.unip.view;

import javax.swing.JOptionPane;

import br.com.contmatic.controller.ClienteController;
import br.com.unip.domain.Cliente;
import br.com.unip.service.ClienteService;

public class ClienteView {
	
	private static Cliente cliente;
	
	private static ClienteController clienteController = new ClienteController(new ClienteService());
	
	public static void verificaSePossuiLogin() {
		int resposta =  JOptionPane.showConfirmDialog(null, "Você possui login ?", "Tela de Login - Possui Login", JOptionPane.YES_NO_OPTION, JOptionPane.NO_OPTION);
		if(resposta == JOptionPane.YES_OPTION) {
			String email = JOptionPane.showInputDialog(null, "Informe o seu Email: ", "Cadastro - Email do Cliente",
					JOptionPane.INFORMATION_MESSAGE);
			String senha = JOptionPane.showInputDialog(null, "Informe a sua Senha: ", "Cadastro - Senha da Cliente",
					JOptionPane.INFORMATION_MESSAGE);
			cliente = new Cliente();
			verificarLogin(email, senha, cliente);
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
		cliente = new Cliente(nome, email, senha);
		clienteController.salvarCliente(cliente);
	}
	
	private static void verificarLogin(String email, String senha, Cliente cliente) {
		System.out.println(clienteController.retornaClientePeloEmailAndSenha(email, senha, cliente, "email", "senha", email, senha));
	}
	
}
