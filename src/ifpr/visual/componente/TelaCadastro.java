package ifpr.visual.componente;

import javax.swing.JFrame;

import ifpr.controle.bd.fabrica.Fabrica;
import ifpr.modelo.DataObject;

public abstract class TelaCadastro<K extends DataObject> extends JFrame {

	protected K item;
	
	protected Fabrica fabrica;
	
	protected abstract void salvarItem();

	protected abstract void preencheFormulario(K item);
	
	public TelaCadastro() {

	}
	
	public TelaCadastro(K item) {
		this.item = item;
	}
	
	public K getItem() {
		return item;
	}
	
	public void setItem(K item) {
		this.item = item;
		preencheFormulario(item);
	}

	public Fabrica getFabrica() {
		return fabrica;
	}
	
	public void setFabrica(Fabrica fabrica) {
		this.fabrica = fabrica;
	}
	
}
