package guibasic;
import java.awt.*;
import javax.swing.*;
public class GUIbasicA05JPanel {
	public static void main(String[]args){
		nokia n=new nokia();
		n.setSize(320,640);
		n.setDefaultCloseOperation(1);
		n.setVisible(true);
		n.setTitle("NOKIA");
		n.setLocationRelativeTo(null);
	}
}
class key extends JPanel{
	key(){
	this.setLayout(new GridLayout(4,3));
	for(int i=1;i<10;i++){
		this.add(new JButton(""+i));
	}
	this.add(new JButton("*"));
	this.add(new JButton("0"));
	this.add(new JButton("#"));
	}
}
class center extends JPanel{
	center(){
		JPanel center=new JPanel();
		this.setLayout(new BorderLayout());
		this.add(new JButton("<"),BorderLayout.WEST);
		this.add(new JButton(">"),BorderLayout.EAST);
		this.add(new JButton("^"),BorderLayout.NORTH);
		this.add(new JButton("_"),BorderLayout.SOUTH);
		this.add(new JButton("@"));
	}
}
class noki extends JPanel{
	center c=new center();
	key k=new key();
	noki(){
	this.setLayout(new BorderLayout());
	this.add(k,BorderLayout.SOUTH);
	this.add(new JButton("call"),BorderLayout.WEST);
	this.add(new JButton("cancel"),BorderLayout.EAST);
	this.add(c);
	}
}
class nokia extends JFrame{
	nokia(){
		this.setLayout(new GridLayout(2,1));
		this.add(new JTextField());
		this.add(new noki());
	}
}