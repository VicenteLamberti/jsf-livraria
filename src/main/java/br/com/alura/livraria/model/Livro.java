package br.com.alura.livraria.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Livro {
	
	@Id
	@GeneratedValue
	private Long id;
	private String titulo;
	private String isbn;
	private Double preco;
	private Date dataLancamento;
	private String genero;


	
	@ManyToMany
	private List<Autor> autores = new ArrayList<>();
	


	public Livro() {
	}

	public void setGenero(String genero) {
	    this.genero = genero;
	}

	public String getGenero() {
	    return genero;
	}
	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	
	public List<Autor> getAutores(){
		return autores;
	}
	
	public void adicionaAutor(Autor autor) {
		this.autores.add(autor);
	}




	public void removeAutor(Autor autor) {
		this.autores.remove(autor);
	}
	
	
}