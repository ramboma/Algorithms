import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import java.util.Arrays;
/**
 * Created by Administrator on 2016/10/10.
 */
public class threeNumFast {
    public static int count(int[] a)
    {
        int cnt=0;
        Arrays.sort(a);
        for(int i=0;i<a.length;i++)
        {
            for(int j=i+1;j<a.length;j++)
            {
            if(BinarySearch.rank(-a[i]-a[j],a)>j)
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
