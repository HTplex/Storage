package object;
import java.util.Scanner;
public class ObjectA02circlelein {
	public static void main(String[]args){
		ObjectA02circlelein a=new ObjectA02circlelein();
		System.out.println("r= "+a.r+"s="+a.area());
		a.r++;
		System.out.println("r= "+a.r+"s="+a.area());
	}
	double r;
		ObjectA02circlelein(){//must same name
			r=1.0;
		}
		ObjectA02circlelein(double ha){
			r=ha;
		}
		Double area(){
			return r*r*Math.PI;
		}	
}
