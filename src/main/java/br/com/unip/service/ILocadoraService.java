package br.com.unip.service;

import java.util.List;

import br.com.unip.domain.Automovel;
import br.com.unip.domain.Locadora;

public interface ILocadoraService {

	Automovel retornaAutomovelPorId(Long id, Automovel automovel);

	List<Automovel> retornaTodosAutomoveis(Automovel automovel);

	void alugarAutomovel(int quantidade);
	
	void cadastrarAutomovel(Automovel automovel);
	
	void cadastrarLocadora(Locadora locadora);
	
	boolean verificaAutomovelEstaCadastrado(List<Automovel> automoveis, Locadora locadora);
}
