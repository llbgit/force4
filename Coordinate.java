public class Coordinate {

  private int x;
  private int y;

  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public static Coordinate addCoordinates(Coordinate a, Coordinate b) {
    return new Coordinate(a.getX() + b.getX(), a.getY() + b.getY());
  }

  // Rendering
  public String toString() {
    return "(" + x + ", " + y + ")";
  }
}
