package javaFX_Basics;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/*
 * Created by htplex on 20/7/2015.
 */
public class A06bindingPlus{

  public static void main(String[]args){
    DoubleProperty a=new SimpleDoubleProperty(1);
    DoubleProperty b=new SimpleDoubleProperty(2);
    a.bind(b);
    System.out.println("a is : " + a.getValue() + " b is : " + b.getValue());
    b.setValue(30);
    System.out.println("a is : " + a.getValue() + " b is : " + b.getValue());
    //a.setValue(20);                   once a is binded with b a can't be changed manually anymore
    //System.out.println("a is : " + a.getValue() + "b is : " + b.getValue());
  }
}
