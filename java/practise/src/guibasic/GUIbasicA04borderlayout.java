package guibasic;
import javax.swing.*;
import java.awt.*;
public class GUIbasicA04borderlayout {
	public static void main(String[]artgs){
		dir d=new dir();
		d.setSize(250,120);
		d.setDefaultCloseOperation(1);
		d.setLocationRelativeTo(null);
		d.setVisible(true);
	}
}
class dir extends JFrame{
	dir(){
		this.setLayout(new BorderLayout(1,1));
		this.add(new JButton("<<<<"),BorderLayout.WEST);
		this.add(new JButton(">>>>"),BorderLayout.EAST);
		this.add(new JButton("jump"),BorderLayout.NORTH);
		this.add(new JButton("couch"),BorderLayout.SOUTH);
		this.add(new JButton("attack"),BorderLayout.CENTER);
	}
}