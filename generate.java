import java.security.SecureRandom;
import java.util.ArrayList;

import org.eclipse.swt.widgets.Shell;

public class generate {
	
	private static SecureRandom random = new SecureRandom();
	private static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMERIC = "0123456789";
    private static final String SPECIAL_CHARS = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
	private static final String SPECIAL_CHARS_B = "!#$%'()+,-./:?@[\\]^_`{}~";


	public generate(Shell appShell, String name, int amount, int length, boolean alphaCap, boolean alpha, boolean number, boolean spChar, boolean spCharB) {
		// TODO Auto-generated constructor stub
//		System.out.printf("input: %d, input2: %d, alphaCap: %s, alpha: %s, number: %s, spChar: %s, spCharB: %s", input, input2, alphaCap, alpha, number, spChar, spCharB);
		
		String s = "";
		
		if(alphaCap) {
			s += ALPHA_CAPS;
		}
		if(alpha) {
			s += ALPHA;
		}
		if(number) {
			s += NUMERIC;
		}
		if(spChar) {
			s += SPECIAL_CHARS;
		}
		if(spCharB) {
			s += SPECIAL_CHARS_B;
		}
		
		ArrayList<String> listOfPasswords = new ArrayList<String>();
		for (int i = 0; i < amount; i++) {
			String pass = "";			
			for (int j = 0; j < length; j++) {
				int index = random.nextInt(s.length());
				pass += s.charAt(index);
			}
			listOfPasswords.add(pass);
		}
		
//		String passwords = "";
//		
//		for (int x = 0; x < listOfPasswords.size(); x++) {
//			passwords += (listOfPasswords.get(x)+ "\n");
//		}
				
		gen ge = new gen(appShell);
		ge.open(name, listOfPasswords);
	}

}
