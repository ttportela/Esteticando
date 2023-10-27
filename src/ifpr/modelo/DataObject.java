package ifpr.modelo;

public abstract class DataObject {
	
	private int id = -1;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean ehNovo() {
		return this.id == -1;
	}

}
