package UserInterface;

import javax.swing.JPanel;

public class BoardPanel extends JPanel{
    
    Cell[][] board = new Cell[6][4];
    
    public BoardPanel(){
    }

    public Cell[][] getBoard() {
        return board;
    }
}
