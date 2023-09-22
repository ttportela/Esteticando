package ifpr.visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class xProdutoCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField txNome;
	private JTextField txPreco;
	private JTextField txQtd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProdutoCadastro frame = new ProdutoCadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public xProdutoCadastro() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 452, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cadastro de Produto");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 6, 438, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lbNome = new JLabel("Nome:");
		lbNome.setBounds(16, 34, 61, 16);
		contentPane.add(lbNome);
		
		txNome = new JTextField();
		txNome.setBounds(89, 29, 355, 26);
		contentPane.add(txNome);
		txNome.setColumns(10);
		
		JLabel lbDescricao = new JLabel("Descrição:");
		lbDescricao.setBounds(16, 67, 79, 16);
		contentPane.add(lbDescricao);
		
		JTextPane txDescicao = new JTextPane();
		txDescicao.setBounds(16, 91, 428, 82);
		contentPane.add(txDescicao);
		
		txPreco = new JTextField();
		txPreco.setColumns(10);
		txPreco.setBounds(177, 182, 178, 26);
		contentPane.add(txPreco);
		
		JLabel lbPreco = new JLabel("Preço:");
		lbPreco.setBounds(16, 187, 149, 16);
		contentPane.add(lbPreco);
		
		txQtd = new JTextField();
		txQtd.setColumns(10);
		txQtd.setBounds(177, 220, 178, 26);
		contentPane.add(txQtd);
		
		JLabel lbQtEstoque = new JLabel("Qt. Estoque:");
		lbQtEstoque.setBounds(16, 225, 149, 16);
		contentPane.add(lbQtEstoque);
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.setBounds(329, 258, 117, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setBounds(200, 258, 117, 29);
		contentPane.add(btnNewButton_1);
	}
}
