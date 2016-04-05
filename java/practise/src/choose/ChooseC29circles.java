package choose;
import javax.swing.JOptionPane;
public class ChooseC29circles {
	public static void main(String[]args){
		String xcent1=JOptionPane.showInputDialog("please input the center x of (x,y)");
		String ycent1=JOptionPane.showInputDialog("please input the center y of (x,y)");
		String r1=JOptionPane.showInputDialog("please input the r of circle 1");
		String xcent2=JOptionPane.showInputDialog("please input the center x of (x,y) of circle 2");
		String ycent2=JOptionPane.showInputDialog("please input the center y of (x,y) of circle 2");
		String r2=JOptionPane.showInputDialog("please input the r of circle 2");
		Double xcenter1=Double.parseDouble(xcent1);
		Double ycenter1=Double.parseDouble(ycent1);
		Double rr1=Double.parseDouble(r1);
		Double xcenter2=Double.parseDouble(xcent2);
		Double ycenter2=Double.parseDouble(ycent2);
		Double rr2=Double.parseDouble(r2);
		Double d=Math.pow((xcenter1-xcenter2)*(xcenter1-xcenter2)+(ycenter1-ycenter2)*(ycenter1-ycenter2),0.5);
		if (rr2-rr1>0&&d==0)
			JOptionPane.showMessageDialog(null, "circle 1 and circle 2 have the same center and circle 1 is in circle 2");
		if (rr1-rr2>0&&d==0)
			JOptionPane.showMessageDialog(null, "circle 1 and circle 2 have the same center and circle 2 is in circle 1");
		else if (d>(rr1+rr2)) 
			JOptionPane.showMessageDialog(null, "circle 1 and circle 2 is separated");
		if (d==(rr1+rr2)) 
			JOptionPane.showMessageDialog(null, "circle 1 and circle 2 is overlaps");
		if (d<(rr1+rr2)&&d>(Math.pow((rr2-rr1)*(rr2-rr1), 0.5))) 
			JOptionPane.showMessageDialog(null, "circle 1 and circle 2 is touching");
		if (rr1-rr2>0&&d==(rr1-rr2)) 
			JOptionPane.showMessageDialog(null, "circle 1 and circle 2 is internal and circel 1 is outside");
		if (rr2-rr1>0&&d==(rr2-rr1)) 
			JOptionPane.showMessageDialog(null, "circle 1 and circle 2 is internal and circel 2 is outside");
		if (rr2-rr1>0&&d<(rr2-rr1)) 
			JOptionPane.showMessageDialog(null, " circle 1 is in circle 2");
		if (rr1-rr2>0&&d<(rr1-rr2)) 
			JOptionPane.showMessageDialog(null, "circle 2 is in circle 1");
		if (d==0)
			JOptionPane.showMessageDialog(null, "circle 1 and circle 2 is exa");
	}
}
