package guibasic;
import javax.swing.*;
import java.awt.*;
public class GUIbasicA03GridLayout {
	public static void main(String[]args){
		info in=new info();
		in.setSize(320,480);
		in.setDefaultCloseOperation(1);
		in.setLocationRelativeTo(null);
		in.setTitle("info");
		in.setVisible(true);
	}
}
class info extends JFrame{
	info(){
		this.setLayout(new GridLayout(5,2,0,0));
		add(new JLabel("Galaxy: "));
		add(new JTextField(12));
		add(new JLabel("System: "));
		add(new JTextField(12));
		add(new JLabel("Planet: "));
		add(new JTextField(12));
		add(new JLabel("Name: "));
		add(new JTextField(12));
		add(new JLabel("code: "));
		add(new JTextField(12));
	}
}