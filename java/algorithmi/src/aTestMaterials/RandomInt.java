package aTestMaterials;
import java.io.*;
import javax.swing.*;

public class RandomInt {
	public static void main(String[]args)throws Exception{
		int num=100000;
		int range=Integer.MAX_VALUE;
		JFileChooser fc=new JFileChooser();
		if(fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
			File f=fc.getSelectedFile();
			PrintWriter p=new PrintWriter(f);
			long t=System.nanoTime();
			for(int i=0;i<num;i++){
				//p.println((int)(Math.random()*range));
				p.println(73541300);
				if((i+1)%(num/100)==0)
					System.out.println((i+1)/(num/100)+"%");
			}
			System.out.println(System.nanoTime()-t);
			p.close();
		}
		else System.out.println("what the hell?");
	}
}
