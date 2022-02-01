package kz.nee;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RectangleTests {
  @Test
  public void testRectangle() {
    Rectangle r = new Rectangle(1, 1);
    Assert.assertEquals(r.area(), 1.0);
  }
}
