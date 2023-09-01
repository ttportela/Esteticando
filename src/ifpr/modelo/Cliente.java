package ifpr.modelo;

public class Cliente extends Pessoa {
	
	private String tratamento; 
	private String observacoes;
	/**
	 * @return the tratamento
	 */
	public String getTratamento() {
		return tratamento;
	}
	/**
	 * @param tratamento the tratamento to set
	 */
	public void setTratamento(String tratamento) {
		this.tratamento = tratamento;
	}
	/**
	 * @return the observacoes
	 */
	public String getObservacoes() {
		return observacoes;
	}
	/**
	 * @param observacoes the observacoes to set
	 */
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	

}
