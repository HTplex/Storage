package guibasic;
import java.awt.*;
import javax.swing.*;
public class GUIbasicB01chessboard {
	public static void main(String[]args){
		JFrame f=new JFrame();
		f.setLayout(new GridLayout(8,8,1,1));
		int aa=1;
		
		for(int i=0;i<72;i++){
			if(aa%8==1)
				i++;
			aa++;
			if(i%2==1){
				JButton a=new JButton();
				a.setBackground(Color.WHITE);
				f.add(a);
			}
			else{
				JButton a=new JButton();
				a.setBackground(Color.BLACK);
				f.add(a);
			}
		}
		f.setSize(320,320);
		f.setDefaultCloseOperation(1);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
	}
}
