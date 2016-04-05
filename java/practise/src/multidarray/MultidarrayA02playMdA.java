package multidarray;
import java.util.Scanner;
public class MultidarrayA02playMdA {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int choose=-1;
		while(choose!=0){
			System.out.println("please input the number you want");
			System.out.println("1:inputmtrx()\n"+
								 "2:randmtrx()\n"+
								"3:calculate()\n"+
								"4:shufflemtrx()\n"
								+ "0:end");
			choose=input.nextInt();
			switch (choose){
			case 1:inputmtrx();break;
			case 2:randmtrx();break;
			case 3:calculate();break;
			case 4:shufflemtrx();break;
			}//switch
		}//while
	}//main
	public static void inputmtrx(){
		Scanner input=new Scanner(System.in);
		System.out.println("please input the column");
		int c=input.nextInt();
		System.out.println("please input the row");
		int r=input.nextInt();
		int i2;
		int[][] matrix=new int[r][c];
		for(int i=0;i<matrix.length;i++){
			for(i2=0;i2<matrix[i].length;i2++){
				System.out.print("["+i+"]["+i2+"]");
				matrix[i][i2]=input.nextInt();
			}
		}
		for(int i=0;i<matrix.length;i++){
			for(i2=0;i2<matrix[i].length;i2++){
				System.out.printf("%3d",matrix[i][i2]);
			}
			System.out.println();
		}
	}
	public static void randmtrx(){
		Scanner input=new Scanner(System.in);
		System.out.println("please input the column");
		int c=input.nextInt();
		System.out.println("please input the row");
		int r=input.nextInt();
		int i2;
		int[][] matrix=new int[r][c];
		for(int i=0;i<matrix.length;i++){
			for(i2=0;i2<matrix[i].length;i2++){
				matrix[i][i2]=(int)(Math.random()*100);
			}
		}
		for(int i=0;i<matrix.length;i++){
			for(i2=0;i2<matrix[i].length;i2++){
				System.out.printf("%3d",matrix[i][i2]);
			}
			System.out.println();
		}
	}
	public static void calculate(){
		Scanner input=new Scanner(System.in);
		System.out.println("please input the column");
		int c=input.nextInt();
		System.out.println("please input the row");
		int r=input.nextInt();
		int i2;
		int[][] matrix=new int[r][c];
		for(int i=0;i<matrix.length;i++){
			for(i2=0;i2<matrix[i].length;i2++){
				System.out.print("["+i+"]["+i2+"]");
				matrix[i][i2]=input.nextInt();
			}
		}
		int[]sum=new int[matrix.length];
		int sumsum=0;
		for(int a=0;a<matrix.length;a++){
			for(int a2=0;a2<matrix.length;a2++){
				sum[a]+=matrix[a][a2];
				sumsum+=matrix[a][a2];
			}
		}
		System.out.println(sumsum);
		for(int q:sum)
		System.out.print(q+" ");
	}
	public static void shufflemtrx(){
		Scanner input=new Scanner(System.in);
		System.out.println("please input the column");
		int c=input.nextInt();
		System.out.println("please input the row");
		int r=input.nextInt();
		int i2;
		int d=0;
		int[][] matrix=new int[r][c];
		for(int i=0;i<matrix.length;i++){
			for(i2=0;i2<matrix[i].length;i2++){
				matrix[i][i2]=d;
				d++;
			}
		}
		for(int i=0;i<matrix.length;i++){
			for(i2=0;i2<matrix[i].length;i2++){
				System.out.printf("%3d",matrix[i][i2]);
			}
			System.out.println();
		}
		int temp;
		for(int i=0;i<matrix.length;i++){
			for(i2=0;i2<matrix[i].length;i2++){
			int r1=(int)(Math.random()*matrix.length);
			int r2=(int)(Math.random()*matrix[i].length);
			temp=matrix[i][i2];
			matrix[i][i2]=matrix[r1][r2];
			matrix[r1][r2]=temp;
			}
		}
		System.out.println("input any number to continue");
		int fa=input.nextInt();
		for(int i=0;i<matrix.length;i++){
			for(i2=0;i2<matrix[i].length;i2++){
				System.out.printf("%3d",matrix[i][i2]);
			}
			System.out.println();
		}
	}
	
}
