package kz.nee;

public class Point {
  int x;
  int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public double distance(Point p) {
    double xx = (p.x - this.x) * (p.x - this.x);
    double yy = (p.y - this.y) * (p.y - this.y);
    return Math.sqrt(xx + yy);
  }

}
