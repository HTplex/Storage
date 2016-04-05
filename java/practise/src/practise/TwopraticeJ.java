package practise;
import javax.swing.JOptionPane;
public class TwopraticeJ {
	public static void main(String[]args){
		String name=JOptionPane.showInputDialog("Enter employee's name: ");
		String time=JOptionPane.showInputDialog("Enter number of hours worked in a week: ");
		String pay =JOptionPane.showInputDialog("Enter hourly pay rate: ");
		String fedtax=JOptionPane.showInputDialog("Enter federal tax witholding rate: ");
		String statetax=JOptionPane.showInputDialog("Enter state tax withholding rate: ");
		double t=Double.parseDouble(time);
		double p=Double.parseDouble(pay);
		double f=Double.parseDouble(fedtax);
		double s=Double.parseDouble(statetax);
		double grosspay=((int)(t*p*100))/100.0;
		double ftax=((int)(grosspay*f*100))/100.0;
		double stax=((int)(grosspay*s))/100.0;
		double finalpay=grosspay-ftax-stax;
		JOptionPane.showMessageDialog(null,"Employee Name:  "+name+"\nHours Worked:  "+t+
				"\nPay Rate:  $"+fedtax+"\nGross Pay:  $"+grosspay+"\nDeductions:\n  "+
				"Federal Withholding ("+100.0*f+"%):  $"+ftax+"\n  State Withholding ("+
				100.0*s+"%):  $"+stax+"\n  Total Deduction:  $"+(ftax+stax)+"\nNet Pay:  $"+
				finalpay
				
				,"WAGE",JOptionPane.DEFAULT_OPTION);
	}
}
