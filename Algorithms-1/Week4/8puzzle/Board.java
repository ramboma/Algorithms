import java.util.Arrays;
import java.util.ArrayList;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;

public class Board
{
    private int[][] blocks;
    private int demain;
    private int local;
    public Board(int[][] blocks)// construct a board from an n-by-n array of blocks  (where blocks[i][j] = block in row i, column j)
    {
        this.blocks=blocks;
        demain=blocks.length;
        local=getLocal(); 
    }
    private int getLocal()
    {
        for(int row=0;row<demain;row++)
            for(int col=0;col<demain;col++)
                if(blocks[row][col]==0)
                    return row*demain+col;
        throw new java.lang.NullPointerException();
    }
    public int dimension()                 // board dimension n
    {
        return demain;
    }
    public int hamming()// number of blocks out of place
    {
        int hammingCount=0;
        for(int row=0;row<demain;row++)
            for(int col=0;col<demain;col++)
            {
                if(blocks[row][col]!=row*demain+col+1)
                    if(row==demain-1&&col==demain-1)
                    {}
                    else
                    {
                        hammingCount++;
                    }
            }
        return hammingCount;
    }
    public int manhattan()// sum of Manhattan distances between blocks and goal
    {
        int manhattanCount=0;
        for(int row=0;row<demain;row++)
            for(int col=0;col<demain;col++)
            {
                if(blocks[row][col]!=0)
                {
                    int distinct=getDistince(row,col);
                    manhattanCount+=distinct;
                }
            }
        return manhattanCount;
    }
    private int getDistince(int row,int col)
    {
        int curValue=blocks[row][col];
        int shouldRow=(curValue-1)/demain;
        int shouldCol=(curValue-1)%demain;
        int rowDis=Math.abs(shouldRow-row);
        int colDis=Math.abs(shouldCol-col);
        return rowDis+colDis;
    }
    public boolean isGoal()                // is this board the goal board?
    {
        return manhattan()==0;
    }
    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
    {
       int[][] aTwin=deepclone(blocks,demain);
       if(aTwin[0][0]*aTwin[0][1]==0)
       {
           exchange(aTwin,1,0,1,1);
       }
       else
       {
           exchange(aTwin,0,0,0,1);
       }
       return new Board(aTwin);
    }
    public boolean equals(Object y)        // does this board equal y?
    {
        if(y==this) return true;
        if(!(y instanceof Board)) return false;
        Board that=(Board)y;
        return (this.demain==that.demain)&&(Arrays.deepEquals(this.blocks,that.blocks));
        //不能用manhattan值判断相等,不准
        //Board yBoard=(Board)y;
        //if(manhattan()==yBoard.manhattan())
        //    return true;
        //return false;
    }
    public Iterable<Board> neighbors()     // all neighboring boards
    {
        ArrayList<Board> neighborList=new ArrayList<Board>();
        int xLocal=local/demain;
        int yLocal=local%demain;
        if(xLocal>0)
        {
            int[][] upBlock=deepclone(blocks,demain);
            exchange(upBlock,xLocal,yLocal,xLocal-1,yLocal);
            Board upNeighbor=new Board(upBlock);
            neighborList.add(upNeighbor); 
        }
        if(xLocal<demain-1)
        {
            int[][] downBlock=deepclone(blocks,demain);
            exchange(downBlock,xLocal,yLocal,xLocal+1,yLocal);
            Board downNeighbor=new Board(downBlock);
           neighborList.add(downNeighbor); 
        }
        if(yLocal>0)
        {
            int[][] leftBlock=deepclone(blocks,demain);
            exchange(leftBlock,xLocal,yLocal,xLocal,yLocal-1);
            Board leftNeighbor=new Board(leftBlock);
           neighborList.add(leftNeighbor); 
        }
        if(yLocal<demain-1)
        {
            int[][] rightBlock=deepclone(blocks,demain);
            exchange(rightBlock,xLocal,yLocal,xLocal,yLocal+1);
            Board rightNeighbor=new Board(rightBlock);
           neighborList.add(rightNeighbor); 
        }
        return  neighborList;
    }
    private void exchange(int[][] Neighbor,int xLocal,int yLocal,int x1Local, int y1Local)
    {
      int temp=Neighbor[xLocal][yLocal];
      Neighbor[xLocal][yLocal]=Neighbor[x1Local][y1Local];
      Neighbor[x1Local][y1Local]=temp;
    }
    private int[][] deepclone(int[][]b,int d)
    {
        int[][] copy=new int[d][d];
        for(int row=0;row<d;row++)
            for(int col=0;col<d;col++)
            {
                copy[row][col]=b[row][col];
            }
        return copy;
    }
    public String toString()               // string representation of this board (in the output format specified below)
    {
        StringBuffer sb=new StringBuffer();
        sb.append(demain+"\r\n");
        for(int row=0;row<demain;row++)
        {
            for(int col=0;col<demain;col++)
            {
                sb.append(String.format("%2d ",blocks[row][col]));
            }
            sb.append("\r\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) // unit tests (not graded)
    {
        int[][] initBlock={
            {0,1,3},
            {4,2,5},
            {7,8,6}
        };
        Board a=new Board(initBlock);
        StdOut.println(a.dimension());
        StdOut.println(a.hamming());
        StdOut.println(a.manhattan());
        StdOut.println(a);
        StdOut.println(a.twin());
        for(Board board:a.neighbors())
        {
            StdOut.println(board);
        }

    }

}
