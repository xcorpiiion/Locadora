package br.com.unip.service;

import java.util.List;

import br.com.unip.dao.ClienteDao;
import br.com.unip.dao.IClienteDao;
import br.com.unip.domain.Automovel;
import br.com.unip.domain.Cliente;

public class ClienteService implements IClienteService {

	private ClienteDao clienteDao = new ClienteDao();

	private IClienteDao iClienteDao = clienteDao;

	@Override
	public Automovel retornaAutomovelPorId(Long id, Automovel automovel) {
		return iClienteDao.retornaAutomovelPorId(id, automovel);
	}

	@Override
	public List<Automovel> retornaTodosAutomoveis(Automovel automovel) {
		return iClienteDao.retornaTodosAutomoveis(automovel);
	}

	@Override
	public void alugarAutomovel(int quantidade) {
		iClienteDao.alugarAutomovel(quantidade);

	}

	@Override
	public Cliente retornaClientePorEmailAndSenha(String email, String senha, Cliente cliente, String condicao1,
			String condicao2, String respostaCondicao1, String respostaCondicao2) {
		return iClienteDao.retornaClientePorEmailAndSenha(email, senha, cliente, condicao1, condicao2, respostaCondicao1, respostaCondicao2);
	}

	@Override
	public void cadastrarCliente(Cliente cliente) {
		iClienteDao.cadastrarCliente(cliente);
	}

}
