package multidarray;
import java.util.Scanner;
public class MultidarrayA05distance {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("number of the points");
		int num=input.nextInt();
		int[][] position=new int[num][2];
		for(int i=0;i<position.length;i++){
			System.out.print("input a["+(i+1)+"]"+"[x]");
			position[i][0]=input.nextInt();
			System.out.print("input a["+(i+1)+"]"+"[y]");
			position[i][1]=input.nextInt();
		}
		int a=0,b=1;
		double c=distance(position[0][0],position[0][1],position[1][0],position[1][1]);
		for(int x=0;x<num;x++){
			for(int y=x+1;y<num;y++){
				if(c>distance(position[x][0],position[x][1],position[y][0],position[y][1])){
					c=distance(position[x][0],position[x][1],position[y][0],position[y][1]);
					a=x;
					b=y;
				}
			}
		}
		System.out.println("the most near point is a["+(a+1)+"]&a["+(b+1)+"]");

	}
	public static double distance(int a,int b,int c,int d){
		double x=Math.pow((a-c)*(a-c)+(b-d)*(b-d), 1/2.0);
		return x;
	}
}
