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

    public void possibleMove(CellButton firstClicked, CellButton secondClicked,
            ChessBoardPanel boardPanel, ArrayList<ChessPiece> allPieces, Player player) throws IOException {
        if (firstClicked.getCell().getChessPiece().getColour().equals(player.getPlayer())) {
            if (ProposeMoveAttack.getInstance().selectMoveAttack(
                    firstClicked.getCell().getChessPiece(),
                    createMovement(firstClicked, secondClicked),
                    createBoard(boardPanel))) {
                player.setPlayer((player.getPlayer().equals("White")) ? "Black" : "White");
                ChessPiece piece = secondClicked.getCell().getChessPiece();
                updateChessPieceIcon(firstClicked, secondClicked, allPieces);
                allPieces.remove(piece);
            }
            if (ProposeMove.getInstance().selectMove(
                    firstClicked.getCell().getChessPiece(),
                    createMovement(firstClicked, secondClicked),
                    createBoard(boardPanel))) {
                if (!ProposeMove.getInstance().isEuclideanDistanceReduced(allPieces, createMovement(firstClicked, secondClicked),
                        firstClicked.getCell().getChessPiece())
                        && !(firstClicked.getCell().getChessPiece() instanceof Pawn)) {
                    return;
                }
                player.setPlayer((player.getPlayer().equals("White")) ? "Black" : "White");
                updateChessPieceIcon(firstClicked, secondClicked, allPieces);
            }
        }
        if (secondClicked.getCell().getChessPiece() instanceof Pawn && ((secondClicked.getCell().getChessPiece().getPosition().getRow() == 0
                && secondClicked.getCell().getChessPiece().getColour().equals("White") || secondClicked.getCell().getChessPiece().getPosition().getRow()
                == boardPanel.getBoard().length - 1 && secondClicked.getCell().getChessPiece().getColour().equals("Black")))) {
            for (ChessPiece chessPiece : allPieces) {
                if (chessPiece.getName().equals(secondClicked.getCell().getChessPiece().getName())
                        && chessPiece.getColour().equals(secondClicked.getCell().getChessPiece().getColour())) {
                    chessPiece.setName("Queen");
                    chessPiece.setPosition(secondClicked.getCell().getPosition());
                    chessPiece.setImage(new Image(new SwingBitmap(ImageIO.read(new File(RadikalChess.filename + "/" + chessPiece.getColour() + "Queen" 
                    + ".png")))));
                    secondClicked.getCell().setChessPiece(chessPiece);
                    secondClicked.setIcon(new ImageIcon(((SwingBitmap) secondClicked.getCell().getChessPiece().getImage().getBitmap()).getBufferedImage()));
                    break;
                }
            }
        }
    }

    private void updateChessPieceIcon(CellButton firstClicked, CellButton secondClicked,
            ArrayList<ChessPiece> allPieces) {
        for (ChessPiece chessPiece : allPieces) {
            if (chessPiece.getName().equals(firstClicked.getCell().getChessPiece().getName())
                    && chessPiece.getColour().equals(firstClicked.getCell().getChessPiece().getColour())) {
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