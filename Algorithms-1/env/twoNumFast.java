import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.BinarySearch;
import java.util.Arrays;
/**
 * Created by Administrator on 2016/10/10.
 */
public class twoNumFast{
    public static int count(int[] a)
    {
        Arrays.sort(a);
        int cnt=0;
        for(int i=0;i<a.length;i++)
        {
            if(BinarySearch.rank(-a[i],a)>i)
                cnt++;
        }
        return cnt;
    }
    public static void main(String[] args)
    {
        int[] a= In.readInts(args[0]);
        StdOut.println(count(a));
    }
}
