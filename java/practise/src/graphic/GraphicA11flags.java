package graphic;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;
public class GraphicA11flags {
	public static void main(String[]args){
		JFrame f=new JFrame();
		ImageIcon a=new ImageIcon("D:/works/Javaworkspace/book/image/ca.gif");
		ImageIcon b=new ImageIcon("D:/works/Javaworkspace/book/image/china.gif");
		ImageIcon c=new ImageIcon("D:/works/Javaworkspace/book/image/flag4.gif");
		ImageIcon d=new ImageIcon("D:/works/Javaworkspace/book/image/flag5.gif");
		ImageIcon e=new ImageIcon("D:/works/Javaworkspace/book/image/flag6.gif");
		ImageIcon r=new ImageIcon("D:/works/Javaworkspace/book/image/flag3.gif");
		
		imag aa=new imag(a.getImage());
		imag bb=new imag(b.getImage());
		imag cc=new imag(c.getImage());
		imag dd=new imag(d.getImage());
		imag ee=new imag(e.getImage());
		imag rr=new imag(r.getImage());
		
		
		f.setLayout(new GridLayout(3,2,5,5));
		imag s=new imag();
		f.setSize(600,400);
		f.setVisible(true);
		f.setDefaultCloseOperation(1);
		f.setLocationRelativeTo(null);
		f.add(aa);
		f.add(bb);
		f.add(cc);
		f.add(dd);
		f.add(ee);
		f.add(rr);
		
	}
}
class imag extends JPanel{
	private Image ima;
	private boolean sk=true;
	private int xx;
	private int yy;
	public imag(){
		
	}
	public imag(Image ima){
		this.ima=ima;
	}
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		if(this.ima!=null){
			g.drawImage(this.ima, 0, 0,this.getWidth(),this.getHeight(),this);
		}
		else {
			g.drawLine(0, 0, this.getWidth(), this.getHeight());
			g.drawLine(0, this.getHeight(), this.getWidth(),0);
		}
		
		
	}
}
	