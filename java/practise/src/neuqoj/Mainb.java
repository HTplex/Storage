package neuqoj;
import java.util.Scanner;
public class Mainb {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int[] ans=new int[10000];
		int c=0;
		int i=input.nextInt();
		while(i!=0){
			//i=1
			if(i==1){
				int a1=input.nextInt();
				 System.out.println(a1);
			}//i=1
			
			if(i==2){
				int [][] ii8=new int[2][2];
				//input2
				for(int ii=0;ii<2;ii++){
					for(int ii1=0;ii1<2;ii1++){
						ii8[ii][ii1]=input.nextInt();
					}
				}
				
				System.out.println(get2(ii8));
				
			}
			
			if(i==3){
				int [][] ii8=new int[3][3];
				//input2
				for(int ii=0;ii<3;ii++){
					for(int ii1=0;ii1<3;ii1++){
						ii8[ii][ii1]=input.nextInt();
					}
				}
				
				System.out.println(get3(ii8));
				
			}
			if(i==4){
				int [][] ii8=new int[4][4];
				//input2
				for(int ii=0;ii<4;ii++){
					for(int ii1=0;ii1<4;ii1++){
						ii8[ii][ii1]=input.nextInt();
					}
				}
				
				System.out.println(get4(ii8));

		}
			i=input.nextInt();
		}

	}
	public static int get2(int[][] a){
		return(a[0][0]*a[1][1]-a[0][1]*a[1][0]);
	}
	public static int get3(int[][] a){
		int sum=0;
		int [][] b={{a[1][1],a[1][2]},{a[2][1],a[2][2]}};
		int [][] b2={{a[1][0],a[1][1]},{a[2][0],a[2][1]}};
		int [][] b1={{a[1][0],a[1][2]},{a[2][0],a[2][2]}};
		return(a[0][0]*get2(b)-a[0][1]*get2(b1)+a[0][2]*get2(b2));
	}
	public static int get4(int[][] a){
		int sum=0;
		int [][] b={{a[1][1],a[1][2],a[1][3]},{a[2][1],a[2][2],a[2][3]},{a[3][1],a[3][2],a[3][3]}};
		int [][] b1={{a[1][0],a[1][2],a[1][3]},{a[2][0],a[2][2],a[2][3]},{a[3][0],a[3][2],a[3][3]}};
		int [][] b2={{a[1][0],a[1][1],a[1][3]},{a[2][0],a[2][1],a[2][3]},{a[3][0],a[3][1],a[3][3]}};
		int [][] b3={{a[1][0],a[1][1],a[1][2]},{a[2][0],a[2][1],a[2][2]},{a[3][0],a[3][1],a[3][2]}};
		return(a[0][0]*get3(b)-a[0][1]*get3(b1)+a[0][2]*get3(b2)-a[0][3]*get3(b3));
	}
	}
 