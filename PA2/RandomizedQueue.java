import java.util.Iterator;
import java.lang.NullPointerException;
import edu.princeton.cs.algs4.StdRandom;
import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
  private Item[] arr;
  private int data_size;
  private int default_size = 2;

  public RandomizedQueue() {
    // construct an empty randomized queue
    arr = (Item[]) new Object[default_size];
    data_size = 0;
  }

  private void resize(int capacity) {
    assert capacity >= this.data_size;
    // if (capacity < arr.length)
    //   System.out.println("shrink arr!");
    Item[] temp = (Item[]) new Object[capacity];
    for(int i = 0; i < data_size; i++) {
      temp[i] = arr[i];
    }
    arr = temp;
  }

  public boolean isEmpty() {
    // is the queue empty?
    return data_size == 0;
  }

  public int size() {
    // return the number of items on the queue
    return data_size;
  }

  public void enqueue(Item item) {
    // add the item
    if (item == null) throw new NullPointerException();
    if (data_size == arr.length - 1) resize(2 * arr.length);
    arr[data_size++] = item;
  }

  public Item dequeue() {
    // remove and return a random item
    if (data_size == 0) throw new NoSuchElementException();
    int index = StdRandom.uniform(data_size);
    Item item = arr[index];
    arr[index] = arr[data_size - 1];
    arr[data_size - 1] = null;
    data_size--;
    if (this.data_size > 0 && this.data_size == arr.length/4) this.resize(arr.length/2);
    return item;
  }

  public Item sample() {
    // return (but do not remove) a random item
    if (data_size == 0) throw new NoSuchElementException();
    return arr[StdRandom.uniform(data_size)];
  }

  public Iterator<Item> iterator() { // return an independent iterator over items in random order
   return new ItemIterator();
  }

  private class ItemIterator implements Iterator<Item> {
    private Item[] arr;
    private int data_size;

    public ItemIterator () {
      this.data_size = RandomizedQueue.this.data_size;
      this.arr = (Item[]) new Object[this.data_size];
      for (int i = 0; i < data_size; i++) {
        this.arr[i] = RandomizedQueue.this.arr[i];
      }
    }

    public boolean hasNext() {
      return data_size != 0;
    }

    public Item next() {
      if (this.hasNext()) {
        int index = StdRandom.uniform(this.data_size);
        Item item = this.arr[index];
        arr[index] = arr[this.data_size - 1];
        arr[this.data_size - 1] = null;
        data_size--;
        return item;
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
    // unit testing
  }
}
