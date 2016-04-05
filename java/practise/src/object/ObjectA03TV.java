package object;
import java.util.Scanner;
public class ObjectA03TV {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		TV a=new TV();
		char c=' ';
		System.out.println("instruction:\n\tf:on/off\n\tw:vol+\n\ts:vol-\n\ta:channel+\n\td:channel-");
		while(true){
			String s=" ";
			 s=input.next();
			 switch(s){
			 case "w":a.volplus();break;
			 case "s":a.volminus();break;
			 case "a":a.channelminus();break;
			 case "d":a.channelplus();break;
			 case "f":a.turn();break;
			 }
			 tvstatus(a);
		}
	}
	public static void tvstatus(TV a){
		String m;
		if(a.onoff)
			m="on";
		else m="off";
		System.out.println("status=      "+m+"\nvolume=      "+a.volume+"\nchannel=     "+a.channel+"\n\n\n\n\n");
	}
}
class TV{
	int volume;
	int channel;
	boolean onoff;
	TV(){
			volume=30;
			channel=1;
			onoff=false;
			}
	public void turn(){
			 boolean a=!onoff;
			 onoff=a;
		}
	public void channelplus(){
			if(channel<8)
				channel++;
			else channel=0;
		}
	public void channelminus(){
			if(channel>0)
				channel--;
			else channel=8;
		}
	public void volplus(){
			if(volume<60)
				volume++;
		}
	public void volminus(){
			if(volume>0)
				volume--;
		}
}

