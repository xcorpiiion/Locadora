package br.com.unip.dao;

import java.util.List;

import br.com.unip.domain.Automovel;
import br.com.unip.domain.Locadora;

public interface ILocadoraDao {
	
	Automovel retornaAutomovelPorId(Long id, Automovel automovel);
	
	Locadora retornaLocadoraPorId(Long id, Locadora locadora);
	
	List<Automovel> retornaTodosAutomoveis(Automovel automovel);
	
	List<Locadora> retornaTodasLocadoras(Locadora locadora);
	
	void alugarAutomovel(int quantidade);
	
	void cadastrarAutomovel(Automovel automovel);
	
	void cadastrarLocadora(Locadora locadora);
	
	boolean verificaAutomovelEstaCadastrado(Automovel automovel, Locadora locadora);
}
