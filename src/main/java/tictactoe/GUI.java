package tictactoe;
/**
 * 
 */

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// import tictactoe.XOView;

/**
 * @author Nitin Satishbabu
 * It creates a JFrame with a JMenuBar, a JPanel called gameContainer, and a JLabel called
 * messageLabel. It also has a function called start() that removes all the components from the
 * gameContainer JPanel, adds a new JPanel to the gameContainer JPanel, and then repaints and
 * revalidates the JFrame
 */
public class GUI extends JFrame{
    private JPanel gameContainer;
    private JLabel messageLabel;
    private JMenuBar menuBar;
    //private Player player1;
    //private Player player2;


/**
* @author Nitin Satishbabu
* Sets title, menubar,labels, etc.
*/
    public GUI(String title){
        // call the superclass constructor
        super();    
        // set the size, title and default close of the jframe
        this.setSize(1000, 800);
        this.setTitle(title);

        makeMenu();
        setJMenuBar(menuBar);
        gameContainer = new JPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        messageLabel = new JLabel("Welcome, lets play!!!");

        // make a new label to store messages
        add(messageLabel,BorderLayout.NORTH);
        add(gameContainer, BorderLayout.CENTER);
        add(makeButtonPanel(),BorderLayout.EAST);
        //start();

    }

/**
 * @author Nitin Satishbabu
 * This function creates a panel that contains two buttons, one for the Tic Tac Toe game and one for
 * the numTTT game
 * 
 */
    private JPanel makeButtonPanel(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(makeTTTButton());
        buttonPanel.add(makeSecondGameButton());
        // buttonPanel.add(makeSaveButton());
        return buttonPanel;
    }

/**
 * @author Nitin Satishbabu
 * Make a button that says "Play XO" and when it's clicked, call the function tictactoe()
 * 
 * @return A button with the text "Play XO" and an action listener that calls the tictactoe() method.
 */
    private JButton makeTTTButton(){
        JButton button = new JButton("Play XO");
        button.addActionListener(e->tictactoe());
        return button;
    }

/**
 * @author Nitin Satishbabu
 * This function creates a JPanel with a JLabel that says "time to play some board games!"
 * 
 * @return A JPanel with a JLabel that says "time to play some board games!"
 */
    private JPanel startupMessage(){
        JPanel temp = new JPanel();
        temp.add(new JLabel("time to play some board games!"));
        return temp;

    }

/**
 * @author Nitin Satishbabu
 * "Make a button that says "Play Num TTT" and when clicked, calls the numGame() function."
 * 
 * @return A button with the text "Play Num TTT" and an action listener that calls the numGame()
 * method.
 */
    private JButton makeSecondGameButton(){
        JButton button = new JButton("Play Num TTT");
        button.addActionListener(e->numGame());
        return button;
    }

    // private JButton makeSaveButton(){
    //     JButton button = new JButton("Save Game");
    //     button.addActionListener(e->saveGame());
    //     return button;
    // }

/**
 * @author Nitin Satishbabu
 * This function creates a menu bar with two menus, "Save" and "Load". The "Save" menu has one item,
 * "Save File".
 */
    public void makeMenu(){
        menuBar = new JMenuBar();
        JMenu menu = new JMenu("Save");
        JMenu loadMenu = new JMenu("Load");
        JMenuItem item = new JMenuItem("Save File");
        menu.add(item);
        menuBar.add(menu);
        // item.addActionListener(e->saveSomething());
        menuBar.add(loadMenu);
        

    }



    public void start(){
        gameContainer.removeAll();
        gameContainer.add(startupMessage());
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
    }

    // protected void saveGame(){
    //     BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
    //     System.out.println("Enter filename of save with .csv:");
    //     String str = reader.readLine();
    //     getStringToSave();
    //     // JOptionPane.showMessageDialog(null,"Enter filename of save with .csv:"); 
    //     BufferedWriter saving = new BufferedWriter(new FileWriter("./assets/" + str));
    //     saving.close();
    // }
/**
 * @author Nitin Satishbabu
 * It removes all the components from the gameContainer JPanel, adds a new XOView JPanel to the
 * gameContainer JPanel, and then repaints and revalidates the JFrame
 */
    protected void tictactoe(){
        gameContainer.removeAll();
        gameContainer.add(new XOView(3,3,1));
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
    }

//     protected void secondGame(){
//     gameContainer.removeAll();
//     gameContainer.add(startupMessage());
//     getContentPane().repaint();
//     getContentPane().revalidate();
//     pack();
//    JOptionPane.showMessageDialog(null,"Judi didn't make a second game"); 
     
//     }

/**
 * @author Nitin Satishbabu
 * It removes all the components from the gameContainer JPanel, adds a new NumTTTView JPanel to the
 * gameContainer JPanel, and then repaints and revalidates the JFrame
 */
    protected void numGame(){
        gameContainer.removeAll();
        gameContainer.add(new NumTTTView(3,3,2));
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
    }

    
/**
 * @author Nitin Satishbabu
 * The main function creates a new GUI object called example and sets it to visible
 */
public static void main(String[] args){
    GUI example = new GUI("Tic-Tac-Toe");
    example.setVisible(true);


}
} 


