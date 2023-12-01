package ifpr.visual;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import ifpr.controle.bd.fabrica.ClienteFabrica;
import ifpr.controle.bd.fabrica.ProfissionalFabrica;
import ifpr.modelo.Agendamento;
import ifpr.modelo.Cliente;
import ifpr.modelo.Profissional;
import ifpr.visual.componente.TelaCadastro;
import ifpr.visual.componente.TopoCadastro;

public class AgendamentoCadastro extends TelaCadastro<Agendamento> {

	private JPanel contentPane;
	private JFormattedTextField txData;
	private JComboBox<Cliente> cbClientes;
	private JComboBox<Profissional> cbProfissionais;

	/**
	 * Create the frame.
	 */
	public AgendamentoCadastro() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 585, 228);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		TopoCadastro topoCadastro = new TopoCadastro("Agendamento: ", this);
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
		
		txData = new JFormattedTextField();
		txData.setHorizontalAlignment(SwingConstants.CENTER);
		txData.setBounds(153, 86, 137, 26);
		try {
			txData.setFormatterFactory(
					new DefaultFormatterFactory(
							new MaskFormatter("##/##/####")));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		panel.add(txData);
		
		JLabel lblCpf_1_1 = new JLabel("Data Atendimento:");
		lblCpf_1_1.setBounds(10, 86, 131, 26);
		panel.add(lblCpf_1_1);
		
		cbClientes = new JComboBox();
		cbClientes.setBounds(93, 11, 479, 27);
		panel.add(cbClientes);
		
		cbProfissionais = new JComboBox();
		cbProfissionais.setBounds(93, 48, 479, 27);
		panel.add(cbProfissionais);
		
		// Eu coloco a data de hoje automaticamente só para mostrar:
		txData.setText(
				LocalDate.now().format(
						DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		
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
			item = new Agendamento();
		
		// A gente preencheu os comboboxes com os objetos, agora é só pegar:
		item.setCliente((Cliente) cbClientes.getSelectedItem());
		item.setProfissional((Profissional) cbProfissionais.getSelectedItem());
		
		item.setData(LocalDate.parse(txData.getText(), 
				DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		
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
	protected void preencheFormulario(Agendamento item) {
		cbClientes.setSelectedItem(item.getCliente());
		cbProfissionais.setSelectedItem(item.getProfissional());
		
		txData.setText(
				item.getData().format(
						DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	}
	
}
