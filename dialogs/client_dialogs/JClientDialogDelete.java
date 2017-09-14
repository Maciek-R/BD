package dialogs.client_dialogs;

import java.awt.Color;

import main.Validation;
import tables.client.ClientDAO;

public class JClientDialogDelete extends JClientDialog{

	@Override
	protected void buttonAcceptAction() {
		okButton.addActionListener(al->{

			if(!Validation.haveOnlyNumbers(id.getText()) || id.getText().equals("")) {
				System.out.println("podany id_klient jest nieprafidlowy");
				id.setBackground(Color.RED);
			} else {
				System.out.println("dziala");
				ClientDAO.delete(Integer.parseInt(id.getText()));
			}
		});
	}

}
