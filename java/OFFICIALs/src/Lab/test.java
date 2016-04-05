package Lab;

import java.util.GregorianCalendar;

/**
 * Created by htplex on 3/29/16.
 */
public class test {
  public static void main(String[]args){
    GregorianCalendar gc=new GregorianCalendar();
    gc.setTimeInMillis(1234567890L);
    System.out.println(gc.get(GregorianCalendar.YEAR));
    System.out.println(gc.get(GregorianCalendar.MONTH)+1);
    System.out.println(gc.get(GregorianCalendar.DAY_OF_MONTH));
  }

}
