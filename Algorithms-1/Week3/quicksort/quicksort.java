public class quicksort
{
    public void sort(Comparable[] a)
    {
        StdRandom.shuffle(a);
        sort(a,0,a.length-1);
    }
    public static void sort(Comparable[] a ,int lo,int hi)
    {
        if(hi<=lo) return;
        int j=partition(a,lo,hi);
        sort(a,lo,j-1);
        sort(j+1,hi);
    }
    public static int partition(Comparable[] a,int lo,int hi)
    {
        Comparable v=a[lo];
        int i=lo;
        int j=hi+1;
        while(true)
        {
            while(less(a[++i],v))
            {
                if(i==hi)
                    break;
            }
            while(less(v,a[--hi]))
            {
                if(j==lo)
                    break;
            }
            if(i>=j) break;
            exch(a,i,j);
        }
        exch(a,lo,j);
        return j;
    }
    public static void main(String[] args)
    {
        
    }
}
