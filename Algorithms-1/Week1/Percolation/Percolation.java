import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{
    private boolean[][] grid;
    private int gridSize;
    private WeightedQuickUnionUF uf;
    private int virtualTop;
    private boolean[] connectedBottom;
    public Percolation(int n)
    {
        this.gridSize=n;
        this.uf=new WeightedQuickUnionUF(n*n+1);
        virtualTop=0;
        grid=new boolean[n][n];

        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                grid[i][j]=false;
        connectedBottom=new boolean[n*n+1];
        for(int i=0;i<=n*(n-1);i++)
        {
            connectedBottom[i]=false;
        }
        for(int i=n*(n-1)+1;i<=n*n;i++)
        {
            connectedBottom[i]=true;
        }
    }
    private boolean isValidIndices(int i,int j)
    {
        return (i>0&&i<=gridSize&&j>0&&j<=gridSize);
    }
    private void validateIndices(int i,int j)
    {
        if(!isValidIndices(i,j))
            throw new IndexOutOfBoundsException("Index out of bound");
    }
    private int[][] getNeighbors(int i,int j)
    {
        return new int[][]{
            {i-1,j},
            {i,j+1},
            {i+1,j},
            {i,j-1}
        };
    }
    private int getUnionFindIndex(int i,int j)
    {
        return 1+(i-1)*gridSize+(j-1);
    }
    public void open(int i,int j)
    {
        validateIndices(i,j);
        if(!isOpen(i,j))
        {
            grid[i-1][j-1]=true;
            int thisUFIndex=getUnionFindIndex(i,j);
            if(i==1)
                uf.union(thisUFIndex,virtualTop);
            for(int[] row:getNeighbors(i,j))
            {
                if(isValidIndices(row[0],row[1])&&isOpen(row[0],row[1]))
                {
                    int thatUFIndex=getUnionFindIndex(row[0],row[1]);
                    boolean connectBottom=connectedBottom[uf.find(thisUFIndex)]
                        || connectedBottom[uf.find(thatUFIndex)];
                    uf.union(thisUFIndex,thatUFIndex);
                    if(connectBottom)
                        connectedBottom[uf.find(thisUFIndex)]=true;
                }
            }
        }

    }
    public boolean isOpen(int i,int j)
    {
       return grid[i-1][j-1];
    }
    public boolean isFull(int i,int j)
    {
        validateIndices(i,j);
        return uf.connected(virtualTop,getUnionFindIndex(i,j));
    }
    public boolean percolates()
    {
        return connectedBottom[uf.find(0)];
    }
    public static void main(String[] args)
    {
        Percolation p=new Percolation(5);
        StdOut.println(p.isFull(1, 1));
        StdOut.println(p.isOpen(2, 1));
        StdOut.println(p.isFull(2,1));
        p.open(3,4);
        StdOut.println(p.isOpen(3, 4));
        StdOut.println(p.isFull(3, 4));
        StdOut.println(p.percolates());
        StdOut.println(p.isValidIndices(6,9));
    }
}
