package dialogs.hire_dialogs;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dialogs.car_dialogs.JCarDialog;
import main.Validation;
import tables.hire.HireDAO;


public abstract class JHireDialog extends JDialog{


	protected JTextField id_klienta;
	protected JTextField Nr_Rejestracyjny;
	protected JTextField start;
	protected JTextField koniec;
	protected JTextField kosztWyp;
	protected JTextField status;
	
	protected JPanel panel;
	protected JButton okButton;
	
	public JHireDialog(){
		
		init();
		buttonAcceptAction();
	}
	
	abstract protected void buttonAcceptAction();

	private void init() {
		panel = new JPanel();
		okButton = new JButton("OK");
		
		id_klienta = new JTextField(5);
		Nr_Rejestracyjny  = new JTextField(5);
		start = new JTextField(5);
		koniec = new JTextField(5);
		kosztWyp = new JTextField(5);
		status = new JTextField(5);
		panel.add(id_klienta);
		panel.add(Nr_Rejestracyjny);
		panel.add(start);
		panel.add(koniec);
		panel.add(kosztWyp);
		panel.add(status);
		
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
		if(id_klienta.getText().equals("")) {
			System.out.println("podaj id klienta");
			id_klienta.setBackground(Color.RED);
			return false;
		}
		if(!Validation.haveOnlyNumbers(Nr_Rejestracyjny.getText()) || Nr_Rejestracyjny.getText().equals("")) {
			System.out.println("podany nr rej jest nieprafidlowy");
			Nr_Rejestracyjny.setBackground(Color.RED);
			return false;
		}
		if(!Validation.haveOnlyNumbers(start.getText()) || start.getText().equals("")) {
			System.out.println("podany czas jest nieprafidlowy");
			start.setBackground(Color.RED);
			return false;
		}
		if(!Validation.haveOnlyNumbers(koniec.getText()) || koniec.getText().equals("")) {
			System.out.println("podany czas jest nieprafidlowy");
			koniec.setBackground(Color.RED);
			return false;
		}
		if(!Validation.haveOnlyNumbers(kosztWyp.getText()) || kosztWyp.getText().equals("")) {
			System.out.println("podany koszt jest nieprafidlowy");
			kosztWyp.setBackground(Color.RED);
			return false;
		}
		if(status.equals("")) {
			System.out.println("niepoprawny status");
			status.setBackground(Color.RED);
			return false;
		}
		return true;
	}
}
