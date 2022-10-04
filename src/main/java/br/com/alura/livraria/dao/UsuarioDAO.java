package br.com.alura.livraria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.alura.livraria.model.Usuario;

public class UsuarioDAO<T> extends DAO {

	public UsuarioDAO() {
		super(Usuario.class);
	}

	public boolean existe(Usuario usuario) {
		EntityManagerFactory createEntityManagerFactory = Persistence.createEntityManagerFactory("livraria");
		EntityManager em = createEntityManagerFactory.createEntityManager();
		TypedQuery<Usuario> query = em.createQuery(
				"SELECT usuario FROM Usuario usuario WHERE usuario.email = :email AND usuario.senha = :senha",
				Usuario.class);
		query.setParameter("email", usuario.getEmail());
		query.setParameter("senha", usuario.getSenha());
		try {

			query.getSingleResult();
		} catch (Exception e) {
			return false;
		}

		em.close();
		return true;
	}

}
