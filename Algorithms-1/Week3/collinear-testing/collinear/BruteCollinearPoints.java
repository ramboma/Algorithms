import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Arrays;
import java.util.ArrayList;
public class BruteCollinearPoints {
    private ArrayList<LineSegment> list=new ArrayList<LineSegment>();
    public BruteCollinearPoints (Point[] points)    // finds all line segments containing 4 points
    {

        if(points==null)
            throw new java.lang.NullPointerException();
        Point[] copy=new Point[points.length];
        for(int i=0;i<points.length;i++)
            copy[i]=points[i];
        Arrays.sort(copy);
        for(int p=0;p<copy.length-3;p++)
            for(int q=p+1;q<copy.length-2;q++)
                for(int r=q+1;r<copy.length-1;r++)
                    for(int s=r+1;s<copy.length;s++)
                    {
                        double a=copy[p].slopeTo(copy[q]);
                        double b=copy[p].slopeTo(copy[r]);
                        double c=copy[p].slopeTo(copy[s]);
                        if(a==b&&a==c)
                        {
                            list.add(new LineSegment(copy[p],copy[s])); 
                        }
                    }

    }
    public int numberOfSegments()        // the number of line segments
    {
        return list.size();
    }
    public LineSegment[] segments()                // the line segments
    {
        LineSegment[] ls=new LineSegment[list.size()];
        for(int i=0;i<list.size();i++)
            ls[i]=list.get(i);
        return ls;
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
        BruteCollinearPoints bc=new BruteCollinearPoints(points);
        for (LineSegment segment : bc.segments()) 
        {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
