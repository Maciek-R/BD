package dialogs.hire_dialogs;

import java.awt.Color;

import tables.hire.HireDAO;

public class JHireDialogDelete extends JHireDialog{

	@Override
	protected void buttonAcceptAction() {
		okButton.addActionListener(al->{
			
			if(Nr_Rejestracyjny.getText().equals("")) {
				System.out.println("podaj rejestracje");
				Nr_Rejestracyjny.setBackground(Color.RED);
			} else {
				HireDAO.delete(Nr_Rejestracyjny.getText());
			}
		});
	}

}
