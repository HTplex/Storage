package object;
import javax.swing.JFrame;
public class ObjectA07GUIstart {
	public static void main(String[]args){
		JFrame helloworld=new JFrame();
		helloworld.setTitle("HELLOWORLD");
		helloworld.setSize(480,320);
		helloworld.setLocation(300,300);
		helloworld.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		helloworld.setVisible(true);
	}
}
