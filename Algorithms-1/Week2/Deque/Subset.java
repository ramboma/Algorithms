import edu.princeton.cs.algs4.StdOut; 
import edu.princeton.cs.algs4.StdIn; 
public class Subset
{
    public static void main(String[] args)
    {
        int K = Integer.parseInt(args[0]); 
        RandomizedQueue<String> queue = new RandomizedQueue<String>(); 
        while (!StdIn.isEmpty())
        {
            String s = StdIn.readString(); 
            queue.enqueue(s); 
        }
        for (int j = 0; j < K;  j++)
            StdOut.println(queue.dequeue()); 
    }
}
