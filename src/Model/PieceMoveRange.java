package Model;

import Aima.RadikalChessState;
import Model.Pieces.Bishop;
import Model.Pieces.King;
import Model.Pieces.Pawn;
import Model.Pieces.Queen;
import Model.Pieces.Rook;
import java.util.ArrayList;

public class PieceMoveRange {

    private static PieceMoveRange instance;

    private PieceMoveRange() {
    }

    public static PieceMoveRange getInstance() {
        if (instance == null) {
            instance = new PieceMoveRange();
        }
        return instance;
    }

    public ArrayList<Movement> selectMove(ChessPiece chessPiece, RadikalChessState state) {
        ArrayList<Movement> moveList = new ArrayList<>();
        if (chessPiece instanceof Pawn) {
            moveRangePawn(chessPiece, state, moveList);
        }
        if (chessPiece instanceof Rook) {
            moveRangeRook(chessPiece, state, moveList);
        }
        if (chessPiece instanceof Bishop) {
            moveRangeBishop(chessPiece, state, moveList);
        }
        if (chessPiece instanceof Queen) {
            moveRangeQueen(chessPiece, state, moveList);
        }
        if (chessPiece instanceof King) {
            moveRangeKing(chessPiece, state, moveList);
        }
        return moveList;
    }

    private void moveRangePawn(ChessPiece chessPiece, RadikalChessState state, ArrayList<Movement> moveList) {
        if (state.getPlayer().getPlayerName().equals("Black") && chessPiece.getColour().equals("Black")) {
            if (chessPiece.getPosition().getRow() + 1 < state.getChessBoard().getRow()) {
                moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow() + 1, chessPiece.getPosition().getColumn())));
            }
            if (chessPiece.getPosition().getColumn() + 1 < state.getChessBoard().getColumn()) {
                moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow() + 1, chessPiece.getPosition().getColumn() + 1)));
            }
            if (chessPiece.getPosition().getColumn() - 1 >= 0) {
                moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow() + 1, chessPiece.getPosition().getColumn() - 1)));
            }
        }
        if (state.getPlayer().getPlayerName().equals("White") && chessPiece.getColour().equals("White")) {
            if (chessPiece.getPosition().getRow() - 1 < state.getChessBoard().getRow()) {
                moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow() - 1, chessPiece.getPosition().getColumn())));
                if (chessPiece.getPosition().getColumn() + 1 < state.getChessBoard().getColumn()) {
                    moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow() - 1, chessPiece.getPosition().getColumn() + 1)));
                }
                if (chessPiece.getPosition().getColumn() - 1 >= 0) {
                    moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow() - 1, chessPiece.getPosition().getColumn() - 1)));
                }
            }
        }
    }

    private void moveRangeRook(ChessPiece chessPiece, RadikalChessState state, ArrayList<Movement> moveList) {
        if (chessPiece.getColour().equals(state.getPlayer())) {
            for (int i = 0; i < state.getChessBoard().getRow(); i++) {
                moveList.add(new Movement(chessPiece.getPosition(), new Position(i, chessPiece.getPosition().getColumn())));
            }
            for (int i = 0; i < state.getChessBoard().getColumn(); i++) {
                moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow(), i)));
            }
        }
    }

    private void moveRangeBishop(ChessPiece chessPiece, RadikalChessState state, ArrayList<Movement> moveList) {
        if (chessPiece.getColour().equals(state.getPlayer())) {
            for (int i = 0; i < state.getChessBoard().getColumn(); i++) {
                moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow() - chessPiece.getPosition().getColumn() + i, i)));
            }
            for (int i = state.getChessBoard().getRow(); i < state.getChessBoard().getColumn(); i--) {
                moveList.add(new Movement(chessPiece.getPosition(), new Position(i, state.getChessBoard().getRow() - 1 - i)));
            }
        }
    }

    private void moveRangeQueen(ChessPiece chessPiece, RadikalChessState state, ArrayList<Movement> moveList) {
        moveRangeRook(chessPiece, state, moveList);
        moveRangeBishop(chessPiece, state, moveList);
    }

    private void moveRangeKing(ChessPiece chessPiece, RadikalChessState state, ArrayList<Movement> moveList) {
        if (chessPiece.getColour().equals(state.getPlayer())) {
            if (chessPiece.getPosition().getRow() + 1 < state.getChessBoard().getRow()) {
                moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow() + 1, chessPiece.getPosition().getColumn())));
            }
            if (chessPiece.getPosition().getRow() + 1 < state.getChessBoard().getRow() && chessPiece.getPosition().getColumn() - 1 >= 0) {
                moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow() + 1, chessPiece.getPosition().getColumn() - 1)));
            }
            if (chessPiece.getPosition().getColumn() - 1 >= 0) {
                moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow(), chessPiece.getPosition().getColumn() - 1)));
            }
            if (chessPiece.getPosition().getRow() - 1 >= 0 && chessPiece.getPosition().getColumn() - 1 >= 0) {
                moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow() - 1, chessPiece.getPosition().getColumn() - 1)));
            }
            if (chessPiece.getPosition().getRow() - 1 >= 0) {
                moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow() - 1, chessPiece.getPosition().getColumn())));
            }
            if (chessPiece.getPosition().getRow() - 1 >= 0 && chessPiece.getPosition().getColumn() + 1 < state.getChessBoard().getColumn()) {
                moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow() - 1, chessPiece.getPosition().getColumn() + 1)));
            }
            if (chessPiece.getPosition().getColumn() + 1 <= state.getChessBoard().getColumn()) {
                moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow(), chessPiece.getPosition().getColumn() + 1)));
            }
            if (chessPiece.getPosition().getRow() + 1 <= state.getChessBoard().getRow() && chessPiece.getPosition().getColumn() + 1 <= state.getChessBoard().getColumn()) {
                moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow() + 1, chessPiece.getPosition().getColumn() + 1)));
            }
        }
    }
}
