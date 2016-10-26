import junit.framework.TestCase;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import java.util.Iterator;


public class DequeTest extends TestCase {

    // Create new test
    public DequeTest (String name) {
        super(name);
    }

    public void testAddFirst() {
      Deque<Integer> dq = new Deque<Integer>();
      Integer i = 1;
      dq.addLast(1);
      dq.addLast(2);
      assertEquals(dq.removeFirst(), (Integer) 1);
      assertEquals(dq.removeFirst(), (Integer) 2);
      assertTrue(dq.isEmpty());
    }

    public void testIsEmpty() {
      Deque<Integer> dq = new Deque<Integer>();
      Integer i = 1;
      dq.addLast(1);
      dq.addLast(2);
      assertEquals(dq.removeFirst(), (Integer) 1);
      assertEquals(dq.removeFirst(), (Integer) 2);
      assertTrue(dq.isEmpty());
      dq.addLast(1);
      assertFalse(dq.isEmpty());
    }

    public void testIterator() {
      Deque<Integer> dq = new Deque<Integer>();
      Integer[] i = {1, 2};
      dq.addLast(i[0]);
      dq.addLast(i[1]);

      Iterator<Integer> iterator = dq.iterator();
      int index = 0;

      while(iterator.hasNext()) {
        // System.out.println("next = " + iterator.next() + ", original = " + i[index++]);

        assertEquals(iterator.next(), i[index++]);
      }
      // assertEquals(dq.removeFirst(), (Integer) 1);
      // assertEquals(dq.removeFirst(), (Integer) 2);

    }

    public void testMultipleIterators() {
      Deque<Integer> dq = new Deque<Integer>();
      Integer[] i = {1, 2};
      dq.addLast(i[0]);
      dq.addLast(i[1]);

      Iterator<Integer> iterator1 = dq.iterator();
      Iterator<Integer> iterator2 = dq.iterator();
      int index = 0;

      while (iterator1.hasNext())
        assertEquals(iterator1.next(), i[index++]);
      index = 0;
      while (iterator2.hasNext())
        assertEquals(iterator2.next(), i[index++]);
    }

    public void testAddLastRandom() {
      // Construct int array from random
      int N = 8;
      Integer[] arr = new Integer[N];
      Integer[] dq_arr = new Integer[N];
      Deque<Integer> dq = new Deque<>();
      for (int i = 0; i < N; i++) {
        arr[i] = StdRandom.uniform(N);
        // arr[i] = i;
        dq.addLast(arr[i]);
      }

      for (int i = 0; i < N; i++) {
        dq_arr[i] = dq.removeFirst();
        // System.out.println("dq[" + i + "] = " + dq_arr[i] + ", original[" + i + "] = " + arr[i]);
        assertEquals(arr[i], dq_arr[i]);
      }
      assertTrue(dq.isEmpty());
      // assertEquals(arr, dq_arr);
    }


    public static void main(String args[]) {
        String[] testCaseName = { DequeTest.class.getName() };
        junit.textui.TestRunner.main(testCaseName);
    }
}
