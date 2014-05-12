package Model;

public class Movement {

    private Position origin;
    private Position destination;

    public Movement(Position origin, Position destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public Position getOrigin() {
        return origin;
    }

    public void setOrigin(Position origin) {
        this.origin = origin;
    }

    public Position getDestination() {
        return destination;
    }

    public void setDestination(Position destination) {
        this.destination = destination;
    }
}