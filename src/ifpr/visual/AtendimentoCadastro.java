package ifpr.visual;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import ifpr.controle.bd.fabrica.AtendimentoFabrica;
import ifpr.controle.bd.fabrica.ClienteFabrica;
import ifpr.controle.bd.fabrica.ProcedimentoFabrica;
import ifpr.controle.bd.fabrica.ProdutoFabrica;
import ifpr.controle.bd.fabrica.ProfissionalFabrica;
import ifpr.modelo.Atendimento;
import ifpr.modelo.Cliente;
import ifpr.modelo.Produto;
import ifpr.modelo.Profissional;
import ifpr.visual.componente.TelaCadastro;
import ifpr.visual.componente.TopoCadastro;

/** Veja que aqui o Generics é Atendimento -> TelaCadastro<Atendimento> */
public class AtendimentoCadastro extends TelaCadastro<Atendimento> {

	private JPanel contentPane;
	private JEditorPane txObservações;
	private JFormattedTextField txDtNascimento;
	private JComboBox cbClientes;
	private JComboBox cbProfissionais;
	private JTable tbProdutos;
	private JFormattedTextField txTotal;
	
	private JButton btAddProdutos;
	private JButton btAddProcedimentos;

	/**
	 * Create the frame.
	 */
	public AtendimentoCadastro() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 585, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		TopoCadastro topoCadastro = new TopoCadastro("Profissional: ", this);
		topoCadastro.addSalvarListner(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvarItem();
			}
		});
		contentPane.add(topoCadastro, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cliente:");
		lblNewLabel.setBounds(10, 10, 89, 26);
		panel.add(lblNewLabel);
		
		JLabel lblEmail = new JLabel("Profissional:");
		lblEmail.setBounds(10, 48, 89, 26);
		panel.add(lblEmail);
		
		JLabel lblEndereo = new JLabel("Obs.:");
		lblEndereo.setBounds(10, 218, 71, 26);
		panel.add(lblEndereo);
		
		txObservações = new JEditorPane();
		txObservações.setBounds(93, 218, 475, 55);
		panel.add(txObservações);
		
		txDtNascimento = new JFormattedTextField();
		txDtNascimento.setHorizontalAlignment(SwingConstants.CENTER);
		txDtNascimento.setEnabled(false);
		txDtNascimento.setEditable(false);
		txDtNascimento.setBounds(153, 285, 137, 26);
		try {
			txDtNascimento.setFormatterFactory(
					new DefaultFormatterFactory(
							new MaskFormatter("##/##/####")));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		panel.add(txDtNascimento);
		
		JLabel lblCpf_1_1 = new JLabel("Data Atendimento:");
		lblCpf_1_1.setBounds(10, 285, 131, 26);
		panel.add(lblCpf_1_1);
		
		cbClientes = new JComboBox();
		cbClientes.setBounds(93, 11, 479, 27);
		panel.add(cbClientes);
		
		cbProfissionais = new JComboBox();
		cbProfissionais.setBounds(93, 48, 479, 27);
		panel.add(cbProfissionais);
		
		JLabel lblxxx_1 = new JLabel("Adicionar produtos/serviços:");
		lblxxx_1.setBounds(10, 86, 186, 26);
		panel.add(lblxxx_1);
		
		btAddProdutos = new JButton("Produtos");
		btAddProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProdutoSelecao telaAdd = new ProdutoSelecao(
						"Selecionar Produtos", new ProdutoFabrica());
				
				telaAdd.setVisible(true);
				
				// Quando ele fechar a janela eu vou adicionar os itens selecionados:
				telaAdd.addWindowListener(new WindowListener() {
					
					@Override
					public void windowClosed(WindowEvent e) {
						item.getItens().addAll(telaAdd.getSelecionados());
						preencheProdutos(item.getItens());
						
						// No futuro precisamos cuidar para não adicionar 
						// elementos repetidos, ou aumentar as quantidades
						// (ainda não implementamos a quantidade)
					}
					
					@Override
					public void windowOpened(WindowEvent e) {}
					
					@Override
					public void windowIconified(WindowEvent e) {}
					
					@Override
					public void windowDeiconified(WindowEvent e) {}
					
					@Override
					public void windowDeactivated(WindowEvent e) {}
					
					@Override
					public void windowClosing(WindowEvent e) {}
					
					@Override
					public void windowActivated(WindowEvent e) {}
				});
			}
		});
		btAddProdutos.setIcon(new ImageIcon(AtendimentoCadastro.class.getResource("/ico/plus-24.png")));
		btAddProdutos.setBounds(208, 86, 131, 29);
		panel.add(btAddProdutos);
		
		btAddProcedimentos = new JButton("Procedimentos");
		btAddProcedimentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//A tela de seleção funciona para produtos ou procedimentos, basta usar a fábrica correta 
				ProdutoSelecao telaAdd = new ProdutoSelecao(
						"Selecionar Procedimentos", new ProcedimentoFabrica());
				
				telaAdd.setVisible(true);
				
				// Quando ele fechar a janela eu vou adicionar os itens selecionados:
				telaAdd.addWindowListener(new WindowListener() {
					
					@Override
					public void windowClosed(WindowEvent e) {
						item.getItens().addAll(telaAdd.getSelecionados());
						preencheProdutos(item.getItens());
					}
					
					@Override
					public void windowOpened(WindowEvent e) {}
					
					@Override
					public void windowIconified(WindowEvent e) {}
					
					@Override
					public void windowDeiconified(WindowEvent e) {}
					
					@Override
					public void windowDeactivated(WindowEvent e) {}
					
					@Override
					public void windowClosing(WindowEvent e) {}
					
					@Override
					public void windowActivated(WindowEvent e) {}
				});
			}
		});
		btAddProcedimentos.setIcon(new ImageIcon(AtendimentoCadastro.class.getResource("/ico/plus-24.png")));
		btAddProcedimentos.setBounds(351, 87, 154, 29);
		panel.add(btAddProcedimentos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 124, 558, 82);
		panel.add(scrollPane);
		
		tbProdutos = new JTable();
		scrollPane.setViewportView(tbProdutos);
		
		// Eu coloco a data de hoje automaticamente só para mostrar:
		txDtNascimento.setText(
				LocalDate.now().format(
						DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(308, 285, 52, 26);
		panel.add(lblTotal);
		
		// Aqui outra forma de dizer qual a formatação do campo, no caso currency=dinheiro: 
		txTotal = new JFormattedTextField(
				NumberFormat.getCurrencyInstance(new Locale("pt","BR")));
		
		// Então não uso o setText(), uso o setValue:
		txTotal.setValue(0.0);
		
		txTotal.setHorizontalAlignment(SwingConstants.CENTER);
		txTotal.setEnabled(false);
		txTotal.setEditable(false);
		txTotal.setBounds(351, 285, 217, 26);
		
		panel.add(txTotal);
		
		// Vamos preencher os comboboxes no final:
		listarClientes();
		listarProfissionais();
		// Lembre que para aparecerem os nomes dos clientes e profissionais 
		// no combo box, precisamos do toString na classe do model.
		// Eu usei o toString na classe Pessoa que é pai de Cliente e Profissional
	}
	
	protected void listarClientes() {
		ClienteFabrica fabCli = new ClienteFabrica();
		
		// O JComboBox tem um model, assim como a JTable
		// e esse model pode ser uma lista de Cliente
		DefaultComboBoxModel<Cliente> model = new DefaultComboBoxModel<Cliente>();
		
		// Eu faço a listagem do BD e preencho o model:
		for (Cliente i : fabCli.listar()) {
			model.addElement(i);
		}
		
		cbClientes.setModel(model);
	}
	
	protected void listarProfissionais() {
		ProfissionalFabrica fabPro = new ProfissionalFabrica();
		
		DefaultComboBoxModel<Profissional> model = 
				new DefaultComboBoxModel<Profissional>();
		
		for (Profissional i : fabPro.listar()) {
			model.addElement(i);
		}
		
		cbProfissionais.setModel(model);
	}
	
	protected void salvarItem() {
		// Quando é um novo
		if (item == null)
			item = new Atendimento();
		
		// A gente preencheu os comboboxes com os objetos, agora é só pegar:
		item.setCliente((Cliente) cbClientes.getSelectedItem());
		item.setProfissional((Profissional) cbProfissionais.getSelectedItem());
		
		item.setObservacoes(txObservações.getText());
		item.setData(LocalDate.now());
		
		if ( getFabrica().salvar(item) ) {
			JOptionPane.showMessageDialog(this, 
					"Salvo com sucesso!", "Sucesso", 
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, 
					"Não foi possível salvar! Tente mais tarde.", "Atenção", 
					JOptionPane.ERROR_MESSAGE);
		}
		
		dispose();
	}

	@Override
	protected void preencheFormulario(Atendimento item) {
		/* Na hora de listar os agendamentos a gente instancia 
		 * só o nome e id dos clientes e profissionais, e no preenchimento
		 * do combobox são outros objetos instanciados pelas fabricas de cada um
		 * para ele automaticamente selecionar o objeto correto, a gente pode fazer
		 * um método "equals" na classe Pessoa. Então, por exemplo, se um Cliente tem
		 * um id igual de outro objeto cliente eles são o mesmo objeto (mesmo que 
		 * na memória sejam instâncias diferentes). Assim, o combo box vai entender 
		 * que o valor que eu atribuir é igual a algum que já está na lista:
		 */
		cbClientes.setSelectedItem(item.getCliente());
		cbProfissionais.setSelectedItem(item.getProfissional());
		
		txObservações.setText(item.getObservacoes());
		txDtNascimento.setText(
				item.getData().format(
						DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		
		// Agora vamos preeencher a lista dos produtos e procedimentos comprados:
		if (!item.ehNovo()) {
			AtendimentoFabrica f = (AtendimentoFabrica) fabrica;
			item.setItens(f.listarProdutos(item)); // esse método de listagem não faz parte da fábrica genérica 
			
			preencheProdutos(item.getItens());
			
			// vamos desabilitar a adição de novos produtos:
			btAddProdutos.setEnabled(false);
			btAddProcedimentos.setEnabled(false);
		}
	}

	protected void preencheProdutos(List<Produto> itens) {
		DefaultTableModel model = new DefaultTableModel();
		
		model.addColumn("Nome");
		model.addColumn("Preço");
		
		for (Produto p : itens) {
			model.addRow(new Object[] {p.getNome(), p.getPreco()});
		}
		
		tbProdutos.setModel(model);

		// apresento um cálculo do total dos itens
		txTotal.setValue(item.calcularTotal());
	}
}
