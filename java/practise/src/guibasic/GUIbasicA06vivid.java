package guibasic;
import java.awt.*;
import javax.swing.*;
public class GUIbasicA06vivid {
	public static void main(String[]args){
		nok2 n=new nok2();
		n.setSize(320,640);
		n.setDefaultCloseOperation(1);
		n.setVisible(true);
		n.setTitle("nok2");
		n.setLocationRelativeTo(null);
	}
}
class key2 extends JPanel{
	key2(){
	this.setLayout(new GridLayout(4,3));
	for(int i=1;i<10;i++){
		this.add(new JButton(""+i));
	}
	this.add(new JButton("*"));
	this.add(new JButton("0"));
	this.add(new JButton("#"));
	}
}
class center2 extends JPanel{
	center2(){
		JPanel center2=new JPanel();
		JButton a=new JButton("<");
		this.setLayout(new BorderLayout());
		this.add(a,BorderLayout.WEST);
		this.add(new JButton(">"),BorderLayout.EAST);
		this.add(new JButton("^"),BorderLayout.NORTH);
		this.add(new JButton("_"),BorderLayout.SOUTH);
		this.add(new JButton("@"));
		a.setForeground(Color.YELLOW);
		a.setBackground(Color.RED);
		Font f=new Font("Sanserif",Font.BOLD,12);
		a.setFont(f);
	}
}
class noki2 extends JPanel{
	center2 c=new center2();
	key2 k=new key2();
	noki2(){
	this.setLayout(new BorderLayout());
	this.add(k,BorderLayout.SOUTH);
	this.add(new JButton("call"),BorderLayout.WEST);
	this.add(new JButton("cancel"),BorderLayout.EAST);
	this.add(c);
	
	}
}
class nok2 extends JFrame{
	nok2(){
		this.setLayout(new GridLayout(2,1));
		this.add(new JTextField());
		this.add(new noki2());
	}
}
