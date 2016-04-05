package graphic;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GraphicB05arrow {
	public static void main(String[]args){
		JFrame f=new JFrame();
		f.add(new drawarrow());
		f.setSize(500,500);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(1);
		f.setVisible(true);
	}
}
class drawarrow extends JPanel{
	int x=(int)(Math.random()*this.getWidth());
	int y=(int)(Math.random()*this.getHeight());
	int x2=(int)(Math.random()*this.getWidth());
	int y2=(int)(Math.random()*this.getHeight());
	drawarrow(){
		this.x=(int)(Math.random()*this.getWidth());
		this.y=(int)(Math.random()*this.getHeight());
		this.x2=(int)(Math.random()*this.getWidth());
		this.y2=(int)(Math.random()*this.getHeight());
		JButton b=new JButton("draw again");
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				repaint();
			}
		});
		this.add(b);
	}
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawLine((int)(Math.random()*this.getWidth()),(int)(Math.random()*this.getHeight()),
				   (int)(Math.random()*this.getWidth()),(int)(Math.random()*this.getHeight()));
		g.drawOval(x2-2,y2-2,4,4);
	}
}

