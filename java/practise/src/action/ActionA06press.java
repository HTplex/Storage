package action;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;;
public class ActionA06press {
	public static void main(String[]args){
	JFrame a=new keylistener();
	a.setSize(400, 400);
	a.setLocationRelativeTo(null);
	a.setDefaultCloseOperation(1);
	a.setVisible(true);
	}
}
class keylistener extends JFrame{
	
	kpp kppp=new kpp();
	keylistener(){
		
		this.add(kppp);
		kppp.setFocusable(true);
	}
	
	static class kpp extends JPanel{
		String s="hei";
		int x=400;
		int y=400;
		kpp(){
		this.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				switch(e.getKeyCode()){
				case(KeyEvent.VK_TAB): s="TAB";break;
				case(KeyEvent.VK_SHIFT): s="SHIFT";break;
				case(KeyEvent.VK_CONTROL): s="CTRL";break;
				case(KeyEvent.VK_UP): y-=5;break;
				case(KeyEvent.VK_DOWN):y+=5;break;
				case(KeyEvent.VK_LEFT): x-=5;break;
				case(KeyEvent.VK_RIGHT): x+=5;break;
				default: s=(e.getKeyChar()+"");
				}
				repaint();
			}
		});
		}
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			g.setFont(new Font("TimesRoman",Font.BOLD,24));
			g.drawString(this.s, (int)(this.x/2), (int)(this.y/2));
		}
	}
	
}