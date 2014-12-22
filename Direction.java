public enum Direction {

  N  ( 0,  1),
  NW (-1,  1),
  W  (-1,  0),
  SW (-1, -1),
  S  ( 0, -1),
  SE ( 1, -1),
  E  ( 1,  0),
  NE ( 1,  1);

  private int xo;
  private int yo;

  private Direction(int xo, int yo) {
    this.xo = xo;
    this.yo = yo;
  }

  public static Direction[] getDirs() {
    Direction[] dirs = new Direction[4];

    dirs[0] = Direction.N;
    dirs[1] = Direction.NW;
    dirs[2] = Direction.W;
    dirs[3] = Direction.SW;
    
    return dirs;
  }

  public Direction getOppositeDir() {
  
    switch(this) {
      case N:  return Direction.S;
      case NW: return Direction.SE;
      case W:  return Direction.E;
      case SW: return Direction.NE;
      case S:  return Direction.N;
      case SE: return Direction.NW;
      case E:  return Direction.W;
      case NE: return Direction.SW;
    
    }

    return null;
  }
  
  public int getXo() {
    return xo;
  }

  public int getYo() {
    return yo;
  }

  public Coordinate getCoordinate() {
    return new Coordinate(xo, yo);
  }

  // Rendering
  public String toString() {
    return super.toString() + " (xo = " + xo + ", yo = " + yo + ")"; 
  }
}
