import App.*;
import Graphics.DepthFirstPath;
import Graphics.DepthFirstSearch;
import Graphics.Graph;
import stdlib.In;
import stdlib.StdOut;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        /*
        String file = "C://Users/Administrator//Downloads//Compressed//algs4-data//algs4-data//tinycG.txt";
        In in = new In(file);//args[0]);
        Graph G = new Graph(in);
        StdOut.println(G);

        //DepthFirstSearch search=new DepthFirstSearch(G,0);
        //if (search.count() != G.V()) StdOut.println("NOT connected");
        //else                         StdOut.println("connected");
        DepthFirstPath path=new DepthFirstPath(G,0);
        StdOut.println(path.pathTo(5));
        */
        Maze maze=new Maze();
        List<Cell> v=maze.getMazeBuffer();
        for(Cell c:v)
        {
            StdOut.print(String.format("row:%d,col:%d,value:%b,num:%d",c.getX(),c.getY(),c.isPass(), c.getCellValue()));
            StdOut.print("|");
        }
        Graphic graphic=maze.getGraph();
        Map<Integer,List<Integer>> hashMap= graphic.getAdj();
        for(Map.Entry<Integer,List<Integer>> entity:hashMap.entrySet())
        {
            StdOut.print(String.format("start:%d=>",entity.getKey()));
            StdOut.print(entity.getValue());
           StdOut.println();
        }

        StdOut.println(maze.getPath());

        NQueen nQueen=new NQueen(8);
        nQueen.SearchAllResult();

        SymbolMaze sm=new SymbolMaze("c:\\symbolMaze.txt"," ");
        Graphic g=sm.G();
        App.DepthFirstPath dfp=new App.DepthFirstPath(g,sm.GetStartIndex());
        Iterable<Integer> list=dfp.pathTo(sm.GetEndIndex());
        for(Integer i:list)
        {
            StdOut.print(String.format("index:%0,loc:%1",i,sm.name(i)));
        }

    }
}
