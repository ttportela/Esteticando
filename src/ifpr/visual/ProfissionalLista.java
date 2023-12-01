package ifpr.visual;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import ifpr.controle.bd.fabrica.ProfissionalFabrica;
import ifpr.modelo.Profissional;
import ifpr.visual.componente.TelaCadastro;
import ifpr.visual.componente.TelaLista;

public class ProfissionalLista extends TelaLista {

	/**
	 * No costrutor a gente passa para a classe de lista genérica 
	 * o título e a fábrica de objetos que ela precisa
	 */
	public ProfissionalLista() {
		super("Profissionais", new ProfissionalFabrica());
	}

	@Override
	public void listarItems() {
		DefaultTableModel model = new DefaultTableModel();
		
		model.addColumn("Nome");
		model.addColumn("Email");
		model.addColumn("Telefone");
		model.addColumn("Especialidade");
		
		lista = fabrica.listar();
		
		for (Profissional i : (List<Profissional>) lista) {
			model.addRow(new Object[] {i.getNome(), i.getEmail(), 
					i.getTelefone(), i.getEspecialidade()});
		}
		
		tbListagem.setModel(model);
	}

	// Método para instanciar a tela de cadastro específica:
	@Override
	public TelaCadastro getNewTelaCadastro() {
		return new ProfissionalCadastro();
	}

}
