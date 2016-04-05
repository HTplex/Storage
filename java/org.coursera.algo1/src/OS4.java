import java.util.Scanner;

/**
 * Created by htplex on 16/12/2015.
 */
public class OS4 {
  public static void main(String[] args) {
    Scanner in=new Scanner(System.in);
    OS4 a=new OS4();
    do{
      String s=in.next();
      if(s.equals("exit")) break;
      if(s.equals("create")||s.equals("md")){
        String t=in.next();
        a.create(t);
      }
      if(s.equals("delete")||s.equals("del")){
        String t=in.next();
        a.delete(t);
      }
      if(s.equals("cd")){
        String t=in.next();
        a.cd(t);
      }
      if(s.equals("cd..")){
        a.ccd();
      }
    }
    while(true);
    System.out.println("thank you for using, powered by 2133605张浩天");
  }

  private Folder current;

  public OS4() {
    Folder root = new Folder("root");
    this.current = root;
  }

  public void cd(String s) {
    boolean b = true;
    for (int i = 0; i < this.current.subfolders.length; i++) {
      if (this.current.subfolders[i].fileName.equals(s)) {
        this.current = this.current.subfolders[i];
        b = false;
        System.out.println(s + " : ");
      }
      if (b) System.out.println("Error: no such folder");
    }
  }

  public void ccd() {
    if (!this.current.father.fileName.equals("root")) {
      this.current = this.current.father;
      System.out.println(this.current.fileName + " : ");
    } else {
      System.out.println("already root");
    }
  }

  public void dir() {
    for (int i = 0; i < this.current.subfolders.length; i++) {
      System.out.println(this.current.subfolders[i].fileName);
    }
  }
  public void create(String s){
     this.current.add(s);
    System.out.println(this.current.fileName+" : ");
  }
  public void delete(String s){
    this.current.del(s);
    System.out.println(this.current.fileName+" : ");
  }

  class Folder {
    private int pointer;
    private String fileName;
    private Folder father;
    private Folder[] subfolders;
    private
    public Folder(String name) {
      this.pointer = 0;
      this.fileName = name;
      this.subfolders = new Folder[16];
      for (int i = 0; i < this.subfolders.length; i++) {
        this.subfolders[i].father = this;
      }
    }

    public void add(String s) {
      this.subfolders[pointer++] = new Folder(s);
    }

    public void del(String s) {
      for (int i = 0; i < this.pointer; i++) {
        if (this.subfolders[i].fileName.equals(s)) {
          for (int j = i; j < this.pointer; j++) {
            this.subfolders[j] = this.subfolders[j + 1];
          }
        }
      }
    }
  }
}



