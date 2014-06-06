package Aima.Heuristic;

import Aima.RadikalChessState;
import Model.Player;

public class MediumHeuristic extends Heuristic {

    @Override
    public int getHeuristic(RadikalChessState state, Player player) {
        int heuristic = 0;
        for (int i = 0; i < state.getChessBoard().getRow(); i++) {
            for (int j = 0; j < state.getChessBoard().getColumn(); j++) {
                if (state.getChessBoard().getCell()[i][j].getChessPiece() != null
                        && "White".equals(state.getChessBoard().getCell()[i][j].getChessPiece().getColour())) {
                    heuristic += state.getChessBoard().getCell()[i][j].getChessPiece().getValue();
                    if (threatenedAdversarialKing(state, player, state.getChessBoard().getCell()[i][j].getChessPiece())) {
                        heuristic += 1000;
                    }
                } else if (state.getChessBoard().getCell()[i][j].getChessPiece() != null
                        && "Black".equals(state.getChessBoard().getCell()[i][j].getChessPiece().getColour())) {
                    heuristic -= state.getChessBoard().getCell()[i][j].getChessPiece().getValue();
                    if (threatenedAdversarialKing(state, player, state.getChessBoard().getCell()[i][j].getChessPiece())) {
                        heuristic -= 1000;
                    }
                }
            }
        }
        return Math.abs(heuristic);
    }
}
