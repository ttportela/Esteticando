package ifpr.visual.componente;

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

import ifpr.controle.bd.fabrica.Fabrica;
import ifpr.modelo.DataObject;
import ifpr.modelo.Profissional;

public abstract class TelaLista extends JFrame {

	private JPanel contentPane;
	protected JTable tbListagem;
	
	protected Fabrica fabrica;
	
	// Propriedades acessíveis por gets e sets
	private String titulo = "XXXX"; // Ex.: Produtos
	
	// A lista dos objetos listados
	protected List<? extends DataObject> lista; 
	
	/** Os métodos a serem implementados nas classes que extenderem esta: */
	// Um para listar os itens na tabela:
	public abstract void listarItems();
	// Um para instanciar a tela de cadastro específica:
	public abstract TelaCadastro getNewTelaCadastro();

	/**
	 * Construtor.
	 */
	public TelaLista(String titulo, Fabrica fabrica) {
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
		setBounds(100, 100, 894, 654);
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
		
		JButton btNovo = new JButton("Novo");
		btNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				abrirNovoCadastro();
				
			}
		});
		btNovo.setBackground(Color.WHITE);
		btNovo.setIcon(new ImageIcon(TelaLista.class.getResource("/ico/plus-24.png")));
		btNovo.setMnemonic('n');
		tbAcoes.add(btNovo);
		
		tbAcoes.addSeparator();
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				abrirAtualizarCadastro( lista.get( tbListagem.getSelectedRow() ) );
				
			}
		});
		btnAlterar.setBackground(Color.WHITE);
		btnAlterar.setIcon(new ImageIcon(TelaLista.class.getResource("/ico/edit-2-24.png")));
		btnAlterar.setMnemonic('a');
		tbAcoes.add(btnAlterar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DataObject item = lista.get( tbListagem.getSelectedRow() );
				
				int op = JOptionPane.showConfirmDialog(null, 
						"Tem certeza que deseja excluir?", 
						"Confirma", JOptionPane.YES_NO_OPTION);
				
				if (op ==  JOptionPane.YES_OPTION) { 
				
					fabrica.excluir(item);
					listarItems();
					
				}
			}
		});
		btnExcluir.setBackground(Color.WHITE);
		btnExcluir.setIcon(new ImageIcon(TelaLista.class.getResource("/ico/x-mark-24.png")));
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
		btFechar.setIcon(new ImageIcon(TelaLista.class.getResource("/ico/exit-24.png")));
		btFechar.setMnemonic('f');
		tbAcoes.add(btFechar);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		tbListagem = new JTable();
		scrollPane.setViewportView(tbListagem);
		listarItems();

	}

	/**
	 * Método genérico que abre a tela de cadastro para novo (insert)
	 */
	public void abrirNovoCadastro(){
		// Classe pai pediu para abrir um cadastro novo
		TelaCadastro telaCad = getNewTelaCadastro();
		telaCad.setFabrica(fabrica);
		
		telaCad.setVisible(true);
		
		addEventoAtualizar(telaCad);
	}
	
	/**
	 * Método genérico que abre a tela de cadastro para edição
	 */
	public void abrirAtualizarCadastro(DataObject item) {
		TelaCadastro telaCad = getNewTelaCadastro();
		
		telaCad.setItem(item);
		telaCad.setFabrica(fabrica);

		telaCad.setVisible(true);
		
		// Método que adiciona um evento para listar os itens
		// quando a tela fechar
		addEventoAtualizar(telaCad);
	}
	
	/**
	 * Método para adicionar a ação de atualizar a tela de listagem quando 
	 * a tela de cadastro for fechada 
	 */
	public void addEventoAtualizar(JFrame tela) {
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
				listarItems();
			}
			
			@Override
			public void windowActivated(WindowEvent e) {}
		});
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
