package j14JavaFX;
import javafx.beans.binding.*;
import javafx.beans.property.*;
public class J08property {
	public static void main(String[]args){
		DoubleProperty d1=new SimpleDoubleProperty(1.0);
		DoubleProperty d2=new SimpleDoubleProperty(2.0);
		d1.bind(d2);
		System.out.println(d1.getValue());
		System.out.println(d2.getValue());
		d2.setValue(0);
		System.out.println(d1.getValue());
		System.out.println(d2.getValue());
		//d1.setValue(15);                    a bind value can not be set
		System.out.println(d1.getValue());
		System.out.println(d2.getValue());
	}
}
