package practise;
import javax.swing.JOptionPane;
public class Inputa {
	public static void main (String[]args){
		String string=JOptionPane.showInputDialog(null,"input whatever you like",
												"MY FIRST INPUT DIAOG PANE",
												JOptionPane.QUESTION_MESSAGE);
		JOptionPane.showMessageDialog(null,"thank you!","haha",JOptionPane.DEFAULT_OPTION);
	}
}
