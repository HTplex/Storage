package choose;
import javax.swing.JOptionPane;
public class ChooseC21weekday {
	public static void main(String[]args){
		String yearinput=JOptionPane.showInputDialog("Enter year :");
		String monthinput=JOptionPane.showInputDialog("Enter month :","(1-12)");
		String dayinput=JOptionPane.showInputDialog("Enter day of the month","(1-31)");
		int year=Integer.parseInt(yearinput);
		int m=Integer.parseInt(monthinput);
		int q=Integer.parseInt(dayinput);
		
		if (m==1||m==2) {
			m+=12;
			year--;
		}
		int j=(int)(year/100);
		int k=year%100;
		int h=(q+((26*(m+1))/10)+k+(k/4)+(j/4)+5*j)%7;
		String a="heihei";
		switch(h){
		case 0:a="Saturday";break;
		case 1:a="Sunday";break;
		case 2:a="Monday";break;
		case 3:a="Tuesday";break;
		case 4:a="Wednesday";break;
		case 5:a="Thuesday";break;
		case 6:a="Friday";break;
		default: a="lalalala";break;
		}
		JOptionPane.showMessageDialog(null,"Day of the week is "+a);
		
	}
}