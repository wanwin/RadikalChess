package Aima.Heuristic;

import Aima.RadikalChessState;
import Model.Player;

public class PiecesDifferenceHeuristic extends Heuristic {

    @Override
    public double getHeuristic(RadikalChessState state, Player player) {
        double heuristic = 0;
        heuristic = threatenedAdversarialPieces(state, player);
        for (int i = 0; i < state.getChessBoard().getRow(); i++) {
            for (int j = 0; j < state.getChessBoard().getColumn(); j++) {
                if (state.getChessBoard().getCell()[i][j].getChessPiece() != null
                        && player.getPlayerName().
                        equals(state.getChessBoard().getCell()[i][j].getChessPiece().getColour())) {
                    heuristic += state.getChessBoard().getCell()[i][j].getChessPiece().getValue();
                } else if (state.getChessBoard().getCell()[i][j].getChessPiece() != null
                        && player.getPlayerName().equals(state.getChessBoard().getCell()[i][j].getChessPiece().getColour())) {
                    heuristic -= state.getChessBoard().getCell()[i][j].getChessPiece().getValue();
                }
            }
        }
        return heuristic;
    }
}
