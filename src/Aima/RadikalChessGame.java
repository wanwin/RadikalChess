package Aima;

import Aima.Heuristic.NumberOfAttackedPiecesHeuristic;
import Aima.Heuristic.PiecesDifferenceHeuristic;
import Aima.Heuristic.RandomHeuristic;
import Model.Movement;
import Model.PieceMoveRange;
import Model.Player;
import java.util.ArrayList;
import java.util.List;

public class RadikalChessGame implements Game<RadikalChessState, Movement, Player> {

    private RadikalChessState initialState;

    @Override
    public RadikalChessState getInitialState() {
        return initialState;
    }

    @Override
    public List<Movement> getActions(RadikalChessState state) {
        ArrayList<Movement> actions = new ArrayList<>();
        for (int i = 0; i < state.getChessBoard().getRow(); i++) {
            for (int j = 0; j < state.getChessBoard().getColumn(); j++) {
                if (state.getChessBoard().getCell()[i][j].getChessPiece() != null
                        && state.getChessBoard().getCell()[i][j].getChessPiece().getColour().
                        equals(state.getPlayer().getPlayerName())) {
                    actions.addAll(PieceMoveRange.getInstance().
                            selectMove(state.getChessBoard().getCell()[i][j].getChessPiece(), state));
                }
            }
        }
        return actions;
    }

    @Override
    public RadikalChessState getResult(RadikalChessState state, Movement action) {
        RadikalChessState result;
        result = state.clone();
        result.mark(action);
        return result;
    }

    @Override
    public boolean isTerminal(RadikalChessState state) {
        int numberOfKings = 0;
        for (int i = 0; i < state.getChessBoard().getRow(); i++) {
            for (int j = 0; j < state.getChessBoard().getColumn(); j++) {
                if (state.getChessBoard().getCell()[i][j].getChessPiece() != null) {
                    if (state.getChessBoard().getCell()[i][j].getChessPiece().getName().equals("King")) {
                        numberOfKings++;
                    }
                }
            }
        }
        return (numberOfKings != 2 || getActions(state).isEmpty());
    }

    @Override
    public Player[] getPlayers() {
        return null;
    }

    @Override
    public Player getPlayer(RadikalChessState state) {
        return state.getPlayer();
    }

    @Override
    public double getUtility(RadikalChessState state, Player player) {
        double result = 0;
        if (player.getPlayerName().equals("White")) {
            result = new PiecesDifferenceHeuristic().getHeuristic(state, player);
        } else {
            result = new NumberOfAttackedPiecesHeuristic().getHeuristic(state, player);
        }
        return result;
    }
}