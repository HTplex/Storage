package inhpoy;
import java.util.Scanner;
public class InhpoyA05extends {
	public static void main(String[]args){
		out(new grandfather());
		out(new fathers());
		out(new suns());
	}
	public static void out(grandfather g){
		System.out.println(g.toString());
	}
}
class grandfather{
}
class fathers extends grandfather{
	public String toString(){
		return "father";
	}
}
class suns extends fathers{
	public String toString(){
		return "sun";
	}//high priority for suns;
}
