package Persistence;

import Model.ChessPiece;
import Model.Image;
import Model.Pieces.Bishop;
import Model.Pieces.King;
import Model.Pieces.Pawn;
import Model.Pieces.Queen;
import Model.Pieces.Rook;
import Model.Position;
import Model.SwingBitmap;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class ChessPieceLoader{
    
    ChessPiece[] chessPieceSet = new ChessPiece[8];
    String path;
    
    public ChessPieceLoader(String path){
        this.path = path;    
    }
    
    public ChessPiece[] load(String colour){
            createPieces(colour);
            return chessPieceSet;
        }

    private void createPawn(String colour){
        if (colour == "White")
            for (int i = 0; i < 4; i++) {
                chessPieceSet[i] = new Pawn(colour + "Pawn", new Position(4, i));
                chessPieceSet[i].setImage(loadImage(chessPieceSet[i].getName()));
            }
        else if (colour == "Black"){
            for (int i = 0; i < 4; i++) {
                chessPieceSet[i] = new Pawn(colour + "Pawn", new Position(1, 3 - i));
                chessPieceSet[i].setImage(loadImage(chessPieceSet[i].getName()));
            }    
        }
    }
    
    private void createKing(String colour){
        if (colour == "White"){
            chessPieceSet[4] = new King(colour + "King", new Position(5, 3));
            chessPieceSet[4].setImage(loadImage(chessPieceSet[4].getName()));
        }
        else if (colour == "Black"){
            chessPieceSet[4] = new King(colour + "King", new Position(0, 0));
            chessPieceSet[4].setImage(loadImage(chessPieceSet[4].getName()));
        }    
    }
    
    private void createQueen(String colour){
        if (colour == "White"){
            chessPieceSet[5] = new Queen(colour + "Queen", new Position(5, 2));
            chessPieceSet[5].setImage(loadImage(chessPieceSet[5].getName()));
        }
        else if (colour == "Black"){
            chessPieceSet[5] = new Queen(colour + "Queen", new Position(0, 1));
            chessPieceSet[5].setImage(loadImage(chessPieceSet[5].getName()));
        }    
    }
    
    private void createBishop(String colour){
        if (colour == "White"){
            chessPieceSet[6] = new Bishop(colour + "Bishop", new Position(5, 1));
            chessPieceSet[6].setImage(loadImage(chessPieceSet[6].getName()));
        }
        else if (colour == "Black"){
            chessPieceSet[6] = new Bishop(colour + "Bishop", new Position(0, 2));
            chessPieceSet[6].setImage(loadImage(chessPieceSet[6].getName()));
        }    
    }
    
    private void createRook(String colour){
        if (colour == "White"){
            chessPieceSet[7] = new Rook(colour + "Rook", new Position(5, 0));
            chessPieceSet[7].setImage(loadImage(chessPieceSet[7].getName()));
        }
        else if (colour == "Black"){
            chessPieceSet[7] = new Rook(colour + "Rook", new Position(0, 3));
            chessPieceSet[6].setImage(loadImage(chessPieceSet[6].getName()));
        }
    }
    
    private Image loadImage(String name){
        return new Image(new SwingBitmap(loadBufferedImage(name)));
    }

    private BufferedImage loadBufferedImage(String name){
        try {
            return ImageIO.read(new File(path + name));
        } 
        catch (IOException ex){
            return null;
        }
    }       
    
    private void createPieces(String colour){
        createPawn(colour);
        createBishop(colour);
        createKing(colour);
        createQueen(colour);
        createRook(colour);
    }
}
