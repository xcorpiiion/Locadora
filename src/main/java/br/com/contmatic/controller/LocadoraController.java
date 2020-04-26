package br.com.contmatic.controller;

import java.util.List;

import org.springframework.stereotype.Controller;

import br.com.unip.domain.Automovel;
import br.com.unip.domain.Locadora;
import br.com.unip.service.ILocadoraService;

@Controller
public class LocadoraController {

	private ILocadoraService iLocadoraService;
	
	
	public LocadoraController(ILocadoraService iLocadoraService) {
		this.iLocadoraService = iLocadoraService;
	}

	public String salvarLocadora(Locadora locadora) {
		iLocadoraService.cadastrarLocadora(locadora);
		return "Locadora cadastrada com sucesso";
	}
	
	public String salvarAutomovel(Automovel automovel) {
		iLocadoraService.cadastrarAutomovel(automovel);
		return "Automovel cadastrado com sucesso";
	}
	
	public List<Automovel> retornarAutomovel(Automovel automovel) {
		return iLocadoraService.retornaTodosAutomoveis(automovel);
	}
	
	public Automovel retornarAutomovelPorId(Automovel automovel, Long id) {
		return (Automovel) iLocadoraService.retornaAutomovelPorId(id, automovel);
	}
	
	public boolean verificaAutomovelEstaCadastrado(List<Automovel> automoveis, Locadora locadora) {
		return iLocadoraService.verificaAutomovelEstaCadastrado(automoveis, locadora);
	}
}
