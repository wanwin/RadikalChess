package Aima;

import Model.Cell;
import Model.ChessBoard;
import Model.Movement;
import Model.Pieces.Pawn;
import Model.Player;
import Model.Position;
import Model.ProposeMove;
import Model.ProposeMoveAttack;

public class RadikalChessState implements Cloneable {

    private ChessBoard chessBoard;
    private Player player;
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

    public boolean possibleMove(Movement movement) {
        if (originCell(movement).getChessPiece().getColour().equals(player.getPlayerName())) {
            if (ProposeMoveAttack.getInstance().selectMoveAttack(
                    originCell(movement).getChessPiece(), movement, chessBoard)) {
                originCell(movement).getChessPiece().setPosition(movement.getDestination());
                destinationCell(movement).setChessPiece(originCell(movement).getChessPiece());
                originCell(movement).setChessPiece(null);
                player.setPlayer((player.getPlayerName().equals("White")) ? "Black" : "White");
                return true;
            }
            if (ProposeMove.getInstance().selectMove(originCell(movement).getChessPiece(),
                    movement, chessBoard)) {
                if (!isEuclideanDistanceReduced(movement.getOrigin(), movement.getDestination())
                        && !(originCell(movement).getChessPiece() instanceof Pawn)) {
                    return false;
                }
                originCell(movement).getChessPiece().setPosition(movement.getDestination());
                destinationCell(movement).setChessPiece(originCell(movement).getChessPiece());
                originCell(movement).setChessPiece(null);
                player.setPlayer((player.getPlayerName().equals("White")) ? "Black" : "White");
                return true;
            }

        }
        return false;
    }

    public boolean mark(Movement movement) {
        if (originCell(movement).getChessPiece().getColour().equals(player.getPlayerName())) {
            if (destinationCell(movement).getChessPiece() != null) {
                originCell(movement).getChessPiece().setPosition(movement.getDestination());
                destinationCell(movement).setChessPiece(originCell(movement).getChessPiece());
                originCell(movement).setChessPiece(null);
                player.setPlayer((player.getPlayerName().equals("White")) ? "Black" : "White");
                return true;
            }
            if (destinationCell(movement).getChessPiece() == null) {
                if (!isEuclideanDistanceReduced(movement.getOrigin(), movement.getDestination())
                        && !(destinationCell(movement).getChessPiece() instanceof Pawn)) {
                    return false;
                }
                originCell(movement).getChessPiece().setPosition(movement.getDestination());
                destinationCell(movement).setChessPiece(originCell(movement).getChessPiece());
                originCell(movement).setChessPiece(null);
                player.setPlayer((player.getPlayerName().equals("White")) ? "Black" : "White");
                return true;
            }

        }
        return false;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    protected RadikalChessState clone() {
        RadikalChessState copy = null;
        try {
            copy = (RadikalChessState) super.clone();
            copy.chessBoard = chessBoard.clone();
        }
        catch (CloneNotSupportedException e) {
        }
        return copy;
    }

    public boolean isEuclideanDistanceReduced(Position origin, Position destination) {
        return (destination.calculateEuclideanDistance(chessBoard, player.getPlayerName())
                < origin.calculateEuclideanDistance(chessBoard, player.getPlayerName()));
    }

    private Cell originCell(Movement movement) {
        return chessBoard.getCell()[movement.getOrigin().getRow()][movement.getOrigin().getColumn()];
    }

    private Cell destinationCell(Movement movement) {
        return chessBoard.getCell()[movement.getDestination().getRow()][movement.getDestination().getColumn()];
    }
}