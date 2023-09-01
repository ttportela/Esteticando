package ifpr.modelo;

public class ItemAtendimento {
	
	private int quantidade = 1;
	private Produto item;
	
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getItem() {
		return item;
	}

	public void setItem(Produto item) {
		this.item = item;
	}

	public double calcularSubTotal() {
		return item.getPreco() * quantidade;
	}

}
