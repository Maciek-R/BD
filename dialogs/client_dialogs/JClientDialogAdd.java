package dialogs.client_dialogs;

import java.awt.Color;

import tables.client.ClientDAO;

public class JClientDialogAdd extends JClientDialog{

	@Override
	protected void buttonAcceptAction() {
		okButton.addActionListener(al->{

			if(validates()) {
				ClientDAO.insert(Integer.parseInt(id.getText()), imie.getText(), nazwisko.getText(), nr_dowodu.getText(), adres.getText(), Integer.parseInt(nr_telefonu.getText()));
			}
		});
	}
}
