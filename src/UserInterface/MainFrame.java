package UserInterface;

import Model.ChessPiece;
import Model.Image;
import Model.Position;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

    
public class MainFrame extends JFrame {
    
    private final ArrayList<ChessPiece> whiteChessPieces;
    private final ArrayList<ChessPiece> blackChessPieces;
    private int row = 6;
    private int column = 4;
    boolean buttonPressed;
    CellButton firstClicked;
    ChessBoardPanel boardPanel;
    
    public MainFrame(ArrayList<ChessPiece> whiteChessPieces, 
                     ArrayList<ChessPiece> blackChessPieces){
        this.whiteChessPieces= whiteChessPieces;
        this.blackChessPieces= blackChessPieces;
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
        boardPanel = new ChessBoardPanel(row, column);
        boardPanel.setLayout(new GridLayout(row, column));
        createCells();
        loadImages(boardPanel);
        return boardPanel;
    }

    private void createCells() {
        boolean blackFirst = true;
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
	        CellButton cell = new CellButton(null, new Position(i,j));
                paintCell(blackFirst, j, cell);
	        boardPanel.getBoard()[i][j] = cell;
                boardPanel.getBoard()[i][j].addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Object source = e.getSource();
                        if (source instanceof CellButton)
                            if (buttonPressed){
                                boardPanel.possibleMove(firstClicked, (CellButton) e.getSource(), boardPanel);
                                buttonPressed = false;
                            }    
                            else{
                                buttonPressed = true;
                                firstClicked = (CellButton) e.getSource();
                            }    
                    }
                });
		boardPanel.add(cell);
            }
            blackFirst = !blackFirst;
        }
        placePieces();
    }
    
    private void placePieces(){
        for (ChessPiece chessPiece : whiteChessPieces) {
            boardPanel.getBoard()[chessPiece.getPosition().getRow()][chessPiece.getPosition().getColumn()].getCell().setChessPiece(chessPiece);
        }
        for (ChessPiece chessPiece : blackChessPieces) {
            boardPanel.getBoard()[chessPiece.getPosition().getRow()][chessPiece.getPosition().getColumn()].getCell().setChessPiece(chessPiece);
        }
    }
    
    private void paintCell(boolean blackFirst, int j, CellButton cell) {
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

    private void loadImages(ChessBoardPanel boardPanel) {
        for (ChessPiece chessPiece : whiteChessPieces) {
            boardPanel.getBoard()[chessPiece.getPosition().getRow()]
                                 [chessPiece.getPosition().getColumn()].setIcon(
                                 convertImageToImageIcon(chessPiece.getImage()));
        }
        for (ChessPiece chessPiece : blackChessPieces) {
            boardPanel.getBoard()[chessPiece.getPosition().getRow()]
                                 [chessPiece.getPosition().getColumn()].setIcon(
                                 convertImageToImageIcon(chessPiece.getImage()));
        }
    }
    
    private Icon convertImageToImageIcon(Image image){
        return new ImageIcon(((SwingBitmap) image.getBitmap()).getBufferedImage());
    }
}