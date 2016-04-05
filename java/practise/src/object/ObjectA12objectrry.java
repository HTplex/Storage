package object;
import java.util.Scanner;
public class ObjectA12objectrry {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		TV[] a=new TV[10];
		int num=0;
		for(int i=0;i<10;i++){
			a[i]=new TV();
		}
		while (true){
		System.out.println("please input the TV you want to change");
		num=input.nextInt();
		if(num==-1)break;
		System.out.println("input the command");
		String s=" ";
		 s=input.next();
		switch(s){
		 case "w":a[num].volplus();break;
		 case "s":a[num].volminus();break;
		 case "a":a[num].channelminus();break;
		 case "d":a[num].channelplus();break;
		 case "f":a[num].turn();break;
		 }
		ObjectA03TV.tvstatus(a[num]);
		}
		for(int i=0;i<10;i++){
			ObjectA03TV.tvstatus(a[i]);
		}
	}
}