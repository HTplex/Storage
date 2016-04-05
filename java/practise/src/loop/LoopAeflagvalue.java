package loop;
import javax.swing.JOptionPane;
public class LoopAeflagvalue {
	public static void main(String[]args){
		double m=1;
		double t=0;
		String b="";
		while (m!=0){
			String s=JOptionPane.showInputDialog("please input the number you want to add \n(input 0 to get the answer)");
			m=Double.parseDouble(s);
			t+=m;
			b+=(s+"+");
		}
		b+=0;
		JOptionPane.showMessageDialog(null, "the answer of \n"+b+"\n="+t);
	}
}
