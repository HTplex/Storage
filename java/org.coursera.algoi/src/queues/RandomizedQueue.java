package queues;


import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by htplex on 17/10/2015.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
  private Item[] ItemQueue;
  private int num;

  public RandomizedQueue() {
    ItemQueue = (Item[]) (new Object[2]);
    this.num = 0;
  }

  private void shrink() {
    Item[] sr = (Item[]) (new Object[this.ItemQueue.length / 2]);
    for (int i = 0; i < num; i++) {
      sr[i] = ItemQueue[i];
    }
    ItemQueue = sr;
  }//if num < 1/4 capacity shrink to 1/2

  private void expand() {
    Item[] sr = (Item[]) (new Object[this.ItemQueue.length * 2]);
    for (int i = 0; i < num; i++) {
      sr[i] = ItemQueue[i];
    }
    ItemQueue = sr;
  }// if num=capacity expand to 2X

  public boolean isEmpty() {
    return this.num == 0;
  }

  public int size() {
    return this.num;
  }

  public void enqueue(Item item) {
    if (item == null) throw new NullPointerException();
    ItemQueue[num++] = item;
    if (num >= ItemQueue.length) this.expand();
  }

  public Item dequeue() {
    if (this.isEmpty()) throw new NoSuchElementException();
    int index = (int) (StdRandom.uniform(this.num));
    Item ret = this.ItemQueue[index];
    this.ItemQueue[index] = this.ItemQueue[num - 1];
    this.ItemQueue[num - 1] = null;
    num--;
    if (num <= ItemQueue.length / 4) this.shrink();
    return ret;
  }

  public Item sample() {
    if (this.isEmpty()) throw new NoSuchElementException();
    int index = (int) (StdRandom.uniform(this.num));
    return this.ItemQueue[index];
  }

  public Iterator<Item> iterator() {
    return new RQIterator<Item>(this.ItemQueue, this.num);
  }

  private class RQIterator<Item> implements Iterator {
    Item[] ItemQueue;
    int num;
    int cursor;
    int[] index;

    RQIterator(Item[] IQ, int num) {
      this.ItemQueue = IQ;
      this.num = num;
      this.cursor = 0;
      this.index = new int[this.num];
      for (int i = 0; i < index.length; i++) {
        this.index[i] = i;
      }
      StdRandom.shuffle(this.index);
    }

    public boolean hasNext() {
      return this.num != this.cursor;
    }

    public Item next() {
      if (!hasNext()) throw new NoSuchElementException();
      Item i = this.ItemQueue[index[cursor++]];
      return i;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

}