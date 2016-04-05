package aTestMaterials;
import javax.swing.*;

import java.io.*;
public class RanLong{
	public static void main(String[]args)throws Exception{
		int num=100000000;
		JFileChooser jfc=new JFileChooser();
		if(jfc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
			File f=jfc.getSelectedFile();
			PrintWriter pw=new PrintWriter(f);
			for(int i=0;i<num;i++){
				
				//pw.println((long)(Math.random()*Long.MAX_VALUE));
				pw.println((short)(Math.random()*Short.MAX_VALUE));
				if(i%(num/100)==0)
					System.out.println("go! "+((int)(i/(num/100)))+"%");
			}
			pw.close();
			System.out.println("BOOOM!!!!!");
		}
		else System.out.println("dont");
	}
}
