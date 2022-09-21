package br.com.alura.livraria.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.alura.livraria.dao.DAO;
import br.com.alura.livraria.model.Livro;


@ManagedBean
public class LivroBean {
	
	private Livro livro = new Livro();
	
	public Livro getLivro() {
		return livro;
	}


	@PostConstruct
	public void posCriacao() {
		System.out.println("criei um livroBean");
	}



	public void gravar() {
		System.out.println("Gravando livro " + livro.getTitulo());
		if(livro.getAutores().isEmpty()) {
			throw new RuntimeException("Livro deve ter pelo menos um autor");
		}
		new DAO<Livro>(Livro.class).adiciona(this.livro);
		this.livro = new Livro();
	}

}