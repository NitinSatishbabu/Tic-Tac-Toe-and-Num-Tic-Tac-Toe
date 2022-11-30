# A3 Tic-Tac-Toe and Num Tic-Tac-Toe

Tic-Tac-Toe game played between 2 human players, turn by turn until win or tie. In termial(XO), in GUI(XO and num TTT).

## Description

The Tic-Tac-Toe can be played in terminal and in GUI, the numerical Tic-Tac-Toe can be played in GUI. Involves 2 human players playing against each other, the game can be restarted (new game) at anytime, the game can be saved anytime to assests folder(using the save game button right below the board). 


## Getting Started

### Dependencies

* Describe any prerequisites, libraries, OS version, etc., needed before installing and running your program.

Gradle
import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;
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
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;


### Executing program

* How to run:
    gradle build
    java -cp build/classes/java/main tictactoe.TextUI
    or
    java -jar build/libs/A3.jar
 
* Expected output (for only the terminal, run GUI to see the output for that):
across? (0 to quit)
1
down?
1
 X



Player 2 please indicate where you would like to put your token.
across? (0 to quit)
3
down?
1
 X   O



Player 1 please indicate where you would like to put your token.
across? (0 to quit)
2
down?
2
 X   O
   X


Player 2 please indicate where you would like to put your token.
across? (0 to quit)
3
down?
2
 X   O
   X O


Player 1 please indicate where you would like to put your token.
across? (0 to quit)
3
down?
3
 X   O
   X O
     X

Player 1 has Won!!!

## Limitations

Only for the 1st move grid is small in size.
Player stats not implemented.
  

## Author Information

Nitin Satishbabu
ID: 1152910
nsatishb@uoguelph.ca

## Development History

* 0.3
    * release with save feature
* 0.2
    * release with GUI XO and num TTT working on GUI
* 0.1
    * Initial Release with only working XO in terminal

## Acknowledgments

Inspiration, code snippets, etc.
* [awesome-readme](https://github.com/matiassingers/awesome-readme)
* [simple-readme] (https://gist.githubusercontent.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc/raw/d59043abbb123089ad6602aba571121b71d91d7f/README-Template.md)
