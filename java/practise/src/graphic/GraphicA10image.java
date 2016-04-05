package graphic;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
public class GraphicA10image {
	public static void main(String[]args){
		JFrame j=new JFrame();
		j.add(new ima());
		j.setDefaultCloseOperation(1);
		j.setSize(300,200);
		j.setLocationRelativeTo(null);
		j.setVisible(true);
	}
}
class ima extends JPanel{
	private ImageIcon iv=new ImageIcon("D:/works/Javaworkspace/book/image/china.gif");
	private Image i=iv.getImage();
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
	if(i==null){
			g.drawLine(0, 0, this.getWidth(), this.getHeight());
			g.drawLine(0,this.getHeight(),this.getWidth(),0);
		}
		else
		g.drawImage(i, 0, 0, this.getWidth(), this.getHeight(), this);

}
}