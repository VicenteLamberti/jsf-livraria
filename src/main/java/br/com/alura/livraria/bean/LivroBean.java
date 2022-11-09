package br.com.alura.livraria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.alura.livraria.dao.AutorDao;
import br.com.alura.livraria.dao.DAO;
import br.com.alura.livraria.dao.LivroDao;
import br.com.alura.livraria.model.Autor;
import br.com.alura.livraria.model.Livro;
import br.com.alura.livraria.model.LivroDataModel;
import br.com.alura.livraria.tx.Transacional;

@Named
@ViewScoped
public class LivroBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Livro livro = new Livro();
	private Long autorId;
	private List<Livro> livrosCadastrados;
	
	@Inject
	private LivroDataModel livroDataModel;
    
    private List<String> generos = Arrays.asList("Romance", "Drama", "Ação");
    
    @Inject
    private LivroDao dao;
    
    @Inject
    private AutorDao autorDao;

    @Inject
    private FacesContext context;
    
 

	public List<String> getGeneros() {
		if(generos == null) {
			generos = new ArrayList<String>();
		}
		return generos;
	}

	public void setGeneros(List<String> generos) {
		this.generos = generos;
	}

	public LivroDataModel getLivroDataModel() {
		return livroDataModel;
	}

	public void setLivroDataModel(LivroDataModel livroDataModel) {
		this.livroDataModel = livroDataModel;
	}

	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}

	public Long getAutorId() {
		return autorId;
	}

	public Livro getLivro() {
		if(livro == null) {
			livro = new Livro();
			
		}
		return livro;
	}
	
	

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	@PostConstruct
	public void posCriacao() {
		System.out.println("criei um livroBean");
	}

	@Transacional
	public void gravar() {
		System.out.println("Gravando livro " + livro.getTitulo());
		if (livro.getAutores().isEmpty()) {
//			throw new RuntimeException("Livro deve ter pelo menos um autor");
			context.addMessage("autor",
					new FacesMessage("Livro deve ter pelo menos um autor"));
			return;
		}
		if (this.livro.getId() == null) {
			
			dao.adiciona(this.livro);
			this.livrosCadastrados = dao.buscaTodos();
		} else {
			dao.atualiza(this.livro);
		}
		this.livro = new Livro();
	}

	public List<Autor> getAutores() {
		List<Autor> autores = autorDao.buscaTodos();
		autores.forEach(x -> System.out.println(x.getNome()));
		return autores;
	}

	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}

	public void gravarAutor() {
		Autor autor = autorDao.buscaPorId(this.autorId);
		System.out.println("Associando autor " + autor.getNome() + " ao livro " + livro.getTitulo());
		this.livro.adicionaAutor(autor);
	}

	public void carregar(Livro livro) {
		System.out.println("Carregando livro");
		this.livro = livro;
	}

	@Transacional
	public void excluir(Livro livro) {
		System.out.println("Excluindo livro");
		dao.excluir(livro);
	}

	public void excluirAutorDoLivro(Autor autor) {
		this.livro.removeAutor(autor);
	}

	public void comecaComDigitoUm(FacesContext fc, UIComponent componente, Object value) {
		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("ISBN deve começar com 1"));
		}
	}

	public List<Livro> getLivrosCadastrados() {
		if(this.livrosCadastrados == null) {
			this.livrosCadastrados = dao.buscaTodos();
		}
		return this.livrosCadastrados;
	}

	public String formAutor() {
		return "autor?faces-redirect=true";
	}
	
	public boolean precoEhMenor(Object valorColuna, Object filtroDigitado, Locale locale) { // java.util.Locale

        //tirando espaços do filtro
        String textoDigitado = (filtroDigitado == null) ? null : filtroDigitado.toString().trim();

        System.out.println("Filtrando pelo " + textoDigitado + ", Valor do elemento: " + valorColuna);

        // o filtro é nulo ou vazio?
        if (textoDigitado == null || textoDigitado.equals("")) {
            return true;
        }

        // elemento da tabela é nulo?
        if (valorColuna == null) {
            return false;
        }

        try {
            // fazendo o parsing do filtro para converter para Double
            Double precoDigitado = Double.valueOf(textoDigitado);
            Double precoColuna = (Double) valorColuna;

            // comparando os valores, compareTo devolve um valor negativo se o value é menor do que o filtro
            return precoColuna.compareTo(precoDigitado) < 0;

        } catch (NumberFormatException e) {

            // usuario nao digitou um numero
            return false;
        }
}

}