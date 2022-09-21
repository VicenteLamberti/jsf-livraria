package br.com.alura.livraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
	
	

}
