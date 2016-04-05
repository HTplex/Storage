package main;
import java.util.Arrays;
import java.util.Scanner;

public class Decryp1tMTP {
  public static void main(String[]args){
    Scanner in=new Scanner(System.in);
    String[][] XorTable=new String[30][30];
    Character[] cTable=new Character[30];
    
    
    cTable[0]=' ';
    cTable[1]='.';
    cTable[2]=',';
    cTable[3]=':';
    cTable[4]='a';
    for(int i=4;i<cTable.length-1;i++){
      cTable[i+1]=(char)(cTable[i]+1);
    }
    
//    for (Character c : a) {
//      System.out.print(c);                //printcTable
//    }
    
    for(int i=0;i<cTable.length;i++){
      for(int i1=0;i1<cTable.length;i1++){            //setupXortable
        XorTable[i][i1]=BasicTools.StrtoHex(
            BasicTools.StrXor(cTable[i].toString(),cTable[i1].toString()));
      }
    }
    
    for (String[] s1:XorTable) {
      for (String string : s1) {              //printXorTable
        System.out.print(string+" ");
      }
     System.out.println();
    }
    
    
    String[] plainCipher=new String[11];
    for(int i=0;i<11;i++){
      plainCipher[i]=in.next(); 
    }
    String[] hexKey=new String[plainCipher[10].length()/2];
    for(int i=0;i<hexKey.length;i++){
      hexKey[i]="--";
    }
    String[][] hexCipher=new String[11][plainCipher[6].length()/2];
    for(int i=0;i<hexCipher.length;i++){
      for(int i1=0;i1<hexCipher[0].length;i1++){
        hexCipher[i][i1]="--";
      }
    }
    for(int cip=0;cip<plainCipher.length;cip++){
      for(int i=0;i<=plainCipher[cip].length()-2;i+=2){         //set up hexcipher
        hexCipher[cip][i/2]=plainCipher[cip].substring(i,i+2);
      }
    }
//    for (String[] strings : hexCipher) {
//      for (String string : strings) {
//        System.out.print(string+" ");                 //output processed hex
//      }
//      System.out.println();
//    }
//   
    for(int i=0;i<hexCipher.length;i++){
      int[] moni=new int[plainCipher[i].length()/2];
      Arrays.fill(moni, 0);
      
      for(int i1=0;i1<plainCipher[i].length()/2;i1++){
        for(int i2=0;i2<hexCipher.length;i2++){
          if(hexCipher[i2][i1].equals("--")) continue;              
          String Xor=BasicTools.HexXor(hexCipher[i2][i1], hexCipher[i][i1]);
          if(Xor.charAt(0)=='4'||Xor.charAt(0)=='5'||Xor.equals("0e")||Xor.equals("0c")){
            moni[i1]++;
          }
        }
      }
      for(int im=0;im<moni.length;im++){
        if(im<hexKey.length&&moni[im]>6){
          hexKey[im]=BasicTools.HexXor(BasicTools.StrtoHex(" "), hexCipher[i][im]);
        }
      }
    }
    System.out.println();
    for(String key:hexKey)
      System.out.print(key+" ");
    for(int a=0;a<11;a++){
    for(int i=0;i<hexKey.length;i++){
      if(hexKey[i].equals("--")) System.out.print("-");
      else System.out.print(BasicTools.HexToStr(BasicTools.HexXor(hexKey[i], hexCipher[a][i])));
    }
    System.out.println();
    }
    //System.out.println("-he secret message is- When us-ng a stream cipher, never use the key more than once");
    in.close();
  } 
}
