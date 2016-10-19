package Lesson1;
import edu.princeton.cs.algs4.StdDraw;
/**
 * Created by Administrator on 2016/10/10.
 */
public class testInAndDraw {
   public static void main(String[] args)
   {
       StdDraw.setScale(-1,1);
       StdDraw.clear(StdDraw.BLACK);

       StdDraw.setPenColor(StdDraw.WHITE);
       StdDraw.square(-10,10,10);
       StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(0, +0.95, "Hello, world! This is a test Java program.");
        StdDraw.text(0, -0.95, "Close this window to finish the installation.");
        StdDraw.setPenColor(StdDraw.BOOK_BLUE);
        StdDraw.filledCircle(0, 0, 0.9);
       StdDraw.setPenColor(StdDraw.WHITE);
       StdDraw.text(0,0,"2");
   }
}
