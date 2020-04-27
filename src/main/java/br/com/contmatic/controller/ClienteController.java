package br.com.contmatic.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import br.com.unip.domain.Automovel;
import br.com.unip.domain.Cliente;
import br.com.unip.domain.Locadora;
import br.com.unip.service.IClienteService;

@Controller
public class ClienteController {

	private IClienteService iClienteService;

	public ClienteController(IClienteService iClienteService) {
		this.iClienteService = iClienteService;
	}

	public String salvarCliente(Cliente cliente) {
		iClienteService.cadastrarCliente(cliente);
		return "Cliente cadastrado com sucesso";
	}

	public List<Automovel> retornarTodosAutomoveis(Automovel automovel) {
		return iClienteService.retornaTodosAutomoveis(automovel);
	}

	public Cliente retornaClientePeloEmailAndSenha(String email, String senha, Cliente cliente, String codicao1,
			String condicao2, String respostaCondicao1, String respostaCondicao2) {
		return iClienteService.retornaClientePorEmailAndSenha(email, senha, cliente, codicao1, condicao2, respostaCondicao1, respostaCondicao2);
	}

	public List<Locadora> retornarTodasLocadoras(Locadora locadora) {
		return iClienteService.retornaTodasLocadoras(locadora);
	}
	
	public Locadora retornarLocadoraPorId(Locadora locadora, Long id) {
		return iClienteService.retornaLocadoraPorId(id, locadora);
	}
	
	public Automovel retornarAutomovelPorId(Automovel automovel, Long id) {
		return iClienteService.retornaAutomovelPorId(id, automovel);
	}

	public void alugaCarro(Cliente cliente) {
		iClienteService.alugarAutomovel(cliente);
	}

}
