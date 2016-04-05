package graphic;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
public class GraphicA03oval {
	public static void main(String[]args){
		JFrame a=new JFrame();
		a.add(new draw());
		a.setDefaultCloseOperation(1);
		a.setSize(320,480);
		a.setLocationRelativeTo(null);
		a.setVisible(true);
	}
}
class draw extends JPanel{
	protected void paintComponent(Graphics g,boolean[][] map){
		super.paintComponent(g);

		g.drawRect(0,0,100,200);
		g.setColor(new java.awt.Color(3));
		g.fill3DRect(20, 20, 50, 50, false);
		
	}
}