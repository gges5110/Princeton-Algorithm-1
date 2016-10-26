import junit.framework.TestCase;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import java.util.Iterator;
import java.util.ArrayList;

public class RandomizedQueueTest extends TestCase {

    // Create new test
    public RandomizedQueueTest (String name) {
        super(name);
    }

    public void testEnqueResize() {
      RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
      rq.enqueue(1);
      rq.enqueue(2);
      rq.enqueue(3);
      rq.enqueue(4);
      rq.enqueue(5);
    }

    public void testSample() {
      RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
      rq.enqueue(1);
      rq.enqueue(2);
      rq.enqueue(3);
      rq.enqueue(4);
      rq.enqueue(5);
      ArrayList<Integer> list  = new ArrayList<>();
      for(int i = 0; i < 5; i++)
        list.add(i + 1);


      assertTrue(list.contains(rq.sample()));
      assertTrue(list.contains(rq.sample()));
      assertTrue(list.contains(rq.sample()));
    }

    public void testDeque() {
      RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
      rq.enqueue(1);
      rq.enqueue(2);
      rq.enqueue(3);
      rq.enqueue(4);
      rq.enqueue(5);
      ArrayList<Integer> list  = new ArrayList<>();
      for(int i = 0; i < 5; i++)
        list.add(i + 1);

      for(int i = 0; i < 5; i++) {
        Integer temp = rq.dequeue();
        assertTrue(list.contains(temp));
        list.remove(temp);
      }
      assertTrue(rq.isEmpty());
    }

    public void testIterator() {
      RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
      rq.enqueue(1);
      rq.enqueue(2);
      rq.enqueue(3);
      rq.enqueue(4);
      rq.enqueue(5);
      Iterator<Integer> iterator = rq.iterator();

      ArrayList<Integer> list  = new ArrayList<>();
      for(int i = 0; i < 5; i++)
        list.add(i + 1);

      int index = 5;
      while(index-- > 0) {
        // System.out.println("123");
        Integer temp = iterator.next();
        assertTrue(list.contains(temp));
        list.remove(temp);
      }
      assertTrue(!iterator.hasNext());
    }

    public static void main(String args[]) {
        String[] testCaseName = { RandomizedQueueTest.class.getName() };
        junit.textui.TestRunner.main(testCaseName);
    }
}
