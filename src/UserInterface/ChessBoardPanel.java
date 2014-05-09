package UserInterface;

import Model.ChessBoard;
import Model.ChessPiece;
import Model.Movement;
import Model.ProposeMove;
import Model.ProposeMoveAttack;
import java.util.ArrayList;
import javax.swing.JPanel;

public class ChessBoardPanel extends JPanel{
    
    CellButton[][] board;
    int row, column;
    
    public ChessBoardPanel(int row, int column){
        this.row = row;
        this.column = column;
        board = new CellButton[row][column];
    }

    public CellButton[][] getBoard() {
        return board;
    }

    public void possibleMove(CellButton firstClicked, CellButton secondClicked, 
                             ChessBoardPanel boardPanel, 
                             ArrayList<ChessPiece> allPieces){
        if (ProposeMove.getInstance().selectMove(firstClicked.getCell().getChessPiece(), 
                                                 createMovement(firstClicked, secondClicked), 
                                                 createBoard(boardPanel)) && 
            ProposeMove.getInstance().isEuclideanDistanceReduced(allPieces, 
                                                                 createMovement(firstClicked, 
                                                                 secondClicked), 
                                                                 firstClicked.getCell()
                                                                 .getChessPiece())){
            updateChessPieceIcon(firstClicked, secondClicked, allPieces);
        }
        if (ProposeMoveAttack.
                getInstance().selectMoveAttack(firstClicked.getCell().getChessPiece(), 
                                                 createMovement(firstClicked, secondClicked), 
                                                 createBoard(boardPanel))){
            updateChessPieceIcon(firstClicked, secondClicked, allPieces);
            allPieces.remove(secondClicked.getCell().getChessPiece());
        }
    }
    
    private void updateChessPieceIcon(CellButton firstClicked, CellButton secondClicked, 
                                      ArrayList<ChessPiece>allPieces){
        for (ChessPiece chessPiece : allPieces) {
                if (chessPiece.getName().equals(firstClicked.getCell().getChessPiece().getName()) &&
                    chessPiece.getColour().equals(firstClicked.getCell().getChessPiece().getColour()))
                    chessPiece.setPosition(secondClicked.getCell().getPosition());
            }
        secondClicked.addPiece(firstClicked);
        firstClicked.removePiece();
    }
    
    private Movement createMovement(CellButton firstClicked, CellButton secondClicked){
        return new Movement(firstClicked.getCell().getPosition(), secondClicked.getCell().getPosition());    
    }
    
    private ChessBoard createBoard(ChessBoardPanel boardPanel){
        ChessBoard chessBoard = new ChessBoard(row, column);
        for (int i = 0; i < boardPanel.getBoard().length; i++)
            for (int j = 0; j < boardPanel.getBoard()[0].length; j++)
                chessBoard.getCell()[i][j] = boardPanel.getBoard()[i][j].getCell();
        return chessBoard;
    }
}
