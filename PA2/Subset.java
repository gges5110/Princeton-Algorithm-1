import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {
  public static void main(String[] args) {
    int k = 0;
    if (args.length == 0)
      return;
    else
      k = Integer.parseInt(args[0]);

    RandomizedQueue<String> rq = new RandomizedQueue<>();
    while (!StdIn.isEmpty())
      rq.enqueue(StdIn.readString());
    while (k-- > 0)
      StdOut.println(rq.dequeue());
  }
}
