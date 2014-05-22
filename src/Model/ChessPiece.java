package Model;

public abstract class ChessPiece {

    private String name;

    public void setName(String name) {
        this.name = name;
    }
    private Position position;
    private Image image;
    private String colour;

    public ChessPiece(String name, Position position, String colour) {
        this.name = name;
        this.position = position;
        this.colour = colour;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getColour() {
        return colour;
    }
    
    public void setColour(String colour) {
        this.colour = colour;
    }
    
    @Override
    public abstract ChessPiece clone();

    public abstract int getValue();
}