package graphic;
import java.util.Calendar;
import java.util.Scanner;
import java.awt.*;

import javax.swing.*;
public class GraphicA10StillClock {
	public static void main(String[]args){
		JFrame f=new JFrame();
		f.setDefaultCloseOperation(1);
		f.setSize(400,400);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.add(new sclock());
	}
}
class sclock extends JPanel{
	private int h;
	private int m;
	private int s;

	sclock(){
		this((int)((System.currentTimeMillis()/(1000*60*60)%24)+8),(int)((System.currentTimeMillis()/(1000*60))%60),(int)((System.currentTimeMillis()/(1000))%60)); 
	}
	sclock(int h, int m, int s){
		this.h=h;
		this.m=m;
		this.s=s;
	}
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		int w=this.getWidth();
		int h=this.getHeight();
		FontMetrics fm=g.getFontMetrics();
		StringBuilder sb=new StringBuilder();
		sb.append(this.h);
		sb.append(":");
		sb.append(this.m);
		sb.append(":");
		sb.append(this.s);
		String s=new String();
		s=sb.toString();
		int q=fm.stringWidth(s);
		int qq=fm.getAscent();
		g.drawOval((int)(0.1*w),(int)(0.1*h),(int)(0.8*w),(int)(0.8*h));
		g.drawString(s,(int)((w-q)/2),(int)(0.95*h+qq/2));
		g.setColor(Color.RED);
		g.drawLine((int)(w/2),(int)(h/2),(int)(w/2+(w*0.3)*Math.cos(-Math.PI/2+(Math.PI/30)*(this.s))),(int)(h/2+(h*0.3)*Math.sin(-Math.PI/2+(Math.PI/30)*(this.s))));
		g.setColor(Color.BLUE);
		g.drawLine((int)(w/2),(int)(h/2),(int)(w/2+(w*0.25)*Math.cos(-Math.PI/2+(Math.PI/30)*(this.m))),(int)(h/2+(h*0.25)*Math.sin(-Math.PI/2+(Math.PI/30)*(this.m))));
		g.setColor(Color.BLACK);
		g.drawLine((int)(w/2),(int)(h/2),(int)(w/2+(w*0.1)*Math.cos(-Math.PI/2+(Math.PI/6)*(this.h))),(int)(h/2+(h*0.1)*Math.sin(-Math.PI/2+(Math.PI/6)*(this.h))));
		
	}
}