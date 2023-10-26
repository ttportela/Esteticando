package ifpr.visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Principal() {
		try {
			UIManager.setLookAndFeel(
			        UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setExtendedState(MAXIMIZED_BOTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Atendimento");
		mnNewMenu.setMnemonic('A');
		menuBar.add(mnNewMenu);
		
		JMenuItem miAtendimentos = new JMenuItem("Atendimentos...");
		miAtendimentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AtendimentoLista().setVisible(true);
			}
		});
		mnNewMenu.add(miAtendimentos);
		
		JSeparator separator_2 = new JSeparator();
		mnNewMenu.add(separator_2);
		
		JMenuItem miAgendamentos = new JMenuItem("Agendamentos...");
		miAgendamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AgendamentoLista().setVisible(true);
			}
		});
		mnNewMenu.add(miAgendamentos);
		
		JMenu mnNewMenu_1 = new JMenu("Cadastros");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem miProdutos = new JMenuItem("Produtos ...");
		miProdutos.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				ProdutoLista pl = new ProdutoLista();
				pl.setVisible(true);
			}
			
		});
		mnNewMenu_1.add(miProdutos);
		
		JMenuItem miProcedimentos = new JMenuItem("Procedimentos ...");
		miProcedimentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ProcedimentoLista().setVisible(true);
			}
		});
		mnNewMenu_1.add(miProcedimentos);
		
		JSeparator separator = new JSeparator();
		mnNewMenu_1.add(separator);
		
		JMenuItem miClientes = new JMenuItem("Clientes ...");
		miClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ClienteLista().setVisible(true);
			}
		});
		mnNewMenu_1.add(miClientes);
		
		JMenuItem miProfissionais = new JMenuItem("Profissionais ...");
		miProfissionais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ProfissionalLista().setVisible(true);
			}
		});
		mnNewMenu_1.add(miProfissionais);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
}
