package br.com.alura.livraria.bean;


import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.alura.livraria.dao.DAO;
import br.com.alura.livraria.model.Autor;

@Named
@ViewScoped // ALterei pro import view
public class AutorBean implements Serializable{

	private Autor autor = new Autor();
	
	private Long autorId;

	public Autor getAutor() {
		return autor;
	}
	
	

	public void setAutor(Autor autor) {
		this.autor = autor;
	}



	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).buscaTodos();
	}

	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if (this.autor.getId() == null) {
			new DAO<Autor>(Autor.class).adiciona(this.autor);
		} else {
			new DAO<Autor>(Autor.class).atualiza(this.autor);
		}

		this.autor = new Autor();

		return "livro?faces-redirect=true";
	}

	public void carregar(Autor autor) {
		System.out.println("Carregando autor");
		this.autor = autor;
	}

	public void remover(Autor autor) {
		System.out.println("Removendo autor");
		new DAO<Autor>(Autor.class).remove(autor);
	}

	public Long getAutorId() {
		return autorId;
	}

	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}
	
	public void carregarAutorPelaId() {
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
	}
	
}
