package kz.nee;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testPointTriangle() {
    int lenX = 5;
    int lenY = 3;
    Point p1 = new Point(0, 0);
    Point p2 = new Point(lenX, lenY);

    double c = Math.sqrt(lenX * lenX + lenY * lenY);
    Assert.assertEquals(c, p1.distance(p2));
  }

  @Test
  public void testPointZero() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(0, 0);
    Assert.assertEquals(0, p1.distance(p2));
  }

  @Test
  public void testPointInt() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(6, 8);
    Assert.assertEquals(10, p1.distance(p2));
  }

  @Test
  public void testFormula(){
    int x1 = 10, y1 = 5, x2 = 8, y2 = 6;
    double xx = (x2 - x1) * (x2 - x1);
    double yy = (y2 - y1) * (y2 - y1);
    double l =  Math.sqrt(xx + yy);
    Assert.assertEquals(l, new Point(x1, y1).distance(new Point(x2, y2)));
  }

}
