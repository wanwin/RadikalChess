package Aima;

import Aima.Heuristic.HeuristicAttack;
import Model.ChessPiece;
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
    public List<Movement> getActions(RadikalChessState state, ArrayList<ChessPiece> allPieces) {
        ArrayList<Movement> actions = new ArrayList<>();
        for (ChessPiece chessPiece : allPieces) {
            actions.addAll(PieceMoveRange.getInstance().selectMove(chessPiece, state, allPieces));    
        }
        return actions;
    }

    @Override
    public RadikalChessState getResult(RadikalChessState state, Movement action, ArrayList<ChessPiece> allPieces) {
        RadikalChessState result;
        result = state.clone();
        result.possibleMove(action, allPieces);
        return result;
    }

    @Override
    public boolean isTerminal(ArrayList<ChessPiece> allPieces) {
        int numberOfKings = 0;
        for (ChessPiece chessPiece : allPieces) {
            if (chessPiece.getName().equals("King")) {
                numberOfKings++;
            }
        }
        return (numberOfKings != 2);
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
    public double getUtility(RadikalChessState state) {
        HeuristicAttack heuristic = new HeuristicAttack();
        return heuristic.getHeuristic(state);
    }
}