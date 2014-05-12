package Aima;

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
import UserInterface.CellButton;
import UserInterface.ChessBoardPanel;
import UserInterface.SwingBitmap;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class RadikalChessState implements Cloneable {

    private ChessBoard chessBoard;
    private Player player = new Player("White");
    private int utility = 0;

    public RadikalChessState(ChessBoard chessBoard, Player player) {
        this.chessBoard = chessBoard;
        this.player = player;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
    
    public int getUtility(){
        return utility;
    }
    
    public void possibleMove(CellButton firstClicked, CellButton secondClicked,
            ChessBoardPanel boardPanel, ArrayList<ChessPiece> allPieces, Player player) throws IOException {
        if (firstClicked.getCell().getChessPiece().getColour().equals(player.getPlayer())) {
            if (ProposeMoveAttack.getInstance().selectMoveAttack(
                    firstClicked.getCell().getChessPiece(),
                    createMovement(firstClicked, secondClicked),
                    chessBoard)) {
                player.setPlayer((player.getPlayer().equals("White")) ? "Black" : "White");
                ChessPiece piece = secondClicked.getCell().getChessPiece();
                allPieces.remove(piece);
            }
            if (ProposeMove.getInstance().selectMove(
                    firstClicked.getCell().getChessPiece(),
                    createMovement(firstClicked, secondClicked),
                    chessBoard)) {
                if (!ProposeMove.getInstance().isEuclideanDistanceReduced(allPieces, createMovement(firstClicked, secondClicked),
                        firstClicked.getCell().getChessPiece())
                        && !(firstClicked.getCell().getChessPiece() instanceof Pawn)) {
                    return;
                }
                player.setPlayer((player.getPlayer().equals("White")) ? "Black" : "White");
            }
        }
        if (secondClicked.getCell().getChessPiece() instanceof Pawn 
                && ((secondClicked.getCell().getChessPiece().getPosition().getRow() == 0
                && secondClicked.getCell().getChessPiece().getColour().equals("White") || 
                secondClicked.getCell().getChessPiece().getPosition().getRow()
                == boardPanel.getBoard().length - 1 && secondClicked.getCell().getChessPiece().getColour().equals("Black")))) {
            for (ChessPiece chessPiece : allPieces) {
                if (chessPiece.getName().equals(secondClicked.getCell().getChessPiece().getName())
                        && chessPiece.getColour().equals(secondClicked.getCell().getChessPiece().getColour())
                        && chessPiece.getPosition().equals(secondClicked.getCell().getChessPiece().getPosition())) {
                    chessPiece = new Queen("Queen", chessPiece.getPosition(), chessPiece.getColour());
                    chessPiece.setImage(new Image(new SwingBitmap(ImageIO.read(new File(RadikalChess.filename + "/"
                            + chessPiece.getColour() + "Queen"
                            + ".png")))));
                    secondClicked.getCell().setChessPiece(chessPiece);
                    secondClicked.setIcon
                    (new ImageIcon
                    (((SwingBitmap) secondClicked.getCell().getChessPiece().getImage().getBitmap()).getBufferedImage()));
                    break;
                }
            }
        }
    }
    
    private Movement createMovement(CellButton firstClicked,
            CellButton secondClicked) {
        return new Movement(firstClicked.getCell().getPosition(),
                secondClicked.getCell().getPosition());
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isTerminal() {
        return false;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        RadikalChessState radikalChessState = new RadikalChessState(chessBoard, player);
        return radikalChessState;
    }
}