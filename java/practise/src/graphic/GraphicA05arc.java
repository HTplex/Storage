package graphic;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;
public class GraphicA05arc {
	public static void main(String[]args){
		JFrame a=new JFrame();
		a.add(new arc());
		a.setDefaultCloseOperation(1);
		a.setSize(200,200);
		a.setLocationRelativeTo(null);
		a.setVisible(true);
	}
}
class arc extends JPanel{
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		int w=(int)(0.1*this.getWidth());
		int h=(int)(0.1*this.getHeight());
		int rw=(int)(this.getWidth()*0.8);
		int rh=(int)(this.getHeight()*0.8);
		for(int i=0;i<180;i++){
			g.fillArc(w,h, rw,rh,2*i,1);	//the last is delta
		}
	}
}