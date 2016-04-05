package guibasic;
import javax.swing.*;
import java.awt.*;
public class GUIbasicA08icon {
	public static void main(String[]args){
		nokiaicon n=new nokiaicon();
		n.setSize(320,480);
		n.setDefaultCloseOperation(1);
		n.setVisible(true);
		n.setTitle("NOKIA");
		n.setLocationRelativeTo(null);
	}
}
class nokiaicon extends JFrame{
	private ImageIcon i=new ImageIcon("D:/MEDIA/Pictures/gif/3.gif");
	private JPanel nok =new noki();
	nokiaicon(){
		this.setLayout(new GridLayout(2,1));
		this.add(new JLabel(i));
		this.add(nok);
	}
}