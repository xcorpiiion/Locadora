package br.com.unip.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import br.com.unip.domain.Automovel;
import br.com.unip.domain.Locadora;

@Repository
public class LocadoraDao implements ILocadoraDao {

	private EntityManager entityManager;

	@Override
	public Automovel retornaAutomovelPorId(Long id, Automovel automovel) {
		return (Automovel) ConectionBancoDados.retornaDadoPorId(entityManager, automovel, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Automovel> retornaTodosAutomoveis(Automovel automovel) {
		return (List<Automovel>) ConectionBancoDados.retornaTodosDados(entityManager, automovel);
	}

	@Override
	public void alugarAutomovel(int quantidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cadastrarAutomovel(Automovel automovel) {
		ConectionBancoDados.insereDadosNoBanco(entityManager, automovel);
	}

	@Override
	public void cadastrarLocadora(Locadora locadora) {
		ConectionBancoDados.insereDadosNoBanco(entityManager, locadora);

	}

	@Override
	public boolean verificaAutomovelEstaCadastrado(Automovel automovel, Locadora locadora) {
		if (locadora.getAutomoveis() == null) {
			return false;
		} else if (automovel.getLocadora() == null || automovel.getLocadora().equals(locadora)) {
			return true;
		}

		return false;
	}

	@Override
	public Locadora retornaLocadoraPorId(Long id, Locadora locadora) {
		return (Locadora) ConectionBancoDados.retornaDadoPorId(entityManager, locadora, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Locadora> retornaTodasLocadoras(Locadora locadora) {
		return (List<Locadora>) ConectionBancoDados.retornaTodosDados(entityManager, locadora);
	}

}
