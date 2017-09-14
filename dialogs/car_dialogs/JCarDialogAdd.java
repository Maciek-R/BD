package dialogs.car_dialogs;

import tables.car.CarDAO;

public class JCarDialogAdd extends JCarDialog{

	@Override
	protected void buttonAcceptAction() {
		okButton.addActionListener(al->{
			if(validates()) {
				CarDAO.addCar(Nr_Rejestracyjny.getText(), Integer.parseInt(Przebieg.getText()), Integer.parseInt(ID_Model.getText()), Integer.parseInt(Rocznik.getText()), Integer.parseInt(ID_Parking.getText()));
			}
		});
	}
}
