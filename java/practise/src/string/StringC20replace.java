package string;
import java.util.Scanner;
import javax.swing.*;
import java.io.*;
public class StringC20replace {
	public static void main(String[]args) throws Exception{
		JFileChooser f=new JFileChooser();
		if(f.showOpenDialog(null)==f.APPROVE_OPTION){
			File f2=new File("d:/2222.txt");
			String s1=JOptionPane.showInputDialog("orginal");
			String s2=JOptionPane.showInputDialog("replace");
			File f1=f.getSelectedFile();
			Scanner in=new Scanner(f1);
			String s=new String();
			if(!f2.exists()){
				f2.delete();
			}
			PrintWriter out=new PrintWriter(f2);
			while(in.hasNextLine()){
			s=in.nextLine();
			String sm=s.replaceAll(s1,s2);
			out.println(sm);
			}
			in.close();
			out.close();
		}
	}
}
