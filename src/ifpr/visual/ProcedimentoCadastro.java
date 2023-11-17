package ifpr.visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

import ifpr.controle.bd.fabrica.ProcedimentoFabrica;
import ifpr.modelo.Procedimento;

public class ProcedimentoCadastro extends JFrame {

	private Procedimento item = new Procedimento();
	
	private JPanel contentPane;
	private JTextField txNome;
	private JTextField txPreco;
	private JTextField txDuracao;
	private JTextPane txDescicao;

	/**
	 * Create the frame.
	 */
	public ProcedimentoCadastro() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 673, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnTopo = new JPanel();
		contentPane.add(pnTopo, BorderLayout.NORTH);
		pnTopo.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblProduto = new JLabel("Procedimento: XXXX");
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
				salvarItem();
			}
		});
		btnAlterar.setBackground(Color.WHITE);
		btnAlterar.setIcon(new ImageIcon(ProcedimentoCadastro.class.getResource("/ico/checkmark-24.png")));
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
		sl_pnCorpo.putConstraint(SpringLayout.EAST, lbPreco, -576, SpringLayout.EAST, pnCorpo);
		pnCorpo.add(lbPreco);
		
		txPreco = new JTextField();
		sl_pnCorpo.putConstraint(SpringLayout.SOUTH, lbPreco, 0, SpringLayout.SOUTH, txPreco);
		sl_pnCorpo.putConstraint(SpringLayout.EAST, txPreco, 333, SpringLayout.WEST, pnCorpo);
		sl_pnCorpo.putConstraint(SpringLayout.WEST, txPreco, 93, SpringLayout.WEST, pnCorpo);
		sl_pnCorpo.putConstraint(SpringLayout.NORTH, txPreco, 6, SpringLayout.SOUTH, txDescicao);
		txPreco.setColumns(10);
		pnCorpo.add(txPreco);
		
		JLabel lbQtEstoque = new JLabel("Duração:");
		sl_pnCorpo.putConstraint(SpringLayout.NORTH, lbQtEstoque, 6, SpringLayout.SOUTH, lbPreco);
		sl_pnCorpo.putConstraint(SpringLayout.WEST, lbQtEstoque, 10, SpringLayout.WEST, pnCorpo);
		sl_pnCorpo.putConstraint(SpringLayout.SOUTH, lbQtEstoque, 32, SpringLayout.SOUTH, lbPreco);
		pnCorpo.add(lbQtEstoque);
		
		txDuracao = new JTextField();
		sl_pnCorpo.putConstraint(SpringLayout.NORTH, txDuracao, 6, SpringLayout.SOUTH, txPreco);
		sl_pnCorpo.putConstraint(SpringLayout.EAST, lbQtEstoque, -6, SpringLayout.WEST, txDuracao);
		sl_pnCorpo.putConstraint(SpringLayout.WEST, txDuracao, 0, SpringLayout.WEST, txPreco);
		sl_pnCorpo.putConstraint(SpringLayout.EAST, txDuracao, -330, SpringLayout.EAST, pnCorpo);
		txDuracao.setColumns(10);
		pnCorpo.add(txDuracao);
	}

	private void salvarItem() {
		// O item é a variável de classe do JFrame, pode ser um 
		// procedimento novo ou um existente 
		item.setNome(txNome.getText());
		item.setDescricao(txDescicao.getText());
		item.setPreco(Double.valueOf(txPreco.getText()));
		item.setDuracaoEstimada(Integer.valueOf(txDuracao.getText()));
		
		ProcedimentoFabrica fabrica = new ProcedimentoFabrica();
		if ( fabrica.salvar(item) ) {
			JOptionPane.showMessageDialog(this, 
					"Procedimento salvo com sucesso!", "Sucesso", 
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, 
					"Não foi possível salvar! Tente mais tarde.", "Atenção", 
					JOptionPane.ERROR_MESSAGE);
		}
		
		dispose();
	}

	public void setItem(Procedimento item) {
		this.item = item;
		txNome.setText(item.getNome());
		txDescicao.setText(item.getDescricao());
		txPreco.setText(String.valueOf(item.getPreco()));
		txDuracao.setText(String.valueOf(item.getDuracaoEstimada()));
	}
	
	public Procedimento getItem() {
		return item;
	}
}
