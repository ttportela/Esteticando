package ifpr.modelo;

import java.time.LocalDate;

public class Agendamento extends DataObject {
	
	private LocalDate data;
	private Cliente cliente;
	private Profissional profissional;
	
	/**
	 * Convertendo um Agendamento para atendimento:
	 * @return
	 */
	public Atendimento converteAtendimento() {
		Atendimento a = new Atendimento();
		a.setData(getData());
		a.setCliente(getCliente());
		a.setProfissional(getProfissional());
		return a;
	}
	
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Profissional getProfissional() {
		return profissional;
	}
	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

}
