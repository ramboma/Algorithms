import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Arrays;
import java.util.ArrayList;
public class Board
{
    private int[][] blocks;
    private int demain;
    public Board(int[][] blocks)// construct a board from an n-by-n array of blocks  (where blocks[i][j] = block in row i, column j)
    {
        this.blocks=blocks;
        demain=blocks.demanion;
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
                int distinct=getDistince(row,col);
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
    {}
    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
    {}
    public boolean equals(Object y)        // does this board equal y?
    {}
    public Iterable<Board> neighbors()     // all neighboring boards
    {}
    public String toString()               // string representation of this board (in the output format specified below)
    {}

    public static void main(String[] args) // unit tests (not graded)
    {}

}
