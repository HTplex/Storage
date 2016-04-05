package guibasic;
import javax.swing.*;
public class GUIbasicA02components {
	public static void main(String[]args){
		JFrame f1=new JFrame("frame with components");
		f1.setSize(640,480);
		f1.setLocationRelativeTo(null);
		f1.setVisible(true);
		f1.setDefaultCloseOperation(1);
		JButton bb=new JButton("click~");
		f1.add(bb);
	}
}
