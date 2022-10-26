package br.com.alura.livraria.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.alura.livraria.dao.DAO;
import br.com.alura.livraria.model.Livro;
import br.com.alura.livraria.model.Venda;

@ManagedBean
@ViewScoped
public class VendaBean {
	
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
		List<Livro> livros = new DAO<Livro>(Livro.class).buscaTodos();
		
		List<Venda> vendas = new ArrayList<Venda>();
		
		Random random = new Random(1234);
		livros.forEach(livro->{
			Integer quantidade = random.nextInt(500);
			vendas.add(new Venda(livro, quantidade));
		});
		return vendas;
	}
}
