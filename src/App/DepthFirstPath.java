package App;

import Common.Stack;
import Graphics.Graph;
import stdlib.StdOut;

/**
 * Created by Administrator on 2016/9/20.
 */
public class DepthFirstPath{
     private boolean[] marked;
    private int[] edgeTo;
    private int count;
    private int s;
    public DepthFirstPath(Graphic G, int s)
    {
        marked=new boolean[100];
        edgeTo=new int[100];
        this.s=s;
        dfs(G,s);
    }
    private void dfs(Graphic G,int v)
    {
        marked[v]=true;
        StdOut.println(String.format(">>%s", v));
        count++;
        for(int w:G.getAdj().get(v))
        {
            if(!marked[w]) {
                edgeTo[w]=v;
                dfs(G, w);
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
