package br.com.alura.livraria.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.alura.livraria.dao.DAO;

public class LivroDataModel extends LazyDataModel<Livro> {

	private DAO dao;

	public LivroDataModel() {

		super.setRowCount(getDao().quantidadeDeElementos());
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

		return dao.listaTodosPaginada(first, pageSize, campoFiltrado, valorDigitado);

	}

	public DAO getDao() {
		if (dao == null) {
			dao = new DAO<Livro>(Livro.class);
		}
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}
}
