package ifpr.modelo;

import java.util.List;

public class Atendimento extends DataObject {
	
	private Cliente cliente;
	private Profissional profissional;
	private List<ItemAtendimento> itens;
	
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

	/**
	 * @return the itens
	 */
	public List<ItemAtendimento> getItens() {
		return itens;
	}

	/**
	 * @param itens the itens to set
	 */
	public void setItens(List<ItemAtendimento> itens) {
		this.itens = itens;
	}

	public double calcularTotal() {
		return 0;
	}

}
