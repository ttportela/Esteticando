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

import ifpr.controle.bd.fabrica.AgendamentoFabrica;
import ifpr.controle.bd.fabrica.AtendimentoFabrica;
import ifpr.modelo.Agendamento;

public class AgendamentoLista extends JFrame {

	private JPanel contentPane;
	private JTable tbListagem;

	protected AgendamentoFabrica fabrica = new AgendamentoFabrica();
	
	/**
	 * Create the frame.
	 */
	public AgendamentoLista() {
		setTitle("Agendamentos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 894, 654);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lbTitulo = new JLabel("Agendamentos");
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
				AgendamentoCadastro telaCad = new AgendamentoCadastro();
				telaCad.setVisible(true);
				telaCad.setFabrica(fabrica);
				
				telaCad.addWindowListener(new WindowListener() {
					
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
		btNovo.setIcon(new ImageIcon(AgendamentoLista.class.getResource("/ico/plus-24.png")));
		btNovo.setMnemonic('n');
		tbAcoes.add(btNovo);
		
		tbAcoes.addSeparator();
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AgendamentoCadastro telaCad = new AgendamentoCadastro();
				telaCad.setFabrica(fabrica);
				
				Agendamento a = lista.get( tbListagem.getSelectedRow() );
				telaCad.setItem(a);
				
				telaCad.setVisible(true);
				
				telaCad.addWindowListener(new WindowListener() {
					
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
		
		JButton btAtender = new JButton("Atender");
		btAtender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transformaParaAtendimento();
			}
		});
		btAtender.setIcon(new ImageIcon(AgendamentoLista.class.getResource("/ico/star-24.png")));
		btAtender.setMnemonic('a');
		btAtender.setBackground(Color.WHITE);
		tbAcoes.add(btAtender);

		tbAcoes.addSeparator();
		
		btnAlterar.setBackground(Color.WHITE);
		btnAlterar.setIcon(new ImageIcon(AgendamentoLista.class.getResource("/ico/edit-2-24.png")));
		btnAlterar.setMnemonic('a');
		tbAcoes.add(btnAlterar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Agendamento item = lista.get( tbListagem.getSelectedRow() );
				
				int op = JOptionPane.showConfirmDialog(null, 
						"Tem certeza que deseja excluir?", 
						"Confirma", JOptionPane.YES_NO_OPTION);
				
				if (op ==  JOptionPane.YES_OPTION) { 
					fabrica.excluir(item);
					listar();
				}
			}
		});
		btnExcluir.setBackground(Color.WHITE);
		btnExcluir.setIcon(new ImageIcon(AgendamentoLista.class.getResource("/ico/x-mark-24.png")));
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
		btFechar.setIcon(new ImageIcon(AgendamentoLista.class.getResource("/ico/exit-24.png")));
		btFechar.setMnemonic('f');
		tbAcoes.add(btFechar);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		tbListagem = new JTable();
		scrollPane.setViewportView(tbListagem);
		listar();

	}
	
	private List<Agendamento> lista;
	
	private void listar() {
		DefaultTableModel model = new DefaultTableModel();
		
		model.addColumn("Data");
		model.addColumn("Cliente");
		model.addColumn("Profissional");
		
		lista = fabrica.listar();
		
		for (Agendamento i : (List<Agendamento>) lista) {
			model.addRow(new Object[] {i.getData(), i.getCliente().getNome(), 
					i.getProfissional().getNome()});
		}
		
		tbListagem.setModel(model);
	}

	protected void transformaParaAtendimento() {
		// Agora vamos pegar o agendamento e converter em atendimento 
		// e abrir a tela de atendimento:
		Agendamento a = lista.get( tbListagem.getSelectedRow() );
		
		if (fabrica.excluir(a)) {
			// Exclui o agendamento e abre uma tela para atendimento:
			AtendimentoCadastro telaAtm = new AtendimentoCadastro();
			telaAtm.setItem( a.converteAtendimento() );
			telaAtm.setFabrica(new AtendimentoFabrica());
			telaAtm.setVisible(true);
			
		} else {
			JOptionPane.showMessageDialog(this, 
				"Não foi possível abrir o atendimento! Tente mais tarde.", "Atenção", 
				JOptionPane.ERROR_MESSAGE);
		}
		
		listar();
	}
}
