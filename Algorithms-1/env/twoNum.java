import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

/**
 * Created by Administrator on 2016/10/10.
 */
public class twoNum {
    public static int count(int[] a)
    {
        int cnt=0;
        for(int i=0;i<a.length;i++)
        {
            for(int j=i+1;j<a.length;j++)
            {
                    if(a[i]+a[j]==0)
                       cnt++;
            }
        }
        return cnt;
    }
    public static void main(String[] args)
    {
        int[] a= In.readInts(args[0]);
        StdOut.println(count(a));
    }
}
