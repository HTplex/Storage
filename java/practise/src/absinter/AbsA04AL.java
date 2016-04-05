package absinter;
import java.util.*;

import javax.swing.*;

import java.awt.*;
public class AbsA04AL {
	public static void main(String[]args){
		wbutton a=new wbutton();
		a.setDefaultCloseOperation(1);
		a.setSize(160,160);
		a.setLocationRelativeTo(null);
		a.setVisible(true);
	}
}
class wbutton extends JFrame{
	wbutton(){
		JButton ok=new JButton("OK");
		JButton nope=new JButton("NOPE");
		JPanel a=new JPanel();
		a.setLayout(new GridLayout(2,1));
		action okk=new action();
		ok.addActionListener(okk);
		action2 okkk=new action2();
		nope.addActionListener(okkk);
		a.add(ok);
		a.add(nope);
		this.add(a);
	}
	
}
class action implements java.awt.event.ActionListener{
	public void actionPerformed(java.awt.event.ActionEvent e){
		System.out.println("VOID 1");
	}
}
class action2 implements java.awt.event.ActionListener{
	public void actionPerformed(java.awt.event.ActionEvent e){
		System.out.println("VOID 2");
	}
}