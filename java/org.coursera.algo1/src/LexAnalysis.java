/**
 * Created by htplex on 21/11/2015.
 */
import javax.swing.*;
import java.io.*;
import java.util.Scanner;

/**
 * 词法分析，根据输入的字符串，进行词法分析
 */
public class LexAnalysis {
  public static String [] KEYWORDS = {"if", "else", "for", "while", "do", "return", "break", "continue"};
  public static String [] BORDERS = {",", ";", "{", "}", "(", ")"};
  public static String [] ARIS = {"+", "-", "*", "/"};
  public static String [] RELATIONS = {"<", "<=", "=", ">", ">=", "<>"};
  public static final String DIR_KEYWORD = "keyword.txt";
  public static final String DIR_BORDER = "border.txt";
  public static final String DIR_RELATION = "relation.txt";
  public static final String DIR_ARITHMETIC = "arithmetic.txt";
  public static final String DIR_IDENTIFY = "identify.txt";


  public static String getStringFromFile(String filePath)throws Exception{
    try {
      FileReader fis = new FileReader(filePath);
      BufferedReader bis = new BufferedReader(fis);
      String lineWords;
      StringBuffer sb = new StringBuffer();
      while ((lineWords = bis.readLine()) != null){
        sb.append(lineWords);
      }
      return sb.toString();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return null;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void analysisWords(String toAnalysisString){

    for (int i = 0; i < toAnalysisString.length(); i++){
      char word = toAnalysisString.charAt(i);
      StringBuffer sb = new StringBuffer();
      //普通标识字符 和 关键字的判断和写入
      if ((word >= 48 && word <= 57) || (word >= 65 && word <= 90) || (word >= 97 && word <= 122)){
        for (int j = i + 1; j < toAnalysisString.length(); j++){
          word = toAnalysisString.charAt(i);
          if ((word >= 48 && word <= 57) || (word >= 65 && word <= 90) || (word >= 97 && word <= 122)){
            sb.append(word);
            i++;
          }
          else {
            writeWordToFile(sb.toString());
            sb.delete(0, sb.length());
            break;
          }
        }
      }

      //边界符号
      if (word == 44 || word == 59 || word == 40 || word == 41 || word == 123 || word == 125){
        sb.append(word);
        writeWordToFile(sb.toString());
        sb.delete(0, sb.length());
      }

      //关系表达式
      if (word == 60 || word == 62 || word == 61){
        for (int j = i + 1; j < toAnalysisString.length(); j++){
          word = toAnalysisString.charAt(i);
          if (word == 60 || word == 62 || word == 61){
            sb.append(word);
            i++;
          }
          else {
            writeWordToFile(sb.toString());
            sb.delete(0, sb.length());
            break;
          }
        }
      }

      //数学运算符号
      if (word == 43 || word == 45 || word == 42 || word == 47){
        sb.append(word);
        writeWordToFile(sb.toString());
        sb.delete(0, sb.length());
      }




    }
  }


  //判断字符类型并写入文件
  public static void writeWordToFile(String word){
    for (int i = 0; i < KEYWORDS.length; i++){
      if (word.equals(KEYWORDS[i])){
        writeToFile(DIR_KEYWORD, word);
        System.out.println("KEYWORD:" + word + "recorded");
        return;
      }
    }

    for (int i = 0; i < ARIS.length; i++){
      if (word.equals(ARIS[i])){
        writeToFile(DIR_ARITHMETIC, word);
        System.out.println("ARITHMETIC SYMBOL:" + word + " recorded");
        return;
      }
    }

    for (int i = 0; i < BORDERS.length; i++){
      if (word.equals(BORDERS[i])){
        writeToFile(DIR_BORDER, word);
        System.out.println("BORDER SYMBOL:" + word + " recorded");
        return;
      }
    }

    for (int i = 0; i < RELATIONS.length; i++){
      if (word.equals(RELATIONS[i])){
        writeToFile(DIR_RELATION, word);
        System.out.println("RELATION:" + word + " recorded");
        return;
      }
    }

    writeToFile(DIR_IDENTIFY, word);
    return;
  }

  public static void writeToFile(String filePath, String word){
    FileOutputStream fos = null;
    try {
      fos = new FileOutputStream(filePath, true);
      BufferedOutputStream bos = new BufferedOutputStream(fos);
      byte [] bytes = word.getBytes();
      bos.write(bytes);
      bos.flush();
      bos.close();
      fos.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }



  public static void main(String[] args)throws Exception{
    JFileChooser jf=new JFileChooser();
    if(jf.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
      File file=jf.getSelectedFile();
      Scanner in=new Scanner(file);
      System.out.println("请选择源文件！");
      StringBuilder sb=new StringBuilder("");

      while(in.hasNext()){
        sb.append(in.next());
      }
      String toAnalysisString=sb.toString();
      System.out.println("字符串：" + toAnalysisString);
      analysisWords(toAnalysisString);
      System.out.println();
      System.out.println("2133605 张浩天");
    }




  }


}
