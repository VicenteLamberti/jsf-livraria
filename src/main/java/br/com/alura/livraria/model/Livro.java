package br.com.alura.livraria.model;

import java.util.ArrayList;
import java.util.List;

public class Livro {
	
	private Long id;
	private String titulo;
	private String isbn;
	private double preco;
	private String dataLancamento;
	private List<Autor> autores = new ArrayList<>();
	


	public Livro() {
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

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	
	public List<Autor> getAutores(){
		return autores;
	}
	
	public void adicionaAutor(Autor autor) {
		this.autores.add(autor);
	}
	
	
}