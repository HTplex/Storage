/**
 * Created by htplex on 5/11/2015.
 */

public class ToolBox {
  public static void main(String[] args) {
    String Hex1 = "attack at dawn";
    String Hex2 = "attack at dusk";
    System.out.println();
    System.out.println(StrToBin(Hex1));
    System.out.println(StrToBin(Hex2));
    System.out.println(StrToBin(StrXor(Hex1, Hex2)));
    //   System.out.println(StrtoHex(StrXor(Hex1,Hex2)));
    System.out.println(HexToBin("09e1c5f70a65ac519458e7e53f36"));
    System.out.println(HexToBin(HexXor("09e1c5f70a65ac519458e7e53f36", "0000000000000000000000140405")));
  }

  public static String HexToStr(String Hex) {
    StringBuilder builder = new StringBuilder("");
    for (int i = 0; i <= Hex.length() - 2; i += 2) {
      builder.append((char) (HextoInt(Hex.substring(i, i + 2))));
    }
    return builder.toString();
  }

  public static String StrtoHex(String string) {
    StringBuilder builder = new StringBuilder("");
    for (int i = 0; i < string.length(); i++) {
      builder.append(IntToHex((int) (string.charAt(i))));
    }
    return builder.toString();
  }

  public static int HextoInt(String Hex) {
    return Integer.parseInt(Hex, 16);
  }

  public static String IntToHex(int Dec) {
    StringBuilder sb = new StringBuilder(Integer.toHexString(Dec));
    if (sb.length() < 2)
      sb.insert(0, 0);
    return sb.toString();
  }

  public static String IntToBin(int Dec) {
    String s = Integer.toBinaryString(Dec);
    StringBuilder sb = new StringBuilder(s);
    while (sb.length() % 4 != 0)
      sb.insert(0, "0");
    for (int i = 0; i < s.length(); i += 5) {
      sb.insert(i + 4, " ");
    }
    return sb.toString();
  }

  public static String sHexToBin(String Hex) {
    return IntToBin(HextoInt(Hex));
  }

  public static String HexToBin(String Hex) {
    StringBuilder sb = new StringBuilder("");
    for (int i = 0; i < Hex.length(); i++) {
      sb.append(sHexToBin(Hex.substring(i, i + 1)));
    }
    return sb.toString();
  }

  public static String StrToBin(String string) {
    String hex = StrtoHex(string);
    StringBuilder sbBuilder = new StringBuilder("");
    for (int i = 0; i <= hex.length() - 2; i += 2) {
      sbBuilder.append(HexToBin(hex.substring(i, i + 2)));
    }
    return sbBuilder.toString();
  }

  public static String HexXor(String Hex1, String Hex2) {
    StringBuilder builder = new StringBuilder("");
    for (int i = 0; i <= Hex1.length() - 2; i += 2) {
      int Int1 = HextoInt(Hex1.substring(i, i + 2));
      int Int2 = HextoInt(Hex2.substring(i, i + 2));
      int Xor = Xor(Int1, Int2);
      builder.append(IntToHex(Xor));
    }
    return builder.toString();
  }

  public static int Xor(int Int1, int Int2) {
    return (Int1 | Int2) & (~(Int1 & Int2));
  }

  public static String StrXor(String s1, String s2) {
    return HexToStr(HexXor(StrtoHex(s1), StrtoHex(s2)));
  }
}


