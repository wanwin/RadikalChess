package Aima;

import Model.Cell;
import Model.ChessBoard;
import Model.ChessPiece;
import Model.Movement;
import Model.Pieces.Pawn;
import Model.Player;
import Model.Position;
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

    public boolean possibleMove(Movement movement, ArrayList<ChessPiece> allPieces) {
        if (originCell(movement).getChessPiece().getColour().equals(player.getPlayerName())) {
            if (ProposeMoveAttack.getInstance().selectMoveAttack(
                    originCell(movement).getChessPiece(), movement, chessBoard)) {
                updateAllPieces(allPieces, movement);
                allPieces.remove(destinationCell(movement).getChessPiece());
                destinationCell(movement).setChessPiece(originCell(movement).getChessPiece());
                originCell(movement).setChessPiece(null);
                player.setPlayer((player.getPlayerName().equals("White")) ? "Black" : "White");
                return true;
            }
            if (ProposeMove.getInstance().selectMove(originCell(movement).getChessPiece(),
                    movement, chessBoard)) {
                if (!isEuclideanDistanceReduced(movement.getOrigin(), movement.getDestination(), allPieces)
                        && !(destinationCell(movement).getChessPiece() instanceof Pawn)) {
                    return false;
                }
                updateAllPieces(allPieces, movement);
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
        } catch (CloneNotSupportedException e) {
        }
        return copy;
    }

    public boolean isEuclideanDistanceReduced(Position origin, Position destination,
            ArrayList<ChessPiece> allPieces) {
        return (destination.calculateEuclideanDistance(allPieces, chessBoard, player.getPlayerName())
                < origin.calculateEuclideanDistance(allPieces, chessBoard, player.getPlayerName()));
    }

    private Cell originCell(Movement movement) {
        return chessBoard.getCell()[movement.getOrigin().getRow()][movement.getOrigin().getColumn()];
    }

    private Cell destinationCell(Movement movement) {
        return chessBoard.getCell()[movement.getDestination().getRow()][movement.getDestination().getColumn()];
    }

    private void updateAllPieces(ArrayList<ChessPiece> allPieces, Movement movement) {
        for (ChessPiece chessPiece : allPieces) {
            if (chessPiece.getName().equals(originCell(movement).getChessPiece().getName())
                    && chessPiece.getColour().equals(originCell(movement).getChessPiece().getColour())
                    && chessPiece.getPosition().equals(originCell(movement).getChessPiece().getPosition())) {
                chessPiece.setPosition(destinationCell(movement).getPosition());
                break;
            }
        }
    }
}