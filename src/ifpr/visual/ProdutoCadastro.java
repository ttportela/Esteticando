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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ifpr.controle.bd.fabrica.ProdutoFabrica;
import ifpr.modelo.ProdutoPrateleira;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ProdutoCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField txNome;
	private JTextField txPreco;
	private JTextField txQtd;
	private JTextPane txDescicao;

	/**
	 * Create the frame.
	 */
	public ProdutoCadastro() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 673, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnTopo = new JPanel();
		contentPane.add(pnTopo, BorderLayout.NORTH);
		pnTopo.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblProduto = new JLabel("Produto: XXXX");
		lblProduto.setOpaque(true);
		lblProduto.setHorizontalAlignment(SwingConstants.CENTER);
		lblProduto.setForeground(Color.WHITE);
		lblProduto.setFont(new Font("Dialog", Font.BOLD, 20));
		lblProduto.setBackground(new Color(255, 192, 203));
		pnTopo.add(lblProduto);
		
		JToolBar tbAcoes = new JToolBar();
		tbAcoes.setBackground(Color.WHITE);
		pnTopo.add(tbAcoes);
		
		JButton btnAlterar = new JButton("Salvar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvarProduto();
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
		// Eu alterei para o evento keyReleased para atualizar o título da javela com o nome digitado
		txNome.addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
				lblProduto.setText("Produto: " + txNome.getText());
			}
		});
		sl_pnCorpo.putConstraint(SpringLayout.WEST, txNome, 10, SpringLayout.EAST, lbNome);
		sl_pnCorpo.putConstraint(SpringLayout.EAST, txNome, -10, SpringLayout.EAST, pnCorpo);
		sl_pnCorpo.putConstraint(SpringLayout.SOUTH, lbNome, 0, SpringLayout.SOUTH, txNome);
		sl_pnCorpo.putConstraint(SpringLayout.NORTH, txNome, 10, SpringLayout.NORTH, pnCorpo);
		txNome.setColumns(10);
		pnCorpo.add(txNome);
		
		txDescicao = new JTextPane();
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

	private ProdutoPrateleira produto = new ProdutoPrateleira();
	
	public void setProduto(ProdutoPrateleira produto) {
		this.produto = produto;
		txNome.setText(produto.getNome());
		txDescicao.setText(produto.getDescricao());
		txPreco.setText(String.valueOf(produto.getPreco()));
		txQtd.setText(String.valueOf(produto.getQuantidadeDisponivel()));
	}
	
	public ProdutoPrateleira getProduto() {
		return produto;
	}
	
	private void salvarProduto() {
		produto.setNome(txNome.getText());
		produto.setDescricao(txDescicao.getText());
		produto.setPreco(Double.valueOf(txPreco.getText()));
		produto.setQuantidadeDisponivel(Integer.valueOf(txQtd.getText()));
		
		ProdutoFabrica fabrica = new ProdutoFabrica();
		if ( fabrica.salvar(produto) ) {
			JOptionPane.showMessageDialog(this, 
					"Produto salvo com sucesso!", "Sucesso", 
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, 
					"Não foi possível salvar! Tente mais tarde.", "Atenção", 
					JOptionPane.ERROR_MESSAGE);
		}
		
		dispose();
	}
}
