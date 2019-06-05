import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;

public class menu {

	protected Shell appShell;
	private Text amount;
	private Text length;
	private Text nickname;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			menu window = new menu();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		appShell.open();
		appShell.layout();
		while (!appShell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		appShell = new Shell();
		appShell.setMinimumSize(new Point(500, 265));
		appShell.setSize(500, 265);
		appShell.setText("Password Genarator Application v2.0");
		
		Label Nickname = new Label(appShell, SWT.NONE);
		Nickname.setBounds(10, 12, 55, 15);
		Nickname.setText("Nickname");
		
		nickname = new Text(appShell, SWT.BORDER);
		nickname.setBounds(71, 9, 150, 21);

		Label numberOfPasswords = new Label(appShell, SWT.NONE);
		numberOfPasswords.setBounds(10, 43, 155, 15);
		numberOfPasswords.setText("Number of Passwords (1-10)");

		amount = new Text(appShell, SWT.BORDER);
		amount.setBounds(171, 40, 30, 21);

		Label passwordLength = new Label(appShell, SWT.NONE);
		passwordLength.setBounds(252, 43, 125, 15);
		passwordLength.setText("Password Length (1-20)");

		length = new Text(appShell, SWT.BORDER);
		length.setBounds(389, 40, 30, 21);

		Label label = new Label(appShell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(5, 70, 475, 2);

		Label lblCharacters = new Label(appShell, SWT.NONE);
		lblCharacters.setBounds(10, 80, 60, 15);
		lblCharacters.setText("Characters");

		Button upperCaseButton = new Button(appShell, SWT.CHECK);
		upperCaseButton.setText("Upper Case (A-Z)");
		upperCaseButton.setBounds(25, 105, 110, 16);

		Button lowerCaseButton = new Button(appShell, SWT.CHECK);
		lowerCaseButton.setText("Lower Case (a-z)");
		lowerCaseButton.setBounds(25, 130, 110, 16);

		Button numberButton = new Button(appShell, SWT.CHECK);
		numberButton.setText("Numbers (0-9)");
		numberButton.setBounds(25, 155, 110, 16);
		
		Label label_1 = new Label(appShell, SWT.SEPARATOR | SWT.VERTICAL);
		label_1.setBounds(163, 105, 2, 64);
		
		Label lblSpecialCharacters = new Label(appShell, SWT.NONE);
		lblSpecialCharacters.setBounds(175, 80, 100, 15);
		lblSpecialCharacters.setText("Special Characters");

		Button specialCharButton = new Button(appShell, SWT.RADIO);
		specialCharButton.setBounds(190, 105, 290, 16);
		specialCharButton.setText("List A (!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~)");

		Button specialCharBButton = new Button(appShell, SWT.RADIO);
		specialCharBButton.setBounds(190, 130, 280, 16);
		specialCharBButton.setText("List B (!#$%'()+,-./:?@[\\]^_`{}~)");
		
		Button btnNone = new Button(appShell, SWT.RADIO);
		btnNone.setBounds(190, 155, 90, 16);
		btnNone.setText("None");

		Button generateButton = new Button(appShell, SWT.NONE);
		generateButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String name;
				int input, input2;
				
				try {
					name = nickname.getText();
					
					if (name.isEmpty()) {
						MessageDialog.openError(appShell, "Error", "Please enter a nickname");
					}
					
					
				} catch (Exception exc) {
					MessageDialog.openError(appShell, "Error", "ERROR");
					return;
				}

				try {
					input = Integer.parseInt(amount.getText());

					if (input <= 0) {
						MessageDialog.openError(appShell, "Error", "Number of Passwords must be greater than 1");
					}

					if (input > 10) {
						MessageDialog.openError(appShell, "Error", "The limit of Number of Passwords is 10");
					}

				} catch (Exception exc) {
					MessageDialog.openError(appShell, "Error", "Please enter an Integer for Number of Passwords");
					return;
				}

				try {
					input2 = Integer.parseInt(length.getText());

					if (input2 <= 0) {
						MessageDialog.openError(appShell, "Error", "Minimum length is 1");
					}

					if (input2 > 20) {
						MessageDialog.openError(appShell, "Error", "Maximum length is 20");
					}

				} catch (Exception exc) {
					MessageDialog.openError(appShell, "Error", "Please enter an Integer for Password Length");
					return;
				}
				
				boolean c = true;
				
				if (name.isEmpty() && !upperCaseButton.getSelection() && !lowerCaseButton.getSelection() && !numberButton.getSelection()
						&& !specialCharButton.getSelection() && !specialCharBButton.getSelection()) {
					c = false;
					MessageDialog.openError(appShell, "Error", "Please select at least one character option");
				}
				
				if(c) {
					@SuppressWarnings("unused")
					generate g = new generate(appShell, name, input, input2, upperCaseButton.getSelection(), lowerCaseButton.getSelection(), numberButton.getSelection(), specialCharButton.getSelection(), specialCharBButton.getSelection());
				}


			}
		});
		generateButton.setBounds(25, 191, 75, 25);
		generateButton.setText("Generate");

	}
}
