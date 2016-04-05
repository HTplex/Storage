package object;
public class circle22{
	double r;
	circle22(){
		r=1.0;
	}
	circle22(double m){
		r=m;
	}
	double getarea(){
		return r*r*pi;
	}
	static final double pi=Math.PI;
	static int publiccircle;
	static int getnumber(){
		return publiccircle;
	}
}