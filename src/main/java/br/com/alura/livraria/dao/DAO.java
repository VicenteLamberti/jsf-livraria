package br.com.alura.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class DAO<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Class<T> classe;
	private EntityManager em;

	public DAO(EntityManager em, Class<T> classe) {
		this.em = em;
		this.classe = classe;
	}

	public void adiciona(T t) {
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
	}

	public void remove(T t) {
		em.getTransaction().begin();
		em.remove(em.merge(t));
		em.getTransaction().commit();
	}

	public List<T> buscaTodos() {
		em.getTransaction().begin();
		String jpql = "SELECT t FROM " + classe.getSimpleName() + " t";

		List<T> resultado = em.createQuery(jpql).getResultList();
		return resultado;

	}

	public T buscaPorId(Long id) {
		em.getTransaction().begin();
		T obj = em.find(classe, id);
		return obj;
	}

	public void excluir(T obj) {
		em.getTransaction().begin();
		T newObj = em.merge(obj);
		em.remove(newObj);
		em.getTransaction().commit();
	}

	public void atualiza(T obj) {
		em.getTransaction().begin();
		T newObj = em.merge(obj);
		em.persist(newObj);
		em.getTransaction().commit();

	}

	// classe DAO
	public int quantidadeDeElementos() {
		long result = (Long) em.createQuery("select count(n) from " + classe.getSimpleName() + " n").getSingleResult();

		return (int) result;
	}
	
	// classe DAO
	public List<T> listaTodosPaginada(int firstResult, int maxResults, String coluna, String valor) {
	    CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
	    Root<T> root = query.from(classe);

	    if(valor != null)
	        query = query.where(em.getCriteriaBuilder().like(root.<String>get(coluna), valor + "%"));

	    List<T> lista = em.createQuery(query).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();

	    return lista;
	}

}
