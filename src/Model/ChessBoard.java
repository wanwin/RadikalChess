package Model;

import java.util.ArrayList;

public class ChessBoard implements Cloneable {

    private final Cell[][] cell;

    public ChessBoard(int row, int column) {
        cell = new Cell[row][column];
    }

    public int getColumn() {
        return cell.length;
    }

    public int getRow() {
        return cell[0].length;
    }

    public Cell[][] getCell() {
        return cell;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ChessBoard board = new ChessBoard(cell.length, cell[0].length);
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                if (cell[i][j].getChessPiece() != null) {
                    board.cell[i][j].setChessPiece((ChessPiece) cell[i][j].getChessPiece().clone());
                }
            }
        }
        return board;
    }
    
    public Position searchKingPosition(ArrayList<ChessPiece> allPieces, String colour) {
        for (ChessPiece actualChessPiece : allPieces) {
            if (actualChessPiece.getName().equals("King")
                    && !actualChessPiece.getColour().equals(colour)) {
                return actualChessPiece.getPosition();
            }
        }
        return null;
    }
}