package ifpr.visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import java.awt.FlowLayout;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;

public class ProdutoLista extends JFrame {

	private JPanel contentPane;
	private JTable tbListagem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProdutoLista frame = new ProdutoLista();
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
	public ProdutoLista() {
		setTitle("Listagem de Produtos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 894, 654);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lbTitulo = new JLabel("Produtos");
		lbTitulo.setFont(new Font("Dialog", Font.BOLD, 20));
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setBackground(new Color(255, 192, 203));
		lbTitulo.setForeground(new Color(255, 255, 255));
		lbTitulo.setOpaque(true);
		panel.add(lbTitulo);
		
		JToolBar tbAcoes = new JToolBar();
		tbAcoes.setBackground(Color.WHITE);
		panel.add(tbAcoes);
		
		JButton btNovo = new JButton("Novo");
		btNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProdutoCadastro prod = new ProdutoCadastro();
				prod.setVisible(true);
			}
		});
		btNovo.setBackground(Color.WHITE);
		btNovo.setIcon(new ImageIcon(ProdutoLista.class.getResource("/ico/plus-24.png")));
		btNovo.setMnemonic('n');
		tbAcoes.add(btNovo);
		
		tbAcoes.addSeparator();
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBackground(Color.WHITE);
		btnAlterar.setIcon(new ImageIcon(ProdutoLista.class.getResource("/ico/edit-2-24.png")));
		btnAlterar.setMnemonic('a');
		tbAcoes.add(btnAlterar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBackground(Color.WHITE);
		btnExcluir.setIcon(new ImageIcon(ProdutoLista.class.getResource("/ico/x-mark-24.png")));
		btnExcluir.setMnemonic('e');
		tbAcoes.add(btnExcluir);
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		tbListagem = new JTable(new DefaultTableModel());
		scrollPane.setViewportView(tbListagem);

		listar();
	}
	
	private void listar() {
		DefaultTableModel model = (DefaultTableModel) tbListagem.getModel();
		
		model.addColumn("Nome");
		model.addColumn("Preço");
		
		model.addRow(new Object[] {"Hidratante", 35.9});
		model.addRow(new Object[] {"Shampoo", 59.9});
	}

}
