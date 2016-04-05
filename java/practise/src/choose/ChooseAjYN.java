package choose;
import javax.swing.JOptionPane;
public class ChooseAjYN {
	public static void main(String[]args){
		int a=JOptionPane.showConfirmDialog(null,"NO1");
		int b=JOptionPane.showConfirmDialog(null,"NO2");
		int c=JOptionPane.showConfirmDialog(null,"NO3");
		int d=JOptionPane.showConfirmDialog(null,"NO4");
		int e=JOptionPane.showConfirmDialog(null,"NO5");
		int f=JOptionPane.showConfirmDialog(null,"NO6");
		int g=JOptionPane.showConfirmDialog(null,"NO7");
		int h=JOptionPane.showConfirmDialog(null,"NO8");
		int i=JOptionPane.showConfirmDialog(null,"NO9");
		if (a==1&&b==2&&c==0&&d==0&&e==2&&f==1&&g==0&&h==2&&i==2){
			JOptionPane.showMessageDialog(null, "THE SECRET MESSAGE IS"+"\n[####]");}
		
		else JOptionPane.showMessageDialog(null, "YOU ARE NOT ALLOWED TO GET THE MESSAGE");
	}
}
