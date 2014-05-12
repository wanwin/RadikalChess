package Aima;

import Model.ChessPiece;
import Model.Movement;
import Model.Player;
import java.util.ArrayList;
import java.util.List;

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
    public List<Movement> getActions(RadikalChessState state){
        return null;
    }

    @Override
    public RadikalChessState getResult(RadikalChessState state, Movement movement) {
        return null;
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