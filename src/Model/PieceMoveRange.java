package Model;

import Model.Pieces.Bishop;
import Model.Pieces.King;
import Model.Pieces.Pawn;
import Model.Pieces.Queen;
import Model.Pieces.Rook;
import java.util.ArrayList;

public class PieceMoveRange {

    private static PieceMoveRange instance;

    private PieceMoveRange(){
    }
    
    public static PieceMoveRange getInstance(){
        if (instance == null)
            instance = new PieceMoveRange();
        return instance;
    }
    
    public ArrayList<Movement> selectMove(ChessPiece chessPiece, ChessBoard chessBoard) {
        ArrayList<Movement> moveList = new ArrayList<>();
        if (chessPiece instanceof Pawn) {
            moveRangePawn(chessPiece, chessBoard, moveList);
        }
        if (chessPiece instanceof Rook) {
            moveRangeRook(chessPiece, chessBoard, moveList);
        }
        if (chessPiece instanceof Bishop) {
            moveRangeBishop(chessPiece, chessBoard, moveList);
        }
        if (chessPiece instanceof Queen) {
            moveRangeQueen(chessPiece, chessBoard, moveList);
        }
        if (chessPiece instanceof King) {
            moveRangeKing(chessPiece, chessBoard, moveList);
        }
        return moveList;
    }

    private void moveRangePawn(ChessPiece chessPiece, ChessBoard chessBoard, ArrayList<Movement> moveList) {
        if (chessPiece.getColour().equals("Black")) {
            if (chessPiece.getPosition().getRow() + 1 < chessBoard.getRow()){ 
                moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow() + 1, chessPiece.getPosition().getColumn())));
                if  (chessPiece.getPosition().getColumn() + 1 < chessBoard.getColumn())
                    moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow() + 1, chessPiece.getPosition().getColumn() + 1)));
                if (chessPiece.getPosition().getColumn() - 1 >= 0)
                    moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow() + 1, chessPiece.getPosition().getColumn() + 1)));
            }
        }
        if (chessPiece.getColour().equals("White")) {
            moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow() - 1, chessPiece.getPosition().getColumn())));
                if  (chessPiece.getPosition().getColumn() + 1 < chessBoard.getColumn())
                    moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow() - 1, chessPiece.getPosition().getColumn() - 1)));
                if (chessPiece.getPosition().getColumn() - 1 >= 0)
                    moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow() - 1, chessPiece.getPosition().getColumn() + 1)));
        }
    }

    private void moveRangeRook(ChessPiece chessPiece, ChessBoard chessBoard, ArrayList<Movement> moveList) {
        for (int i = 0; i < chessBoard.getRow(); i++) {
            moveList.add(new Movement(chessPiece.getPosition(), new Position(i, chessPiece.getPosition().getColumn())));
        }
        for (int i = 0; i < chessBoard.getColumn(); i++) {
            moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow(), i)));
        }
    }

    private void moveRangeBishop(ChessPiece chessPiece, ChessBoard chessBoard, ArrayList<Movement> moveList) {
        for (int i = 0; i < chessBoard.getColumn(); i++) {
            moveList.add(new Movement(chessPiece.getPosition(), new Position(i, i)));
        }
        for (int i = chessBoard.getRow(); i < chessBoard.getColumn(); i--) {
            moveList.add(new Movement(chessPiece.getPosition(), new Position(i, chessBoard.getRow() - 1 - i)));
        }
    }

    private void moveRangeQueen(ChessPiece chessPiece, ChessBoard chessBoard, ArrayList<Movement> moveList) {
        moveRangeBishop(chessPiece, chessBoard, moveList);
        moveRangeRook(chessPiece, chessBoard, moveList);
    }

    private void moveRangeKing(ChessPiece chessPiece, ChessBoard chessBoard, ArrayList<Movement> moveList) {
        if (chessPiece.getPosition().getRow() + 1 < chessBoard.getRow())
            moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow() + 1, chessPiece.getPosition().getColumn())));
        if (chessPiece.getPosition().getRow() + 1 < chessBoard.getRow() && chessPiece.getPosition().getColumn() - 1 >= 0)
            moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow() + 1, chessPiece.getPosition().getColumn() - 1)));
        if (chessPiece.getPosition().getColumn() - 1 >= 0)
            moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow(), chessPiece.getPosition().getColumn() - 1)));
        if (chessPiece.getPosition().getRow() - 1 >= 0 && chessPiece.getPosition().getColumn() - 1 >= 0)
            moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow() - 1, chessPiece.getPosition().getColumn() - 1)));
        if (chessPiece.getPosition().getRow() - 1 >= 0)
            moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow() - 1, chessPiece.getPosition().getColumn())));
        if (chessPiece.getPosition().getRow() - 1 >= 0 && chessPiece.getPosition().getColumn() + 1 < chessBoard.getColumn())
            moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow() - 1, chessPiece.getPosition().getColumn() + 1)));
        if (chessPiece.getPosition().getColumn() + 1 <= chessBoard.getColumn())
            moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow(), chessPiece.getPosition().getColumn() + 1)));
        if (chessPiece.getPosition().getRow() + 1 <= chessBoard.getRow() && chessPiece.getPosition().getColumn() + 1 <= chessBoard.getColumn())
            moveList.add(new Movement(chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow() + 1, chessPiece.getPosition().getColumn() + 1)));
    }
}
