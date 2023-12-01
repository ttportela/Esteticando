package ifpr.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Atendimento extends DataObject {
	
	private LocalDate data;
	private Cliente cliente;
	private Profissional profissional;
	private String observacoes;
	private List<Produto> itens = new ArrayList<>();
	
	public LocalDate getData() {
		return data;
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the profissional
	 */
	public Profissional getProfissional() {
		return profissional;
	}

	/**
	 * @param profissional the profissional to set
	 */
	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}
	
	public String getObservacoes() {
		return observacoes;
	}
	
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	/**
	 * @return the itens
	 */
	public List<Produto> getItens() {
		return itens;
	}

	/**
	 * @param itens the itens to set
	 */
	public void setItens(List<Produto> itens) {
		this.itens = itens;
	}

	public double calcularTotal() {
		double total = 0.0;
		for (Produto p : itens) {
			total += p.getPreco();
		}
		return total;
	}

}
