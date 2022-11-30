package tictactoe;

import java.util.Iterator;
/* the creation of a parent class is what lets use the same TTT game
to provide both games */

public abstract class GameGrid extends boardgame.Grid{

public GameGrid(int across, int down){
    super(across,down);
}   
public abstract boolean horizontalWin();

public abstract boolean verticalWin();

public abstract boolean diagonalWin();

public  abstract void validateInput(String input, int player) throws Exception;

public  abstract void validateLocation(int across, int down)throws Exception;


/**
 * The function iterates through the board and counts the number of non-empty spaces.  If the number
 * of non-empty spaces is equal to the number of spaces on the board, then the board is full
 * 
 * @return The method isFull() is returning a boolean value.
 */
public boolean isFull(){
    /* I should have provided this method in the base class 
    but I didn't think of it.  Fortunately, we can
     use the iterator to count*/
    Iterator<String> iter = iterator();
    int count = 0;
        while(iter.hasNext()){
            if(!iter.next().equals(" ")){
            count++;
            }
        }
    if(count == getWidth()*getHeight()){
        return true;
    }
    return false;

}




}