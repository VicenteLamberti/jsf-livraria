package br.com.alura.livraria.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.alura.livraria.dao.UsuarioDAO;
import br.com.alura.livraria.model.Usuario;

@ManagedBean
@ViewScoped
public class LoginBean {

	private Usuario usuario = new Usuario();
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	@PostConstruct
	public void pos() {
		System.out.println("pos");
	}
	
	public String logar() {
		System.out.println("logando");
		boolean existe = new UsuarioDAO<Usuario>().existe(getUsuario());
		if(existe) {
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			return "livro?faces-redirect=true";
		}
		return null;
		
	}
	
	public String deslogar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";
	}
	
	
	
}
