package dialogs.client_dialogs;

import java.awt.Color;

import main.Validation;
import tables.client.ClientDAO;

public class JClientDialogUpdate extends JClientDialog{

	@Override
	protected void buttonAcceptAction() {

		okButton.addActionListener(al->{
			if (!Validation.haveOnlyNumbers(id.getText()) || id.getText().equals("")) {
				System.out.println("podany id_klient jest nieprafidlowy");
				id.setBackground(Color.RED);
			} else {
				if (ClientDAO.findById(Integer.parseInt(id.getText())) != null) {
						
						if(validates()) {
							ClientDAO.update(Integer.parseInt(id.getText()), imie.getText(), nazwisko.getText(), nr_dowodu.getText(), adres.getText(), Integer.parseInt(nr_telefonu.getText()));
						}
				}
			}
		});
	}

}
