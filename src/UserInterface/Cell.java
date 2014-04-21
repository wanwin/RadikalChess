package UserInterface;

import Model.ChessPiece;
import Model.Image;
import javax.swing.JButton;

public class Cell extends JButton{
    
    private ChessPiece chessPiece;
    
    public ChessPiece getChessPiece() {
        return chessPiece;
    }

    public void setChessPiece(ChessPiece chessPiece) {
        this.chessPiece = chessPiece;
    }
}
