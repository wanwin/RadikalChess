package Aima.Heuristic;

import Aima.RadikalChessState;
import Model.ChessPiece;
import Model.Movement;
import Model.PieceMoveRange;
import Model.Player;
import java.util.ArrayList;

public abstract class Heuristic {

    public abstract int getHeuristic(RadikalChessState state, Player player);

    public int threatenedAdversarialPieces(RadikalChessState state, Player player, ChessPiece chessPiece) {
        int threatValue = 0;
        ArrayList<Movement> actions = new ArrayList<>();
        actions.addAll(PieceMoveRange.getInstance().selectMove(chessPiece, state));
        for (Movement movement : actions) {
            if (state.destinationCell(movement).getChessPiece() != null) {
                threatValue += state.destinationCell(movement).getChessPiece().getValue();
            }
        }
        return threatValue;
    }
}