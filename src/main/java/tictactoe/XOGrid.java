package tictactoe;


/**
 * This class is a subclass of the GameGrid class and it is used to play a game of Tic-Tac-Toe
 */
public class XOGrid extends GameGrid{

private static  String[] symbols ={"X","O"};

public XOGrid(int across, int down){
    super(across,down);
}
    
/**
 * If the first, second, and third values in a row are the same and not equal to a space, then return
 * true
 * 
 * @return A boolean value.
 */
public boolean horizontalWin(){
    for(int i = 1; i <= 3; i++){
        if(super.getValue(1,i) == super.getValue(2, i)
        && super.getValue(2,i) == super.getValue(3, i)
        && super.getValue(3,i) != " "){
            return true;
        }
    }
return false;
}

/**
 * If the first, second, and third values in a column are the same and not equal to a space, then
 * return true
 * 
 * @return A boolean value.
 */
public boolean verticalWin(){
    for(int i = 1; i <= 3; i++){
        if(super.getValue(i,1) == super.getValue(i, 2)
        && super.getValue(i,2) == super.getValue(i, 3)
        && super.getValue(i,3) != " "){
            return true;
        }
    }
return false;
}

/**
 * If the center square is not empty and the center square is the same as the top right and bottom left
 * squares, or the center square is the same as the top left and bottom right squares, then return true
 * 
 * @return The method is returning a boolean value.
 */
public boolean diagonalWin(){
    if(super.getValue(1,1) == super.getValue(2, 2)
    && super.getValue(2,2) == super.getValue(3, 3)
    && super.getValue(3,3) != " "){
        return true;
    }

    if(super.getValue(3,1) == super.getValue(2, 2)
    && super.getValue(2,2) == super.getValue(1, 3)
    && super.getValue(2,2) != " "){
        return true;
    }    

return false;
    
}

/**
 * If the input is not one of the possible symbols, throw an exception
 * 
 * @param input The input that the user has entered.
 * @param currentPlayer The player who is currently playing.
 */
@Override
public void validateInput(String input, int currentPlayer) throws Exception{
    
    for(String possible: symbols){
        if(input.equals(possible)){
            return;
        }
    }
    throw new Exception("Invalid input");

}

/**
 * This function checks to see if the position is out of bounds or already full
 * 
 * @param across the x-coordinate of the position
 * @param down the row of the board
 */
@Override
public void validateLocation(int across, int down)throws Exception{
    int check = 0;

    if(across >= 0 && across < 4){
        if(down >= 0 && down < 4){
            check++;
        }
    }

    if(super.getValue(across, down) == " " && check == 1){
        return;
    }
    throw new Exception("Full or out of bounds");

/* method should check to see if positions are out of bounds
as well as if the position is already full*/
    
}

/* private helper methods*/

private boolean rowCheck(int row){
     boolean match = false;

    return match;
}

private boolean columnCheck(int col){
    return false;

}

private boolean diagonalCheck(){
    return false;

}




}