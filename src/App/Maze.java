package App;
import Graphics.*;
import Graphics.DepthFirstPath;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/19.
 */
public class Maze {
    private List<Cell> MazeBuffer;
    private int start;
    private int end;
    public int[][] mz={
            {1,1,1,1,1,1,1,1,1,1},
            {1,0,1,1,1,0,0,1,1,1},
            {1,0,0,1,0,0,1,1,1,1},
            {1,1,0,0,0,1,0,1,1,1},
            {1,0,0,1,1,1,0,1,1,1},
            {1,0,1,1,1,1,0,0,1,1},
            {1,0,0,0,0,1,0,0,0,1},
            {1,0,0,1,0,0,0,1,0,1},
            {1,1,1,1,1,1,1,1,1,1}
    };
    public Maze()
    {
        InitMazeBuffer();
        start=11;
        end=78;
    }
    public void InitMazeBuffer()
    {
        MazeBuffer=new ArrayList<Cell>();

        int cellCount=0;
        for(int row=0;row<9;row++)
        {
            for(int col=0;col<10;col++)
            {
               Cell c=new Cell(row,col,cellCount);
                cellCount++;
                if(mz[row][col]==0)
                {
                    c.setPass(true);
                }
                MazeBuffer.add(c);
            }
        }
    }

    public Graphic getGraph()
    {
        Graphic graph =new Graphic();
        for(Cell c:MazeBuffer)
        {
            if(c.isPass())
            {
                //查看上下左右有没有连通
                if(mz[c.getX()-1][c.getY()]==0)
                {
                    graph.AddEdge(c.getCellValue(),c.getCellValue()-10);
                }
                if(mz[c.getX()+1][c.getY()]==0)
                {
                    graph.AddEdge(c.getCellValue(),c.getCellValue()+10);
                }
                if(mz[c.getX()][c.getY()-1]==0)
                {
                    graph.AddEdge(c.getCellValue(),c.getCellValue()-1);
                }
                if(mz[c.getX()][c.getY()+1]==0)
                {
                    graph.AddEdge(c.getCellValue(),c.getCellValue()+1);
                }
            }
        }
        return graph;
    }
    public List<Cell> getMazeBuffer()
    {
        return MazeBuffer;
    }
    public String getPath()
    {
        App.DepthFirstPath firstPath=new App.DepthFirstPath(this.getGraph(),this.start);
        App.BreadthFirstPath firstBreadthPath=new App.BreadthFirstPath(this.getGraph(),this.start);
        Iterable<Integer> lastPath=firstPath.pathTo(this.end);
        Iterable<Integer> breadtPath=firstBreadthPath.pathTo(this.end);
        String str="";
        //绘制原始maze
        for(int row=0;row<9;row++)
        {
            for(int col=0;col<10;col++)
            {
                str+=String.format("%d ",mz[row][col]);
            }
            str+="\r\n";
        }
        str+="=============================================\r\n";
        //绘制dfs路径
        for(int row=0;row<9;row++)
        {
            for(int col=0;col<10;col++)
            {
                Cell c=getCellByLoc(row,col);
                if(hasPath(lastPath,c.getCellValue())) {
                    str+="X ";
                }
                else
                {
                    str+=String.format("%d ",mz[row][col]);
                }
            }
            str+="\r\n";
        }
        str+="=============================================\r\n";
        //绘制bfs路径
        for(int row=0;row<9;row++)
        {
            for(int col=0;col<10;col++)
            {
                Cell c=getCellByLoc(row,col);
                if(hasPath(breadtPath,c.getCellValue())) {
                    str+="X ";
                }
                else
                {
                    str+=String.format("%d ",mz[row][col]);
                }
            }
            str+="\r\n";
        }
        str+="=============================================\r\n";
        //绘制深度遍历路径
        for(Integer i:lastPath)
        {
           str+=String.format("=>%d",i);
        }
        str+="\r\n=============================================\r\n";
        //绘制广度遍历路径
        for(Integer i:breadtPath)
        {
            str+=String.format("=>%d",i);
        }
        return str;
    }

    private boolean hasPath(Iterable<Integer> lastPath, int cellValue) {
        for(Integer i:lastPath)
        {
            if(i==cellValue)
                return true;
        }
        return false;
    }

    private Cell getCellByLoc(int row, int col) {
        for(Cell c:MazeBuffer)
        {
            if(c.getX()==row&&c.getY()==col)
            {
                return c;
            }
        }
        return null;
    }
}

