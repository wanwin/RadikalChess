package Aima.Heuristic;

import Aima.RadikalChessState;
import Model.Movement;
import Model.PieceMoveRange;
import Model.Player;
import java.util.ArrayList;

public class NumberOfAttackedPiecesHeuristic extends Heuristic {

    @Override
    public double getHeuristic(RadikalChessState state, Player player) {
        double heuristic = 0;
        heuristic = threatenedAdversarialPieces(state, player);
        for (int i = 0; i < state.getChessBoard().getRow(); i++) {
            for (int j = 0; j < state.getChessBoard().getColumn(); j++) {
                if (state.getChessBoard().getCell()[i][j].getChessPiece() != null
                        && player.getPlayerName().
                        equals(state.getChessBoard().getCell()[i][j].getChessPiece().getColour())) {
                    ArrayList<Movement> movements = new ArrayList<>();
                    movements.addAll(PieceMoveRange.getInstance().selectMove(state.getChessBoard().getCell()[i][j].getChessPiece(), state));
                    for (Movement movement : movements) {
                        if (state.getChessBoard().getCell()[movement.getDestination().getRow()][movement.getDestination().getColumn()].getChessPiece() != null) {
                            heuristic += state.getChessBoard().getCell()[movement.getDestination().getRow()][movement.getDestination().getColumn()].getChessPiece().getValue();
                        }
                    }
                } else if (state.getChessBoard().getCell()[i][j].getChessPiece() != null
                        && player.getPlayerName().
                        equals(state.getChessBoard().getCell()[i][j].getChessPiece().getColour())) {
                    ArrayList<Movement> movements = new ArrayList<>();
                    Player adversarialPlayer = new Player((player.getPlayerName().equals("White")) ? "Black" : "White");
                    RadikalChessState clonedState = new RadikalChessState(state.getChessBoard(), adversarialPlayer);
                    movements.addAll(PieceMoveRange.getInstance().selectMove(state.getChessBoard().getCell()[i][j].getChessPiece(), clonedState));
                    for (Movement movement : movements) {
                        if (state.getChessBoard().getCell()[movement.getDestination().getRow()][movement.getDestination().getColumn()].getChessPiece() != null) {
                            heuristic -= state.getChessBoard().getCell()[movement.getDestination().getRow()][movement.getDestination().getColumn()].getChessPiece().getValue();
                        }
                    }
                }
            }
        }
        return heuristic;
    }
}
