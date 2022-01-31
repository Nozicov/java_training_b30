package kz.nee;

public class MyFirstProgram {

  public static void main(String[] args) {
    System.out.println("Hello, world!");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " равна = " + s.area());

    Rectangle r = new Rectangle(4, 6);
    System.out.println("Площадь прямоугольника со сторонами a = " + r.a + " и b = " + r.b + " равна = " + r.area());

    Point p1 = new Point(5, 3);
    Point p2 = new Point(7, 8);

    System.out.println("Функция - Расстояние между двумя точками " +
            "Point1(" + p1.x + ":" + p1.y + ")" +
            " и " +
            "Point2(" + p2.x + ":" + p2.y + ")" +
            " равно = " + distance(p1, p2));


    System.out.println("Метод - Расстояние между двумя точками " +
            "Point1(" + p1.x + ":" + p1.y + ")" +
            " и " +
            "Point2(" + p2.x + ":" + p2.y + ")" +
            " равно = " + p1.distance(p2));
  }

  public static double distance(Point p1, Point p2) {
    double xx = (p2.x - p1.x) * (p2.x - p1.x);
    double yy = (p2.y - p1.y) * (p2.y - p1.y);
    return Math.sqrt(xx + yy);
  }

}