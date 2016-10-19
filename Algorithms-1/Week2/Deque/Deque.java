import java.lang.UnsupportedOperationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;
public class Deque<Item> implements Iterable<Item>
{
    private Node<Item> head;
    private Node<Item> tail;
    private int N;
    private class Node<Item>
    {
        Item item;
        Node<Item> next;
        Node<Item> pre;
    }
   public Deque()
   {
       head = new Node<Item>();
       tail = new Node<Item>();
       head.next=tail;
       tail.pre=head;
       N = 0;
   }
    public boolean isEmpty()
    {
        if(N==0) return true;
        return false;
    }
    public int size()
    {
        return N;
    }
    public void addFirst(Item item)
    {
        if(item==null)
            throw new NullPointerException();
        Node<Item> oldFirst=head.next;
        Node<Item> first = new Node<Item>();
        first.item = item;
        first.pre=head;
        first.next=oldFirst;
        oldFirst.pre=first;
        head.next=first;
        N++;
    }
    public void addLast(Item item)
    {
        if(item==null)
            throw new NullPointerException();
        
        Node<Item> oldlast = tail.pre;
        Node<Item> last = new Node<Item>();
        last.item = item;
        last.next=tail;
        last.pre=oldlast;
        oldlast.next=last;
        tail.pre=last;

        N++;

    }
    public Item removeFirst()
    {
        if(N==0)
            throw new NoSuchElementException();
        Item item=head.next.item;
        head.next=head.next.next;
        head.next.pre=head;

        N--;
       return  item;
    }
    public Item removeLast()
    {
        if(N==0)
            throw new NoSuchElementException();
        Item item=tail.pre.item;
        tail.pre=tail.pre.pre;
        tail.pre.next=tail;
        N--;
        return item;
    }
    public Iterator<Item> iterator()
    {
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item>
    {
        private Node<Item> current = head;
        public boolean hasNext()
        {
            return current.next != null;
        }
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
        public Item next()
        {
            if(current.next==null)
                throw new NoSuchElementException();
            Item item = current.next.item;
            current = current.next;
            return item;
        }
    }
    public static void main(String[] args)
    {
        Deque<Integer> list = new Deque<Integer>();
        //test3 calls to addfirst(),removeLast(), and isempty
        list.addFirst(5);
        list.removeLast();
        StdOut.println(list.isEmpty());
        //test4 add,add remove
        list.addFirst(0);
        list.addFirst(1);
        list.removeLast();
        StdOut.println(list.removeLast()==1);

        list.addFirst(2);
        list.addFirst(1);
        StdOut.println(list.isEmpty());
        StdOut.println(list.size());
        list.addLast(3);
        list.addLast(4);
        list.removeLast();
        StdOut.println(list.size());
        Iterator<Integer>   iterat = list.iterator();
        while(iterat.hasNext())
        {
            StdOut.println(iterat.next());
        }
    }
}
