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
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;

public class AtendimentoCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField txCliente;
	private JTable tbItens;

	/**
	 * Create the frame.
	 */
	public AtendimentoCadastro() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 673, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnTopo = new JPanel();
		contentPane.add(pnTopo, BorderLayout.NORTH);
		pnTopo.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblProduto = new JLabel("Atendimento");
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
		btnAlterar.setIcon(new ImageIcon(AtendimentoCadastro.class.getResource("/ico/checkmark-24.png")));
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
		
		JLabel lbNome = new JLabel("Cliente:");
		sl_pnCorpo.putConstraint(SpringLayout.NORTH, lbNome, 10, SpringLayout.NORTH, pnCorpo);
		sl_pnCorpo.putConstraint(SpringLayout.WEST, lbNome, 10, SpringLayout.WEST, pnCorpo);
		pnCorpo.add(lbNome);
		
		txCliente = new JTextField();
		txCliente.setEnabled(false);
		txCliente.setEditable(false);
		sl_pnCorpo.putConstraint(SpringLayout.WEST, txCliente, 10, SpringLayout.EAST, lbNome);
		// Eu alterei para o evento keyReleased para atualizar o título da javela com o nome digitado
		txCliente.addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
				lblProduto.setText("Produto: " + txCliente.getText());
			}
		});
		txCliente.setColumns(10);
		pnCorpo.add(txCliente);
		
		JLabel lbDescricao = new JLabel("Produtos / Serviços:");
		sl_pnCorpo.putConstraint(SpringLayout.WEST, lbDescricao, 0, SpringLayout.WEST, lbNome);
		pnCorpo.add(lbDescricao);
		
		JLabel lbTotal = new JLabel("TOTAL:");
		sl_pnCorpo.putConstraint(SpringLayout.NORTH, lbTotal, 135, SpringLayout.SOUTH, lbDescricao);
		sl_pnCorpo.putConstraint(SpringLayout.WEST, lbTotal, 10, SpringLayout.WEST, pnCorpo);
		sl_pnCorpo.putConstraint(SpringLayout.SOUTH, lbTotal, -10, SpringLayout.SOUTH, pnCorpo);
		sl_pnCorpo.putConstraint(SpringLayout.EAST, lbTotal, -10, SpringLayout.EAST, pnCorpo);
		pnCorpo.add(lbTotal);
		
		JButton btnNewButton = new JButton("");
		sl_pnCorpo.putConstraint(SpringLayout.NORTH, txCliente, 0, SpringLayout.NORTH, btnNewButton);
		sl_pnCorpo.putConstraint(SpringLayout.SOUTH, txCliente, 0, SpringLayout.SOUTH, btnNewButton);
		sl_pnCorpo.putConstraint(SpringLayout.EAST, txCliente, -6, SpringLayout.WEST, btnNewButton);
		btnNewButton.setIcon(new ImageIcon(AtendimentoCadastro.class.getResource("/ico/search-3-24.png")));
		sl_pnCorpo.putConstraint(SpringLayout.NORTH, btnNewButton, 10, SpringLayout.NORTH, pnCorpo);
		sl_pnCorpo.putConstraint(SpringLayout.EAST, btnNewButton, 0, SpringLayout.EAST, lbTotal);
		pnCorpo.add(btnNewButton);
		
		JLabel lblProfissional = new JLabel("Profissional:");
		sl_pnCorpo.putConstraint(SpringLayout.NORTH, lblProfissional, 53, SpringLayout.NORTH, pnCorpo);
		sl_pnCorpo.putConstraint(SpringLayout.WEST, lblProfissional, 10, SpringLayout.WEST, pnCorpo);
		sl_pnCorpo.putConstraint(SpringLayout.SOUTH, lbNome, -7, SpringLayout.NORTH, lblProfissional);
		pnCorpo.add(lblProfissional);
		
		JComboBox cbProfissional = new JComboBox();
		sl_pnCorpo.putConstraint(SpringLayout.NORTH, lbDescricao, 6, SpringLayout.SOUTH, cbProfissional);
		sl_pnCorpo.putConstraint(SpringLayout.NORTH, cbProfissional, -4, SpringLayout.NORTH, lblProfissional);
		sl_pnCorpo.putConstraint(SpringLayout.WEST, cbProfissional, 6, SpringLayout.EAST, lblProfissional);
		sl_pnCorpo.putConstraint(SpringLayout.EAST, cbProfissional, 0, SpringLayout.EAST, lbTotal);
		pnCorpo.add(cbProfissional);
		
		JScrollPane scrollPane = new JScrollPane();
		sl_pnCorpo.putConstraint(SpringLayout.NORTH, scrollPane, 6, SpringLayout.SOUTH, lbDescricao);
		sl_pnCorpo.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, pnCorpo);
		sl_pnCorpo.putConstraint(SpringLayout.SOUTH, scrollPane, -6, SpringLayout.NORTH, lbTotal);
		sl_pnCorpo.putConstraint(SpringLayout.EAST, scrollPane, 653, SpringLayout.WEST, pnCorpo);
		pnCorpo.add(scrollPane);
		
		tbItens = new JTable();
		scrollPane.setViewportView(tbItens);
		
	}

	private void salvarProduto() {
		
	}
}
