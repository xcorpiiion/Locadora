package br.com.unip.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.unip.dao.ILocadoraDao;
import br.com.unip.dao.LocadoraDao;
import br.com.unip.domain.Automovel;
import br.com.unip.domain.Locadora;

@Service
@Transactional
public class LocadoraService implements ILocadoraService {

	private LocadoraDao locadoraDao = new LocadoraDao();

	private ILocadoraDao iLocadoraDao = locadoraDao;

	@Override
	public Automovel retornaAutomovelPorId(Long id, Automovel automovel) {
		return iLocadoraDao.retornaAutomovelPorId(id, automovel);
	}

	@Override
	public List<Automovel> retornaTodosAutomoveis(Automovel automovel) {
		return iLocadoraDao.retornaTodosAutomoveis(automovel);
	}

	@Override
	public void alugarAutomovel(int quantidade) {
		iLocadoraDao.alugarAutomovel(quantidade);
	}

	@Override
	public void cadastrarAutomovel(Automovel automovel) {
		iLocadoraDao.cadastrarAutomovel(automovel);
	}

	@Override
	public void cadastrarLocadora(Locadora locadora) {
		iLocadoraDao.cadastrarLocadora(locadora);
	}

	@Override
	public boolean verificaAutomovelEstaCadastrado(List<Automovel> automoveis, Locadora locadora) {
		return iLocadoraDao.verificaAutomovelEstaCadastrado(automoveis, locadora);
	}

}
