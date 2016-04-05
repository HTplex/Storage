package guibasic;
import javax.swing.JFrame;
public class GUIbasicA01JFrame {
	public static void main(String[]args){
		JFrame f1=new JFrame("HelloGUI");
		//f1.setLocationRelativeTo(null);left-up location if not defined size
		f1.setSize(480, 320);
		//f1.setLocation(480,320);
		f1.setVisible(true);
		f1.setDefaultCloseOperation(1);
		f1.setLocationRelativeTo(null);
	}
}
