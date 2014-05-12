package UserInterface;

import Main.RadikalChess;
import Model.ChessBoard;
import Model.ChessPiece;
import Model.Image;
import Model.Movement;
import Model.Pieces.Pawn;
import Model.Pieces.Queen;
import Model.Player;
import Model.ProposeMove;
import Model.ProposeMoveAttack;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ChessBoardPanel extends JPanel {

    private final CellButton[][] cellPanel;

    public ChessBoardPanel(int row, int column) {
        this.cellPanel = new CellButton[row][column];
    }

    public CellButton[][] getBoard() {
        return cellPanel;
    }

    public void updateChessPieceIcon(CellButton firstClicked, CellButton secondClicked,
            ArrayList<ChessPiece> allPieces) {
        for (ChessPiece chessPiece : allPieces) {
            if (chessPiece.getName().equals(firstClicked.getCell().getChessPiece().getName())
                    && chessPiece.getColour().equals(firstClicked.getCell().getChessPiece().getColour())
                    && chessPiece.getPosition().equals(firstClicked.getCell().getChessPiece().getPosition())) {
                chessPiece.setPosition(secondClicked.getCell().getPosition());
            }
        }
        secondClicked.addPiece(firstClicked);
        firstClicked.removePiece();
    }

    private Movement createMovement(CellButton firstClicked,
            CellButton secondClicked) {
        return new Movement(firstClicked.getCell().getPosition(),
                secondClicked.getCell().getPosition());
    }

    private ChessBoard createBoard(ChessBoardPanel boardPanel) {
        ChessBoard chessBoard = new ChessBoard(cellPanel.length, cellPanel[0].length);
        for (int i = 0; i < boardPanel.getBoard().length; i++) {
            for (int j = 0; j < boardPanel.getBoard()[0].length; j++) {
                chessBoard.getCell()[i][j] = boardPanel.getBoard()[i][j].getCell();
            }
        }
        return chessBoard;
    }
}