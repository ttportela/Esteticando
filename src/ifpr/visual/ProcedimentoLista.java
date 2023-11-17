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

import ifpr.controle.bd.fabrica.ProcedimentoFabrica;
import ifpr.controle.bd.fabrica.ProdutoFabrica;
import ifpr.modelo.Procedimento;
import ifpr.modelo.ProdutoPrateleira;

public class ProcedimentoLista extends JFrame {

	private JPanel contentPane;
	private JTable tbListagem;

	/**
	 * Create the frame.
	 */
	public ProcedimentoLista() {
		setTitle("Listagem de Procedimentos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 894, 654);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lbTitulo = new JLabel("Procedimentos");
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
				ProcedimentoCadastro tela = new ProcedimentoCadastro();
				tela.setVisible(true);

				// Método que adiciona um evento para listar os itens
				// quando a tela fechar
				addEventoAtualizar(tela);
			}
		});
		btNovo.setBackground(Color.WHITE);
		btNovo.setIcon(new ImageIcon(ProcedimentoLista.class.getResource("/ico/plus-24.png")));
		btNovo.setMnemonic('n');
		tbAcoes.add(btNovo);
		
		tbAcoes.addSeparator();
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProcedimentoCadastro telaCad = new ProcedimentoCadastro();
				
				Procedimento item = lista.get( tbListagem.getSelectedRow() );
				telaCad.setItem(item);

				telaCad.setVisible(true);
				// Método que adiciona um evento para listar os itens
				// quando a tela fechar
				addEventoAtualizar(telaCad);
				
			}
		});
		btnAlterar.setBackground(Color.WHITE);
		btnAlterar.setIcon(new ImageIcon(ProcedimentoLista.class.getResource("/ico/edit-2-24.png")));
		btnAlterar.setMnemonic('a');
		tbAcoes.add(btnAlterar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Procedimento item = lista.get( tbListagem.getSelectedRow() );
				
				int op = JOptionPane.showConfirmDialog(null, 
						"Tem certeza que deseja excluir "+item.getNome()+"?", 
						"Confirma", JOptionPane.YES_NO_OPTION);
				
				if (op ==  JOptionPane.YES_OPTION) { 
					ProcedimentoFabrica fabrica = new ProcedimentoFabrica();
					fabrica.excluir(item);
					listar();
				}
			}
		});
		btnExcluir.setBackground(Color.WHITE);
		btnExcluir.setIcon(new ImageIcon(ProcedimentoLista.class.getResource("/ico/x-mark-24.png")));
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
		btFechar.setIcon(new ImageIcon(ProcedimentoLista.class.getResource("/ico/exit-24.png")));
		btFechar.setMnemonic('f');
		tbAcoes.add(btFechar);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		tbListagem = new JTable();
		scrollPane.setViewportView(tbListagem);
		listar();

	}
	
	List<Procedimento> lista;
	
	private void listar() {
		DefaultTableModel model = new DefaultTableModel();
		
		model.addColumn("Nome");
		model.addColumn("Preço");
		model.addColumn("Duração");
		
		ProcedimentoFabrica fabrica = new ProcedimentoFabrica();
		lista = fabrica.listar();
		
		for (Procedimento item : lista) {
			model.addRow(new Object[] {item.getNome(), item.getPreco(), 
					item.getDuracaoEstimada()});
		}
		
		tbListagem.setModel(model);
	}
	
	private void addEventoAtualizar(JFrame tela) {
		tela.addWindowListener(new WindowListener() {
			
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

}
