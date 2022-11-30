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
import javax.swing.JFileChooser;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
// import tictactoe.GUI;

/**
* 
*/
/**
 * @author Nitin Satishbabu
 * This class is a view for a TicTacToe game that uses numbers 
 */
public class NumTTTView extends JPanel{

    private JLabel messageLabel;
    private TicTacToe game;
    private PositionAwareButton[][] buttons;
    private JPanel buttonPanel;
    // private GUI root;
    

    public NumTTTView(int wide, int tall, int mode){
        // call the superclass constructor
        super();    
        setLayout(new BorderLayout());
        // root = gameFrame;

        //setGameController(new game.setGrid(game.newGrid(mode, wide,tall)));
        setGameController(new TicTacToe(3,3));
        game.setGrid(game.newGrid(mode,wide,tall));

        // make a new label to store messages
        messageLabel = new JLabel("Welcome to NumTTT");
        add(makeNewGameButton(),BorderLayout.EAST);
        add(makeButtonGrid(tall,wide), BorderLayout.CENTER);
        add(makeSaveButton(), BorderLayout.SOUTH);
    }

    private JButton makeNewGameButton(){
        JButton button = new JButton("New Game");
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
                                        enterNum(e);
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
            selection = gameOver.showConfirmDialog(null,
             game.getGameStateMessage(), "PlayAgain?", JOptionPane.YES_NO_OPTION);
            if(selection == JOptionPane.YES_OPTION){
                newGame();
            }
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

/**
 * @author Nitin Satishbabu
 * MakeSaveButton() returns a JButton that, when clicked, calls saveGame().
 * 
 * @return A button with the text "Save Game" and an action listener that calls the saveGame() method.
 */
    private JButton makeSaveButton(){
        JButton button = new JButton("Save Game");
        button.addActionListener(e->saveGame());
        return button;
    }

/**
 * @author Nitin Satishbabu
 * It opens a file chooser, and if the user selects a file, it writes the game's state to that file
 */
    protected void saveGame(){
        
        // System.out.println("Enter filename of save with .csv:");
        // String str = reader.readLine();
        JFileChooser fc = new JFileChooser("./assets/");    
        int i = fc.showOpenDialog(this);    
         if (i == JFileChooser.APPROVE_OPTION) {
          File f = fc.getSelectedFile();    
          String fileP = f.getPath();  
          String strToWrote = game.getStringToSave();
          try{
             BufferedWriter saving = new BufferedWriter(new FileWriter(fileP));
             saving.write(strToWrote);
             saving.close();
          } catch (IOException eer){
          return;
          }
         }
    }

    /**
     * @author Nitin Satishbabu
     * The function takes in an ActionEvent, gets the input from the user, sends the input to the game,
     * and updates the view
     * 
     * @param e the event that was triggered
     */
    private void enterNum(ActionEvent e){
        //get input from user
        String inp =  "";

        if(game.getCurrentPlayer() == 1){
            inp =  JOptionPane.showInputDialog("Player 1 input: 1, 3 , 5, 7, 9");        
        }else{
            inp =  JOptionPane.showInputDialog("Player 2 input: 0, 2 , 4, 6, 8");
        }
        
        //send input to game and update view
        PositionAwareButton clicked = ((PositionAwareButton)(e.getSource()));
        if(game.takeTurn(clicked.getAcross(), clicked.getDown(),inp)){
            clicked.setText(game.getCell(clicked.getAcross(),clicked.getDown()));
        }
    }


}