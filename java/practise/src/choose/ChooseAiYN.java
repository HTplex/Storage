package choose;
import javax.swing.JOptionPane;
public class ChooseAiYN {
	public static void main(String[]args){
		int answer=JOptionPane.showConfirmDialog(null, "are you a idiot?");
		JOptionPane.showMessageDialog(null, answer);
	}
}
