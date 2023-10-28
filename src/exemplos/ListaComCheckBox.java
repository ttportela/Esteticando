package exemplos;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

public class ListaComCheckBox extends JFrame {

	private JPanel contentPane;
	private JList list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaComCheckBox frame = new ListaComCheckBox();
					frame.setVisible(true);
					
					frame.addItem();
					frame.addItem();
					frame.addItem();
					frame.addItem();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ListaComCheckBox() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		list = new JList(new DefaultListModel());
		list.setCellRenderer(new CheckboxListCellRenderer());
		contentPane.add(list);
		
		list.addMouseListener(new MouseAdapter(){
	          @Override
	          public void mouseClicked(MouseEvent e) {
	              int index = list.getSelectedIndex();
	              Tarefa t = (Tarefa) list.getSelectedValue();
	              t.setCheck(!t.isCheck());
	          }
	    });
	}
	
	public void addItem() {
	    DefaultListModel model = (DefaultListModel) list.getModel();
	    model.addElement(new Tarefa("Teste"));
	    list.setModel(model);
	}

}

class Tarefa {
	private String texto = "";
	private boolean check = false;
	
	public Tarefa(String texto) {
		this.texto = texto;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public boolean isCheck() {
		return check;
	}
	
	public void setCheck(boolean check) {
		this.check = check;
	}
	
	@Override
	public String toString() {
		return this.texto;
	}
}

class CheckboxListCellRenderer extends JCheckBox implements ListCellRenderer {

    public Component getListCellRendererComponent(JList list, Object value, int index, 
            boolean isSelected, boolean cellHasFocus) {

    	Tarefa v = (Tarefa) value;
    	
        setComponentOrientation(list.getComponentOrientation());
        setFont(list.getFont());
        setBackground(list.getBackground());
        setForeground(list.getForeground());
        setSelected( v.isCheck() );
        setEnabled(list.isEnabled());

        setText(value == null ? "" : value.toString());

        return this;
    }
    
}
