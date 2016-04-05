package graphic;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;
public class GraphicA06polygon {
	public static void main(String[]args){
		JFrame a=new JFrame();
		a.add(new poly());
		a.setDefaultCloseOperation(1);
		a.setSize(400,400);
		a.setLocationRelativeTo(null);
		a.setVisible(true);
	}
}
class poly extends JPanel{
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		int w=this.getWidth();
		int h=this.getHeight();
		int[] x={(int)(0.1*w),(int)(0.9*w),(int)(0.25*w),(int)(0.5*w),(int)(0.75*w),(int)(0.1*w)};
		int[] y={(int)(0.3*h),(int)(0.3*h),(int)(0.7*h),(int)(0.1*h),(int)(0.7*h),(int)(0.3*h)};
		g.fillPolygon(x, y, 6);
	}
}