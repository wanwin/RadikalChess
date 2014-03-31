package Model;

public class ChessPiece {
    private final String name;
    private Movement movement;

    public ChessPiece(String name, Movement movement) {
        this.name = name;
        this.movement = movement;
    }

    public String getName() {
        return name;
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }
}