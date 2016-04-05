package neuqoj;
import java.util.*;
public class problem1098 {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int total=input.nextInt();
		int[] count=new int [total];
		for (int i1=0;i1<total;i1++){
			int mount=input.nextInt();
			int[] tri=new int[mount];
			for (int i2=0;i2<mount;i2++){
				tri[i2]=input.nextInt();
			}//input
			count[i1]=counttri(tri);
		}//for
		for (int i1=0;i1<total;i1++){
			System.out.println(count[i1]);
		}
	}

	public static int counttri(int[] t){
		int ia=0;  int ib=1;  int ic=2;
		int count=0;
		
		while (ia<t.length-2){
			while(ib>ia&&ib<t.length-1){
				while(ic<t.length){
					if(t[ia]+t[ib]>t[ic]&&t[ib]+t[ic]>t[ia]&&t[ic]+t[ia]>t[ib]){
						count++;
					}
					ic++;
				}//c
					ib++;
					ic=ib+1;
			}//b
				ia++;
				ib=ia+1;
				ic=ib+1;
		}//a
			return count;
	}
}
	/*public static void main(String[]args){
	Scanner input=new Scanner(System.in);
	int [] t=new int [4];
	for (int i=0;i<4;i++)
		t[i]=input.nextInt();
	System.out.println(counttri(t));
}*/

