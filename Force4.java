public class Force4 {

  public static void main(String[] args) {

    clearConsole();

    System.out.println("  Welcome to the Force 4 !");
    
    // Selecting Game mode
    int nbPlayers;
    System.out.println("\n  GAME MODE - How many players? \n" 
                     + "   1 - (playing against AI) \n"
                     + "   2 - (playing against each other)");
    
    while (true) {
      nbPlayers = IOUtil.readInt();
    
      if (nbPlayers == 1 || nbPlayers == 2) {
          break;
      }

      System.out.println("  /!\\ Invalid selection. Choose between 1 and 2 players");
    }

    clearConsole();
    
    switch (nbPlayers) {
      case 1:
        playSinglePlayerGame();
        break;
      case 2:
        playTwoPlayersGame();
        break;
    }
  }

  
  private static void playSinglePlayerGame() {
      
    System.out.println(" ENTERING SINGLE PLAYER MODE...\n\n"
                     + " You get to play first, AI will be next.\n");
    Game game = new Game();

    do {

      game.swapPlayers();
      int choice;
      
      if (game.getCurrentPlayer() == 2) {
        // TODO: AI's turn...
      
        choice = game.getAIChoice();

        assert game.canPlaceToken(choice) : "Illegal AI Choice";

        System.out.println("\n AI " + game.getAssociatedPiece()
                         + " chosed column " + choice + ".\n\n");

      } else {
        System.out.println("\n Your turn... "
                         + " Place a " + game.getAssociatedPiece()
                         + " in a column! (from 1 to 7)\n\n"
                         + game + "\n");
  
        do {
          choice = IOUtil.readInt() - 1;
  
          if(!game.canPlaceToken(choice))
            System.out.println("Invalid entry");
  
        } while (!game.canPlaceToken(choice));
        
        clearConsole();
      }

      game.placeToken(choice);

    } while (!game.isGameOver());

      System.out.println(game + "\n\n");

      if (game.getCurrentPlayer() == 1) {
        System.out.println(" --> You win ! Well done you defeated the AI!\n\n\n");
      
      } else {
        System.out.println(" --> You loose... AI was smarter than you! \n\n\n");

      }
  
  }

  
  private static void playTwoPlayersGame() {

    System.out.println(" ENTERING 2 PLAYERS MODE..\n\n"); 
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
