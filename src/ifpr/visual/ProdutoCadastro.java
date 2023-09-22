package ifpr.visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ifpr.modelo.ProdutoPrateleira;

public class ProdutoCadastro extends JFrame {

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
	public ProdutoCadastro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnTopo = new JPanel();
		contentPane.add(pnTopo, BorderLayout.NORTH);
		pnTopo.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblProdutoXxxx = new JLabel("Produto: XXXX");
		lblProdutoXxxx.setOpaque(true);
		lblProdutoXxxx.setHorizontalAlignment(SwingConstants.CENTER);
		lblProdutoXxxx.setForeground(Color.WHITE);
		lblProdutoXxxx.setFont(new Font("Dialog", Font.BOLD, 20));
		lblProdutoXxxx.setBackground(new Color(255, 192, 203));
		pnTopo.add(lblProdutoXxxx);
		
		JToolBar tbAcoes = new JToolBar();
		tbAcoes.setBackground(Color.WHITE);
		pnTopo.add(tbAcoes);
		
		JButton btnAlterar = new JButton("Salvar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProdutoPrateleira p = new ProdutoPrateleira();
				p.setNome(txNome.getText());
				p.
			}
		});
		btnAlterar.setBackground(Color.WHITE);
		btnAlterar.setIcon(new ImageIcon(ProdutoCadastro.class.getResource("/ico/checkmark-24.png")));
		btnAlterar.setMnemonic('s');
		tbAcoes.add(btnAlterar);
		
		tbAcoes.addSeparator();
		JButton btFechar = new JButton("Fechar");
		btFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btFechar.setBackground(Color.WHITE);
		btFechar.setIcon(new ImageIcon(ProdutoLista.class.getResource("/ico/exit-24.png")));
		btFechar.setMnemonic('f');
		tbAcoes.add(btFechar);
		
		JPanel pnCorpo = new JPanel();
		contentPane.add(pnCorpo, BorderLayout.CENTER);
		SpringLayout sl_pnCorpo = new SpringLayout();
		pnCorpo.setLayout(sl_pnCorpo);
		
		JLabel lbNome = new JLabel("Nome:");
		sl_pnCorpo.putConstraint(SpringLayout.NORTH, lbNome, 10, SpringLayout.NORTH, pnCorpo);
		sl_pnCorpo.putConstraint(SpringLayout.WEST, lbNome, 10, SpringLayout.WEST, pnCorpo);
		pnCorpo.add(lbNome);
		
		txNome = new JTextField();
		sl_pnCorpo.putConstraint(SpringLayout.WEST, txNome, 10, SpringLayout.EAST, lbNome);
		sl_pnCorpo.putConstraint(SpringLayout.EAST, txNome, -10, SpringLayout.EAST, pnCorpo);
		sl_pnCorpo.putConstraint(SpringLayout.SOUTH, lbNome, 0, SpringLayout.SOUTH, txNome);
		sl_pnCorpo.putConstraint(SpringLayout.NORTH, txNome, 10, SpringLayout.NORTH, pnCorpo);
		txNome.setColumns(10);
		pnCorpo.add(txNome);
		
		JTextPane txDescicao = new JTextPane();
		sl_pnCorpo.putConstraint(SpringLayout.WEST, txDescicao, 0, SpringLayout.WEST, lbNome);
		sl_pnCorpo.putConstraint(SpringLayout.EAST, txDescicao, 0, SpringLayout.EAST, txNome);
		pnCorpo.add(txDescicao);
		
		JLabel lbDescricao = new JLabel("Descrição:");
		sl_pnCorpo.putConstraint(SpringLayout.NORTH, txDescicao, 6, SpringLayout.SOUTH, lbDescricao);
		sl_pnCorpo.putConstraint(SpringLayout.SOUTH, txDescicao, 117, SpringLayout.SOUTH, lbDescricao);
		sl_pnCorpo.putConstraint(SpringLayout.NORTH, lbDescricao, 14, SpringLayout.SOUTH, lbNome);
		sl_pnCorpo.putConstraint(SpringLayout.WEST, lbDescricao, 0, SpringLayout.WEST, lbNome);
		pnCorpo.add(lbDescricao);
		
		JLabel lbPreco = new JLabel("Preço:");
		sl_pnCorpo.putConstraint(SpringLayout.NORTH, lbPreco, 9, SpringLayout.SOUTH, txDescicao);
		sl_pnCorpo.putConstraint(SpringLayout.WEST, lbPreco, 10, SpringLayout.WEST, pnCorpo);
		sl_pnCorpo.putConstraint(SpringLayout.SOUTH, lbPreco, -48, SpringLayout.SOUTH, pnCorpo);
		pnCorpo.add(lbPreco);
		
		txPreco = new JTextField();
		sl_pnCorpo.putConstraint(SpringLayout.EAST, lbPreco, -6, SpringLayout.WEST, txPreco);
		sl_pnCorpo.putConstraint(SpringLayout.EAST, txPreco, 333, SpringLayout.WEST, pnCorpo);
		sl_pnCorpo.putConstraint(SpringLayout.WEST, txPreco, 93, SpringLayout.WEST, pnCorpo);
		sl_pnCorpo.putConstraint(SpringLayout.NORTH, txPreco, 6, SpringLayout.SOUTH, txDescicao);
		txPreco.setColumns(10);
		pnCorpo.add(txPreco);
		
		JLabel lbQtEstoque = new JLabel("Qt. Estoque:");
		sl_pnCorpo.putConstraint(SpringLayout.NORTH, lbQtEstoque, 6, SpringLayout.SOUTH, lbPreco);
		sl_pnCorpo.putConstraint(SpringLayout.WEST, lbQtEstoque, 0, SpringLayout.WEST, lbNome);
		sl_pnCorpo.putConstraint(SpringLayout.SOUTH, lbQtEstoque, -30, SpringLayout.SOUTH, pnCorpo);
		pnCorpo.add(lbQtEstoque);
		
		txQtd = new JTextField();
		sl_pnCorpo.putConstraint(SpringLayout.NORTH, txQtd, 6, SpringLayout.SOUTH, txPreco);
		sl_pnCorpo.putConstraint(SpringLayout.WEST, txQtd, 6, SpringLayout.EAST, lbQtEstoque);
		sl_pnCorpo.putConstraint(SpringLayout.EAST, txQtd, 246, SpringLayout.EAST, lbQtEstoque);
		txQtd.setColumns(10);
		pnCorpo.add(txQtd);
	}
}
