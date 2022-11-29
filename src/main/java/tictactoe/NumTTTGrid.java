package tictactoe;

/* specialized win check and validation methods for NumTTT */

public class NumTTTGrid extends GameGrid{



public NumTTTGrid(int across, int down){
    super(across,down);
}
    
public boolean horizontalWin(){
    
    for(int i = 1; i <= 3; i++){
      if(super.getValue(1,i) != " " && super.getValue(2, i) != " " && super.getValue(3, i) != " "){
        if(Integer.parseInt(super.getValue(1,i)) + Integer.parseInt(super.getValue(2,i)) 
        + Integer.parseInt(super.getValue(3, i)) >= 15
        && super.getValue(3,i) != " " && super.getValue(2,i) != " " && super.getValue(1,i) != " "){
            return true;
        }
      }
    }
return false;
}

public boolean verticalWin(){
 for(int i = 1; i <= 3; i++){
    if(super.getValue(i,1) != " " && super.getValue(i, 2) != " " && super.getValue(i, 3) != " "){
        if(Integer.parseInt(super.getValue(i,1)) + Integer.parseInt(super.getValue(i, 2))
        + Integer.parseInt(super.getValue(i, 3)) >= 15
        && super.getValue(i,3) != " " && super.getValue(i,2) != " " && super.getValue(i,1) != " "){
            return true;
        }
    }
 }
return false;
    
}

public boolean diagonalWin(){

if(super.getValue(1,1) != " " && super.getValue(2, 2) != " " && super.getValue(3, 3) != " "){
    if(Integer.parseInt(super.getValue(1,1)) + Integer.parseInt(super.getValue(2, 2))
    + Integer.parseInt(super.getValue(3, 3)) >= 15
    && super.getValue(3,3) != " " && super.getValue(2,2) != " " && super.getValue(1,1) != " "){
        return true;
    }
}

if(super.getValue(3,1) != " " && super.getValue(2, 2) != " " && super.getValue(1, 3) != " "){
    if(Integer.parseInt(super.getValue(3,1)) + Integer.parseInt(super.getValue(2, 2))
    + Integer.parseInt(super.getValue(1, 3)) >= 15
    && super.getValue(2,2) != " " && super.getValue(3,1) != " " && super.getValue(1,3) != " "){
        return true;
    }
}   

return false; 
}

public void validateInput(String input, int player)throws Exception{ 
        int play = player;
System.out.println("Number = "+input);
System.out.println("Int = "+Integer.parseInt(input));
System.out.println("Mod = "+(Integer.parseInt(input) %2));
System.out.println("Play = "+play);

        if(Integer.parseInt(input) %2 == 0 && Integer.parseInt(input) <= 8 && play == 2){
            System.out.println("Entered even");
            return;
        }
        if((Integer.parseInt(input) %2) != 0 && Integer.parseInt(input) <= 9 && play == 1){
            System.out.println("Entered odd");
            return;
        }
    
    throw new Exception("Invalid input");


}

public void validateLocation(int across, int down)throws Exception{

int check = 0;

    if(across >= 0 && across < 4){
        if(down >= 0 && down < 4){
            check++;
        }
    }

    if(super.getValue(across, down) == " " && check == 1){
        System.out.println("Valid loc");
        return;
    }
    throw new Exception("Full");
    
}
    
}