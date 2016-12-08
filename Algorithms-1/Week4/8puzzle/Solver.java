import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver
{
    private boolean _isSolvable=false;
    private SearchNode last;
    private int _move;
    public Solver(Board initial)           // find a solution to the initial board (using the A* algorithm)
    {
        if(initial==null)
            throw new java.lang.NullPointerException();
        SearchNode firstNode=new SearchNode(initial,0,null);
        SearchNode twinNode=new SearchNode(initial.twin(),0,null);
        MinPQ<SearchNode> pq=new MinPQ<SearchNode>();
        MinPQ<SearchNode> twinpq=new MinPQ<SearchNode>();
        pq.insert(firstNode);
        twinpq.insert(twinNode);
        while(true)
        {
            SearchNode node=pq.delMin();
            SearchNode nodeTwin=twinpq.delMin();
            if(node.board.isGoal())
            {
                last=node;
                _isSolvable=true;
               _move=last.move;
                break;
            }
            if(nodeTwin.board.isGoal())
            {
                _isSolvable=false;
                _move=-1;
                break;
            }
            enqueueSearchNOde(node,pq);
            enqueueSearchNOde(nodeTwin,twinpq);
        }
        //last=firstNode;
        //find(last,null);
    }
    private class SearchNode implements Comparable<SearchNode>
    {
        private Board board;
        private int move;
        private SearchNode preNode;
        private int priority;
        public SearchNode(Board b,int m,SearchNode pre)
        {
           board=b;
            move=m;
            preNode=pre;
            priority=board.manhattan()+move;
        }
        public int compareTo(SearchNode that)
        {
            int thisnode=this.priority;
            int thatnode=that.priority;
            if(thisnode-thatnode>0)
                return 1;
            else if(thisnode==thatnode)
                return 0;
            return -1;
        }

    }
    private void enqueueSearchNOde(SearchNode sn,MinPQ<SearchNode> pq)
        {
            for(Board b:sn.board.neighbors())
            {
               if(sn.preNode==null||!b.equals(sn.preNode.board))
               {
                       pq.insert(new SearchNode(b,sn.move+1,sn));
               }
            }
        }
    public boolean isSolvable()            // is the initial board solvable?
    {
        return _isSolvable;
    }
    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
        return _move;
    }
    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {
        Stack<Board> solution=new Stack<Board>();
        if(!_isSolvable)
            solution=null;
        else
        {
            SearchNode s=last;
            while(s!=null)
            {
                solution.push(s.board);
                s=s.preNode;
            }
        }
        return solution;
    }
    public static void main(String[] args) // solve a slider puzzle (given below)
    {
        In in=new In(args[0]);
        int N=in.readInt();
        int[][] blocks=new int[N][N];
        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++)
                blocks[i][j]=in.readInt();
        Board init=new Board(blocks);
        Solver solver=new Solver(init);
        if(!solver.isSolvable())
            StdOut.println("no solution possable");
        else
        {
            StdOut.println("number of moves="+solver.moves());
            for(Board board:solver.solution())
                StdOut.println(board);
        }
    }
}
