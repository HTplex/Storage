package practise;
import  javax.swing.JOptionPane;
public class TwopriticeR {
	public static void main(String[]args){
	long x=System.currentTimeMillis();
	int y=(int)(x%128);
	char ch=(char)y;
	JOptionPane.showMessageDialog(null,ch);
	}
}
