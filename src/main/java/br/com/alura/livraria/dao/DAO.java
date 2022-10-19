package br.com.alura.livraria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class DAO<T> {
	private final Class<T> classe;

	public DAO(Class<T> classe) {
		this.classe = classe;
	}

	public void adiciona(T t) {
		EntityManagerFactory createEntityManagerFactory = Persistence.createEntityManagerFactory("livraria");
		EntityManager em = createEntityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
	}

	public void remove(T t) {
		EntityManagerFactory createEntityManagerFactory = Persistence.createEntityManagerFactory("livraria");
		EntityManager em = createEntityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(t));
		em.getTransaction().commit();
		em.close();
	}

	public List<T> buscaTodos() {
		EntityManagerFactory creaEntityManagerFactory = Persistence.createEntityManagerFactory("livraria");
		EntityManager em = creaEntityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		String jpql = "SELECT t FROM " + classe.getSimpleName() + " t";

		List<T> resultado = em.createQuery(jpql).getResultList();
		em.close();
		return resultado;

	}

	public T buscaPorId(Long id) {
		EntityManagerFactory creaEntityManagerFactory = Persistence.createEntityManagerFactory("livraria");
		EntityManager em = creaEntityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		T obj = em.find(classe, id);
		em.close();
		return obj;
	}

	public void excluir(T obj) {
		EntityManagerFactory cemf = Persistence.createEntityManagerFactory("livraria");
		EntityManager em = cemf.createEntityManager();
		em.getTransaction().begin();
		T newObj = em.merge(obj);
		em.remove(newObj);
		em.getTransaction().commit();
		em.close();
	}

	public void atualiza(T obj) {
		EntityManagerFactory creaEntityManagerFactory = Persistence.createEntityManagerFactory("livraria");
		EntityManager em = creaEntityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		T newObj = em.merge(obj);
		em.persist(newObj);
		em.getTransaction().commit();
		em.close();

	}

	// classe DAO
	public int quantidadeDeElementos() {
		EntityManagerFactory creaEntityManagerFactory = Persistence.createEntityManagerFactory("livraria");
		EntityManager em = creaEntityManagerFactory.createEntityManager();
		long result = (Long) em.createQuery("select count(n) from " + classe.getSimpleName() + " n").getSingleResult();
		em.close();

		return (int) result;
	}
	
	// classe DAO
	public List<T> listaTodosPaginada(int firstResult, int maxResults, String coluna, String valor) {
		EntityManagerFactory creaEntityManagerFactory = Persistence.createEntityManagerFactory("livraria");
		EntityManager em = creaEntityManagerFactory.createEntityManager();
	    CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
	    Root<T> root = query.from(classe);

	    if(valor != null)
	        query = query.where(em.getCriteriaBuilder().like(root.<String>get(coluna), valor + "%"));

	    List<T> lista = em.createQuery(query).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();

	    em.close();
	    return lista;
	}

}
