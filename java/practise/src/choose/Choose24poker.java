package choose;
import javax.swing.JOptionPane;
public class Choose24poker {
	public static void main(String[]args){
		int c=(int)(4*Math.random());
		int n=(int)(13*Math.random());
		String color="piapiapia";
		String number="lalalallala";
		switch(c){
		case 1:color="Heart";break;
		case 2:color="Spades";break;
		case 3:color="Diamond";break;
		case 0:color="Clubs";break;
		default:color="hahahahaha";break;
		}
		switch(n){
		case 0: number="Ace";break;
		case 11:number="Jack";break;
		case 12:number="Queen";break;
		case 13:number="King";break;
		}
		if (n<11&&n>0)JOptionPane.showMessageDialog(null,"The card you picked is "+n+" of "+color);
		else
		JOptionPane.showMessageDialog(null,"The card you picked is "+number+" of "+color);
	}
}
