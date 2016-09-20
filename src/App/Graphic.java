package App;

import Common.Bag;

import java.util.*;

/**
 * Created by Administrator on 2016/9/20.
 */
public class Graphic {
    private int V;
    private int E;
    private HashMap<Integer,List<Integer>> adj;
    public Graphic()
    {
        V=0;
        E=0;
        adj=new HashMap<Integer, List<Integer>>() ;
    }
    public void AddEdge(int v,int e)
    {
        if(adj.containsKey(v))
        {
            adj.get(v).add(e);
        }
        else
        {
            ArrayList temp=new ArrayList();
            temp.add(e);
            adj.put(v,temp);
        }
    }
    public HashMap<Integer,List<Integer>> getAdj()
    {
        return adj;
    }
}
