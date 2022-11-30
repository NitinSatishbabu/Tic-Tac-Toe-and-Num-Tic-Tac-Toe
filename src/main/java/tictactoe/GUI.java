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
* 
*/
public class GUI extends JFrame{
    private JPanel gameContainer;
    private JLabel messageLabel;
    private JMenuBar menuBar;
    //private Player player1;
    //private Player player2;


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

    private JPanel makeButtonPanel(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(makeTTTButton());
        buttonPanel.add(makeSecondGameButton());
        // buttonPanel.add(makeSaveButton());
        return buttonPanel;
    }

    private JButton makeTTTButton(){
        JButton button = new JButton("Play XO");
        button.addActionListener(e->tictactoe());
        return button;
    }

    private JPanel startupMessage(){
        JPanel temp = new JPanel();
        temp.add(new JLabel("time to play some board games!"));
        return temp;

    }

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

    protected void numGame(){
        gameContainer.removeAll();
        gameContainer.add(new NumTTTView(3,3,2));
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
    }

    
public static void main(String[] args){
    GUI example = new GUI("Tic-Tac-Toe");
    example.setVisible(true);


}
} 


