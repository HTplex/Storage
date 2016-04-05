package graphic;
import java.awt.*;
import javax.swing.*;
public class GraphicB01line {
	public static void main(String[]args){
		JFrame f=new JFrame();
		f.add(new triline());
		f.setSize(300, 200);
		f.setDefaultCloseOperation(1);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
}
class triline extends JPanel{
	protected void paintComponent(Graphics s){
	super.paintComponent(s);
	int x=this.getWidth();
	int y=this.getHeight();
	s.setColor(Color.BLUE);
	s.drawLine(0,(int)(y/3),x,(int)(y/3));
	s.drawLine(0,(int)(2*y/3),x,(int)(2*y/3));
	s.drawLine((int)(x/3),0,(int)(x/3),y);
	s.drawLine((int)(2*x/3),0,(int)(2*x/3),y);
	}
}