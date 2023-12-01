package ifpr.visual.componente;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class TopoCadastro extends JPanel {

	protected JFrame father;
	protected String titulo = "Edição"; 
	
	private JButton btnAlterar;
	private JLabel lblTitulo;
	
	/**
	 * Create the panel.
	 */
	public TopoCadastro(String titulo, JFrame father) {
		this.titulo = titulo;
		this.father = father;
		
		setLayout(new GridLayout(2, 1, 0, 0));
		
		lblTitulo = new JLabel(titulo);
		lblTitulo.setOpaque(true);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 20));
		lblTitulo.setBackground(new Color(255, 192, 203));
		add(lblTitulo);
		
		JToolBar tbAcoes = new JToolBar();
		tbAcoes.setBackground(Color.WHITE);
		add(tbAcoes);
		
		btnAlterar = new JButton("Salvar");
		
		btnAlterar.setBackground(Color.WHITE);
		btnAlterar.setIcon(new ImageIcon(TopoCadastro.class.getResource("/ico/checkmark-24.png")));
		btnAlterar.setMnemonic('s');
		tbAcoes.add(btnAlterar);
		
		tbAcoes.addSeparator();
		JButton btFechar = new JButton("Fechar");
		btFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				father.dispose();
			}
		});
		btFechar.setBackground(Color.WHITE);
		btFechar.setIcon(new ImageIcon(TopoCadastro.class.getResource("/ico/exit-24.png")));
		btFechar.setMnemonic('f');
		tbAcoes.add(btFechar);

	} 
	
	public void addSalvarListner(ActionListener listner) {
		btnAlterar.addActionListener(listner);
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
		lblTitulo.setText(titulo);
	}

}
