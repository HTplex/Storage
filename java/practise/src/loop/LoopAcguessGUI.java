package loop;
import javax.swing.JOptionPane;
public class LoopAcguessGUI {
	public static void main(String[]args){
	int com=(int)(10000*Math.random());
	int count=0;
	String us=JOptionPane.showInputDialog("Please guess a number from 0 to 9999 \n(You have 13 times in all)");
	int user=Integer.parseInt(us);
	while (user!=com){
		if (user>com){
			us=JOptionPane.showInputDialog("your answer is to high, guess again \n(you have "+(12-count)+" times guess left)");
			user=Integer.parseInt(us);
			count++;
		}
			else {
				us=JOptionPane.showInputDialog("your answer is to low, guess again \n(you have "+(12-count)+" times guess left)");
				user=Integer.parseInt(us);
			count++;
			}
	if ((10-count)<=0){
		JOptionPane.showMessageDialog(null,"Sorry,you guess too much times, you lost!");
		break;
	}
	else if (user==com)
		JOptionPane.showMessageDialog(null,"CONGRATULATIONS! you are right, you had guessed "+count+" times");
	}

	}
}