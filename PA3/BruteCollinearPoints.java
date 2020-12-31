import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
  private final ArrayList<LineSegment> list = new ArrayList<>();
  private Point[] mutable_points;

  private void checkInput(Point[] points) {
    if (points == null) {
      throw new NullPointerException();
    }

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

  public BruteCollinearPoints(Point[] points) {
    checkInput(points);
     // finds all line segments containing 4 points
     Point p1, p2, p3, p4;
     for (int index1 = 0; index1 < points.length - 3; index1++) {
       p1 = mutable_points[index1];
      //  System.out.println("Origin = " + p1.toString());
       for (int index2 = index1 + 1; index2 < points.length - 2; index2++) {
         p2 = mutable_points[index2];
         double slope12 = p1.slopeTo(p2);
         for (int index3 = index2 + 1; index3 < points.length - 1; index3++) {
           p3 = mutable_points[index3];
           double slope13 = p1.slopeTo(p3);
          //  System.out.println("slope12 = " + slope12 + ", slope13 = " + slope13);
          //  if (slope12 != slope13) {
          //    break;
          //  }

           for (int index4 = index3 + 1; index4 < points.length; index4++) {
             p4 = mutable_points[index4];
             double slope14 = p1.slopeTo(p4);
            //  System.out.println("p1 = " + p1.toString() + ", p2 = " + p2.toString() + ", p3 = " + p3.toString() + ", p4 = " + p4.toString());
             if(slope13 == slope14 && slope12 == slope13) {
               // 4 points are collinear
               list.add(new LineSegment(mutable_points[index1], mutable_points[index4]));
             }
           }
         }
       }
     }
   }

   public int numberOfSegments() {
     // the number of line segments
     return list.size();
   }

   public LineSegment[] segments() {
     // the line segments
     LineSegment[] arr = new LineSegment[list.size()];
     return list.toArray(arr);
   }

   public static void main(String[] args) {
   }
}
