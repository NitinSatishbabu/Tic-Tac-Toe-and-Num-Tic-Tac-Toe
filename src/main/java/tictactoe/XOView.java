package tictactoe;
/**
 *   GUI view 
 */

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import boardgame.ui.PositionAwareButton;
// import tictactoe.GUI;

/**
* 
*/
public class XOView extends JPanel{

    private JLabel messageLabel;
    private TicTacToe game;
    private PositionAwareButton[][] buttons;
    private JPanel buttonPanel;
    // private GUI root;
    

    public XOView(int wide, int tall, int mode){
        // call the superclass constructor
        super();    
        setLayout(new BorderLayout());
        // root = gameFrame;

        //setGameController(new game.setGrid(game.newGrid(mode, wide,tall)));
        setGameController(new TicTacToe(3,3));
        game.setGrid(game.newGrid(mode,wide,tall));

        // make a new label to store messages
        messageLabel = new JLabel("Welcome to TTT");
        add(makeNewGameButton(),BorderLayout.EAST);
        add(makeButtonGrid(tall,wide), BorderLayout.CENTER);
    }

    private JButton makeNewGameButton(){
        JButton button = new JButton("New Puzzle");
        button.addActionListener(e->newGame());
        return button;
    }

    public void setGameController(TicTacToe controller){
        this.game = controller;
    }


    private JPanel makeButtonGrid(int tall, int wide){
        JPanel panel = new JPanel();
        buttons = new PositionAwareButton[tall][wide];
        panel.setLayout(new GridLayout(wide, tall));
                for (int y=0; y<wide; y++){
            for (int x=0; x<tall; x++){ 
                //Create buttons and link each button back to a coordinate on the grid
                buttons[y][x] = new PositionAwareButton();
                buttons[y][x].setAcross(x+1); //made the choice to be 1-based
                buttons[y][x].setDown(y+1);
                buttons[y][x].addActionListener(e->{
                                        enterXO(e);
                                        checkGameState();
                                        });
                panel.add(buttons[y][x]);
            }
        }
        return panel;
    }

/* controller methods start here */

    private void checkGameState(){
        int selection= 0;
        JOptionPane gameOver = new JOptionPane();
        if(game.isDone()){
                selection = gameOver.showConfirmDialog(null,game.getGameStateMessage(), 
                "Play again?", JOptionPane.YES_NO_OPTION);

                if(selection == JOptionPane.YES_OPTION){
                newGame();
            }
            // if(selection == JOptionPane.NO_OPTION){
            //     root.start();
            // } else{
            //     newGame();
            // }
        }
    
    }   

    protected void updateView(){
        //update the labels on the buttons according to the model
        for (int y=0; y<game.getHeight(); y++){
            for (int x=0; x<game.getWidth(); x++){  
                buttons[y][x].setText(game.getCell(buttons[y][x].getAcross(),buttons[y][x].getDown())); 
            }
        }

    }

    protected void newGame(){
        game.newGame();
        updateView();
    }



    private void enterXO(ActionEvent e){
        //get input from user
        // String num = JOptionPane.showInputDialog("Please input a value");
        String sym;
        if(game.getCurrentPlayer() == 1){
            sym = "X";
        }else{
            sym = "O";
        }
        
        //send input to game and update view
        PositionAwareButton clicked = ((PositionAwareButton)(e.getSource()));
        if(game.takeTurn(clicked.getAcross(), clicked.getDown(),sym)){
            clicked.setText(game.getCell(clicked.getAcross(),clicked.getDown()));
        }
    }

}

