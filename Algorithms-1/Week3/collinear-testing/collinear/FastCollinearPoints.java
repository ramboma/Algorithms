import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class FastCollinearPoints {
    private ArrayList<LineSegment> list=new ArrayList<LineSegment>();
    public FastCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {

        if(points==null)
            throw new java.lang.NullPointerException();
        Point[] copy=points.clone();
        Point[] temp=points.clone();
        Arrays.sort(copy);
        if(hasDuplicate(copy))
            throw new java.lang.IllegalArgumentException();
        for(int p=0;p<copy.length;p++)
        {
            Arrays.sort(temp,copy[p].slopeOrder());
            int count=0;
            for(int q=0;q<temp.length-1;q++)
            {
                double a=copy[p].slopeTo(temp[q]);
                double b=copy[p].slopeTo(temp[q+1]);
                if(a==b)
                {
                    count++;
                    if(count>=2&&q>=temp.length-2)
                    {
                        //if(copy[p].compareTo(temp[q+1])==-1)
                        list.add(new LineSegment(copy[p],temp[q+1]));
                    }
                }
                else
                {
                    if(count>=2)
                    {
                        //if(copy[p].compareTo(temp[q])==-1)
                            list.add(new LineSegment(copy[p],temp[q]));
                    }
                    count=0;
                }
            }
        }
    }
   private boolean hasDuplicate(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                return true;
            }
        }
        return false;
    } 
    public int numberOfSegments()        // the number of line segments
    {
        return list.size();
    }
    public LineSegment[] segments()                // the line segments
    {
        return list.toArray(new LineSegment[list.size()]);
    }
    public static void main(String[] args)
    {
        In in=new In(args[0]);
        int N=in.readInt();
        Point[] points=new Point[N];
        for(int i=0;i<N;i++)
        {
            int x=in.readInt();
            int y=in.readInt();
            points[i]=new Point(x,y);
        }
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
        FastCollinearPoints  fc=new FastCollinearPoints (points);
        StdOut.println(String.format("segments=numerOfSegments,%b",fc.numberOfSegments()==fc.segments().length));
        for (LineSegment segment : fc.segments()) 
        {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
    
}
