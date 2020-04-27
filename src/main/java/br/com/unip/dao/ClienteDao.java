package br.com.unip.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.unip.domain.Automovel;
import br.com.unip.domain.Cliente;

public class ClienteDao implements IClienteDao {

	private EntityManager entityManager;

	@Override
	public Automovel retornaAutomovelPorId(Long id, Automovel automovel) {
		return null;
	}

	@Override
	public List<Automovel> retornaTodosAutomoveis(Automovel automovel) {
		return null;
	}

	@Override
	public void alugarAutomovel(int quantidade) {

	}

	@Override
	public Cliente retornaClientePorEmailAndSenha(String email, String senha, Cliente cliente, String condicao1,
			String condicao2, String respostaCondicao1, String respostaCondicao2) {
		return (Cliente) ConectionBancoDados.retornaDadosUsandoDuasCondicoes(entityManager, cliente, condicao1, condicao2, respostaCondicao1, respostaCondicao2);
	}

	@Override
	public void cadastrarCliente(Cliente cliente) {
		ConectionBancoDados.insereDadosNoBanco(entityManager, cliente);
	}

}
