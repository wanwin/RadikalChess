package Aima;

import Model.ChessPiece;
import Model.Movement;
import Model.PieceMoveRange;
import Model.Player;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RadikalChessGame implements Game<RadikalChessState, Movement, Player> {

    private RadikalChessState initialState;
    private RadikalChessState actualState;

    public RadikalChessState getActualState() {
        return actualState;
    }

    @Override
    public RadikalChessState getInitialState() {
        return initialState;
    }

    public void setActualState(RadikalChessState actualState) {
        this.actualState = actualState;
    }

    @Override
    public List<Movement> getActions(ArrayList<ChessPiece> allPieces){
        ArrayList<Movement> actions = new ArrayList<>();
        for (ChessPiece chessPiece : allPieces) {
            actions.addAll(PieceMoveRange.getInstance().selectMove(chessPiece, actualState.getChessBoard()));     
        }
        return actions;
    }

    @Override
    public RadikalChessState getResult(RadikalChessState state, Movement action, ArrayList<ChessPiece> allPieces){
        RadikalChessState result;
        result = state.clone();
        result.possibleMove(action, state.getChessBoard(), allPieces, null);
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
        return null;
    }

    @Override
    public double getUtility(RadikalChessState state, Player player) {
        return 0;
    }
}