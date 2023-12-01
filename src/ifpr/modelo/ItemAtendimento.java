package ifpr.modelo;

public class ItemAtendimento {
	
	private Produto item;

	public Produto getItem() {
		return item;
	}

	public void setItem(Produto item) {
		this.item = item;
	}

	public double calcularSubTotal() {
		return item.getPreco(); 
		// No futuro podemos ter a quantidade  
		// return item.getPreco() * quantidade;
	}

}
