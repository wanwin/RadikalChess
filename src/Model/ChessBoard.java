package Model;

public class ChessBoard implements Cloneable {

    private final Cell[][] cell;

    public ChessBoard(int row, int column) {
        cell = new Cell[row][column];
    }

    public int getColumn() {
        return cell[0].length;
    }

    public int getRow() {
        return cell.length;
    }

    public Cell[][] getCell() {
        return cell;
    }

    @Override
    public ChessBoard clone() throws CloneNotSupportedException {
        ChessBoard board = new ChessBoard(cell.length, cell[0].length);
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                if (this.cell[i][j].getChessPiece() != null) {
                    board.cell[i][j] = new Cell((ChessPiece) this.cell[i][j].getChessPiece().clone(), new Position(i, j));
                } else {
                    board.cell[i][j] = new Cell(null, new Position(i, j));
                }
            }
        }
        return board;
    }

    public Position searchKingPosition(String colour){
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                if (cell[i][j].getChessPiece() != null) {
                    if (cell[i][j].getChessPiece().getName().equals("King")
                            && !cell[i][j].getChessPiece().getColour().equals(colour)) {
                        return cell[i][j].getPosition();
                    }
                }
            }
        }
        return null;
    }
}