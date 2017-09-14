package main;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

public abstract class JAction extends JDialog{

	public static final int ACTION_ADD = 0;
	public static final int ACTION_DELETE = 1;
	public static final int ACTION_UPDATE = 2;
	
	protected JPanel panel;
	protected JComboBox types;
	protected JButton okButton;
	
	public JAction(){
		init();
	}

	private void init() {		
		panel = new JPanel();
		types = new JComboBox();
		types.addItem("Dodawanie");
		types.addItem("Usuwanie");
		types.addItem("Modyfikacja");
		okButton = new JButton("OK");
		
		buttonAcceptAction();
		
		panel.add(types);
		panel.add(okButton);
		
		this.add(panel);
		this.setLocationRelativeTo(null);
		this.pack();
		this.setVisible(true);
	}
	
	abstract protected void buttonAcceptAction();
}
