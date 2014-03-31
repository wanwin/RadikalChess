package Model;

public class Position {
    private int file;
    private int column;

    public Position(int file, int column) {
        this.file = file;
        this.column = column;
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}