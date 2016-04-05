package mergeSort;

/**
 * Created by htplex on 22/10/2015.
 */
public class MergeSort {


  public static void main(String[] args) {
    int[] ar = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
    mergeSort(ar, 0, 9);
    for (int i : ar) {
      System.out.println(i);
    }

  }

  public static void merge(int[] a, int start, int mid, int end) {
    //int m = (start + end) / 2;
    int[] ret = new int[a.length];
    int cursor1 = start;
    int cursor2 = mid + 1;
    int cursor;
    for (cursor = start; cursor <= end; cursor++) {
      boolean b;
      if (cursor2 > end) {
        b = true;
      } else if (cursor1 > mid) {
        b = false;
      } else {
        b = a[cursor1] > a[cursor2];
      }
      if (b) {
        ret[cursor] = a[cursor1++];
      } else {
        ret[cursor] = a[cursor2++];
      }
    }


    for (int i = start; i <= end; i++) {
      a[i] = ret[i];
    }

  }


  public static void mergeSort(int[] a, int start, int end) {
    if (start >= end) return;
    else {
      int mid = (start + end) / 2;
      mergeSort(a, start, mid);
      mergeSort(a, mid + 1, end);
      merge(a, start, mid, end);
    }

  }
}
