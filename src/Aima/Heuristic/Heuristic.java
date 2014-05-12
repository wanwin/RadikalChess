package Aima.Heuristic;

import Aima.RadikalChessState;
import Model.ChessPiece;

public interface Heuristic {
    public int getHeuristic(RadikalChessState state, ChessPiece chessPiece);
}