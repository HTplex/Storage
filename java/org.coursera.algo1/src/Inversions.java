/**
 * Created by htplex on 3/11/2015.
 */

import javax.swing.*;
import java.io.File;
import java.util.Scanner;

public class Inversions {
  private int[] data;
  private long inversions;

  public Inversions(int[] dat) {
    this.data = dat;
    this.inversions = 0;
  }
  public static void main(String[] args) throws Exception {

    JFileChooser jf = new JFileChooser();
    if (jf.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      File file = jf.getSelectedFile();

      int[] a = new int[100000];
      int i = 0;
      Scanner in2 = new Scanner(file);
      while (in2.hasNext()) {
        a[i++] = in2.nextInt();
      }

      Inversions m = new Inversions(a);
      long t = System.currentTimeMillis();
      m.mergeSort();

      System.out.println("time: " + (System.currentTimeMillis() - t));
      m.showInv();

      // m.print();
    }
  }

  public void merge(int start, int end) {
    int mid = (start + end) / 2;
    int[] temp = new int[end - start + 1];
    int pointer1 = start;
    int pointer2 = mid + 1;
    for (int i = 0; i < temp.length; i++) {
      if (pointer1 > mid) {
        inversions += (mid - pointer1 + 1);
        temp[i] = data[pointer2++];
      } else if (pointer2 > end) {
        // inversions+=(end-pointer2+1);
        temp[i] = data[pointer1++];
      } else if (data[pointer1] < data[pointer2]) {
        // inversions+=(end-pointer2+1);
        temp[i] = data[pointer1++];
      } else {
        inversions += (mid - pointer1 + 1);
        temp[i] = data[pointer2++];

      }
    }
    for (int i = 0; i < temp.length; i++) {
      data[i + start] = temp[i];
    }
  }

  public void mergeSort(int start, int end) {
    if (start >= end) return;
    int mid = (start + end) / 2;
    mergeSort(start, mid);
    mergeSort(mid + 1, end);
    merge(start, end);
  }

  public void mergeSort() {
    this.mergeSort(0, this.data.length - 1);
  }

  public void print() {
    for (int i : this.data) {
      System.out.println(i);
    }
  }

  public void showInv() {
    System.out.println("number of inversions: " + this.inversions);
  }
}
