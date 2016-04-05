package queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by htplex on 12/10/2015.
 */

public class Deque<Item> implements Iterable<Item> {
  private Node<Item> first;
  private Node<Item> last;
  private int N;
  public Deque() {
    this.first = this.last = null;
    this.N = 0;


  }

  public static void main(String[] args) {

//    edu.princeton.cs.algs4.Queue<String> dq=new edu.princeton.cs.algs4.Queue<>();
//    dq.enqueue("this ");
//    dq.enqueue("is ");
//    dq.enqueue("GAY!");
//    for(String s:dq) {
//      System.out.println(s);
//    }

//    Deque<Integer> deque = new Deque<>();
//
//    deque.isEmpty();
//    deque.addFirst(101);
//
//    deque.isEmpty();
//    deque.removeFirst();
//    deque.addFirst(2);
//    for (Integer integer :deque) {
//      System.out.println(integer);
//    }


  }

  public boolean isEmpty() {
    return this.N == 0;
  }

  public int size() {
    return this.N;
  }

  public void addFirst(Item item) {
    if (item == null) {
      throw new NullPointerException();
    }
    Node<Item> newNode = new Node<>();
    newNode.item = item;
    if (this.isEmpty()) {
      this.first = this.last = newNode;
    } else {
      Node<Item> oldFirst = this.first;
      this.first = new Node<Item>();
      this.first.item = item;
      this.first.next = oldFirst;
      oldFirst.pre = this.first;
    }
    this.N++;
  }

  public void addLast(Item item) {
    if (item == null) {
      throw new NullPointerException();
    }
    Node<Item> newNode = new Node<>();
    newNode.item = item;
    if (this.isEmpty()) {
      this.first = this.last = newNode;
    } else {
      Node<Item> oldLast = this.last;
      this.last = new Node<>();
      this.last.item = item;
      this.last.pre = oldLast;
      oldLast.next = this.last;
    }
    this.N++;
  }

  public Item removeFirst() {
    if (this.isEmpty()) throw new NoSuchElementException();
    Item re = this.first.item;
    this.first = this.first.next;
    if(this.first!=null) {
      this.first.pre = null;
    }
    else{
      this.first = this.last = null;
      this.N = 1;
    }
    this.N--;
    return re;
  }

  public Item removeLast() {
    if (this.isEmpty()) throw new NoSuchElementException();
    Item re = this.last.item;
    this.last = this.last.pre;
    if(this.last!=null) {
      this.last.next = null;
    }
    else{
      this.first = this.last = null;
      this.N = 1;
    }
    this.N--;
    return re;

  }

  public Iterator<Item> iterator() {
    return new DequeIterator<Item>(first);
  }

  private class Node<Item> {
    Item item;
    Node next;
    Node pre;
  }

  private class ListIterator<Item> implements Iterator<Item> {
    private Node<Item> current;

    public ListIterator(Node<Item> first) {
      current = first;
    }

    public boolean hasNext() {
      return current != null;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }

    public Item next() {
      if (!hasNext()) throw new NoSuchElementException();
      Item item = current.item;
      current = current.next;
      return item;
    }
  }

  private class DequeIterator<Item> implements Iterator<Item> {
    private Node<Item> current;

    public DequeIterator(Node<Item> first) {
      current = first;
    }

    public boolean hasNext() {
      return current != null;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }

    public Item next() {
      if (!hasNext()) throw new NoSuchElementException();
      Item item = current.item;
      current = current.next;
      return item;
    }

  }
}