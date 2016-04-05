import java.util.Scanner;


/**
 * Created by htplex on 28/11/2015.
 */
public class F {
  public static void main(String[]args){
    Scanner in =new Scanner(System.in);
    while(in.hasNext()){
      int x=in.nextInt();
      int y=in.nextInt();
      String S=in.next();
      int[] rx=new int[S.length()+1];
      int[] ry=new int[S.length()+1];
      rx[0]=0;
      ry[0]=0;
      for (int i = 1; i <=S.length(); i++) {
        if(S.charAt(i-1)=='L'){rx[i]=rx[i-1]-1;ry[i]=ry[i-1];}
        if(S.charAt(i-1)=='R'){rx[i]=rx[i-1]+1;ry[i]=ry[i-1];}
        if(S.charAt(i-1)=='U'){ry[i]=ry[i-1]+1;rx[i]=rx[i-1];}
        if(S.charAt(i-1)=='D'){ry[i]=ry[i-1]-1;rx[i]=rx[i-1];}
      }
      boolean b=false;
      int gox=rx[rx.length-1];
      int goy=ry[rx.length-1];

        int d;
        if(gox!=0&&Math.abs(x)>=Math.abs(200*(gox/Math.abs(gox)))) {
          int xm=x>0?(x-200):(x+200);
          d = xm / gox;
        }
        else {d=0;}
        int x1 = x - gox * d;
        int y1 = y - goy * d;
        int mx=0;
        int my=0;
        int nx=0;
        int ny=0;
        for (int i = 0; i < 500; i++) {
          for (int j = 0; j < rx.length; j++) {
            mx=rx[j]+nx;
            my=ry[j]+ny;
            if(mx==x1&&my==y1)  b=true;
          }
          nx+=gox;
          ny+=goy;
        }


       if(goy!=0&&Math.abs(y)>= Math.abs(200*(goy/Math.abs(goy)))) {
        int ym=y>0?(y-200):y+200;
         d = ym / goy;
       }
       else d=0;
        int x2 = x - gox * d;
        int y2 = y - goy * d;
        int mx1=0;
        int my1=0;
        int nx1=0;
        int ny1=0;
        for (int i = 0; i < 500; i++) {
          for (int j = 0; j < rx.length; j++) {
            mx1=rx[j]+nx1;
            my1=ry[j]+ny1;
            if(mx1==x2&&my1==y2) b=true;
          }
          nx1+=gox;
          ny1+=goy;
        }



      if(x==0&&y==0)
        b=true;
      System.out.println(b?"Yes":"No");
    }
  }
}
