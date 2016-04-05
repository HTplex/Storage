package practise;
import javax.swing.JOptionPane;
public class TwopriticeO {
	public static void main(String[]args){
		String kilogrammes=JOptionPane.showInputDialog("enter the amount of water in kilogrammes");
		String temputer=JOptionPane.showInputDialog("Enter the initial temperature");
		String fintemputer=JOptionPane.showInputDialog("Enter the final temperature");
		double kg=Double.parseDouble(kilogrammes);
		double temp=Double.parseDouble(temputer);
		double fintem=Double.parseDouble(fintemputer);
		double energy=kg*(fintem-temp)*4184;
		JOptionPane.showMessageDialog(null, "The energy needed is "+energy);
		
	}
}
