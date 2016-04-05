package guibasic;
import java.awt.*;
import javax.swing.*;
import java.util.Scanner;
public class GUIbasicB02poker {
	public static void main(String[]args){
		poker a=new poker();
		a.setSize(236,140);
		a.setDefaultCloseOperation(1);
		a.setLocationRelativeTo(null);
		a.setVisible(true);
	}
}
class poker extends JFrame{
	StringBuilder sb1=new StringBuilder("D:/works/testfiles/book/image/card/");
	StringBuilder sb2=new StringBuilder("D:/works/testfiles/book/image/card/");
	StringBuilder sb3=new StringBuilder("D:/works/testfiles/book/image/card/");
	private int i1=(int)(Math.random()*54)+1;
	private int i2=(int)(Math.random()*54)+1;
	private int i3=(int)(Math.random()*54)+1;
	private ImageIcon a =new ImageIcon("");
	private ImageIcon b =new ImageIcon("");
	private ImageIcon c =new ImageIcon("");
	poker(){
		sb1.append(i1);
		sb2.append(i2);
		sb3.append(i3);
		sb1.append(".png");
		sb2.append(".png");
		sb3.append(".png");
		this.setLayout(new GridLayout(1,3,5,5));
		this.add(new JLabel(new ImageIcon(sb1.toString())));
		this.add(new JLabel(new ImageIcon(sb2.toString())));
		this.add(new JLabel(new ImageIcon(sb3.toString())));
	}
}