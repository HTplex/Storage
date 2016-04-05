package aTestMaterials;
import java.io.*;
import javax.swing.*;

public class RanBoolean {
	public static void main(String[]args)throws Exception{
		int num=10000;
		int range=1;
		JFileChooser fc=new JFileChooser();
		if(fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
			File f=fc.getSelectedFile();
			PrintWriter p=new PrintWriter(f);
			long t=System.nanoTime();
			for(int i=0;i<num;i++){
				p.print((int)(Math.random()*range));
				if((i+1)%(num/100)==0)
					System.out.println((i+1)/(num/100)+"%");
			}
			System.out.println(System.nanoTime()-t);
			p.close();
		}
		else System.out.println("what the hell?");
	}
}
