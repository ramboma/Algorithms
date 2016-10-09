package App;

import edu.princeton.cs.algs4.*;
/**
 * Created by Administrator on 2016/10/8.
 */
public class SymbolMaze {
    //迷宫起点和终点
    private String Start;
    private String End;
    //两个映射列表
    private ST<String,Integer> st;//字符串 索引号
    private String[] keys;//索引号 字符串
    private Graphic graphic;

   public SymbolMaze(String stream,String sp)
   {
       st=new ST<String, Integer>();
       In in=new In(stream);
       String[] startAndRear=in.readLine().split(sp);
       Start=startAndRear[0];
       End=startAndRear[1];
       while(in.hasNextLine())
       {
           String[] split=in.readLine().split(sp);
           for(int i=0;i<split.length;i++)
           {
               if(!st.contains(split[i]))
               {
                   st.put(split[i],st.size());
               }
           }
       }
       keys=new String[st.size()];
       for(String symbol:st.keys())
       {
          keys[st.get(symbol)]=symbol;
       }

       graphic=new Graphic();
       in=new In(stream);
       while(in.hasNextLine())
       {
           String[] split=in.readLine().split(sp);
           int p=st.get(split[0]);
           for(int i=1;i<split.length;i++)
           {
               int q=st.get(split[i]);
               graphic.AddEdge(p,q);
           }
       }
   }
    public boolean Contains(String key)
    {
       return st.contains(key);
    }
    public int Index(String key)
    {
        return st.get(key);
    }
    public String name(int index)
    {
        return keys[index];
    }
    public Graphic G()
    {
        return graphic;
    }
    public int GetStartIndex()
    {
        return st.get(Start);
    }
    public int GetEndIndex()
    {
        return st.get(End);
    }



}
