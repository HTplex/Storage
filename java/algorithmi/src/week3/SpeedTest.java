package week3;
import java.io.*;
import java.util.Scanner;
import javax.swing.JFileChooser;
public class SpeedTest {
	public static void main(String[]args)throws Exception{
		int m=10000;
		Integer[] i=new Integer[m];
		int n=0;
		
		JFileChooser cho=new JFileChooser();
		if(cho.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
			File in=cho.getSelectedFile();
			Scanner input=new Scanner(in);
			while(input.hasNext()){
				i[n]=Integer.parseInt(input.nextLine());
				n++;
			}
			System.out.println(i[0]);
			System.out.println(n);
			
			//DBInsertionSort<Integer> d=new DBInsertionSort<Integer>();
			DCShellSort<Integer> d=new DCShellSort<Integer>();
			
			long t=System.currentTimeMillis();
			
			d.ShellSort(i);
			
			System.out.println(System.currentTimeMillis()-t);
			input.close();
			
			PrintWriter p=new PrintWriter(in);
			for(int o=0;o<m;o++){
				p.println(i[o]);
			}
			p.close();
		}
		else System.out.println("where is your file jerk?");
		
			
		

	}
}
