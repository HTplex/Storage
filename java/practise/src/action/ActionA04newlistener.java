package action;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class ActionA04newlistener {
	public static void main(String[]args){
		JFrame f=new JFrame();
		f.add(new pad());
		f.setSize(400, 400);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(1);
		f.setVisible(true);
	}
}
class pad extends JPanel{
	 private JButton hehe=new JButton("hehe");
	 private JButton haha=new JButton("haha");
	 private JButton lala=new JButton("lala");
	 private JButton yaya=new JButton("yaya");
	 
	 pad(){
		 hehe.addActionListener(new yy());
		 haha.addActionListener(new yy());
		 lala.addActionListener(new yy());
		 yaya.addActionListener(new yy());
		 this.setLayout(new GridLayout(2,2));
		 this.add(hehe);
		 this.add(haha);
		 this.add(lala);
		 this.add(yaya);
	 }
	 
	 class yy implements ActionListener{
		 public void actionPerformed(ActionEvent e){
			 if(e.getSource()==hehe)
				 System.out.println("hehe");
			 else if(e.getSource()==haha)
				 System.out.println("haha");
			 else if(e.getSource()==lala)
				 System.out.println("lala");
			 else if(e.getSource()==yaya)
				 System.out.println("yaya");
		 }
	 }
	 
}