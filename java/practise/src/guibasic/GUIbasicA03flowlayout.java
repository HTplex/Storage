package guibasic;
import javax.swing.*;
import java.awt.*;
public class GUIbasicA03flowlayout {
	public static void main(String[]args){
		login l=new login();
		l.setTitle("info required");
		l.setSize(320,160);
		l.setLocationRelativeTo(null);
		l.setDefaultCloseOperation(1);
		l.setVisible(true);
	}
}
class login extends JFrame{
	login(){
		this.setLayout(new FlowLayout(FlowLayout.TRAILING,5,10));
		this.add(new JLabel("planet:\t"));
		this.add(new JTextField(12));
		this.add(new JLabel("code:\t"));
		this.add(new JTextField(12));
	}
}