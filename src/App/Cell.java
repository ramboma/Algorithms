package App;

/**
 * Created by Administrator on 2016/9/20.
 */
public class Cell
{
    private int CellValue;
    private int X;
    private int Y;
    private boolean Pass;
    public Cell(int x,int y,int cellValue)
    {
        this.X=x;
        this.Y=y;
        this.CellValue=cellValue;
        Pass=false;
    }
    public int getX() {
        return X;
    }
    public void setX(int x) {
        X = x;
    }
    public int getY() {
        return Y;
    }
    public void setY(int y) {
        Y = y;
    }

    public int getCellValue() {
        return CellValue;
    }

    public boolean isPass() {
        return Pass;
    }

    public void setPass(boolean pass) {
        Pass = pass;
    }
}
