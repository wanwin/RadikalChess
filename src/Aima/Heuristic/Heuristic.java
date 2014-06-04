package Aima.Heuristic;

import Aima.RadikalChessState;
import Model.ChessPiece;
import Model.Movement;
import Model.PieceMoveRange;
import Model.Player;
import java.util.ArrayList;

public abstract class Heuristic {

    public abstract int getHeuristic(RadikalChessState state, Player player);

    public boolean threatenedKing(RadikalChessState state, Player player, ChessPiece chessPiece) {
        ArrayList<Movement> actions = new ArrayList<>();
        actions.addAll(PieceMoveRange.getInstance().selectMove(chessPiece, state));
        for (Movement movement : actions) {
            if (state.destinationCell(movement).getChessPiece() != null) {
                if (player.getPlayerName().equals("White")) {
                    if (state.destinationCell(movement).getChessPiece().getName().equals("King") 
                            && state.destinationCell(movement).getChessPiece().getColour().equals("Black")) {
                        return true;
                    }
                }    
                if (player.getPlayerName().equals("Black")) {
                    if (state.destinationCell(movement).getChessPiece().getName().equals("King") 
                            && state.destinationCell(movement).getChessPiece().getColour().equals("White")) {
                        return true;
                    }
                } 
            }
        }
        return false;
    }
}
