package Aima.Heuristic;

import Aima.RadikalChessState;
import Model.ChessPiece;
import Model.Movement;
import Model.PieceMoveRange;
import Model.Player;
import java.util.ArrayList;

public abstract class Heuristic {

    public abstract int getHeuristic(RadikalChessState state, Player player);

    public boolean threatenedAdversarialKing(RadikalChessState state, Player player, ChessPiece chessPiece) {
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

    /*public boolean threatenedOwnKing(RadikalChessState state, Player player) {
     Player adversarialPlayer = player.getPlayerName().equals("White")
     ? new Player("Black") : new Player("White");
     ArrayList<Movement> actions = new ArrayList<>();
     RadikalChessState clonedState = state.clone();
     clonedState.setPlayer(adversarialPlayer);
     for (int i = 0; i < state.getChessBoard().getRow(); i++) {
     for (int j = 0; j < state.getChessBoard().getColumn(); j++) {
     if (state.getChessBoard().getCell()[i][j].getChessPiece() != null) {
     if (state.getChessBoard().getCell()[i][j].getChessPiece().getColour().
     equals(adversarialPlayer.getPlayerName())) {
     actions.addAll(PieceMoveRange.getInstance().
     selectMove(state.getChessBoard().getCell()[i][j].getChessPiece(), clonedState));
     for (Movement movement : actions) {
     if (adversarialPlayer.getPlayerName().equals("White")) {
     if (clonedState.destinationCell(movement).getChessPiece().getName().equals("King")
     && clonedState.destinationCell(movement).getChessPiece().
     getColour().equals("Black")) {
     return true;
     }
     }
     if (adversarialPlayer.getPlayerName().equals("Black")) {
     if (clonedState.destinationCell(movement).getChessPiece().getName().equals("King")
     && clonedState.destinationCell(movement).getChessPiece().
     getColour().equals("White")) {
     return true;
     }
     }
     }
     }

     }
     }
     }
     return false;
     }
     }*/
}