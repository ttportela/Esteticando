package ifpr.visual;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import ifpr.controle.bd.fabrica.ClienteFabrica;
import ifpr.modelo.Cliente;
import ifpr.visual.componente.TelaCadastro;
import ifpr.visual.componente.TelaLista;

public class ClienteLista extends TelaLista {

	/**
	 * Create the frame.
	 */
	public ClienteLista() {
		super("Clientes", new ClienteFabrica());
	}

	@Override
	public void listarItems() {
		DefaultTableModel model = new DefaultTableModel();
		
		model.addColumn("Nome");
		model.addColumn("Email");
		model.addColumn("Telefone");
		model.addColumn("Tratamento");
		
		lista = fabrica.listar();
		
		for (Cliente i : (List<Cliente>) lista) {
			model.addRow(new Object[] {i.getNome(), i.getEmail(), 
					i.getTelefone(), i.getTratamento()});
		}
		
		tbListagem.setModel(model);
	}
	
	// Método para instanciar a tela de cadastro específica:
	@Override
	public TelaCadastro getNewTelaCadastro() {
		return new ClienteCadastro();
	}
	
	/* antes eu fiz assim, como métodos abstratos, mas agora isso faz parte da 
	 * classe TelaLista (genérica)
	@Override
	public void abrirNovoCadastro() {
		// Classe pai pediu para abrir um cadastro novo
		ClienteCadastro telaCad = new ClienteCadastro();
		telaCad.setVisible(true);
		
		addEventoAtualizar(telaCad);
	}

	@Override
	public void abrirAtualizarCadastro(DataObject item) {
		ClienteCadastro telaCad = new ClienteCadastro();
		
		telaCad.setItem((Cliente) item);

		telaCad.setVisible(true);
		
		// Método que adiciona um evento para listar os itens
		// quando a tela fechar
		addEventoAtualizar(telaCad);
	} 
	*/

}
