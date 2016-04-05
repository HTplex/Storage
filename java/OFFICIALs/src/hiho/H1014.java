//package hiho;
//
//import java.util.Scanner;
//
///**
// * Created by htplex on 3/9/16.
// */
//public class H1014 {
//  private class node{
//    public node[] next;
//    public int words;
//    public node(){
//      this.next=new node[26];
//      this.words=0;
//    }
//    public node nextNode(char c){
//      return this.next[c-'a'];
//    }
//
//  }
//
//  public H1014(){
//    this.root=new node();
//  }
//  private node root;
//  public void addWord(String s){
//    node current=this.root;
//    for (int i = 0; i < s.length(); i++) {
//      current.words++;
//      if(current.nextNode(s.charAt(i))==null){
//        current=new node();
//      }
//      current=current.nextNode(s.charAt(i));
//
//
//    }
//  }
//  public static int words(String pre){
//    node cu
//    for (int i = 0; i < pre.length(); i++) {
//
//    }
//  }
//}
