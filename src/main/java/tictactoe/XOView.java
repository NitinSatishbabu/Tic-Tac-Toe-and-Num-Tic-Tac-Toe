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
 * This class is the view for the TicTacToe game. It creates the buttons and the grid, and updates the
 * view when the model changes
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
        add(makeSaveButton(), BorderLayout.SOUTH);
        // add(makeLoadButton(), BorderLayout.SOUTH);
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

/**
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
 * It opens a file chooser, and if the user selects a file, it writes a string to that file
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


       
        // JOptionPane.showMessageDialog(null,"Enter filename of save with .csv:"); 

    }


    // private JButton makeLoadButton(){
    //     JButton button = new JButton("Load Game");
    //     button.addActionListener(e->loadGame());
    //     return button;
    // }


/**
 * It opens a file chooser, and if the user selects a file, it writes a string to that file
 */
    // protected void loadGame(){
        
    //     // System.out.println("Enter filename of save with .csv:");
    //     // String str = reader.readLine();
    //     // JFileChooser fc = new JFileChooser("./assets/");    
    //     // int i = fc.showOpenDialog(this);    
    //     // //  if (i == JFileChooser.APPROVE_OPTION) {
    //     //   File f = fc.getSelectedFile();    
    //     //   String fileP = f.getPath();  
    //     //   String strToWrote = game.getStringToSave();
    //     String loadStr = "";
    //      if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
    //         if (fileChooser.getSelectedFile().canRead()) {
    //             try {
    //                 //open file
    //                 BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
    //                  while (reader.ready()) {
    //                   loadStr += (char)reader.read();
    //                  }
    //                 // call game.loadSavedString();
    //                   loadSavedString(loadStr);
    //                 //do whatever else needs doing to load
    //                 outputArea.setText(fileChooser.getSelectedFile().getName());
    //                 outputArea.append("\nThe Full Path\n");
    //                 outputArea.append(fileChooser.getSelectedFile().getAbsolutePath());
    //             } catch (Exception ex) {
    //                 outputArea.append("\n"+ ex.getMessage());
    //             }
    //         }
    //     }
    // }
        // JOptionPane.showMessageDialog(null,"Enter filename of save with .csv:"); 

    

/**
 * The function takes in an ActionEvent, which is a button click, and then it gets the button that was
 * clicked, and then it sends the button's position to the game, and then it updates the button's text
 * to the value that the game has stored in that position
 * 
 * @param e the event that triggered the method
 */
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

