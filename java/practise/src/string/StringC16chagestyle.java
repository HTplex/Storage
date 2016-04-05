package string;
import java.util.Scanner;
import javax.swing.JFileChooser;
import java.io.*;
public class StringC16chagestyle {
	public static void main(String[]args)throws Exception{
		JFileChooser a=new JFileChooser();
		if(a.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
			File c=a.getSelectedFile();
			Scanner input=new Scanner(c);
			StringBuilder ss=new StringBuilder();
			while(input.hasNextLine()){
				String s=input.nextLine();
				if(s.contains("{"))	{
				ss.append("{\r\n");
				}
				else
					ss.append("\r\n"+s);
			}
			input.close();
			PrintWriter h=new PrintWriter(c);
			h.print(ss.toString());
			h.close();
		}
	}
}
