package graphic;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;

public class GraphicA09eeeeeeeee {
	public static void main(String[]args){
		JFrame a=new JFrame();
		a.setSize(300,300);
		a.setDefaultCloseOperation(1);
		a.setLocationRelativeTo(null);
		a.setVisible(true);
		a.add(new move());
	}
}
class move extends JPanel{
	private int xCoordiate;
	private int yCoordiate;
	private boolean centered;
	private String message;
	private int interval;
	
	int w=this.getWidth();
	int h=this.getHeight();
	
	protected void paintComponent(Graphics g){
		super.paintComponents(g);
		FontMetrics fm=g.getFontMetrics();
		this.xCoordiate=(int)((this.getWidth()-fm.stringWidth(this.message))/2);
		this.yCoordiate=(int)((this.getHeight()-fm.getAscent())/2);
		g.drawString(this.message, this.xCoordiate,this.yCoordiate);
	}
	public void moveright(){
		this.xCoordiate++;
		this.repaint();
	}
	public void moveleft(){
		this.xCoordiate--;
		this.repaint();
	}
	public void moveup(){
		this.yCoordiate++;
		this.repaint();
	}
	public void movedown(){
		this.yCoordiate--;
		this.repaint();
	}
	public move(){
		this("null");
	}

	public move(String a){
		this.message=a;
		JButton w=new JButton("up");
		up ww=new up();
		down ss=new down();
		left aaa=new left();
		right dd=new right();
		w.addActionListener(ww);
		JButton s=new JButton("down");
		s.addActionListener(ss);
		JButton aa=new JButton("left");
		aa.addActionListener(aaa);
		JButton d=new JButton("right");
		d.addActionListener(dd);
		this.setLayout(new BorderLayout());
		this.add(w,BorderLayout.NORTH);
		this.add(s,BorderLayout.SOUTH);
		this.add(aa,BorderLayout.WEST);
		this.add(d,BorderLayout.EAST);
		
	}
}
class up extends move implements java.awt.event.ActionListener{
	public void actionPerformed(java.awt.event.ActionEvent g) {
		this.moveup();
	}
}
class down extends move implements java.awt.event.ActionListener{
	public void actionPerformed(java.awt.event.ActionEvent g){
		this.movedown();
	}
}
class left extends move implements java.awt.event.ActionListener{
	public void actionPerformed(java.awt.event.ActionEvent g){
		this.moveleft();
	}
}
class right extends move implements java.awt.event.ActionListener{
	public void actionPerformed(java.awt.event.ActionEvent g){
		this.moveright();
	}
}