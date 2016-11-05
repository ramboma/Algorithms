import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class FastCollinearPoints {
    private ArrayList<LineSegment> list=new ArrayList<LineSegment>();
    private Map<String,Integer> savedLine=new HashMap<String,Integer>();
    public FastCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {

        if(points==null)
            throw new java.lang.NullPointerException();
        Point[] copy=new Point[points.length];
        Point[] temp=new Point[points.length];
        
        for(int i=0;i<points.length;i++)
        {
            copy[i]=points[i];
            temp[i]=points[i];
        }
        Arrays.sort(copy);
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
                }
                else
                {
                if(count>3)
                {
                    saveLineMap(String.format("%d,%d,%d,%d",copy[p].X(),copy[p].Y(),temp[q].X(),temp[q].Y()),count);
                    count=0;
                }
                }
                
            }
        }
    }
    private void saveLineMap(String key,Integer count)
    {
        if(!savedLine.containsKey(key))
        {
            savedLine.put(key,count);
        }
    }
    public int numberOfSegments()        // the number of line segments
    {
        return list.size();
    }
    public LineSegment[] segments()                // the line segments
    {
        LineSegment[] ls=new LineSegment[savedLine.keySet().size()];
        int index=0;
        for(String key: savedLine.keySet())
        {
            String[] split=key.split(",");
            LineSegment line=new LineSegment(
                    new Point(Integer.parseInt(split[0]),
                                Integer.parseInt(split[1])),
                    new Point(Integer.parseInt(split[2]),
                                Integer.parseInt(split[3])));
            ls[index]=line;
            index++;
        }
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
        FastCollinearPoints  fc=new FastCollinearPoints (points);
        for (LineSegment segment : fc.segments()) 
        {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
