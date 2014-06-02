package Persistence;

import Model.ChessPiece;
import Model.Image;
import Model.Pieces.Bishop;
import Model.Pieces.King;
import Model.Pieces.Pawn;
import Model.Pieces.Queen;
import Model.Pieces.Rook;
import Model.Position;
import UserInterface.SwingBitmap;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class ChessPieceLoader {

    ArrayList<ChessPiece> chessPieceSet = new ArrayList<>();
    String path;

    public ChessPieceLoader(String path) {
        this.path = path;
    }

    public ArrayList<ChessPiece> load(String colour) {
        createPieces(colour);
        return chessPieceSet;
    }

    private void createPieces(String colour) {
        //createPawn(colour);
        createKing(colour);
        //createQueen(colour);
        createBishop(colour);
        //createRook(colour);
    }

    private void createPawn(String colour) {
        if ("white".equals(colour.toLowerCase())) {
            for (int i = 0; i < 4; i++) {
                chessPieceSet.add(new Pawn("Pawn" + i, new Position(4, i), colour));
                chessPieceSet.toArray(new ChessPiece[8])[i].setImage(loadImage(colour + "Pawn"));
            }
        }
        else if ("black".equals(colour.toLowerCase())) {
            for (int i = 0; i < 4; i++) {
                chessPieceSet.add(new Pawn("Pawn" + i, new Position(1, 3 - i), colour));
                chessPieceSet.toArray(new ChessPiece[8])[i].setImage(loadImage(colour + "Pawn"));
            }
        }
    }

    private void createKing(String colour) {
        if ("white".equals(colour.toLowerCase())) {
            chessPieceSet.add(new King("King", new Position(5, 3), colour));
            chessPieceSet.toArray(new ChessPiece[8])[0].setImage(loadImage(colour + "King"));
        }
        else if ("black".equals(colour.toLowerCase())) {
            chessPieceSet.add(new King("King", new Position(0, 0), colour));
            chessPieceSet.toArray(new ChessPiece[8])[0].setImage(loadImage(colour + "King"));
        }
    }

    private void createQueen(String colour) {
        if ("white".equals(colour.toLowerCase())) {
            chessPieceSet.add(new Queen("Queen", new Position(5, 2), colour));
            chessPieceSet.toArray(new ChessPiece[8])[5].setImage(loadImage(colour + "Queen"));
        }
        else if ("black".equals(colour.toLowerCase())) {
            chessPieceSet.add(new Queen("Queen", new Position(0, 1), colour));
            chessPieceSet.toArray(new ChessPiece[8])[5].setImage(loadImage(colour + "Queen"));
        }
    }

    private void createBishop(String colour) {
        if ("white".equals(colour.toLowerCase())) {
            chessPieceSet.add(new Bishop("Bishop", new Position(5, 1), colour));
            chessPieceSet.toArray(new ChessPiece[8])[1].setImage(loadImage(colour + "Bishop"));
        }
        else if ("black".equals(colour.toLowerCase())) {
            chessPieceSet.add(new Bishop("Bishop", new Position(0, 2), colour));
            chessPieceSet.toArray(new ChessPiece[8])[1].setImage(loadImage(colour + "Bishop"));
        }
    }

    private void createRook(String colour) {
        if ("white".equals(colour.toLowerCase())) {
            chessPieceSet.add(new Rook("Rook", new Position(5, 0), colour));
            chessPieceSet.toArray(new ChessPiece[8])[7].setImage(loadImage(colour + "Rook"));
        }
        else if ("black".equals(colour.toLowerCase())) {
            chessPieceSet.add(new Rook("Rook", new Position(0, 3), colour));
            chessPieceSet.toArray(new ChessPiece[8])[7].setImage(loadImage(colour + "Rook"));
        }
    }

    private Image loadImage(String name) {
        return new Image(new SwingBitmap(loadBufferedImage(name)));
    }

    private BufferedImage loadBufferedImage(String name) {
        try {
            return ImageIO.read(new File(path + "/" + name + ".png"));
        }
        catch (IOException ex) {
            return null;
        }
    }
}
