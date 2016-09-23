package App;

import stdlib.StdOut;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/22.
 */
public class NQueen {
    //定义棋盘
    private int num_queens;
    int[] board;
    //定义未知量
    List<int[]> result;

    public NQueen(int n)
    {
        num_queens=n;
        board=new int[num_queens];
        result=new ArrayList<int[]>();
    }
    public void SearchAllResult()
    {
        putChess(0);
    }
    //放置第row行的皇后
    private void putChess(int row)
    {
        //递归退出条件
        if(row>=num_queens)
        {
            //输出结果
            for(int i=0;i<num_queens;i++)
            {
                StdOut.print(String.format("%d ",board[i]));
            }
            StdOut.println();
            StdOut.println("========");
        }
        else {
            for(int col=0;col<num_queens;col++)
            {
               board[row]=col;

                //如果满足条件，那么说明棋子没问题,放下一个
                if (!constraint(row)) {
                    putChess(row + 1);
                }
            }
        }
    }
    private boolean constraint(int row)
    {
       for(int i=0;i<row;i++)
       {
           if((board[row]==board[i])||
                   Math.abs(i-row)==Math.abs(board[row]-board[i]))
           {
               return true;
           }
       }
        return false;
    }
}
