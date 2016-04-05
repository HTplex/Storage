package practise;
import javax.swing.JOptionPane;
public class Showtimeinput {
	public static void main(String[]args){
		String GMTinput=JOptionPane.showInputDialog("Enter the time zone offset ot GMT");
		Integer GMT=Integer.parseInt(GMTinput);
		long tms=System.currentTimeMillis();
		long rms=tms%1000;
		long rs=(tms/1000)%60;
		long rm=(tms/(1000*60))%60;
		long rh=(tms/(1000*60*60))%24+GMT;
		JOptionPane.showMessageDialog(null,"the current time for GMT"+GMT+" time zone is\n       "
		+rh+":"+rm+":"+rs+"."+rms+""
				,"Show Current TIME",JOptionPane.DEFAULT_OPTION);
	}
}
