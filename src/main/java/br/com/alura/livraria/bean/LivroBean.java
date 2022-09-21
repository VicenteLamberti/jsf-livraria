package br.com.alura.livraria.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

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
//			throw new RuntimeException("Livro deve ter pelo menos um autor");
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um autor"));
			return;
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
		System.out.println("Associando autor " + autor.getNome() + " ao livro " + livro.getTitulo());
		this.livro.adicionaAutor(autor);
	}
	
	public void comecaComDigitoUm(FacesContext fc, UIComponent componente, Object value) {
		String valor = value.toString();
		if(!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("ISBN deve começar com 1"));
		}
	}

}