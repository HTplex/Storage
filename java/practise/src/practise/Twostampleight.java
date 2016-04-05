package practise;
import javax.swing.JOptionPane;
public class Twostampleight {
	public static void main(String[]args){
		long tms=System.currentTimeMillis();
		long rms=tms%1000;
		long rs=(tms/1000)%60;
		long rm=(tms/(1000*60))%60;
		long rh=(tms/(1000*60*60))%24+8;
		JOptionPane.showMessageDialog(null,"the current time is "+rh+":"+rm+":"+rs+"."+rms+"  (GMT +8)"
				,"Show Current TIME",JOptionPane.DEFAULT_OPTION);
	}
}
