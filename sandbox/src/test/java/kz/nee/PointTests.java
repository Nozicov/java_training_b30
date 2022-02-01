package kz.nee;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
  @Test
  public void testPoint() {
    int lenX = 5;
    int lenY = 3;
    Point p1 = new Point(0, 0);
    Point p2 = new Point(lenX, lenY);

//    Для полной проверки применим независимый принцип вычесления длины гипотенузы прямоугольного треугольника
    double c = Math.sqrt(lenX * lenX + lenY * lenY);
    Assert.assertEquals(c, p1.distance(p2));
  }
}
