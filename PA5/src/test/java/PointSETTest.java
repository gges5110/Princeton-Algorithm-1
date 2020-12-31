import edu.princeton.cs.algs4.Point2D;
import junit.framework.TestCase;

import java.util.Random;

public class PointSETTest extends TestCase {

    public void testIsEmpty() {
        PointSET set = new PointSET();
        assertTrue(set.isEmpty());

        Iterable<Point2D> iterable = TestUtil.readFromFile("src/test/resources/input10.txt");
        for (Point2D p: iterable) {
            set.insert(p);
        }
        assertFalse(set.isEmpty());
    }

    public void testSize() {
        PointSET set = new PointSET();
        Iterable<Point2D> iterable = TestUtil.readFromFile("src/test/resources/input10.txt");
        for (Point2D p: iterable) {
            set.insert(p);
        }
        assertEquals(10, set.size());
    }

    public void testInsert() {

    }

    public void testContains() {
        PointSET set = new PointSET();
        Iterable<Point2D> iterable = TestUtil.readFromFile("src/test/resources/input10.txt");
        for (Point2D p: iterable) {
            set.insert(p);
            assertTrue(set.contains(p));
        }
    }

    public void testContainsFalse() {
        PointSET set = new PointSET();
        Iterable<Point2D> iterable = TestUtil.readFromFile("src/test/resources/input10.txt");
        for (Point2D p: iterable) {
            set.insert(p);
        }

        Point2D randomPoint = new Point2D(new Random().nextDouble(), new Random().nextDouble());
        assertFalse(set.contains(randomPoint));
    }

    public void testDraw() {
        PointSET set = new PointSET();
        Iterable<Point2D> iterable = TestUtil.readFromFile("src/test/resources/input10.txt");
        for (Point2D p: iterable) {
            set.insert(p);
        }
        set.draw();
    }
}