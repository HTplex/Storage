package practices;
import java.util.Scanner;
public class DP1WIS {
  int AmountOfNums;
  int[] nums;
  //edge to is easy too
  public DP1WIS(int[] m) {
    // TODO Auto-generated constructor stub
    this.nums=m.clone();
    this.AmountOfNums=m.length;
  }
  public int[] MWIS(){
    int[] MWIS=new int[this.AmountOfNums];
    MWIS[0]=nums[0];
    MWIS[1]=max(nums[1],nums[0]);
    for(int i=2;i<this.AmountOfNums;i++)
      MWIS[i]=max(nums[i]+MWIS[i-2], MWIS[i-1]);
      return MWIS;
  }

  
  public static void main(String[]args){
    Scanner in=new Scanner(System.in);
    int n=in.nextInt();
    int[] nums=new int[n];
    for(int i=0;i<n;i++)
      nums[i]=in.nextInt();
    DP1WIS a=new DP1WIS(nums);
    for(int i:a.MWIS())
    System.out.println(i+" ");

    in.close();

  }
  public static int max(int a, int b){
    return a>b?a:b;
  }
}
