package br.com.alura.livraria.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.alura.livraria.dao.UsuarioDAO;
import br.com.alura.livraria.model.Usuario;

@Named
@ViewScoped
public class LoginBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario = new Usuario();
	
	@Inject
	UsuarioDAO dao;
	
	@Inject
	FacesContext contexto;
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	@PostConstruct
	public void pos() {
		System.out.println("pos");
	}
	
	public String logar() {
		System.out.println("logando");
		boolean existe = dao.existe(getUsuario());
		if(existe) {
			contexto.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			return "livro?faces-redirect=true";
		}
		contexto.getExternalContext().getFlash().setKeepMessages(true);
		contexto.addMessage(null, new FacesMessage("Usu�rio n�o encontrado"));
		return "login?faces-redirect=true";
		
	}
	
	public String deslogar() {
		contexto.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";
	}
	
	
	
}
