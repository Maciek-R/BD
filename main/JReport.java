package main;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tables.car.CarDAO;
import tables.client.ClientDAO;

public class JReport extends JDialog{
	
	private JPanel panel;
	private JTextField idParking;
	private JComboBox types;
	JButton okButton;
	
	public JReport(){
		init();
	}
	private void init() {
		panel = new JPanel();
		idParking = new JTextField("     ");
		idParking.setBounds(0, 0, 100, 50);
		types = new JComboBox();
		types.addItem("Samochody na danym parkingu");
		types.addItem("Wypozyczone samochody");
		types.addItem("Wyswietlenie klientow");
		types.addItemListener(ie->{
			if(types.getSelectedIndex() == 0){
				idParking.setVisible(true);
				idParking.setText("     ");
			}
			else{
				idParking.setVisible(false);
				idParking.setText("");
			}
		});
		okButton = new JButton("OK");
		okButton.addActionListener(ae1->{
			if (types.getSelectedIndex() == 0) {
				if(!Validation.haveOnlyNumbers(idParking.getText().trim()) || idParking.getText().trim().equals("")) {
					System.out.println("podany ID_Parking jest nieprafidlowy");
					idParking.setBackground(Color.RED);
				} else {
					CarDAO.getCarsFromParking(Integer.parseInt(idParking.getText().trim()));
				}
			}
			if (types.getSelectedIndex() == 1)
				CarDAO.getFreeCars();
			if (types.getSelectedIndex() == 2)
				ClientDAO.findAll();
			
			this.setVisible(false);
		});
		
		panel.add(types);
		panel.add(idParking);
		panel.add(okButton);
		
		this.setTitle("Rodzaj raportu");
		this.setModal(true);
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
