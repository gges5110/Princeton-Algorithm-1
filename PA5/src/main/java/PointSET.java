import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

// Corner cases.  Throw an IllegalArgumentException if any argument is null.
public class PointSET {
    private final TreeSet<Point2D> set;

    /**
     * construct an empty set of points
     */
    public PointSET() {
        set = new TreeSet<>();
    }

    /**
     * @return is the set empty?
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * @return number of points in the set
     */
    public int size() {
        return set.size();
    }

    /**
     * add the point to the set (if it is not already in the set)
     * should support in time proportional to the logarithm of the number of points in the set in the worst case
     * @param p
     */
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        set.add(p);
    }

    /**
     * @param p
     * @return does the set contain point p?
     */
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        return set.contains(p);
    }

    /**
     * draw all points to standard draw
     */
    public void draw() {
        for (Point2D point: set) {
            point.draw();
        }
    }

    /**
     * @param rect
     * @return all points that are inside the rectangle (or on the boundary)
     */
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }
        List<Point2D> list = new LinkedList<>();

        for (Point2D point: set) {
            if (rect.contains(point)) {
                list.add(point);
            }
        }
        return list;
    }

    /**
     * @param p
     * @return a nearest neighbor in the set to point p; null if the set is empty
     */
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        if (isEmpty()) {
            return null;
        }

        Point2D nearest = set.first();

        for (Point2D point: set) {
            if (p.distanceTo(point) < p.distanceTo(nearest)) {
                nearest = point;
            }
        }
        return nearest;
    }
}