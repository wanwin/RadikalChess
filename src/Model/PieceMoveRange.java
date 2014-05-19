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

    public ArrayList<Movement> selectMove(ChessPiece chessPiece, RadikalChessState state,
            ArrayList<ChessPiece> allPieces) {
        ArrayList<Movement> moveList = new ArrayList<>();
        if (chessPiece instanceof Pawn) {
            moveRangePawn(chessPiece, state, moveList);
        }
        if (chessPiece instanceof Rook) {
            moveRangeRook(chessPiece, state, moveList, allPieces);
        }
        if (chessPiece instanceof Bishop) {
            moveRangeBishop(chessPiece, state, moveList, allPieces);
        }
        if (chessPiece instanceof Queen) {
            moveRangeQueen(chessPiece, state, moveList, allPieces);
        }
        if (chessPiece instanceof King) {
            moveRangeKing(chessPiece, state, moveList);
        }
        return moveList;
    }

    private void moveRangePawn(ChessPiece chessPiece, RadikalChessState state, ArrayList<Movement> moveList) {
        if (state.getPlayer().getPlayerName().equals("Black") && chessPiece.getColour().equals("Black")) {
            if (chessPiece.getPosition().getRow() + 1 < state.getChessBoard().getRow()) {
                moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow()
                        + 1, chessPiece.getPosition().getColumn())));
            }
            if (chessPiece.getPosition().getColumn() + 1 < state.getChessBoard().getColumn()
                    && chessPiece.getPosition().getRow() + 1 < state.getChessBoard().getRow()) {
                moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow()
                        + 1, chessPiece.getPosition().getColumn() + 1)));
            }
            if (chessPiece.getPosition().getColumn() - 1 >= 0 && chessPiece.getPosition().getRow() + 1
                    < state.getChessBoard().getRow()) {
                moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow()
                        + 1, chessPiece.getPosition().getColumn() - 1)));
            }
        }
        if (state.getPlayer().getPlayerName().equals("White") && chessPiece.getColour().equals("White")) {
            if (chessPiece.getPosition().getRow() - 1 < state.getChessBoard().getRow()) {
                moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow()
                        - 1, chessPiece.getPosition().getColumn())));
            }
            if (chessPiece.getPosition().getColumn() + 1 < state.getChessBoard().getColumn()
                    && chessPiece.getPosition().getRow() - 1 < state.getChessBoard().getRow()) {
                moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow()
                        - 1, chessPiece.getPosition().getColumn() + 1)));
            }
            if (chessPiece.getPosition().getColumn() - 1 >= 0 && chessPiece.getPosition().getRow() - 1
                    < state.getChessBoard().getRow()) {
                moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow()
                        - 1, chessPiece.getPosition().getColumn() - 1)));
            }

        }
    }

    private void moveRangeRook(ChessPiece chessPiece, RadikalChessState state, ArrayList<Movement> moveList,
            ArrayList<ChessPiece> allPieces) {
        if (chessPiece.getColour().equals(state.getPlayer().getPlayerName())) {
            for (int i = 0; i < state.getChessBoard().getRow(); i++) {
                if (state.isEuclideanDistanceReduced(chessPiece.getPosition(),
                        new Position(i, chessPiece.getPosition().getColumn()), allPieces)) {
                    moveList.add(new Movement(chessPiece.getPosition(),
                            new Position(i, chessPiece.getPosition().getColumn())));
                }
            }
            for (int i = 0; i < state.getChessBoard().getColumn(); i++) {
                if (state.isEuclideanDistanceReduced(chessPiece.getPosition(),
                        new Position(i, chessPiece.getPosition().getColumn()), allPieces)) {
                }
                moveList.add(new Movement(chessPiece.getPosition(),
                        new Position(chessPiece.getPosition().getRow(), i)));
            }
        }
    }

    private void moveRangeBishop(ChessPiece chessPiece, RadikalChessState state, ArrayList<Movement> moveList,
            ArrayList<ChessPiece> allPieces) {
        if (chessPiece.getColour().equals(state.getPlayer().getPlayerName())) {
            for (int i = 0; i < state.getChessBoard().getRow(); i++) {
                for (int j = chessPiece.getPosition().getColumn(); j >= 0; j--) {
                    if (i + chessPiece.getPosition().getRow() < state.getChessBoard().getRow()
                            && j >= 0) {
                        if (state.isEuclideanDistanceReduced(chessPiece.getPosition(),
                                new Position(i, j), allPieces)) {
                        }
                        moveList.add(new Movement(chessPiece.getPosition(), new Position(i
                                + chessPiece.getPosition().getRow(), j)));
                    } else {
                        break;
                    }
                }
            }
            for (int i = 0; i < state.getChessBoard().getRow(); i++) {
                for (int j = 0; j < state.getChessBoard().getColumn(); j++) {
                    if (i + chessPiece.getPosition().getRow() < state.getChessBoard().getRow()
                            && j + chessPiece.getPosition().getColumn() < state.getChessBoard().getColumn()) {
                        if (state.isEuclideanDistanceReduced(chessPiece.getPosition(),
                                new Position(i, j), allPieces)) {
                        }
                        moveList.add(new Movement(chessPiece.getPosition(), new Position(i
                                + chessPiece.getPosition().getRow(), j + chessPiece.getPosition().getColumn())));
                    } else {
                        break;
                    }
                }
            }
            for (int i = chessPiece.getPosition().getRow(); i >= 0; i--) {
                for (int j = 0; j < state.getChessBoard().getColumn(); j++) {
                    if (i >= 0 && chessPiece.getPosition().getColumn() + j < state.getChessBoard().getColumn()) {
                        if (state.isEuclideanDistanceReduced(chessPiece.getPosition(),
                                new Position(i, j), allPieces)) {
                        }
                        moveList.add(new Movement(chessPiece.getPosition(),
                                new Position(i, j + chessPiece.getPosition().getColumn())));
                    } else {
                        break;
                    }
                }
            }
            for (int i = chessPiece.getPosition().getRow(); i >= 0; i--) {
                for (int j = chessPiece.getPosition().getColumn(); j >= 0; j--) {
                    if (chessPiece.getPosition().getRow() - i >= 0
                            && chessPiece.getPosition().getColumn() - j >= 0) {
                        if (state.isEuclideanDistanceReduced(chessPiece.getPosition(),
                                new Position(i, j), allPieces)) {
                        }
                        moveList.add(new Movement(chessPiece.getPosition(), new Position(i, j)));
                    } else {
                        break;
                    }
                }
            }
        }
    }

    private void moveRangeQueen(ChessPiece chessPiece, RadikalChessState state, ArrayList<Movement> moveList,
            ArrayList<ChessPiece> allPieces) {
        moveRangeRook(chessPiece, state, moveList, allPieces);
        moveRangeBishop(chessPiece, state, moveList, allPieces);
    }

    private void moveRangeKing(ChessPiece chessPiece, RadikalChessState state, ArrayList<Movement> moveList) {
        if (chessPiece.getColour().equals(state.getPlayer().getPlayerName())) {
            if (chessPiece.getPosition().getRow() + 1 < state.getChessBoard().getRow()) {
                moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow()
                        + 1, chessPiece.getPosition().getColumn())));
            }
            if (chessPiece.getPosition().getRow() + 1 < state.getChessBoard().getRow()
                    && chessPiece.getPosition().getColumn() - 1 >= 0) {
                moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow()
                        + 1, chessPiece.getPosition().getColumn() - 1)));
            }
            if (chessPiece.getPosition().getColumn() - 1 >= 0) {
                moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow(),
                        chessPiece.getPosition().getColumn() - 1)));
            }
            if (chessPiece.getPosition().getRow() - 1 >= 0 && chessPiece.getPosition().getColumn() - 1 >= 0) {
                moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow()
                        - 1, chessPiece.getPosition().getColumn() - 1)));
            }
            if (chessPiece.getPosition().getRow() - 1 >= 0) {
                moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow()
                        - 1, chessPiece.getPosition().getColumn())));
            }
            if (chessPiece.getPosition().getRow() - 1 >= 0
                    && chessPiece.getPosition().getColumn() + 1 < state.getChessBoard().getColumn()) {
                moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow()
                        - 1, chessPiece.getPosition().getColumn() + 1)));
            }
            if (chessPiece.getPosition().getColumn() + 1 < state.getChessBoard().getColumn()) {
                moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow(),
                        chessPiece.getPosition().getColumn() + 1)));
            }
            if (chessPiece.getPosition().getRow() + 1 < state.getChessBoard().getRow()
                    && chessPiece.getPosition().getColumn() + 1 <= state.getChessBoard().getColumn()) {
                moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow()
                        + 1, chessPiece.getPosition().getColumn() + 1)));
            }
        }
    }
}
