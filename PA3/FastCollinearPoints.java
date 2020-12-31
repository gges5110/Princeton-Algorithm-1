import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FastCollinearPoints {
  private List<LineSegment> lineSegmentList = new ArrayList<>();
  private Point[] mutable_points;

  private void checkInput(Point[] points) {
    if (points == null) {
      throw new NullPointerException();
    }
    // finds all line segments containing 4 or more points
    mutable_points = new Point[points.length];
    for (int i = 0; i < points.length; i++) {
      if (points[i] == null) {
        throw new NullPointerException();
      }
      mutable_points[i] = points[i];
    }
    Arrays.sort(mutable_points);
    for (int i = 0; i < points.length - 1; i++) {
      if (mutable_points[i].compareTo(mutable_points[i + 1]) == 0) {
        throw new IllegalArgumentException();
      }
    }
  }

  public FastCollinearPoints(Point[] points) {
    checkInput(points);

    for (Point origin: points) {
      // sort the points according to their slope with respect to points[i]
      Arrays.sort(mutable_points, origin.slopeOrder());

      // First point must be Double.NEGATIVE_INFINITY (self), start from second point
      ArrayList<Point> same_slope_points = new ArrayList<>();
      Double slope = 0.0, prevSlope = Double.NEGATIVE_INFINITY;

      for (int j = 1; j < mutable_points.length; j++) {
        slope = origin.slopeTo(mutable_points[j]);
        if (origin.slopeTo(mutable_points[j]) == prevSlope) {
          same_slope_points.add(mutable_points[j]);
        }
        else {
          if (same_slope_points.size() >= 3) {
            same_slope_points.add(origin);
            Point min = Collections.min(same_slope_points);
            Point max = Collections.max(same_slope_points);
            if (min == origin) {
              LineSegment ls = new LineSegment(min, max);
              lineSegmentList.add(ls);
            }
          }
          same_slope_points.clear();
          same_slope_points.add(mutable_points[j]);
        }
        prevSlope = slope;
      }

      // Dealing with remaining points
      if (same_slope_points.size() >= 3) {
        same_slope_points.add(origin);
        Point min = Collections.min(same_slope_points);
        Point max = Collections.max(same_slope_points);
        if (min == origin) {
          LineSegment ls = new LineSegment(min, max);
          lineSegmentList.add(ls);
        }
      }
    }
  }

  public int numberOfSegments() {
    // the number of line segments
    return lineSegmentList.size();
  }

  public LineSegment[] segments() {
    // the line segments
    return lineSegmentList.toArray(new LineSegment[lineSegmentList.size()]);
  }
}
