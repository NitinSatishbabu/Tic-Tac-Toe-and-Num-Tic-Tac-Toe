package tictactoe;

/**
 * The TicTacToe class is a subclass of BoardGame that implements the abstract methods of BoardGame and
 * adds a few methods of its own
 */
public class TicTacToe extends boardgame.BoardGame implements boardgame.Saveable{ //implements boardgame.Saveable
 private int currentPlayer = 1;
 private String gameStateMessage;
 private boolean done = false;

public TicTacToe(int wide, int tall){
        super(wide, tall);
        setGameStateMessage(nextPlayerMessage());
    }


/* methods added that aren't in BoardGame*/
public int getCurrentPlayer(){
    return currentPlayer;
}
public void setGameOver(boolean state){
    done = state;
}

/* overridden methods from BoardGame*/
@Override
public  boolean takeTurn(int across, int down, String input){

    // System.out.println("Across = " + across);
    // System.out.println("down = " + down);
    // System.out.println("input = " + input);
    try{
    // validate location
    grid().validateLocation(across,down);
    // validate input
    grid().validateInput(input, getCurrentPlayer());
    // System.out.println("Across after = " + across);
    // System.out.println("down after= " + down);
    // System.out.println("input after= " + input);

    // set token
    setValue(across,down,input);
    if(!isDone()){
    //change player
        switchPlayer();
        setGameStateMessage(nextPlayerMessage());
        return true;
    }else{
        setGameStateMessage(gameOverMessage());
        return true;
    }
    }catch(Exception e){
    throw new RuntimeException(e.getMessage());
    }
}

/* really don't need this method for the design we're using 
so it can be left as a stub*/
@Override
public  boolean takeTurn(int across, int down, int input){
    
    return false;

}

/* I needed this method to be public for 
this design */

@Override
public void setGrid(boardgame.Grid grid){ //used full package name instead of import
    super.setGrid(grid);
    setGameOver(false); //resets done boolean
}


/*this method does a 'win check' every time it is called.
In this design it is used by the user interfaces to determine what
to do next */

@Override
 public boolean isDone(){
    if(grid().horizontalWin() || grid().verticalWin() || grid().diagonalWin()){
        return true;
    }
    if(grid().isFull()){
        return true;
    }
    return false;
 }

/* get Winner needs to use the currentPlayer, the isFull() method (to identify tie game)
to decide what to send back.   Has some duplicate functionality with isDone()
because the original design was meant to allow lots of flexibility. */

@Override
  public  int getWinner(){  

    if(grid().horizontalWin() || grid().verticalWin() || grid().diagonalWin()){
        return getCurrentPlayer();
    }

    if(grid().isFull()){
        return 3;
    }      

    return 0;
  }

@Override
public String getGameStateMessage(){
    return gameStateMessage;

}
/* private helper methods */

    private void switchPlayer(){
        if(getCurrentPlayer() == 1){
            currentPlayer = 2;
        }else{
            currentPlayer = 1;
        }
    }


    private GameGrid grid(){
        /*because I need the grid frequently I wrote a method 
        to do the casting for me so I don't forget*/

        return (GameGrid) getGrid();
    }

    /* The gameStateMessage can be used by both the GUI
    and the TextUI to easily communicate the current state
    to the user.  Private methods to compose the desired message promote
    encapsulation */

    private void setGameStateMessage(String msg){
        gameStateMessage = msg;
    }
    private String nextPlayerMessage(){
        String player = "Player 1";
        if(currentPlayer == 2){
            player = "Player 2";
        }
        return(player + " please indicate where you would like to put your token.");
    }
 /**
  * This function returns a string that says who won the game or when tie occurs
  * 
  * @return The gameOverMessage() method is returning a string that is based on the winner of the game.
  */
    private String gameOverMessage(){
        // int con = 3;
        /*should compose a nice string about who won and/or tie game*/
        if(getWinner() == 1){
            return ("Player 1 has Won!!!");
        }else if(getWinner() == 2){
            return ("Player 2 has Won!!!");
        }else if(getWinner() == 3){
            return ("Game is a Tie");
        } else{
             return("game over");
        }
    }


/* static method to facilitate changing the grid type without adding coupling
This is used by the user interfaces to select the game*/

public static GameGrid newGrid(int kind, int wide, int tall){
    if(kind == 1){
        return new XOGrid(wide,tall);
    }else{
        return new NumTTTGrid(wide,tall);
    }
}

/**
 * It returns a string that contains the current state of the game, including the current player, and
 * the board
 * 
 * @return The current state of the game is being returned.
 */
@Override
public String getStringToSave(){
    char store = '0';
    char sy = '?';
    int curP = getCurrentPlayer();
    if(curP == 1){
        sy = 'O';
    } else{
        sy = 'X';
    }
    String finalStr = ""+sy+"\n";

    for(int x = 1; x <= 3; x++){
        for(int y = 1; y <= 3; y++){

         if(getCell(y,x) == " " && y <= 2){
            finalStr += ",";
         }
         if(getCell(y,x) != " "){
            finalStr += getCell(y,x);
            if(y <= 2){
              finalStr += ",";
            }    
         }
        }
        finalStr += "\n";
    }

    return finalStr;

}

@Override
public void loadSavedString(String toLoad){
    
}

}
