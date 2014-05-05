package UserInterface;

import Model.ChessBoard;
import Model.Movement;
import Model.ProposeMove;
import Model.ProposeMoveAttack;
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

    public void possibleMove(CellButton firstClicked, CellButton secondClicked, ChessBoardPanel boardPanel) {
        if (ProposeMoveAttack.getInstance().selectMoveAttack(firstClicked.getCell().getChessPiece(), 
                                                 createMovement(firstClicked, secondClicked), 
                                                 createBoard(boardPanel)) 
            ||
            ProposeMove.getInstance().selectMove(firstClicked.getCell().getChessPiece(), 
                                                 createMovement(firstClicked, secondClicked), 
                                                 createBoard(boardPanel))){
            secondClicked.addPiece(firstClicked);
            firstClicked.removePiece();
        }
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
