package aTestMaterials;
import javax.swing.*;
import java.io.*;
public class RanString {
	public static void main(String[]args) throws Exception{
		//33-126,161-255
		final int length=32;
		final int number=10000000;
		
		JFileChooser jfc=new JFileChooser();
		if(jfc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
			File output=jfc.getSelectedFile();
			PrintWriter pw=new PrintWriter(output);
			for(int i=0;i<number;i++){
				pw.println(GenString(length));
				if(i%(number/100)==0)
					System.out.println("progress :"+(int)(i/(number/100))+"%");
			}
			System.out.println("COMPLETE!");
			pw.close();
		}
		else System.out.println("HEY!!!!!");
/*		for(int i=0;i<800000;i++){
			System.out.print(GenChar());
			if(i%80==79)
				System.out.println();
		}*/
		
	}
	public static char GenChar(){
		int a=(int)(Math.random()*189);
		if(a<=94) a+=33;
		if(a>94) a+=68;
		return (char)a;
	}
	public static String GenString(int a){
		StringBuilder sb=new StringBuilder("");
		for(int i=0;i<a;i++)
			sb.append(GenChar());
		return sb.toString();
	}
}
