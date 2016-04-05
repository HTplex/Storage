package practise;
import javax.swing.JOptionPane;
public class TwoexerciseT {
	public static void main(String[]args){
		String a=JOptionPane.showInputDialog("Please input the coordinate","x1");
		String b=JOptionPane.showInputDialog("Please input the coordinate","y1");
		String c=JOptionPane.showInputDialog("Please input the coordinate","x2");
		String d=JOptionPane.showInputDialog("Please input the coordinate","y2");
		String e=JOptionPane.showInputDialog("Please input the coordinate","x3");
		String f=JOptionPane.showInputDialog("Please input the coordinate","y3");
		Double aa=Double.parseDouble(a);
		Double ab=Double.parseDouble(b);
		Double ba=Double.parseDouble(c);
		Double bb=Double.parseDouble(d);
		Double ca=Double.parseDouble(e);
		Double cb=Double.parseDouble(f);
		Double A=Math.pow((ab-bb)*(ab-bb)+(aa-ba)*(aa-ba),0.5);
		Double B=Math.pow((cb-bb)*(cb-bb)+(ca-ba)*(ca-ba),0.5);
		Double C=Math.pow((ab-cb)*(ab-cb)+(aa-ca)*(aa-ca),0.5);
		Double s=(A+B+C)/2;
		Double area=Math.pow(s*(s-A)*(s-B)*(s-C), 0.5);
		JOptionPane.showMessageDialog(null,"The area of the triangle is "+area);
		
	}
}
