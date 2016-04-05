package guibasic;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.Scanner;
public class GUIbasicA07swing {
	public static void main(String[]args){
		useless s=new useless();
		s.setSize(480, 320);
		s.setDefaultCloseOperation(1);
		s.setLocationRelativeTo(null);
		s.setVisible(true);
	}
}
class useless extends JFrame{
	useless(){
		this.setLayout(new GridLayout(2,1,5,5));
		this.add(new up());
		this.add(new down());
	}
}
class up extends JPanel{
	up(){
		this.setLayout(new FlowLayout(FlowLayout.TRAILING,5,5));
		JButton a=new JButton("left");
		a.setToolTipText("this is red");
		a.setBackground(Color.red);
		JButton b=new JButton("middle");
		b.setToolTipText("this is yellow");
		b.setBackground(Color.yellow);
		JButton c=new JButton("right");
		c.setToolTipText("this is blue");
		c.setBackground(Color.blue);
		this.add(a);
		this.add(b);
		this.add(c);
		this.setBorder(new TitledBorder("up"));
	}
}
class down extends JPanel{
	down(){
		this.setLayout(new GridLayout(1,2,5,5));
		Font f=new Font("System",Font.BOLD,20);
		JButton a=new JButton("one");
		a.setToolTipText("1");
		a.setBackground(Color.red);
		a.setForeground(Color.BLUE);
		a.setFont(f);
		JButton b=new JButton("two");
		b.setFont(f);
		b.setToolTipText("2");
		b.setBackground(Color.yellow);
		b.setForeground(Color.green);
		a.setBorder(new LineBorder(Color.cyan,3));
		b.setBorder(new LineBorder(Color.YELLOW,5));
		this.add(a);
		this.add(b);
		this.setBorder(new TitledBorder("down"));
	}
	
}