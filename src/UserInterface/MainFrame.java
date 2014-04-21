package UserInterface;

import Model.ChessPiece;
import Model.Image;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    
    private final ArrayList<ChessPiece> whiteChessPieces, blackChessPieces;
    
    public MainFrame(ArrayList<ChessPiece> whiteChessPieces, ArrayList<ChessPiece> blackChessPieces){
        this.whiteChessPieces = whiteChessPieces;
        this.blackChessPieces=blackChessPieces;
        this.setTitle("RadikalChess");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.createComponent();
        this.setSize(500,600);
        this.setLocationRelativeTo(null);
    }

    private void createComponent() {
        this.add(createToolbar(), BorderLayout.NORTH);
        this.add(createBoardPanel(), BorderLayout.CENTER);
    }

    private JPanel createToolbar() {
        JPanel panel=new JPanel();
        panel.add(createDifficulty());
        panel.add(createBoardSize());
        panel.add(createAlgorithm());
        panel.add(createPlayButton());
        panel.add(createResetButton());
        panel.add(createProposeMoveButton());
        return panel;
    }

    private JComboBox createDifficulty() {
        JComboBox difficulty=new JComboBox(new String[]{"Easy", "Medium", "Hard"});
        difficulty.setSelectedIndex(0);
        return difficulty;
    }

    private JComboBox createBoardSize() {
        JComboBox size=new JComboBox(
                       new String[]{"Size=6x4", "Size=8x6", "Size=10x8", "Size=12x10"});
        size.setSelectedIndex(0);
        return size;
    }

    private JComboBox createAlgorithm() {
        JComboBox algorithm=new JComboBox(new String[]{"Minimax", "AlphaBeta"});
        algorithm.setSelectedIndex(0);
        return algorithm;
    }

    private JButton createPlayButton() {
        JButton play=new JButton("Play");
        return play;
    }

    private JButton createResetButton() {
        JButton reset=new JButton("Reset");
        return reset;
    }

    private JButton createProposeMoveButton() {
        JButton proposeMove=new JButton("Propose Move");
        return proposeMove;
    }

    private JPanel createBoardPanel() {
        BoardPanel boardPanel = new BoardPanel();
        boardPanel.setLayout(new GridLayout(6, 4));
        createCells(boardPanel);
        loadImages(boardPanel);
        return boardPanel;
    }

    private void createCells(BoardPanel boardPanel) {
        boolean blackFirst = true;
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 4; j++){
	        Cell cell = new Cell();
                paintCell(blackFirst, j, cell);
	        boardPanel.getBoard()[i][j] = cell;
		boardPanel.add(cell);
            }
            blackFirst = !blackFirst;
        }
    }        

    private void paintCell(boolean blackFirst, int j, Cell cell) {
        if (blackFirst){
            if (j % 2 == 0)
                cell.setBackground(Color.DARK_GRAY);
            else
                cell.setBackground(Color.WHITE);
        } 
        else{
            if (j % 2 == 0)
                cell.setBackground(Color.WHITE);
            else
                cell.setBackground(Color.DARK_GRAY);
        }
    }

    private void loadImages(BoardPanel boardPanel) {
        /*for (int i = 0; i < 8; i++) {
            boardPanel.getBoard()[whiteChessPieces[i].getPosition().getRow()] 
                                 [whiteChessPieces[i].getPosition().getColumn()].setIcon(
                                  convertImageToImageIcon(whiteChessPieces[i].getImage()));
            boardPanel.getBoard()[blackChessPieces[i].getPosition().getRow()]
                                 [blackChessPieces[i].getPosition().getColumn()].setIcon(
                                  convertImageToImageIcon(blackChessPieces[i].getImage()));
        }*/
        
        for (ChessPiece chessPiece : whiteChessPieces) {
            boardPanel.getBoard()[chessPiece.getPosition().getRow()][chessPiece.getPosition().getColumn()].setIcon(
            convertImageToImageIcon(chessPiece.getImage()));
        }
        for (ChessPiece chessPiece : blackChessPieces) {
            boardPanel.getBoard()[chessPiece.getPosition().getRow()][chessPiece.getPosition().getColumn()].setIcon(
            convertImageToImageIcon(chessPiece.getImage()));
        }
    }
    
    
    private int getAbsPosition(int i, ChessPiece[] chessPiece) {
        return chessPiece[i].getPosition().getRow() * 4 + chessPiece[i].getPosition().getColumn();
    }
    
    private ImageIcon convertImageToImageIcon(Image image){
        return new ImageIcon(image.getBitmap().getBufferedImage());
    }
}