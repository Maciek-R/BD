package dialogs.client_dialogs;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Board;
import main.Validation;
import tables.client.ClientDAO;

public abstract class JClientDialog extends JDialog{

	protected JTextField id;
	protected JTextField imie;
	protected JTextField nazwisko;
	protected JTextField nr_dowodu;
	protected JTextField adres;
	protected JTextField nr_telefonu;
	
	protected JPanel panel;
	protected JButton okButton;
	
	public JClientDialog(){
		
		init();
		buttonAcceptAction();
	}
	
	abstract protected void buttonAcceptAction();

	private void init() {
		panel = new JPanel();
		okButton = new JButton("OK");
		
		id = new JTextField(5);
		imie  = new JTextField(5);
		nazwisko = new JTextField(5);
		nr_dowodu = new JTextField(5);
		adres = new JTextField(5);
		nr_telefonu = new JTextField(5);
		panel.add(id);
		panel.add(imie);
		panel.add(nazwisko);
		panel.add(nr_dowodu);
		panel.add(adres);
		panel.add(nr_telefonu);
		
		panel.add(okButton);
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setModal(true);
	}

	public void start(){
		this.setVisible(true);
	}
	
	protected boolean validates(){
		if(!Validation.haveOnlyNumbers(id.getText()) || id.getText().equals("")) {
			System.out.println("podany Id jest nieprafidlowy");
			id.setBackground(Color.RED);
			return false;
		}
		if(!Validation.haveOnlyNumbers(nr_telefonu.getText()) || nr_telefonu.getText().equals("")) {
			System.out.println("podany numer jest nieprafidlowy");
			nr_telefonu.setBackground(Color.RED);
			return false;
		}
		if(imie.getText().equals("")){
			System.out.println("podaj imie");
			imie.setBackground(Color.RED);
			return false;
		}
		if(nazwisko.getText().equals("")) {
			System.out.println("podaj nazwisko");
			nazwisko.setBackground(Color.RED);
			return false;
		}
		if(nr_dowodu.getText().equals("")) {
			System.out.println("podaj nr_dowodu");
			nr_dowodu.setBackground(Color.RED);
			return false;
		}
		if(adres.getText().equals("")) {
			System.out.println("podaj adres");
			adres.setBackground(Color.RED);
			return false;
		}
		return true;
	}
}
