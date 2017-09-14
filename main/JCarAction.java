package main;

import dialogs.car_dialogs.JCarDialog;
import dialogs.car_dialogs.JCarDialogAdd;
import dialogs.car_dialogs.JCarDialogDelete;
import dialogs.car_dialogs.JCarDialogUpdate;

public class JCarAction extends JAction{
	
	@Override
	protected void buttonAcceptAction() {
		okButton.addActionListener(al -> {
			if(types.getSelectedIndex() == ACTION_ADD){
				JCarDialog carDialog = new JCarDialogAdd();
				carDialog.start();
			}
			
			else if(types.getSelectedIndex() == ACTION_DELETE){
				JCarDialog carDialog = new JCarDialogDelete();
				carDialog.start();
			}
			
			else if(types.getSelectedIndex() == ACTION_UPDATE){
				JCarDialog carDialog = new JCarDialogUpdate();
				carDialog.start();
			}
		});	
	}
}
