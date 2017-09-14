package dialogs.hire_dialogs;

import tables.hire.HireDAO;

public class JHireDialogAdd extends JHireDialog{

	@Override
	protected void buttonAcceptAction() {
		okButton.addActionListener(al->{
			if(validates()) {
				HireDAO.insert(Nr_Rejestracyjny.getText(), Integer.parseInt(id_klienta.getText()), new java.sql.Date(Integer.parseInt(start.getText())), new java.sql.Date(Integer.parseInt(koniec.getText())), Float.parseFloat(kosztWyp.getText()), Integer.parseInt(status.getText()));
			}
		});
	}
}
