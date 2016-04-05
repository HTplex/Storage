import javax.swing.JOptionPane;
public class Input {
public static void main (String[]args){
	String string=JOptionPane.showInputDialog("lala","INPUT");
	double x=Double.parseDouble(string);
	int y=Integer.parseInt(string);
	JOptionPane.showMessageDialog(null,x+y,"hei",JOptionPane.DEFAULT_OPTION);
	}
}
