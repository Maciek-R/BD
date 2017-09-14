package dialogs.car_dialogs;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import main.Validation;

public abstract class JCarDialog extends JDialog{

	protected JTextField Nr_Rejestracyjny;
	protected JTextField Przebieg;
	protected JTextField ID_Model;
	protected JTextField Rocznik;
	protected JTextField ID_Parking;
	
	protected JPanel panel;
	protected JButton okButton;
	
	public JCarDialog(){
		
		init();
		buttonAcceptAction();
	}
	
	abstract protected void buttonAcceptAction();

	private void init() {
		panel = new JPanel();
		okButton = new JButton("OK");
		
		Nr_Rejestracyjny = new JTextField(5);
		Przebieg  = new JTextField(5);
		ID_Model = new JTextField(5);
		Rocznik = new JTextField(5);
		ID_Parking = new JTextField(5);
		panel.add(Nr_Rejestracyjny);
		panel.add(Przebieg);
		panel.add(ID_Model);
		panel.add(Rocznik);
		panel.add(ID_Parking);
		
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

		if(Nr_Rejestracyjny.getText().equals("")) {
			System.out.println("podaj Nr_Rejestracyjny");
			Nr_Rejestracyjny.setBackground(Color.RED);
			return false;
		}
		if(!Validation.haveOnlyNumbers(Przebieg.getText()) || Przebieg.getText().equals("")) {
			System.out.println("podany Przebieg jest nieprafidlowy");
			Przebieg.setBackground(Color.RED);
			return false;
		}
		if(!Validation.haveOnlyNumbers(ID_Model.getText()) || ID_Model.getText().equals("")) {
			System.out.println("podany ID_Model jest nieprafidlowy");
			ID_Model.setBackground(Color.RED);
			return false;
		}
		if(!Validation.haveOnlyNumbers(Rocznik.getText()) || Rocznik.getText().equals("")) {
			System.out.println("podany Rocznik jest nieprafidlowy");
			Rocznik.setBackground(Color.RED);
			return false;
		}
		if(!Validation.haveOnlyNumbers(ID_Parking.getText()) || ID_Parking.getText().equals("")) {
			System.out.println("podany ID_Parking jest nieprafidlowy");
			ID_Parking.setBackground(Color.RED);
			return false;
		}
		return true;
	}
}
