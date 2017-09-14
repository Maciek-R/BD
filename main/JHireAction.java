package main;

import dialogs.hire_dialogs.JHireDialog;
import dialogs.hire_dialogs.JHireDialogAdd;
import dialogs.hire_dialogs.JHireDialogDelete;
import dialogs.hire_dialogs.JHireDialogUpdate;

public class JHireAction extends JAction{
	
	@Override
	protected void buttonAcceptAction() {
		okButton.addActionListener(al -> {
			if(types.getSelectedIndex() == ACTION_ADD){
				JHireDialog hireDialog = new JHireDialogAdd();
				hireDialog.start();
			}
			
			else if(types.getSelectedIndex() == ACTION_DELETE){
				JHireDialog hireDialog = new JHireDialogDelete();
				hireDialog.start();
			}
			
			else if(types.getSelectedIndex() == ACTION_UPDATE){
				JHireDialog hireDialog = new JHireDialogUpdate();
				hireDialog.start();
			}
		});	
	}
}
