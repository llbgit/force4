public class Game {

  // NB: Player 1 is idPlayer 0 and play with the dots o
  //            2             1                   crosses +

  private final int WIDTH = 7;
  private final int HEIGHT = 6;

  private int idPlayer;
  private Piece[][] grid = new Piece[HEIGHT][WIDTH];

  private Coordinate lastMove;

  public Game() {
    this.idPlayer = 1;
    this.lastMove = null;

    for (int row = 0; row < grid.length; row++) {

      assert grid[row].length == grid[0].length;
      for (int col = 0; col < grid[0].length; col++) {

        this.grid[row][col] = Piece.EMPTY;

      }
    }
  }

  /*
   * Players utils 
   */
  public void swapPlayers() {
    idPlayer = (idPlayer + 1) % 2;
    assert (idPlayer == 0||idPlayer == 1);
  }
 
  public String getAssociatedPiece() {
    return ((idPlayer == 0) ? "(o)" : "(+)");
  }

  public int getCurrentPlayer() {
    return idPlayer + 1;
  }


  /*
   * Tokens check 
   */
  public boolean canPlaceToken(int x) {

    return getCoordFromCol(x) != null;
  }

  public boolean isInBounds(Coordinate c) {

    return c.getX() >= 0 &&
           c.getY() >= 0 &&
           c.getX() <  HEIGHT &&
           c.getY() <  WIDTH;
  }

  public void placeToken(int x) {

    assert canPlaceToken(x): "Can't plance token!";

    Coordinate c = getCoordFromCol(x);

    if (idPlayer == 0) {
      grid[c.getX()][c.getY()] = Piece.DOT;

    } else {
      grid[c.getX()][c.getY()] = Piece.CROSS;

    }

    lastMove = c;
  }

  private Coordinate getCoordFromCol(int x) {

    if (x < 0 || grid[0].length <= x) {
      return null;
    }

    for (int row = 0; row < grid.length; row++) {

     if (grid[row][x] == Piece.EMPTY) {
       return new Coordinate(row, x);
     }

   }

   //System.out.println("Column is already full...");
   return null;
  }


  /*
   * Game over check
   */
  public boolean isGameOver() {

    Direction[] dirs = Direction.getDirs();

    for (Direction dir : dirs) {
      
      int count = 1;

      Coordinate c = lastMove;
      
      //System.out.println(c + " | " + dir); 

      while (hasTokenInDirection(c, dir)) {
        c = Coordinate.addCoordinates(c, dir.getCoordinate());
        count++;
        //System.out.println(c + " | " + dir + " with count "+ count); 
      }

      Direction opposite = dir.getOppositeDir();
      c = lastMove;
      //System.out.println(c + " | " + opposite); 

      while (hasTokenInDirection(c, opposite)) {
        c = Coordinate.addCoordinates(c, opposite.getCoordinate());
        count++;
        //System.out.println(c + " | " + opposite + " with count "+ count); 
      } 

      if (count >= 4) {
        return true;
      }
    }

    return false;
  }

  private boolean hasTokenInDirection(Coordinate c, Direction dir) {
    
    Coordinate next = Coordinate.addCoordinates(c, dir.getCoordinate());

    if (!isInBounds(next))
      return false;

    if (idPlayer == 0 && grid[next.getX()][next.getY()] == Piece.DOT)
      return true;

    if (idPlayer == 1 && grid[next.getX()][next.getY()] == Piece.CROSS)
      return true;

    return false;
  }

  /*
   * Rendering utils
   */
  public String toString() {

    StringBuilder sb = new StringBuilder();
    sb.append("\n\t -----------------------------");

    for (int row = 0; row < grid.length; row++) {

      sb.append("\n\t |");

      for (int col = 0; col < grid[0].length; col++) {

        switch (grid[HEIGHT - 1 - row][col]) {
          case DOT:
            sb.append(" o ");
            break;
          case CROSS:
            sb.append(" + ");
            break;
          case EMPTY:
            sb.append("   ");
            break;
        }

        sb.append("|");
      }
      sb.append("\n\t |---|---|---|---|–––|–––|---|");
    }

    sb.append("\n\t +-1-+-2-+-3-+-4-+-5-+-6-+-7-+\n");

    return sb.toString();
  }
}
