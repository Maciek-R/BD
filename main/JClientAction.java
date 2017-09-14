package main;

import dialogs.client_dialogs.JClientDialog;
import dialogs.client_dialogs.JClientDialogAdd;
import dialogs.client_dialogs.JClientDialogDelete;
import dialogs.client_dialogs.JClientDialogUpdate;

public class JClientAction extends JAction{
	
	@Override
	protected void buttonAcceptAction() {
		okButton.addActionListener(al -> {
			if(types.getSelectedIndex() == ACTION_ADD){
				JClientDialog clientDialog = new JClientDialogAdd();
				clientDialog.start();
			}
			
			else if(types.getSelectedIndex() == ACTION_DELETE){
				JClientDialog clientDialog = new JClientDialogDelete();
				clientDialog.start();
			}
			
			else if(types.getSelectedIndex() == ACTION_UPDATE){
				JClientDialog clientDialog = new JClientDialogUpdate();
				clientDialog.start();
			}
		});	
	}
}
