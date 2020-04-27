package br.com.unip.dao;

import java.util.List;

import br.com.unip.domain.Automovel;
import br.com.unip.domain.Cliente;
import br.com.unip.domain.Locadora;

public interface IClienteDao {

	void cadastrarCliente(Cliente cliente);

	Cliente retornaClientePorEmailAndSenha(String email, String senha, Cliente cliente, String condicao1,
			String condicao2, String respostaCondicao1, String respostaCondicao2);

	Automovel retornaAutomovelPorId(Long id, Automovel automovel);

	List<Automovel> retornaTodosAutomoveis(Automovel automovel);

	List<Locadora> retornaTodasLocadoras(Locadora locadora);
	
	Locadora retornaLocadoraPorId(Long id, Locadora locadora);
	
	void alugarAutomovel(Cliente cliente);

}
