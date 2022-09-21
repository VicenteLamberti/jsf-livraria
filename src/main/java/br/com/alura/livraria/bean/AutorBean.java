package br.com.alura.livraria.bean;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.alura.livraria.dao.DAO;
import br.com.alura.livraria.model.Autor;

@ManagedBean
public class AutorBean {

	private Autor autor = new Autor();
	public Autor getAutor() {
		return autor;
	}
	
	@PostConstruct
	public void posConstrucao() {
		System.out.println("Criei um autor bean");
	}
	
	public void gravar() {
		new DAO<Autor>(Autor.class).adiciona(this.autor);
		System.out.println("Gravando autor " + autor.getNome());
		this.autor = new Autor();
	}
	
}
