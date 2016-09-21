package App;

import Common.Queue;
import stdlib.StdOut;


import java.util.List;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Administrator on 2016/9/21.
 */
public class BreadthFirstPath {
    private boolean[] marked;
    private int[] edgeTo;
    private int count;
    private int s;
    public BreadthFirstPath (Graphic G, int s)
    {
        marked=new boolean[100];
        edgeTo=new int[100];
        this.s=s;
        bfs(G, s);
    }
    private void bfs(Graphic G,int v)
    {
        marked[v]=true;
        Queue queue=new Queue<Integer>();
        queue.enqueue(v);
        StdOut.println(String.format(">>%s", v));
        count++;
        while(!queue.isEmpty())
        {
            Integer cur=(Integer)queue.dequeue();
            List<Integer> list=G.getAdj().get(cur);
            for(Integer i :list)
            {
                if(!marked[i])
                {
                    marked[i]=true;
                    edgeTo[i]=cur;
                    queue.enqueue(i);
                }
            }
        }
    }
    public boolean hasPathTo(int w)
    {
        return marked[w];
    }
    public Iterable<Integer> pathTo(int v)
    {
       if(!hasPathTo(v))  return null;
        Stack<Integer> path=new Stack<Integer>();
        for(int x=v;x!=s;x=edgeTo[x])
        {
            path.push(x);
        }
        path.push(s);
        return path;

    }
    public boolean[] getMarked()
    {
        return marked;
    }
}
