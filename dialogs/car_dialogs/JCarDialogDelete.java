package dialogs.car_dialogs;

import java.awt.Color;

import tables.car.CarDAO;

public class JCarDialogDelete extends JCarDialog{

	@Override
	protected void buttonAcceptAction() {

		okButton.addActionListener(al->{
			if(Nr_Rejestracyjny.getText().equals("")) {
				System.out.println("podaj rejestracje");
				Nr_Rejestracyjny.setBackground(Color.RED);
			} else {
				CarDAO.deleteCar(Nr_Rejestracyjny.getText());
			}
		});
	}

}
