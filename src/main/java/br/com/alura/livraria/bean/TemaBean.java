package br.com.alura.livraria.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class TemaBean  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tema="vader";

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}
	
	
}
