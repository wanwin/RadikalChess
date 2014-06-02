package Model;

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

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (this.row != other.row) {
            return false;
        }
        if (this.column != other.column) {
            return false;
        }
        return true;
    }
    
    public int calculateEuclideanDistance(ChessBoard chessBoard, String colour) {
        if (chessBoard.searchKingPosition(colour) != null){
            int rowDifference = this.getRow() - chessBoard.searchKingPosition(colour).getRow();
            int columnDifference = this.getColumn() - chessBoard.searchKingPosition(colour).getColumn();
            return (rowDifference* rowDifference + columnDifference * columnDifference);
        }
        return 0;
    }
}