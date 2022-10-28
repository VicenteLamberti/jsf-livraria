package br.com.alura.livraria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.alura.livraria.dao.LivroDao;
import br.com.alura.livraria.model.Livro;
import br.com.alura.livraria.model.Venda;

@Named
@ViewScoped
public class VendaBean implements Serializable{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private LivroDao livroDao;
	
	public BarChartModel getVendasModel() {
		BarChartModel model = new BarChartModel();
		
		ChartSeries vendasSeries = new ChartSeries();
		
		vendasSeries.setLabel("Vendass");
		
		List<Venda> vendas = getVendas();
		
		vendas.forEach(venda->{
			vendasSeries.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		});
		
		
	
		
		model.addSeries(vendasSeries);
		return model;
	}

	
	public List<Venda>getVendas(){
		List<Livro> livros = livroDao.buscaTodos();
		
		List<Venda> vendas = new ArrayList<Venda>();
		
		Random random = new Random(1234);
		livros.forEach(livro->{
			Integer quantidade = random.nextInt(500);
			vendas.add(new Venda(livro, quantidade));
		});
		return vendas;
	}
}
