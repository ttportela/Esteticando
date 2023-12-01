package ifpr.visual;

import java.time.LocalDate;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import ifpr.controle.bd.fabrica.AtendimentoFabrica;
import ifpr.modelo.Atendimento;
import ifpr.visual.componente.TelaCadastro;
import ifpr.visual.componente.TelaLista;

public class AtendimentoLista extends TelaLista {

	/**
	 * No costrutor a gente passa para a classe de lista genérica 
	 * o título e a fábrica de objetos que ela precisa
	 */
	public AtendimentoLista() {
		super("Atendimentos", new AtendimentoFabrica());
	}

	@Override
	public void listarItems() {
		DefaultTableModel model = new DefaultTableModel();
		
		model.addColumn("Data");
		model.addColumn("Cliente");
		model.addColumn("Profissional");
		
		lista = fabrica.listar();
		
		for (Atendimento i : (List<Atendimento>) lista) {
			model.addRow(new Object[] {i.getData(), i.getCliente().getNome(), 
					i.getProfissional().getNome()});
		}
		
		tbListagem.setModel(model);
	}

	// Método para instanciar a tela de cadastro específica:
	@Override
	public TelaCadastro getNewTelaCadastro() {
		TelaCadastro telaCad = new AtendimentoCadastro();
		telaCad.setFabrica(fabrica);
		return telaCad;
	}

	/**
	 * Eu sobrescrevi esse método que abre a tela de cadastro para novo (insert)
	 * Assim eu posso atribuir um new Atendimento() para a tela vir pré preenchida.
	 */
	public void abrirNovoCadastro(){
		// Classe pai pediu para abrir um cadastro novo
		TelaCadastro telaCad = getNewTelaCadastro();
		telaCad.setFabrica(fabrica);
		
		Atendimento a = new Atendimento();
		a.setData(LocalDate.now());
		
		telaCad.setItem(a);
		
		telaCad.setVisible(true);
		
		addEventoAtualizar(telaCad);
	}
	
}
