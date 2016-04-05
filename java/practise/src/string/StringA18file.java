package string;
import java.util.Scanner;
public class StringA18file {
	public static void main(String[]args){
		java.io.File newfile=new java.io.File("D:/works/Javaworkspace/practise/files/itsanewworld.txt");
		java.io.File d=new java.io.File("d:");
		System.out.println(newfile.exists());
		System.out.println(newfile.canRead());
		System.out.println(newfile.canWrite());
		System.out.println(d.isFile());
		System.out.println(d.isDirectory());
		System.out.println(newfile.isDirectory());
		System.out.println(newfile.isAbsolute());
		System.out.println(newfile.isHidden());
		System.out.println(newfile.getAbsolutePath());
		System.out.println(newfile.getName());
		System.out.println(newfile.getPath());
		System.out.println(newfile.getParent());
		System.out.println(newfile.lastModified());
		System.out.println(newfile.length());
		java.io.File[] a=d.listFiles();
		for(int i=0;i<a.length;i++)
		System.out.println(a[i].getAbsolutePath());
		//System.out.println(newfile.delete());//boolean;
		System.out.println(newfile.renameTo(newfile));
		
	}
}
