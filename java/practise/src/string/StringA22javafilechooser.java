package string;
import java.util.Scanner;
import javax.swing.JFileChooser;
import java.io.*;
public class StringA22javafilechooser {
	public static void main(String[]args) throws Exception{
		JFileChooser c=new JFileChooser();
		if(c.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
			File f=c.getSelectedFile();
			Scanner input=new Scanner(f);
			while(input.hasNextLine()){
			String s=input.nextLine();
			System.out.println(s);
			}
		}
		else System.out.println("idiot");
	}
}
