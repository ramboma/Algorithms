import java.lang.UnsupportedOperationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int N;
    public RandomizedQueue()                 // construct an empty randomized queue
    {
        queue=(Item[])new Object[2];
    }
    private void Resize(int capacity) //resize the buffer
    {
        assert capacity>=N;
        Item[] temp = (Item[]) new Object[capacity];
        for(int i = 0;i<N;i++)
            temp[i] = queue[i];
        queue = temp; 
    }
    public boolean isEmpty()                 // is the queue empty?
    {
        return N==0;
    }
    public int size()                        // return the number of items on the queue
    {
        return N;
    }
    public void enqueue(Item item)           // add the item
    {
        
        if(item==null)
            throw new NullPointerException();
        queue[N] = item;
        N++;
        if(N==queue.length)
        {
            Resize(2*N);
        }       
    }
    public Item dequeue()                    // remove and return a random item
    {
        if(N==0)
            throw new NoSuchElementException();
        int random = StdRandom.uniform(N);
        Item item = queue[random];
        queue[random] = queue[N-1];
        queue[N-1] = null;
        N--;
        if(N>0&&N==queue.length/4)
            Resize(queue.length/2);
        return item;
    }
    public Item sample()                     // return (but do not remove) a random item
    {
        if(N==0)
            throw new NoSuchElementException();
        return queue[StdRandom.uniform(N)];
    }
    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    {
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item>
    {
        private int current = 0;
        public boolean hasNext()
        {
            return current<N;
        }
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
        public Item next()
        {
            if(queue[current]==null)
                throw new NoSuchElementException();
            Item item = queue[current];
            current++;
            return item;
        }
    }
    public static void main(String[] args)   // unit testing
    {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        StdOut.println(queue.isEmpty());
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(10);
        //iterator
        Iterator<Integer>   iterat = queue.iterator();
        while(iterat.hasNext())
        {
            StdOut.println(iterat.next());
        }
        //pop
        StdOut.println(queue.sample());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());

        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
        //Iterator<Integer> iterator=queue.iterator();
        //while(iterator.hasNext())
        //{
        //    StdOut.println(queue.next());
        //}

    }
}
