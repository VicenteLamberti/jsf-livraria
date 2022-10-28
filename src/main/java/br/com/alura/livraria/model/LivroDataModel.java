package br.com.alura.livraria.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.alura.livraria.dao.DAO;
import br.com.alura.livraria.dao.LivroDao;

public class LivroDataModel extends LazyDataModel<Livro> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	LivroDao livroDao;

	@PostConstruct
	public void init() {
		super.setRowCount(livroDao.quantidadeDeElementos());
	}

	@Override
	public List<Livro> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		Set<String> keySet = filters.keySet();
		String valorDigitado = null;
		String campoFiltrado = null;
		for (String key : keySet) {
			if (filters.containsKey(key))
				valorDigitado = (String) filters.get(key);
			campoFiltrado = key;
		}

		return livroDao.listaTodosPaginada(first, pageSize, campoFiltrado, valorDigitado);

	}
}
	
