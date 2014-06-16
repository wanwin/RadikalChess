package Aima.Heuristic;

import Aima.RadikalChessState;
import Model.Player;

public class NumberOfAttackedPiecesHeuristic extends Heuristic {

    @Override
    public double getHeuristic(RadikalChessState state, Player player) {
        double heuristic = 0;
        for (int i = 0; i < state.getChessBoard().getRow(); i++) {
            for (int j = 0; j < state.getChessBoard().getColumn(); j++) {
                if (state.getChessBoard().getCell()[i][j].getChessPiece() != null
                        && state.getPlayer().getPlayerName().equals(state.getChessBoard().getCell()[i][j].
                        getChessPiece().getColour())) {
                    heuristic += state.getChessBoard().getCell()[i][j].getChessPiece().getValue();
                    heuristic += numberOfAttackedPieces(state, player, state.getChessBoard().
                            getCell()[i][j].getChessPiece());
                } else if (state.getChessBoard().getCell()[i][j].getChessPiece() != null
                        && !state.getPlayer().getPlayerName().equals(state.getChessBoard().getCell()[i][j].
                        getChessPiece().getColour())) {
                    heuristic -= state.getChessBoard().getCell()[i][j].getChessPiece().getValue();
                    heuristic += numberOfAttackedPieces(state, player,
                            state.getChessBoard().getCell()[i][j].getChessPiece());
                }
            }
        }
        return heuristic;
    }
}