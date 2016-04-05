package practise;
import javax.swing.JOptionPane;
public class Inputc {
	public static void main (String[]args){
		String string=JOptionPane.showInputDialog(null,"putin","putin",
													JOptionPane.CANCEL_OPTION);
		JOptionPane.showMessageDialog(null, string);
	}
}
