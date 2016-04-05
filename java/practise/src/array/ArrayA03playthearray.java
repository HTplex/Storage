package array;
import java.util.Scanner;
public class ArrayA03playthearray {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input the number of the program");
		System.out.println("1:inputinitial()\n"
				+ "2:inputrandom()\n"
				+ "3:sum()\n"
				+ "4:findmax()\n"
				+ "5:randomlize()\n"
				+ "6:train()");
		int i=input.nextInt();
		switch (i){
		case 1:inputinitial();break;
		case 2:inputrandom();break;
		case 3:sum();break;
		case 4:findmax();break;
		case 5:randomlize();break;
		case 6:train();break;
		default:System.out.println("are you kidding me?");
		}//switch
	}//main
	public static void inputinitial(){
		Scanner input=new Scanner(System.in);
		int[] put=new int[10];
		int i=0;
		for (i=0;i<put.length;i++){
			System.out.println("please input the No."+(i+1)+" number.");
			put[i]=input.nextInt();
		}//for
		for (i=0;i<put.length;i++)
		System.out.println("put["+i+"]="+put[i]);
	}
	public static void inputrandom(){
		int [] random=new int[100];
		int i=0;
		for (i=0;i<random.length;i++)
			random[i]=(int)(100*Math.random());
		for (i=0;i<random.length;i++)
		System.out.println("random["+(i+1)+"]=\t"+random[i]);
	}
	public static void sum(){
		Scanner input=new Scanner(System.in);
		int i=0;
		int sum=0;
		int [] ms=new int[10];
		for (i=0;i<ms.length;i++){
			System.out.println("please input the No."+(i+1)+" number.");
			ms[i]=input.nextInt();
			sum+=(ms[i]*ms[i]);
			System.out.println("now sum is "+sum);
			
		}
	}
	public static void findmax(){
		Scanner input=new Scanner(System.in);
		int i=0;
		int max=0;
		int maxnum=0;
		int[] maxb=new int [10];
		for(i=0;i<maxb.length;i++){
			System.out.println("please input the number "+(i+1)+" or some sord of thing");
			maxb[i]=input.nextInt();
			if (maxb[i]>max){
				max=maxb[i];
				maxnum=i+1;
				System.out.println("the biggest number now is \nthe No."+maxnum+"  number :  "+max);
			}//if
			else 
				System.out.println("the biggest number is still \nthe No."+maxnum+"  number :  "+max);
		}
	}
	public static void randomlize(){
		Scanner input=new Scanner(System.in);
		int i=0;
		int [] random=new int[10];
		for (i=0;i<random.length;i++){
			System.out.print("input No."+(i+1)+"\t\t\t");
			random[i]=input.nextInt();
			System.out.println();
		}
		int temp=0;
		for (i=0;i<random.length;i++){
			int r=(int)(10*Math.random());
			temp=random[i];
			random[i]=random[r];
			random[r]=temp;
		}
		for (i=0;i<random.length;i++){
			System.out.println("No."+i+"=\t"+random[i]);
		}
	}
	public static void train(){
	Scanner input=new Scanner(System.in);
		int[] tra=new int[10];
		for (int i=0;i<tra.length;i++){
			System.out.println("input No."+(i+1));
			tra[i]=input.nextInt();
		}
		System.out.println("how many do you want to circle?");
		int i=input.nextInt();
		int temp=0;
		for (int ii=0;ii<i;ii++){
			temp=tra[0];
			for (int iii=0;iii<tra.length-1;iii++){
				tra[iii]=tra[iii+1];
			}
			tra[tra.length-1]=temp;
		}
		for (int a=0;a<tra.length;a++)
			System.out.println("put["+a+"]="+tra[a]);
	}
}
