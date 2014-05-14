package Aima;

import Model.Cell;
import Model.ChessBoard;
import Model.ChessPiece;
import Model.Movement;
import Model.Pieces.Pawn;
import Model.Pieces.Queen;
import Model.Player;
import Model.ProposeMove;
import Model.ProposeMoveAttack;
import java.util.ArrayList;

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

    public int getUtility() {
        return utility;
    }

    public boolean possibleMove(Movement movement, ChessBoard board, ArrayList<ChessPiece> allPieces,
            Player player) {
        if (originCell(movement).getChessPiece().getColour().equals(player.getPlayer())){
            if (ProposeMoveAttack.getInstance().selectMoveAttack(
                    originCell(movement).getChessPiece(), movement, chessBoard)) {
                player.setPlayer((player.getPlayer().equals("White")) ? "Black" : "White");
                ChessPiece piece = destinationCell(movement).getChessPiece();
                //ChessBoardPanel.updateChessPieceIcon(movement, allPieces);//
                allPieces.remove(piece);
                return true;
            }
            if (ProposeMove.getInstance().selectMove(originCell(movement).getChessPiece(),
                    movement, chessBoard)) {
                if (!isEuclideanDistanceReduced(originCell(movement).getPosition().
                        calculateEuclideanDistance(allPieces, chessBoard, player.getPlayer()),
                        destinationCell(movement).getPosition().calculateEuclideanDistance(allPieces, chessBoard, player.getPlayer()))
                        && !(destinationCell(movement).getChessPiece() instanceof Pawn)) {
                    return false;
                }
                player.setPlayer((player.getPlayer().equals("White")) ? "Black" : "White");
                //ChessBoardPanel.updateChessPieceIcon(movement, allPieces);
                return true;
            }
        }
        return false;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isTerminal() {
        return false;
    }

    @Override
    protected RadikalChessState clone() {
        RadikalChessState copy = null;
        try {
            copy = (RadikalChessState) super.clone();
            copy.chessBoard = chessBoard.clone();
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return copy;
    }

    public boolean isEuclideanDistanceReduced(int origin, int destination) {
        return (destination < origin);
    }

    private Cell originCell(Movement movement) {
        return chessBoard.getCell()[movement.getOrigin().getRow()][movement.getOrigin().getColumn()];
    }

    private Cell destinationCell(Movement movement) {
        return chessBoard.getCell()[movement.getDestination().getRow()][movement.getDestination().getColumn()];
    }
}