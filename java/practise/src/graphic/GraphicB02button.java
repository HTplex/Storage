package graphic;
import javax.swing.*;
import java.util.*;
import java.awt.*;
public class GraphicB02button {
	public static void main(String[]args){
		JFrame a=new JFrame();
		a.setLayout(new GridLayout(2,1));
		a.add(new ovalbutton("ok"));
		a.add(new ovalbutton("nope"));
		a.setSize(320, 180);
		a.setLocationRelativeTo(null);
		a.setDefaultCloseOperation(1);
		a.setVisible(true);
		
	}
}
class ovalbutton extends JButton{
	public int x=this.getWidth();
	public int y=this.getHeight();
	public ovalbutton(String s){
		super(s);
	}
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawOval(0,0,getWidth(),getHeight());
	}
	
}
