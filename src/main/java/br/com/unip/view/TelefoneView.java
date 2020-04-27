package br.com.unip.view;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import br.com.unip.domain.DddBrasil;
import br.com.unip.domain.Telefone;
import br.com.unip.domain.TipoTelefone;

public final class TelefoneView {

	private TelefoneView() {

	}

	public static Set<Telefone> cadastrarTelefone() {
		TipoTelefone tipoTelefone = cadastrarTipoTelefone();
		DddBrasil dddTelefone = TelefoneView.cadastrarDddTelefone();
		String phone = JOptionPane.showInputDialog(null, "Informe o número do telefone: ", "Cadastro - Telefone",
				JOptionPane.INFORMATION_MESSAGE);
		Set<Telefone> telefones = new HashSet<>();
		telefones.add(new Telefone(phone, tipoTelefone, dddTelefone));
		return telefones;
	}
	
	private static TipoTelefone cadastrarTipoTelefone() {
		List<TipoTelefone> tiposTelefones = Arrays.asList(TipoTelefone.values());
		Object[] tipoTelefone = new Object[TipoTelefone.values().length];
		for (int i = 0; i < tiposTelefones.size(); i++) {
			tipoTelefone[i] = tiposTelefones.get(i).getTipoTelefone();
		}
		String nomeTipoTelefone = (String) JOptionPane.showInputDialog(null, "Informe o tipo de telefone: ", "Cadastro",
				JOptionPane.QUESTION_MESSAGE, null, tipoTelefone, null);
		return TipoTelefone.valueOf(nomeTipoTelefone);
	}

	private static DddBrasil cadastrarDddTelefone() {
		List<DddBrasil> dddsBrasil = Arrays.asList(DddBrasil.values());
		Object[] dddTelefones = new Object[DddBrasil.values().length];
		for (int i = 0; i < dddsBrasil.size(); i++) {
			dddTelefones[i] = dddsBrasil.get(i).name();
		}
		String nomeDddTelefone = (String) JOptionPane.showInputDialog(null, "Informe o seu DDD: ", "Cadastro",
				JOptionPane.QUESTION_MESSAGE, null, dddTelefones, null);
		return DddBrasil.valueOf(nomeDddTelefone);
	}

}
