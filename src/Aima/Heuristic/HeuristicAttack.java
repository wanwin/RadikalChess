package Aima.Heuristic;

import Aima.RadikalChessState;
import Model.ChessPiece;

public class HeuristicAttack implements Heuristic {

    @Override
    public int getHeuristic(RadikalChessState state, ChessPiece chessPiece) {
        int heuristic=0;
        for (int i=0;i<state.getChessBoard().getRow(); i++) {
            for (int j=0;j<state.getChessBoard().getColumn(); j++) {
                if(state.getChessBoard().getCell()[i][j].getChessPiece()!=null&&
                        "White".equals(state.getChessBoard().getCell()[i][j].getChessPiece().getColour()))
                    heuristic+=state.getChessBoard().getCell()[i][j].getChessPiece().getValue();
                else if(state.getChessBoard().getCell()[i][j].getChessPiece()!=null&&
                        "Black".equals(state.getChessBoard().getCell()[i][j].getChessPiece().getColour()))
                    heuristic-=state.getChessBoard().getCell()[i][j].getChessPiece().getValue();
            }
        }
        return heuristic;
    }
}