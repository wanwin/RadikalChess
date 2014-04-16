package Main;

import Model.ChessPiece;
import Persistence.ChessPieceLoader;
import View.ApplicationFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        new Application().execute();
    }
    
    private static String PATH = "C:\\Users\\Darwin\\Desktop\\Fichas\\";
    private ApplicationFrame frame;
    
    private void execute(){
        ChessPiece[] WhiteChessPieceSet = new ChessPieceLoader(PATH).load("White");
        ChessPiece[] BlackChessPieceSet = new ChessPieceLoader(PATH).load("Black");
        frame = new ApplicationFrame(BlackChessPieceSet, WhiteChessPieceSet);
        frame.setVisible(true);
    }
}