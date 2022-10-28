package br.com.alura.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.alura.livraria.model.Livro;

public class LivroDao implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;
	
	private DAO<Livro> dao;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<Livro>(this.em, Livro.class);
	}

	public void adiciona(Livro t) {
		dao.adiciona(t);
	}

	public void remove(Livro t) {
		dao.remove(t);
	}

	public List<Livro> buscaTodos() {
		return dao.buscaTodos();
	}

	public Livro buscaPorId(Long id) {
		return dao.buscaPorId(id);
	}

	public void excluir(Livro obj) {
		dao.excluir(obj);
	}

	public void atualiza(Livro obj) {
		dao.atualiza(obj);
	}

	public int quantidadeDeElementos() {
		return dao.quantidadeDeElementos();
	}

	public List<Livro> listaTodosPaginada(int firstResult, int maxResults, String coluna, String valor) {
		return dao.listaTodosPaginada(firstResult, maxResults, coluna, valor);
	}

	
	

}
