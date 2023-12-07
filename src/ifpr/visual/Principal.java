package ifpr.visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import ifpr.controle.bd.Conexao;
import ifpr.controle.bd.fabrica.ProdutoFabrica;
import ifpr.modelo.ProdutoPrateleira;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

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
		
		JMenu mnNewMenu_2 = new JMenu("Relatórios");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem miRelProduto = new JMenuItem("Rel. Produtos");
		miRelProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirRelProdutos();
			}
		});
		mnNewMenu_2.add(miRelProduto);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Agendamentos");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirRelAgendamentos();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
	private void abrirRelProdutos() {
		String template = "produtos.jasper";
		ProdutoFabrica fabrica = new ProdutoFabrica();
		List<ProdutoPrateleira> list = fabrica.listar();
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
		
		Map parameters = new HashMap();
		try {
			JasperPrint relatorio = JasperFillManager.fillReport(
					template, parameters, dataSource);
			JasperViewer.viewReport(relatorio,true);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
	
	private void abrirRelAgendamentos() {
		String template = "agendamentos.jasper";
		
		Connection con = Conexao.getInstancia().getCon();
		
		Map parameters = new HashMap();
		try {
			JasperPrint relatorio = JasperFillManager.fillReport(
					template, parameters, con);
			
			JasperViewer.viewReport(relatorio,true);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
