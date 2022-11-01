package br.com.alura.livraria.bean;


import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.alura.livraria.dao.AutorDao;
import br.com.alura.livraria.model.Autor;
import br.com.alura.livraria.tx.Transacional;

@Named
@ViewScoped // ALterei pro import view
public class AutorBean implements Serializable{

	private Autor autor = new Autor();
	
	private Long autorId;
	
	@Inject
	private AutorDao dao; //CDI daz new AutorDao() e injeta

	public Autor getAutor() {
		return autor;
	}
	
	

	public void setAutor(Autor autor) {
		this.autor = autor;
	}



	public List<Autor> getAutores() {
		return dao.buscaTodos();
	}

	@Transacional
	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if (this.autor.getId() == null) {
			dao.adiciona(this.autor);
		} else {
			dao.atualiza(this.autor);
		}

		this.autor = new Autor();

		return "livro?faces-redirect=true";
	}

	public void carregar(Autor autor) {
		System.out.println("Carregando autor");
		this.autor = autor;
	}

	@Transacional
	public void remover(Autor autor) {
		System.out.println("Removendo autor");
		dao.remove(autor);
	}

	public Long getAutorId() {
		return autorId;
	}

	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}
	
	public void carregarAutorPelaId() {
		this.autor = dao.buscaPorId(autorId);
	}
	
}
