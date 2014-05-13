package Model;

import java.util.ArrayList;

public class Position {

    private int row;
    private int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    
    public int calculateEuclideanDistance(ArrayList<ChessPiece> allPieces, ChessBoard chessBoard, String colour) {
        if (chessBoard.searchKingPosition(allPieces, colour) != null){
            int rowDifference = this.getRow() - chessBoard.searchKingPosition(allPieces, colour).getRow();
            int columnDifference = this.getColumn() - chessBoard.searchKingPosition(allPieces, colour).getColumn();
            return (rowDifference* rowDifference + columnDifference * columnDifference);
        }
        return 0;
    }
}