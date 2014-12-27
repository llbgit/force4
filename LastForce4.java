public class LastForce4 {

  public static void main(String[] args) {

    clearConsole();

    System.out.println("Welcome to the Force 4 !");
    Game game = new Game();

    do {

      game.swapPlayers();
      System.out.println("\n Player " + game.getCurrentPlayer() 
                                      + game.getAssociatedPiece()
                                      + " pick a column! (from 1 to 7)\n\n"
                                      + game + "\n");
      int choice;

      do {
        choice = IOUtil.readInt() - 1;

        if(!game.canPlaceToken(choice))
          System.out.println("Invalid entry");

      } while (!game.canPlaceToken(choice));

      clearConsole();
      game.placeToken(choice);

    } while (!game.isGameOver());

      System.out.println(game + "\n\n" 
                         + " --> Well done Player " + game.getCurrentPlayer()
                         + " ! You win the game ! \n\n\n");
  }

  // Thank you internet !
  public final static void clearConsole() {
    System.out.print("\033[H\033[2J");
  }
}
