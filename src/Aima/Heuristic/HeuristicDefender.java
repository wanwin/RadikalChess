package Aima.Heuristic;

import Aima.RadikalChessState;
import Model.ChessPiece;

public class HeuristicDefender implements Heuristic {

    @Override
    public int getHeuristic(RadikalChessState state, ChessPiece chessPiece) {
        int heuristic=0;
        for (int i=0;i<state.getChessBoard().getRow();i++) {
            for (int j=0;j<state.getChessBoard().getColumn();j++) {
                if(state.getChessBoard().getCell()[i][j].getChessPiece()!=null){
                    // CHEQUEAR MOVIMIENTO DE ATAQUE
                    //heuristic+=
                }
            }
        }
        return heuristic;
    }
}