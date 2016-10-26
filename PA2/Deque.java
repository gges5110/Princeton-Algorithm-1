import java.util.Iterator;
import java.lang.NullPointerException;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

public class Deque<Item> implements Iterable<Item> {
  private int N;
  private Node head; // sentinel before first item
  private Node tail; // sentinel after last item

  private class Node {
    private Item item;
    private Node next;
    private Node prev;

    public Node(Item item) {
      this.item = item;
    }
    public Node() {}
  }

  public Deque() {
    head = new Node();
    tail = new Node();
    head.next = tail;
    tail.prev = head;
  }

  public boolean isEmpty() {
    return head.next == tail && N == 0;
  }

  public int size() {
    return N;
  }

  public void addFirst(final Item item) {
    if (item == null) throw new NullPointerException();

    Node temp = new Node(item);
    if(isEmpty()) tail.prev = temp;

    temp.next = head.next;
    temp.prev = head;

    temp.next.prev = temp;
    head.next = temp;
    N++;
  }

  public void addLast(final Item item) {
    if (item == null) throw new NullPointerException();

    Node temp = new Node(item);
    if(isEmpty()) head.next = temp;

    temp.prev = tail.prev;
    temp.next = tail;

    temp.prev.next = temp;
    tail.prev = temp;
    N++;
  }

  public Item removeFirst() {
    if (this.isEmpty()) throw new NoSuchElementException();

    Node temp = head.next;
    final Item item = temp.item;

    head.next = temp.next;
    head.next.prev = head;

    temp = null;
    N--;
    return item;
  }

  public Item removeLast() {
    if (this.isEmpty()) throw new NoSuchElementException();

    Node temp = tail.prev;
    final Item item = temp.item;

    tail.prev = temp.prev;
    tail.prev.next = tail;

    temp = null;
    N--;
    return item;
  }


  public Iterator<Item> iterator() {
    return new ItemIterator();
  }

  // Inner class example
  private class ItemIterator implements Iterator<Item> {
    private Node cursor;

    public ItemIterator() {
      this.cursor = Deque.this.head;
    }

    public boolean hasNext() {
      return cursor.next != Deque.this.tail;
    }

    public Item next() {
      if(this.hasNext()) {
        cursor = cursor.next;
        Item current = cursor.item;        
        return current;
      }
      else {
        throw new NoSuchElementException();
      }
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  public static void main(String[] args) {
    Deque<Integer> dq = new Deque<>();
    int N = 4;
    Integer[] dq_arr = new Integer[N];
    for (int i = 0; i < N; i++) {
      dq.addLast(i);
    }

    for (int i = 0; i < N; i++) {
      dq_arr[i] = dq.removeFirst();
      System.out.println("dq[" + i + "] = " + dq_arr[i] + ", original[" + i + "] = " + i);
    }
  }
}
