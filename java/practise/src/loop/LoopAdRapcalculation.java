package loop;
import javax.swing.JOptionPane;
public class LoopAdRapcalculation {
	public static void main(String[]args){
		int count=1;
		JOptionPane.showMessageDialog(null,"Welcome to\nRAPID CALCULATION COMPETITION(plus)\npress ok when you are ready");
		double starttime=System.currentTimeMillis();
		int c=0;
		String s="haha";
		while (count<=10){
			int a=(int)(100*Math.random());
			int b=(int)(100*Math.random());
			s=JOptionPane.showInputDialog(a+"+"+b+"= ?\n"+(10-count)+" formula(s) left");
			c=Integer.parseInt(s);
			if (c==a+b)
				count++;
		}//while
		double overtime=System.currentTimeMillis();
		JOptionPane.showMessageDialog(null, "CONGRATULATIONS!\nyou used "+(overtime-starttime)/1000.0+"seconds to solve the formulas");
	}
}
