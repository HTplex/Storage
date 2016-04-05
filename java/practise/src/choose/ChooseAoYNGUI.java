package choose;
import javax.swing.JOptionPane;
public class ChooseAoYNGUI {
	public static void main(String[]args){
		int i=0;
		int sum=0;
		int confirm=JOptionPane.YES_OPTION;
		while (confirm==JOptionPane.YES_OPTION){
			String s=JOptionPane.showInputDialog("please input the value");
			i=Integer.parseInt(s);
			sum+=i;
			confirm=JOptionPane.showConfirmDialog(null, "do you want to continue?");
		}
			JOptionPane.showMessageDialog(null, "the answer is "+sum);
	}
}
