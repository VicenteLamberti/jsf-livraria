package br.com.alura.livraria.bean;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.alura.livraria.dao.DAO;
import br.com.alura.livraria.model.Autor;
import br.com.alura.livraria.util.RedirectView;

@ManagedBean
public class AutorBean {

	private Autor autor = new Autor();
	public Autor getAutor() {
		return autor;
	}
	
	public List<Autor> getAutores(){
		List<Autor> autores = new DAO<Autor>(Autor.class).buscaTodos();
		autores.forEach(x->System.out.println(x.getNome()));
		return autores;
	}
	
	@PostConstruct
	public void posConstrucao() {
		System.out.println("Criei um autor bean");
	}
	
	public RedirectView gravar() {
		new DAO<Autor>(Autor.class).adiciona(this.autor);
		System.out.println("Gravando autor " + autor.getNome());
		this.autor = new Autor();
		
		return new RedirectView("livro");
	}
	
}
