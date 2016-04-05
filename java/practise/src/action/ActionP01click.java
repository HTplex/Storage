package action;
import java.awt.*;

import javax.swing.*;

import action.movingtexts.TMLS;

import java.awt.event.*;
public class ActionP01click {
	public static void main(String[]args){
	JFrame f=new JFrame();
	f.setLayout(new BorderLayout());
	game s=new game();
	f.add(s,BorderLayout.CENTER);
	f.setSize(400, 400);
	f.setLocationRelativeTo(null);
	f.setDefaultCloseOperation(1);
	f.setVisible(true);
	}
}
class game extends JPanel{
	private String s;
	private int r=10;
	private int x=(int)(Math.random()*(this.getWidth()));
	private int y=(int)(Math.random()*(this.getHeight()));
	private int n=0;
	private long t;
	private long t1;

	game(){
		this.setLayout(new BorderLayout());
		Timer tm=new Timer(3000000,new TML());
		tm.start();
		if(t1-t>6000){
			tm.stop();
		}
		this.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e){
				
				if(((e.getX()-x)*(e.getX()-x)+(e.getY()-y)*(e.getY()-y))<=r*r/4&&t1-t<=6000){
					x=(int)(Math.random()*getWidth());
					y=(int)(Math.random()*getHeight());
					n++;
					repaint();
				}
				if(n==1){
					t=System.currentTimeMillis();
				}
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			public void mouseExited(MouseEvent arg0) {
			}

			public void mousePressed(MouseEvent arg0) {
			}

			public void mouseReleased(MouseEvent arg0) {
			}
		});
	}
	class timee extends JPanel{
		timee(){
			Timer tm2=new Timer(1,new TMLL());
			if(n==1){
				tm2.start();
			}
		}
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawString((60-((t1-t)/1000.0)+""), this.getWidth()/2, this.getHeight()-20);
			if(t1-t>=6000){
				g.drawString("TIME IS UP!",this.getWidth()/2,0);
			}
		}
	}
	class TML implements ActionListener{
		public void actionPerformed(ActionEvent e){
			repaint();
		}
	}
	class TMLL implements ActionListener{
		public void actionPerformed(ActionEvent e){
			t1=System.currentTimeMillis();
		}
	}
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		x=(int)(Math.random()*getWidth());
		y=(int)(Math.random()*getHeight());
		g.drawOval(x-r/2, y-r/2, r, r);
		g.setFont(new Font("TimesRoman",Font.BOLD,20));
		g.drawString((n+""), this.getWidth()/2, this.getHeight()/2);
		
	}
}