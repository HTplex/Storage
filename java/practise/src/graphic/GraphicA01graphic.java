package graphic;
import java.util.Scanner;
import java.awt.Graphics;
import javax.swing.*;

public class GraphicA01graphic {
	public static void main(String[]args){
		all a=new all();
		a.setSize(200,100);
		a.setDefaultCloseOperation(1);
		a.setLocationRelativeTo(null);
		a.setVisible(true);
	}
}
class tri extends JPanel{
	protected void paintComponent(Graphics g){
		super.paintComponents(g);
		g.draw3DRect(0, 0, 50, 50, true);
	}

}
 
class all extends JFrame{
	public all(){
		this.add(new tri());
	}
}