package tictactoe;
import java.util.Scanner;

public class TextUI{
    private TicTacToe game = new TicTacToe(3,3);    
    private Scanner input = new Scanner(System.in);
    private int acrossVal;
    private int downVal;

    public TextUI(int gameType){
        game = new TicTacToe(3,3);
        /*could print out a welcome message here or set a gameType
        variable that let us decide which message to print */
        game.setGrid(TicTacToe.newGrid(1,3,3));
    }

    public void play(){
        while(!game.isDone()){
            getPosition();
            if(acrossVal == 0){
                game.setGameOver(true);
                break;
            }
            try{
                game.takeTurn(acrossVal,downVal,getToken());
                System.out.println(game);
                System.out.println(game.getGameStateMessage());
            }catch(RuntimeException e){
                System.out.println(game.getGameStateMessage());
                /* this only works if the game sets the gameStateMessage when
                there is an exception caught*/
            }

        }
        
    }

    private void getPosition(){
        /*this method needs some validation and error checking*/
        System.out.println("across? (0 to quit)");
        acrossVal = input.nextInt();

        input.nextLine(); //to get rid of hard return
        System.out.println("down?");
        downVal = input.nextInt(); //to get rid or hard return;
    }
    private String getToken(){
        if(game.getCurrentPlayer() == 1){
            return "X";
        } else{
            return "O";
        }
    }
/* main method to run the UI */

    public static void main(String[] args){
        TextUI tester = new TextUI(1); //textUI for XO game
        tester.play();
    }
}