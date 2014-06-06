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
                    heuristic += threatenedAdversarialPieces(state, player, state.getChessBoard().
                            getCell()[i][j].getChessPiece());
                } else if (state.getChessBoard().getCell()[i][j].getChessPiece() != null
                        && "Black".equals(state.getChessBoard().getCell()[i][j].getChessPiece().getColour())) {
                    heuristic -= state.getChessBoard().getCell()[i][j].getChessPiece().getValue();
                    RadikalChessState clonedState = state.clone();
                    clonedState.setPlayer((clonedState.getPlayer().getPlayerName().equals("White"))
                            ? new Player("Black") : new Player("White"));
                    heuristic -= threatenedAdversarialPieces(clonedState, player,
                            clonedState.getChessBoard().getCell()[i][j].getChessPiece());
                }
            }
        }
        return Math.abs(heuristic);
    }
}
