import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;

import java.io.*;
import java.util.ArrayList;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class gen extends Dialog {

	protected Object result;
	protected Shell shlPasswords;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public gen(Shell parent) {
		super(parent, SWT.DIALOG_TRIM | SWT.MIN);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @param name 
	 * @param listOfPasswords 
	 * 
	 * @return the result
	 */
	public Object open(String name, ArrayList<String> listOfPasswords) {
		createContents(name, listOfPasswords);
		shlPasswords.open();
		shlPasswords.layout();
		Display display = getParent().getDisplay();
		while (!shlPasswords.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */

	private void createContents(String name, ArrayList<String> listOfPasswords) {
		shlPasswords = new Shell(getParent());
		shlPasswords.setSize(258, 262);
		shlPasswords.setText("Passwords");
		
		List list = new List(shlPasswords, SWT.BORDER | SWT.SINGLE );
		
		for(int x = 0; x < listOfPasswords.size(); x++) {
			list.add(listOfPasswords.get(x), x);
		}
		
		list.setTouchEnabled(true);		
		list.setBounds(24, 21, 200, 154);
		
		Button btnOk = new Button(shlPasswords, SWT.CENTER);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int y = list.getSelectionIndex();
				
				String file_name = "passwords.txt";
			
				try {
					FileWriter writer = new FileWriter(file_name, true);
					PrintWriter print_line = new PrintWriter(writer);
					print_line.printf("nickname: %s  password: %s \n", name, listOfPasswords.get(y));
					print_line.close();
					
				} catch (Exception exc) {
					MessageDialog.openError(shlPasswords, "Error", "Sorry something went wrong");
				}
				
				shlPasswords.close();
			}
		});
		btnOk.setBounds(89, 198, 75, 25);
		btnOk.setText("OK");

	}
}
