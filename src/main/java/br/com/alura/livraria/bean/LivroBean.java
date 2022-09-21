package br.com.alura.livraria.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.alura.livraria.dao.DAO;
import br.com.alura.livraria.model.Autor;
import br.com.alura.livraria.model.Livro;


@ManagedBean
@ViewScoped
public class LivroBean {
	
	private Livro livro = new Livro();
	private Long autorId;
	
	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}
	
	public Long getAutorId() {
		return autorId;
	}
	
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
	
	public List<Autor> getAutores(){
		System.out.println("KSOPAKSPOAKSPO");
		List<Autor> buscaTodos = new DAO<Autor>(Autor.class).buscaTodos();
		buscaTodos.forEach(x->System.out.println(x.getNome()));
		return buscaTodos;
	}
	
	public List<Autor> getAutoresDoLivro(){
		return this.livro.getAutores();
	}
	
	public void gravarAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
		System.out.println(autor.getNome() + "KKKKKKKKKKKKKKKKKKKKKK");
		this.livro.adicionaAutor(autor);
	}

}