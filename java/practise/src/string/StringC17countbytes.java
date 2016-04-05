package string;
import java.util.Scanner;
import javax.swing.JFileChooser;
import java.io.*;
public class StringC17countbytes {
	public static void main(String[]args)throws Exception{
		JFileChooser a=new JFileChooser();
		if(a.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
			File f=a.getSelectedFile();
			Scanner in=new Scanner(f);
			long line=0;
			long word=0;
			long character=0;
			while(in.hasNextLine()){
				String s=in.nextLine();
				line++;
				String[] s2=s.split(" ");
				word+=s2.length;
				for(int i=0;i<s2.length;i++){
					char[] c=s2[i].toCharArray();
					for(int i3=0;i3<c.length;i3++){
					Character cc=new Character(c[i3]);
					if(cc.isLetter(cc))
						character++;
				}
			}
			}
			in.close();
			System.out.println(line+"\n"+word+"\n"+character);
		}
	}
}
