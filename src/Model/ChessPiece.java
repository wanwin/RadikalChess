package Model;

public abstract class ChessPiece{
    
    private String name;
    private Position position;
    private Image image;

    public ChessPiece(String name, Position position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}