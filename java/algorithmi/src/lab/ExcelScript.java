package lab;

public class ExcelScript {
	public static void main(String[]args){
		//int a=3181;
		//char c='A';
		/*for(int i=0;i<9;i++){
			System.out.println(""
				+ "Range(@A"+a+":"+"C"+(a+59)+"@).Select\n"
				+"Application.CutCopyMode = False\n"
				+"Selection.Cut\n"
				+"Range(@F"+c+"1@).Select\n"
				+"ActiveSheet.Paste\n");
			a+=60;
			c+=3;
		}*/
		/*char b='A';
		for(int i=0;i<26;i++){

			System.out.println("Columns(@"+b+":"+b+"@).EntireColumn.AutoFit");
			
			b++;
		}*/

		for(int a=0;a<180;a+=3){
			System.out.println("Range(\""+go(a)+"1:"+go(a)+"60\").Select");
			System.out.println(""+
   " Selection.Borders(xlDiagonalDown).LineStyle = xlNone\n"+
   " Selection.Borders(xlDiagonalUp).LineStyle = xlNone\n"+
   " Selection.Borders(xlEdgeLeft).LineStyle = xlNone\n"+
   " With Selection.Borders(xlEdgeTop)\n"+
    "    .LineStyle = xlContinuous\n"+
    "    .Weight = xlThin\n"+
    "    .ColorIndex = 1\n"+
    "End With\n"+
    "With Selection.Borders(xlEdgeBottom)\n"+
     "   .LineStyle = xlContinuous\n"+
      "  .Weight = xlThin\n"+
       " .ColorIndex = 1\n"+
   " End With\n"+
   " With Selection.Borders(xlEdgeRight)\n"+
     "   .LineStyle = xlContinuous\n"+
      "  .Weight = xlThin\n"+
       " .ColorIndex = xlAutomatic\n"+
   " End With\n"+
    "Selection.Borders(xlInsideVertical).LineStyle = xlNone\n"
);
 
		}
	}
	static String go(int a){
		char f='A';
		char s='A';
		for(int i=0;i<a;i++){
			f++;
			if(f>'Z'){
				s++;
				f-=26;
			}
		}
		StringBuilder sb=new StringBuilder("");
		sb.append(s);
		sb.append(f);
		return sb.toString();
	}
}

