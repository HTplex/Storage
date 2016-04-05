//package hiho;
//
//import java.util.Arrays;
//import java.util.Currency;
//import java.util.Scanner;
//
///**
// * Created by htplex on 3/13/16.
// */
//public class A {
//
//
//  public static void main(String[]args) {
//    Scanner in = new Scanner(System.in);
//
//    item[][] items = new item[10][50];
//    int[] curser = new int[10];
//
//    int N = in.nextInt();
//    int n = in.nextInt();
//    for (int r = 0; r < N; r++) {
//
//
//      for (int i = 0; i < n; i++) {
//        float price = in.nextFloat();
//        int des = in.nextInt();
//        item t = new item(price, des);
//        int mod = ((int) (price * 2)) % 10;
//        items[mod][curser[mod]++] = t;
//      }
//
//      for (int i = 0; i < 10; i++) {
//        Arrays.sort(items[i], 0, curser[i]);
//      }
//      int max = 0;
//      double p = 0;
//
//      for (int i = 0; i < 10; i++) {
//        if (curser[i] != 0 && max < items[i][0].desire) {
//          max = items[i][0].desire;
//          p = items[i][0].price;
//        }
//      }
//      for (int i = 0; i < n; i++) {
//      if(Currency.getAvailableCurrencies().add(m));
//      }
//
//      for (int i = 1; i < 10; i++) {
//        if (curser[i] != 0 && curser[10 - i] != 0) {
//          int mm = items[i][0].desire + items[10 - i][0].desire;
//          if (max < mm) {
//            max = mm;
//            p = items[i][0].price + items[10 - i][0].price;
//          }
//        }
//      }
//      if (curser[0] > 1) {
//        int mm = items[0][0].desire + items[0][1].desire;
//        if (max < mm) {
//          max = mm;
//          p = items[0][0].price + items[0][1].price;
//        }
//      }
//      if (curser[0] > 2) {
//        int mm = items[0][0].desire + items[0][1].desire + items[0][2].desire;
//        if (max < mm) {
//          max = mm;
//          p = items[0][0].price + items[0][1].price + items[0][2].price;
//        }
//      }
//
//      for (int i = 0; i < 10; i++) {
//        for (int j = 1; j < 10; j++) {
//          int[] cc = new int[10];
//          cc[i]++;
//          cc[j]++;
//          int k = i + j > 10 ? 20 - i - j : 10 - i - j;
//          cc[k]++;
//          if (cc[i] <= curser[i] && cc[j] <= curser[j] && cc[k] <= curser[k]) {
//            int[] cur = new int[10];
//            int mm = 0;
//            int pp = 0;
//            mm += items[i][cur[i]].desire;
//            pp += items[i][cur[i]++].price;
//            mm += items[j][cur[j]].desire;
//            pp += items[i][cur[j]++].price;
//            mm += items[k][cur[k]].desire;
//            pp += items[i][cur[k]++].price;
//            if (max < mm) {
//              max = mm;
//              p = pp;
//            }
//          }
//        }
//      }
//
//
//      System.out.println(max);
//
//
////    for (int i = 0; i < 10; i++) {
////      for (int i1=0;i1<curser[i];i1++) {
////        System.out.print(items[i][i1].desire+" ");
////      }
////      System.out.println();
////    }
//
//    }
//  }
//  static class item implements Comparable<item>{
//    float price;
//    int desire;
//    item(float a, int b){
//      this.price=a;
//      this.desire=b;
//    }
//    public int compareTo(item b){
//      return (int) (b.desire-this.desire);
//    }
//  }
//}
