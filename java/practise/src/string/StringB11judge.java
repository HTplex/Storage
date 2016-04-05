package string;
import java.util.Scanner;
public class StringB11judge {
	public static void main(String[]args){
	String s=new String(" egfqpe fbcp aefc%^$*(^ (*paefcpaef pUKCVP(eufbcfbcs%*& (iudgbv  cpsle KJD");
	char [] a=s.toCharArray();
	for(int i=0;i<a.length;i++){
		if(Character.isUpperCase(a[i]))
			System.out.print("E");
		if(Character.isLowerCase(a[i]))
			System.out.print("e");
		if(Character.isDigit(a[i]))
		System.out.print("7");
		if(!(Character.isLetterOrDigit(a[i])))
		System.out.print("@");
	}
	}
}
