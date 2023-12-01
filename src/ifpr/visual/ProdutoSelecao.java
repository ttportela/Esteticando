package ifpr.visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ifpr.controle.bd.fabrica.Fabrica;
import ifpr.modelo.Produto;

public class ProdutoSelecao extends JFrame {

	private JPanel contentPane;
	protected JTable tbListagem;
	
	protected Fabrica fabrica;
	
	// Propriedades acessíveis por gets e sets
	private String titulo = "XXXX"; // Ex.: Produtos
	
	// A lista dos objetos listados
	protected List<? extends Produto> lista; 
	protected List<Produto> selecionados = new ArrayList<Produto>(); 
	
	/**
	 * Construtor.
	 */
	public ProdutoSelecao(String titulo, Fabrica fabrica) {
		this.titulo = titulo;
		this.fabrica = fabrica;
		
		init();
	}

	/**
	 * Método que inicializa os componentes de tela.
	 */
	public void init() {
		setTitle( "Listagem de " + getTitulo() );
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 579, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lbTitulo = new JLabel(getTitulo());
		lbTitulo.setFont(new Font("Dialog", Font.BOLD, 20));
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setBackground(new Color(255, 192, 203));
		lbTitulo.setForeground(new Color(255, 255, 255));
		lbTitulo.setOpaque(true);
		panel.add(lbTitulo);
		
		JToolBar tbAcoes = new JToolBar();
		tbAcoes.setBackground(Color.WHITE);
		panel.add(tbAcoes);
		
		JButton btSelecionar = new JButton("Adicionar");
		btSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Vamos criar uma lista de selecinados:
				for (int i : tbListagem.getSelectedRows()) {
					// para cada linha selecionada na tabela adicionamos na lista de selecionados:
					selecionados.add(lista.get(i));
				}
				dispose();
			}
		});
		btSelecionar.setBackground(Color.WHITE);
		btSelecionar.setIcon(new ImageIcon(ProdutoSelecao.class.getResource("/ico/plus-24.png")));
		btSelecionar.setMnemonic('n');
		tbAcoes.add(btSelecionar);
		
		tbAcoes.addSeparator();
		
		JButton btFechar = new JButton("Fechar");
		btFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btFechar.setBackground(Color.WHITE);
		btFechar.setIcon(new ImageIcon(ProdutoSelecao.class.getResource("/ico/exit-24.png")));
		btFechar.setMnemonic('f');
		tbAcoes.add(btFechar);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		tbListagem = new JTable();
		scrollPane.setViewportView(tbListagem);
		listarItems();

	}
	
	public void listarItems() {
		DefaultTableModel model = new DefaultTableModel();
		
		model.addColumn("Nome");
		model.addColumn("Preço");
		
		lista = fabrica.listar();
		
		for (Produto p : lista) {
			model.addRow(new Object[] {p.getNome(), p.getPreco()});
		}
		
		tbListagem.setModel(model);
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public List<Produto> getSelecionados() {
		return selecionados;
	}

}
