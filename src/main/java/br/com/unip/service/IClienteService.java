package br.com.unip.service;

import java.util.List;

import br.com.unip.domain.Automovel;
import br.com.unip.domain.Cliente;
import br.com.unip.domain.Locadora;

public interface IClienteService {
	
	Cliente retornaClientePorEmailAndSenha(String email, String senha, Cliente cliente, String condicao1,
			String condicao2, String respostaCondicao1, String respostaCondicao2);
	
	void cadastrarCliente(Cliente cliente);
	
	Automovel retornaAutomovelPorId(Long id, Automovel automovel);
	
	List<Automovel> retornaTodosAutomoveis(Automovel automovel);
	
	List<Locadora> retornaTodasLocadoras(Locadora locadora);
	
	Locadora retornaLocadoraPorId(Long id, Locadora locadora);
	
	void alugarAutomovel(Cliente cliente);
	
	
}
