import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;

import java.util.LinkedList;

public class TestUtil {
    public static Iterable<Point2D> readFromFile(String filePath) {
        if (filePath == null) {
            throw new IllegalArgumentException();
        }

        LinkedList<Point2D> list = new LinkedList<>();

        In in = new In(filePath);
        while (in.hasNextLine()) {
            String str = in.readLine();
            String[] a = str.split(" ");
            assert a.length == 2;
            list.add(new Point2D(Double.parseDouble(a[0]), Double.parseDouble(a[1])));
        }
        return list;
    }
}
