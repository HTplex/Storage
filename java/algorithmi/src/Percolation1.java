import java.util.Scanner;
public class Percolation1 {
	class block{
		int x;
		int y;
		boolean white;
		block(){
			this.x=0;
			this.y=0;
			this.white=false;
		}
	}
	block [][]cube;
	

	public Percolation1(int N){
		
		
		this.cube=new block[N][N];
		for(int i=0;i<N;i++){
			for(int i1=0;i1<N;i1++){
				this.cube[i][i1]=new block();
				this.cube[i][i1].x=i;
				this.cube[i][i1].y=i1;
				this.cube[i][i1].white=false;
				//this.cube[i][i1].white=false;
			}
		}
	}
	
	public void open(int i, int j){
		this.cube[i-1][j-1].white=true;
	}
	
	public boolean isOpen(int i, int j){
		return cube[i-1][j-1].white;
	}
	
	public boolean isFull(int i, int j){
		return !cube[i-1][j-1].white;
	}
	
	public static boolean percolates(block[][] a){
		return connected(a,0,0,a.length-1,a.length-1);
	}
	
	public static int[] testroot(block[][] a, int x,int y){
		int[] roo={x,y};
		
		while(!(a[roo[0]][roo[1]].x==roo[0]&&a[roo[0]][roo[1]].y==roo[1])){
			System.out.println("roo[0]="+roo[0]+"roo[1]"+roo[1]);
			System.out.println("a[roo[0]][roo[1]].x"+a[roo[0]][roo[1]].x+"a[roo[0]][roo[1]].y"+a[roo[0]][roo[1]].y);
			int temp=a[roo[0]][roo[1]].y;
			roo[0]=a[roo[0]][roo[1]].x;
			roo[1]=temp;
			System.out.println("roo[0]="+roo[0]+"roo[1]"+roo[1]);
			System.out.println("a[roo[0]][roo[1]].x"+a[roo[0]][roo[1]].x+"a[roo[0]][roo[1]].y"+a[roo[0]][roo[1]].y);
		}
		return roo;
	}
	
	public static int[] root(block[][] a, int x,int y){
		int[] roo={x,y};
		roo[0]=x;
		roo[1]=y;
		while(!(a[roo[0]][roo[1]].x<0||(a[roo[0]][roo[1]].x==roo[0]&&a[roo[0]][roo[1]].y==roo[1]))){
			int temp=a[roo[0]][roo[1]].y;
			roo[0]=a[roo[0]][roo[1]].x;
			roo[1]=temp;
		}
		return roo;
	}
	
	public static int[] randArray(int N){
		int []a=new int[N];
		for(int i=0;i<N;i++){
			a[i]=i;
		}
		for(int i=0;i<N;i++){
			int r=(int)(Math.random()*N);
			int temp=a[i];
			a[i]=a[r];
			a[r]=temp;
		}
		return a;
	}
	public static void connect(block a[][], int x1, int y1, int x2, int y2){
		a[root(a,a[x1][y1].x,a[x1][y1].y)[0]][root(a,a[x1][y1].x,a[x1][y1].y)[1]].x=
		root(a,a[x2][y2].x,a[x2][y2].y)[0];
		a[root(a,a[x1][y1].x,a[x1][y1].y)[0]][root(a,a[x1][y1].x,a[x1][y1].y)[1]].y=
				root(a,a[x2][y2].x,a[x2][y2].y)[1];
	}
	public static void connecte(block a[][], int x1, int y1, int x2, int y2){
		
	}
	
	public static boolean connected(block a[][], int x1, int y1, int x2,int y2){
		return (root(a,x1,y1)[0]==root(a,x2,y2)[0])&&(root(a,x1,y1)[1]==root(a,x2,y2)[1]);
	}
	
	public static void whiten(block a[][], int x, int y){
		a[x][y].white=true;
		if(x+1<a.length)
			if(a[x+1][y].white)
				connect(a,x,y,x+1,y);
		if(y+1<a.length)
			if(a[x][y+1].white)
				connect(a,x,y,x,y+1);
		if(x-1>=0)
			if(a[x-1][y].white)
				connect(a,x,y,x-1,y);
		if(y-1>=0)
			if(a[x][y-1].white)
				connect(a,x,y,x,y-1);

	}
	
	public static void inpu(int[][] a,block[][] m){
		for(int i=0;i<a.length;i++){
			for(int i1=0;i1<a.length;i1++){
				if(a[i][i1]==0)
					whiten(m,i,i1);
			}
		}
	}
	
	public static void main(String[]args){	
		Scanner input=new Scanner(System.in);
		int i=input.nextInt();
		
		
		for(int n=0;n<10;n++){
			Percolation1 a=new Percolation1(i);
			for(int i1=0;i1<i;i1++){
			connect(a.cube,0,0,0,i1);
			connect(a.cube,i-1,0,i-1,i1);
			}
			//int[][] index=new int[i][i];
			
			
			
	/*	for(int m=0;m<i;m++){
			for(int m1=0;m1<i;m1++){
				int c=(int)(2*Math.random());
				index[m][m1]=c;
				System.out.print(c);
			}
			System.out.println();
		}
		inpu(index,a.cube); */
		/*whiten(a.cube,0,0);
		//whiten(a.cube,0,1);
		//whiten(a.cube,0,2);
		//whiten(a.cube,1,0);
		whiten(a.cube,1,1);
		whiten(a.cube,1,2);
		//whiten(a.cube,2,0);
		whiten(a.cube,2,1);
		whiten(a.cube,2,2);*/
		//}
		//connect(a.cube,0,0,i-1,i-1);
			//for(int i=0;i)
		
		System.out.println(percolates(a.cube));
		}
		//System.out.println(connected(a.cube,0,0,0,3));
		//System.out.println(connected(a.cube,i-1,0,i-1,2));
		//System.out.println(connected(a.cube,0,0,2,3));
		/*for(int i=0;i<2;i++){
			for(int i1=0;i1<2;i1++){
				System.out.print(a.cube[i][i1].x+" "+a.cube[i][i1].y+"\n");
			}
		}*/
		//connect(a.cube,1,0,0,1);
		//connect(a.cube,1,1,1,0);
		//a.cube[0][1].x=-1;
		//a.cube[0][1].y=-1;
		//System.out.println(root(a.cube,1,1)[0]+" "+root(a.cube,1,1)[1]);
		input.close();
	}
}
