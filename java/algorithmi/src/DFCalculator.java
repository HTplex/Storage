
public class DFCalculator {
	public static void main(String[]args){
		week2.DDGenericalLinkList<Double> number=new week2.DDGenericalLinkList<Double>();
		week2.DDGenericalLinkList<String> symbol=new week2.DDGenericalLinkList<String>();
		while(!StdIn.isEmpty()){
			String s=StdIn.readString();
			if(s.equals("+")||s.equals("*")){
				symbol.push(s);
			}
			else if(s.equals("(")){
				/*do nothing*/
			}
			else if(s.equals(")")){
				double a=number.pop();
				double b=number.pop();
				String sym=symbol.pop();
				if(sym.equals("+")){
					number.push(a+b);
				}
			}
		}
	}
}
