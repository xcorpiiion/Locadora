package br.com.unip.view;

import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.unip.domain.Endereco;
import br.com.unip.domain.EstadosBrasil;

public final class EnderecoView {

	private EnderecoView() {

	}
	
	public static Endereco cadastrarEndereco() {
		String rua = JOptionPane.showInputDialog(null, "Informe o nome da rua: ", "Cadastro - Endereco nome da rua",
				JOptionPane.INFORMATION_MESSAGE);
		String bairro = JOptionPane.showInputDialog(null, "Informe o nome do bairro: ", "Cadastro - Endereco - nome do bairro",
				JOptionPane.INFORMATION_MESSAGE);
		String cep = JOptionPane.showInputDialog(null, "Informe o numero do cep: ", "Cadastro - Endereco - número do cep",
				JOptionPane.INFORMATION_MESSAGE);
		int numeroResidencia = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o número da residencia: ",
				"Cadastro - Endereco - número da residencia", JOptionPane.QUESTION_MESSAGE));
		String cidade = JOptionPane.showInputDialog(null, "Informe o nome da cidade: ", "Cadastro - Endereco - nome da cidade",
				JOptionPane.INFORMATION_MESSAGE);
		return new Endereco(rua, bairro, cep, numeroResidencia, cidade,
				EnderecoView.cadastrarEstadosBrasil());
	}

	private static EstadosBrasil cadastrarEstadosBrasil() {
		List<EstadosBrasil> estadosBrasil = Arrays.asList(EstadosBrasil.values());
		Object[] estados = new Object[EstadosBrasil.values().length];
		for (int i = 0; i < estadosBrasil.size(); i++) {
			estados[i] = estadosBrasil.get(i).name();
		}
		String nomeEstado = (String) JOptionPane.showInputDialog(null, "Informe o número do estado: ", "Cadastro",
				JOptionPane.QUESTION_MESSAGE, null, estados, null);

		return EstadosBrasil.valueOf(nomeEstado);
	}
}
