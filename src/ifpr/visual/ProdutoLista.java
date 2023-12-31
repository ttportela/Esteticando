package ifpr.visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ifpr.controle.bd.fabrica.ProdutoFabrica;
import ifpr.modelo.ProdutoPrateleira;

public class ProdutoLista extends JFrame {

	private JPanel contentPane;
	private JTable tbListagem;

	/**
	 * Create the frame.
	 */
	public ProdutoLista() {
		setTitle("Listagem de Produtos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
				
				prod.addWindowListener(new WindowListener() {
					
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
					public void windowClosed(WindowEvent e) {
						listar();
					}
					
					@Override
					public void windowActivated(WindowEvent e) {}
				});
			}
		});
		btNovo.setBackground(Color.WHITE);
		btNovo.setIcon(new ImageIcon(ProdutoLista.class.getResource("/ico/plus-24.png")));
		btNovo.setMnemonic('n');
		tbAcoes.add(btNovo);
		
		tbAcoes.addSeparator();
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProdutoCadastro prod = new ProdutoCadastro();
				
				ProdutoPrateleira p = lista.get( tbListagem.getSelectedRow() );
				prod.setProduto(p);
				
				prod.setVisible(true);
				
				prod.addWindowListener(new WindowListener() {
					
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
					public void windowClosed(WindowEvent e) {
						listar();
					}
					
					@Override
					public void windowActivated(WindowEvent e) {}
				});
			}
		});
		btnAlterar.setBackground(Color.WHITE);
		btnAlterar.setIcon(new ImageIcon(ProdutoLista.class.getResource("/ico/edit-2-24.png")));
		btnAlterar.setMnemonic('a');
		tbAcoes.add(btnAlterar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ProdutoPrateleira p = lista.get( tbListagem.getSelectedRow() );
				
				int op = JOptionPane.showConfirmDialog(null, 
						"Tem certeza que deseja excluir "+p.getNome()+"?", 
						"Confirma", JOptionPane.YES_NO_OPTION);
				
				if (op ==  JOptionPane.YES_OPTION) { 
				
					ProdutoFabrica fabrica = new ProdutoFabrica();
					fabrica.excluir(p);
					listar();
				}
			}
		});
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
		
		tbListagem = new JTable();
		scrollPane.setViewportView(tbListagem);
		listar();

	}
	
	private List<ProdutoPrateleira> lista;
	
	private void listar() {
		DefaultTableModel model = new DefaultTableModel();
		
		model.addColumn("Nome");
		model.addColumn("Preço");
		model.addColumn("QTD.");
		
		ProdutoFabrica fabrica = new ProdutoFabrica();
		lista = fabrica.listar();
		
		for (ProdutoPrateleira p : lista) {
			model.addRow(new Object[] {p.getNome(), p.getPreco(), 
					p.getQuantidadeDisponivel()});
		}
		
		tbListagem.setModel(model);
	}

}
