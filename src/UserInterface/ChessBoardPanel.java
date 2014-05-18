package UserInterface;

import Aima.RadikalChessState;
import Main.RadikalChess;
import Model.ChessPiece;
import Model.Image;
import Model.Movement;
import Model.Pieces.Pawn;
import Model.Pieces.Queen;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ChessBoardPanel extends JPanel {

    private CellButton[][] cellPanel;

    public ChessBoardPanel(int row, int column) {
        this.cellPanel = new CellButton[row][column];
    }

    public CellButton[][] getBoard() {
        return cellPanel;
    }

    public void updateChessPiece(Movement movement,
        ArrayList<ChessPiece> allPieces) {
        destinationCellButton(movement).addPiece(originCellButton(movement));
        originCellButton(movement).removePiece(); 
    }
    
    public void checkPromotionedPawn(Movement movement, ArrayList<ChessPiece> allPieces, RadikalChessState state) throws IOException {
        if (destinationCellButton(movement).getCell().getChessPiece() instanceof Pawn
                && (destinationCellButton(movement).getCell().getChessPiece().getPosition().getRow() == 0
                && destinationCellButton(movement).getCell().getChessPiece().getColour().equals("White")
                || destinationCellButton(movement).getCell().getChessPiece().getPosition().getRow()
                == cellPanel.length - 1 && destinationCellButton(movement).getCell().getChessPiece().getColour().equals("Black"))) {
            for (ChessPiece chessPiece : allPieces) {
                if (chessPiece.getName().equals(destinationCellButton(movement).getCell().getChessPiece().getName())
                        && chessPiece.getColour().equals(destinationCellButton(movement).getCell().getChessPiece().getColour())
                        && chessPiece.getPosition().equals(destinationCellButton(movement).getCell().getChessPiece().getPosition())) {
                    chessPiece = new Queen("Queen", chessPiece.getPosition(), chessPiece.getColour());
                    chessPiece.setImage(new Image(new SwingBitmap(ImageIO.read(new File(RadikalChess.filename + 
                    "/"
                    + chessPiece.getColour() + "Queen"
                    + ".png")))));
                    destinationCellButton(movement).getCell().setChessPiece(chessPiece);
                    state.getChessBoard().getCell()[movement.getDestination().getRow()][movement.getDestination().getColumn()].setChessPiece(chessPiece);
                    destinationCellButton(movement).setIcon(new ImageIcon(((SwingBitmap) destinationCellButton(movement).getCell().getChessPiece().getImage().getBitmap()).getBufferedImage()));
                    break;
                }
            }
        }
    }
    private CellButton originCellButton(Movement movement){
        return cellPanel[movement.getOrigin().getRow()][movement.getOrigin().getColumn()];
    }
    
    private CellButton destinationCellButton(Movement movement){
        return cellPanel[movement.getDestination().getRow()][movement.getDestination().getColumn()];
    }
}