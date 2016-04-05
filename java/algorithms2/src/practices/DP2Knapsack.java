package practices;
import java.util.Scanner;
public class DP2Knapsack {
  
  public int numOfItems;
  public int capacity;
  public item[] items;
  private int[][] data;
  
  
  public DP2Knapsack(int capacity, item[] items) {
    // TODO Auto-generated constructor stub
    this.capacity=capacity+1;
    this.items=items.clone();
    this.numOfItems=items.length;
    this.data=new int[numOfItems+1][this.capacity];
  }
  
  public void creatKpsk(){
    for(int i=1;i<=this.numOfItems;i++){
      for(int i1=0;i1<this.capacity;i1++){
        if(items[i-1].size>i1) continue;
        data[i][i1]=max(data[i-1][i1], data[i-1][i1-items[i-1].size]+items[i-1].value);
      }
    }
  }
  
  public void showData(){
    for (int[] is : data) {
      for (int i : is) {
        System.out.print(i+"\t");
      }
      System.out.println();
    }
  }
  
  public static int max(int a, int b){
    return a>b?a:b;
  }
  
  
  
  
  
  public static void main(String[]args){
    Scanner inScanner=new Scanner(System.in);
    int num=inScanner.nextInt();
    int cap=inScanner.nextInt();
    item[] items=new item[num];
    for(int i=0;i<num;i++){
      int size=inScanner.nextInt();
      int value=inScanner.nextInt();
      items[i]=new item(value, size);
    }
    DP2Knapsack knapsack=new DP2Knapsack(cap, items);
    knapsack.creatKpsk();
    knapsack.showData();
    inScanner.close();
  }
}
class item{
  int value;
  int size;
  item(int value, int size){
    this.value=value;
    this.size=size;
  }
}