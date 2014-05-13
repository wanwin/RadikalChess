package UserInterface;

import Model.ChessPiece;
import java.util.ArrayList;
import javax.swing.JPanel;

public class ChessBoardPanel extends JPanel {

    private final CellButton[][] cellPanel;

    public ChessBoardPanel(int row, int column) {
        this.cellPanel = new CellButton[row][column];
    }

    public CellButton[][] getBoard() {
        return cellPanel;
    }

    public void updateChessPieceIcon(CellButton firstClicked, CellButton secondClicked,
            ArrayList<ChessPiece> allPieces) {
        for (ChessPiece chessPiece : allPieces) {
            if (chessPiece.getName().equals(firstClicked.getCell().getChessPiece().getName())
                    && chessPiece.getColour().equals(firstClicked.getCell().getChessPiece().getColour())
                    && chessPiece.getPosition().equals(firstClicked.getCell().getChessPiece().getPosition())) {
                chessPiece.setPosition(secondClicked.getCell().getPosition());
            }
        }
        secondClicked.addPiece(firstClicked);
        firstClicked.removePiece();
    }
}