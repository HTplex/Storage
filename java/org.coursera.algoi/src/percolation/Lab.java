package percolation;

/**
 * Created by htplex on 24/9/2015.
 */
public class Lab {

  private boolean[] AX;
  private boolean[] BX;
  private boolean CF;
  private boolean OF;

  Lab(int N) {
    this.AX = new boolean[N];
    this.BX = new boolean[N];
    this.CF = false;
  }

  public static void main(String[] args) {
    Lab l = new Lab(6);
    l.setAX("010101");
    l.setBX("010111");
    l.MUL2();

  }

  private boolean adc(boolean a, boolean b) {
    this.CF = a & b;
    return a ^ b;
  }

  private boolean add(boolean a, boolean b) {
    boolean val = !((o(a) + o(b) + o(this.CF)) % 2 == 0);
    this.CF = (o(a) + o(b) + o(this.CF) > 1);
    return val;
  }

  public void setAX(String s) {
    int p = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '0') this.AX[p++] = false;
      if (s.charAt(i) == '1') this.AX[p++] = true;
    }
  }

  public void setAX(boolean[] b) {
    this.AX = b.clone();
  }

  public void setBX(boolean[] b) {
    this.BX = b.clone();
  }

  public void setBX(String s) {
    int p = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '0') this.BX[p++] = false;
      if (s.charAt(i) == '1') this.BX[p++] = true;
    }
  }

  public boolean[] Str2Boo(String s) {
    int p = 0;
    boolean[] b = new boolean[s.length()];
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '0') b[p++] = false;
      if (s.charAt(i) == '1') b[p++] = true;
    }
    return b;
  }

  public String Boo2Str(boolean[] b) {
    StringBuilder sb = new StringBuilder("");
    for (int i = 0; i < b.length; i++) {
      sb.append(b[i] ? "1" : "0");
    }
    return sb.toString();
  }

  public void clrCF() {
    this.CF = false;
  }

  public boolean[] ADD() {
    clrCF();
    for (int i = this.AX.length - 1; i >= 0; i--) {
      this.AX[i] = add(this.AX[i], this.BX[i]);
    }
    //adc(this.AX[0],this.BX[0]);
    this.OF = this.CF;
    return this.AX;
  }//AX=AX+BX

  public void fullR() {
    for (int i = this.BX.length - 2; i >= 0; i--) {
      this.BX[i + 1] = this.BX[i];
    }
    this.BX[0] = this.AX[AX.length - 1];
    for (int i = this.AX.length - 2; i >= 0; i--) {
      this.AX[i + 1] = this.AX[i];
    }
    this.AX[0] = this.CF;
  }

  public void MUL1() {

    boolean[] CX = AX.clone();
    this.AX = new boolean[this.AX.length];

    for (int i = 0; i < BX.length; i++) {
      this.print2();
      if (BX[BX.length - 1]) {
        this.AX = this.ADD(AX, CX).clone();
        System.out.println(o(this.CF) + "." + this.Boo2Str(AX));
      } else {
        System.out.print(o(this.CF) + ".");
        for (int j = 0; j < this.AX.length; j++) {
          System.out.print("0");
        }
        System.out.println();
      }
      this.print2();
      this.fullR();
    }
  }

  public void MUL2() {
    this.clrCF();
    boolean[] CX = this.AX.clone();
    boolean[] DX = this.NEG(CX);
    this.AX = new boolean[this.AX.length];

    for (int i = 0; i < AX.length / 2; i++) {
      System.out.println(Boo2Str(AX) + Boo2Str(BX));
      boolean sl = this.BX[this.BX.length - 2];
      boolean l = this.BX[this.BX.length - 1];
      int a = o(sl) * 2 + o(l) + o(this.CF);
      if (a == 0) {
        for (int j = 0; j < this.AX.length; j++) {
          System.out.println("0");
        }
        System.out.println(Boo2Str(AX));
        this.fullR();
        this.fullR();
      } else if (a == 1) {
        System.out.println(Boo2Str(CX));
        AX = this.ADD(AX, CX).clone();
        System.out.println(Boo2Str(AX));
        this.clrCF();
        this.fullR();
        this.fullR();

      } else if (a == 2) {
        for (int j = 1; j < AX.length; j++) {
          System.out.print(o(CX[j]));
        }
        System.out.print("0");
        System.out.println();
        AX = this.ADD(AX, CX).clone();
        AX = this.ADD(AX, CX).clone();
        System.out.println(Boo2Str(AX));
        this.clrCF();
        this.fullR();
        this.fullR();
      } else if (a == 3) {
        System.out.println(this.Boo2Str(DX));
        AX = this.ADD(AX, DX).clone();
        System.out.println(Boo2Str(AX));
        this.CF = true;
        this.fullR();
        this.fullR();

      } else if (a == 4) {
        for (int j = 0; j < this.AX.length; j++) {
          System.out.println("0");
        }
        System.out.println(Boo2Str(AX));
        this.CF = true;
        this.fullR();
        this.fullR();

      }

    }
    if (this.CF) AX = this.ADD(AX, CX);
    //System.out.println(Boo2Str(AX) + Boo2Str(BX));
  }

  public void print2() {
    System.out.print(o(this.CF) + ".");
    for (boolean b : this.AX) {
      System.out.print(o(b));
    }
    for (boolean b : this.BX) {
      System.out.print(o(b));
    }
    System.out.println();
  }

  public boolean[] ADD(boolean[] a, boolean[] b) {
    Lab l = new Lab(a.length);
    l.setAX(a);
    l.setBX(b);
    return l.ADD();
  }

  public boolean[] NEG(boolean[] a) {
    boolean[] c = a.clone();
    boolean[] b = new boolean[a.length];
    b[b.length - 1] = true;
    c = NOT(c);

    return ADD(c, b);

  }

  public boolean[] NOT(boolean[] a) {
    for (int i = 0; i < a.length; i++) {
      a[i] = !a[i];
    }
    return a;
  }

  public void print() {
    System.out.print("AX: ");
    for (boolean b : AX) System.out.print(o(b));
    System.out.println();
    System.out.print("BX: ");
    for (boolean b : BX) System.out.print(o(b));
    System.out.println();
    System.out.println("CF: " + this.CF);
  }

  public int o(boolean a) {
    return a ? 1 : 0;
  }
}
