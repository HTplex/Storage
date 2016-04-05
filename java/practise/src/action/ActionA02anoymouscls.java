package action;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ActionA02anoymouscls {
	public static void main(String[]args){
		JFrame f=new JFrame();
		f.add(new panel());
		f.setSize(400, 400);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(1);
		f.setVisible(true);
	}
}
class panel extends JPanel{

	
	class board extends JPanel{
		String s=new String("Say something");
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawString(s, 0, (int)(this.getHeight()/2));
		}
		public void repaint(String s2){
			this.s+=s2;
			this.repaint();
		}
		public void clear(){
			this.s="";
			this.repaint();
		}
	}
	panel(){
		this.setLayout(new BorderLayout());
		this.add(b,BorderLayout.CENTER);
		this.add(new pane(),BorderLayout.SOUTH);
		JButton clr=new JButton("CLR");
		this.add(clr,BorderLayout.NORTH);
		clr.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				b.clear();
			}
		});
	}
	board b=new board();
	class pane extends JPanel{
		pane(){
		this.setLayout(new GridLayout(3,3));
			JButton a1=new JButton("1");
		a1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				b.repaint(1+"");
			}
		});
		this.add(a1);
		
		JButton a2=new JButton("2");
		a2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				b.repaint(2+"");
			}
		});
		this.add(a2);
		
		JButton a3=new JButton("3");
		a3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				b.repaint(3+"");
			}
		});
		this.add(a3);
		
		JButton a4=new JButton("4");
		a4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				b.repaint(4+"");
			}
		});
		this.add(a4);
		
		JButton a5=new JButton("5");
		a5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				b.repaint(5+"");
			}
		});
		this.add(a5);
		
		JButton a6=new JButton("6");
		a6.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				b.repaint(6+"");
			}
		});
		this.add(a6);
		
		JButton a7=new JButton("7");
		a7.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				b.repaint(7+"");
			}
		});
		this.add(a7);
		
		JButton a8=new JButton("8");
		a8.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				b.repaint(8+"");
			}
		});
		this.add(a8);
		
		JButton a9=new JButton("9");
		a9.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				b.repaint(9+"");
			}
		});
		this.add(a9);
		}
	}
}