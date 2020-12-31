import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class BruteCollinearPointsHashMap {
  private final HashMap<Double, HashSet<Point>> map = new HashMap<>();

  private void checkInput(Point[] points) {
    if (points == null) throw new NullPointerException();

    HashSet<Point> set = new HashSet<>();
    for(Point p: points) {
      if (p == null) throw new NullPointerException();
      if(!set.add(p)) throw new IllegalArgumentException();
    }
  }

  public BruteCollinearPointsHashMap(Point[] points) {
     checkInput(points);

     // finds all line segments containing 4 points
     Point p1, p2, p3, p4;
     for (int index1 = 0; index1 < points.length; index1++) {
       p1 = points[index1];
       for (int index2 = index1 + 1; index2 < points.length; index2++) {
         p2 = points[index2];
         double slope12 = p1.slopeTo(p2);
         for (int index3 = index2 + 1; index3 < points.length; index3++) {
           p3 = points[index3];
           double slope13 = p1.slopeTo(p3);
           for (int index4 = index3 + 1; index4 < points.length; index4++) {
             p4 = points[index4];
             double slope14 = p1.slopeTo(p4);

             if(slope12 == slope13 && slope13 == slope14) {
               // 4 points are collinear
               HashSet<Point> set;
               if (map.get(slope12) == null)  set = new HashSet<>();
               else                           set = map.get(slope12);

               set.add(p1);
               set.add(p2);
               set.add(p3);
               set.add(p4);
               map.put(slope12, set);
             }
           }
         }
       }
     }
   }

   public int numberOfSegments() {
     // the number of line segments
     return map.size();
   }

   public LineSegment[] segments() {
     // the line segments
     LineSegment[] arr = new LineSegment[map.size()];
     int index = 0;

     for (Double key: map.keySet()) {
       HashSet<Point> pointSet = map.get(key);
       Point max = Collections.max(pointSet);
       Point min = Collections.min(pointSet);
       arr[index++] = new LineSegment(min, max);
     }
     return arr;
   }

   public static void main(String[] args) {
   }
}
