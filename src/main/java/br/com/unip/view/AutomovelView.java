package br.com.unip.view;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.unip.domain.Automovel;
import br.com.unip.domain.Locadora;
import br.com.unip.domain.Marca;
import br.com.unip.domain.Modelo;

public final class AutomovelView {

	private AutomovelView() {
		
	}
	
	public static Automovel cadastrarAutomovel(Locadora locadora) {
		return new Automovel(cadastrarMarca(), cadastrarModelo(), atribuiPrecoAoAutomovel(), locadora);
	}

	private static BigDecimal atribuiPrecoAoAutomovel() {
		return BigDecimal.valueOf(Double.parseDouble(JOptionPane.showInputDialog(null,
				"Informe o preço do veiculo: ", "Cadastro - Preço", JOptionPane.INFORMATION_MESSAGE)));
	}
	
	private static Marca cadastrarMarca() {
		List<Marca> marcas = Arrays.asList(Marca.values());
		Object[] marcasObjects = new Object[Marca.values().length];
		for (int i = 0; i < marcas.size(); i++) {
			marcasObjects[i] = marcas.get(i).name();
		}
		String nomeMarca = (String) JOptionPane.showInputDialog(null, "Informe a Marca do Automovel: ", "Cadastro - Automovel",
				JOptionPane.QUESTION_MESSAGE, null, marcasObjects, null);

		return Marca.valueOf(nomeMarca);
	}
	
	private static Modelo cadastrarModelo() {
		List<Modelo> modelos = Arrays.asList(Modelo.values());
		Object[] modelosObjects = new Object[Modelo.values().length];
		for (int i = 0; i < modelos.size(); i++) {
			modelosObjects[i] = modelos.get(i).name();
		}
		String nomeModelo = (String) JOptionPane.showInputDialog(null, "Informe a Marca do Automovel: ", "Cadastro - Automovel",
				JOptionPane.QUESTION_MESSAGE, null, modelosObjects, null);
		
		return Modelo.valueOf(nomeModelo);
	}
}
