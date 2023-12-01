package ifpr.visual;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.xml.bind.DatatypeConverter;

import ifpr.modelo.Cliente;
import ifpr.modelo.Profissional;
import ifpr.visual.componente.TelaCadastro;
import ifpr.visual.componente.TopoCadastro;

/** Veja que aqui o Generics é Profissional -> TelaCadastro<Profissional> */
public class ProfissionalCadastro extends TelaCadastro<Profissional> {

	private JPanel contentPane;
	private JTextField txNome;
	private JPasswordField txSenha;
	private JPasswordField txSenhaRepete;
	private JFormattedTextField txEspecialidade;
	private JFormattedTextField txEmail;
	private JFormattedTextField txCPF;
	private JFormattedTextField txTelefone;
	private JEditorPane txEndereco;
	private JFormattedTextField txDtNascimento;

	/**
	 * Create the frame.
	 */
	public ProfissionalCadastro() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 585, 419);
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
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(10, 10, 89, 26);
		panel.add(lblNewLabel);
		
		txNome = new JTextField();
		txNome.setBounds(106, 10, 462, 26);
		panel.add(txNome);
		txNome.setColumns(10);
		
		JLabel lblEmail = new JLabel("Especialidade:");
		lblEmail.setBounds(10, 48, 89, 26);
		panel.add(lblEmail);
		
		txEspecialidade = new JFormattedTextField();
		txEspecialidade.setBounds(106, 48, 462, 26);
		panel.add(txEspecialidade);
		
		txEmail = new JFormattedTextField();
		txEmail.setBounds(106, 86, 462, 26);
		panel.add(txEmail);
		
		JLabel lblTelefone = new JLabel("Email:");
		lblTelefone.setBounds(10, 86, 89, 26);
		panel.add(lblTelefone);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(10, 124, 33, 26);
		panel.add(lblCpf);
		
		txCPF = new JFormattedTextField();
		txCPF.setBounds(55, 124, 232, 26);
		try {
			txCPF.setFormatterFactory(
					new DefaultFormatterFactory(
							new MaskFormatter("###.###.###-##")));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		panel.add(txCPF);
		
		JLabel lblCpf_1 = new JLabel("Telefone:");
		lblCpf_1.setBounds(299, 124, 71, 26);
		panel.add(lblCpf_1);
		
		txTelefone = new JFormattedTextField();
		txTelefone.setBounds(382, 124, 186, 26);
		try {
			txTelefone.setFormatterFactory(
					new DefaultFormatterFactory(
							new MaskFormatter("(##) #####-####")));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		panel.add(txTelefone);
		
		JLabel lblEndereo = new JLabel("Endereço:");
		lblEndereo.setBounds(10, 162, 71, 26);
		panel.add(lblEndereo);
		
		txEndereco = new JEditorPane();
		txEndereco.setBounds(93, 162, 475, 55);
		panel.add(txEndereco);
		
		txDtNascimento = new JFormattedTextField();
		txDtNascimento.setBounds(153, 229, 186, 26);
		try {
			txDtNascimento.setFormatterFactory(
					new DefaultFormatterFactory(
							new MaskFormatter("##/##/####")));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		panel.add(txDtNascimento);
		
		JLabel lblCpf_1_1 = new JLabel("Data de Nascimento:");
		lblCpf_1_1.setBounds(10, 229, 131, 26);
		panel.add(lblCpf_1_1);
		
		JLabel lblCpf_1_1_1 = new JLabel("Senha:");
		lblCpf_1_1_1.setBounds(10, 267, 71, 26);
		panel.add(lblCpf_1_1_1);
		
		txSenha = new JPasswordField();
		txSenha.setBounds(93, 267, 186, 26);
		panel.add(txSenha);
		
		txSenhaRepete = new JPasswordField();
		txSenhaRepete.setBounds(374, 267, 186, 26);
		panel.add(txSenhaRepete);
		
		JLabel lblCpf_1_1_1_1 = new JLabel("Repita:");
		lblCpf_1_1_1_1.setBounds(291, 267, 71, 26);
		panel.add(lblCpf_1_1_1_1);
	}
	
	protected void salvarItem() {
		// Quando é um novo
		if (item == null)
			item = new Profissional();
		
		if (txSenha.getPassword().equals(txSenhaRepete.getPassword())) {
			JOptionPane.showMessageDialog(this, 
					"A senha não confere! Tente novamente.", "Atenção", 
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		item.setNome(txNome.getText());
		item.setCpf(txCPF.getText());
		item.setEmail(txEmail.getText());
		item.setTelefone(txTelefone.getText());
		item.setEndereco(txEndereco.getText());
		item.setEspecialidade(txEspecialidade.getText());
		
		item.setDataNascimento(
				LocalDate.parse(txDtNascimento.getText(), 
					DateTimeFormatter.ofPattern("dd/MM/yyyy"))
		);		

		// se a senha foi digitada ele deve salvar a nova senha:
		// a senha será criptografada na fábrica
		if (txSenha.getPassword().length > 0) {
			item.setSenha(new String(txSenha.getPassword()));
		}
		
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
	protected void preencheFormulario(Profissional item) {
		txNome.setText(item.getNome());
		txCPF.setText(item.getCpf());
		txEmail.setText(item.getEmail());
		txTelefone.setText(item.getTelefone());
		txEndereco.setText(item.getEndereco());
		txEspecialidade.setText(item.getEspecialidade());
		txDtNascimento.setText(
				item.getDataNascimento().format(
						DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		
		// a senha eu não preencho no formulário, se ele preencher, é pq quer alterar
	}
}
