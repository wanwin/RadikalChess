package Aima.Search;

import Aima.Game;
import Aima.Metrics;
import Aima.RadikalChessState;
import Model.ChessPiece;
import java.util.ArrayList;

public class MinimaxSearch<STATE, ACTION, PLAYER> implements
        AdversarialSearch<STATE, ACTION> {

    private Game<STATE, ACTION, PLAYER> game;
    private int expandedNodes;
    private static int totalExpandedNodes;

    public static <STATE, ACTION, PLAYER> MinimaxSearch<STATE, ACTION, PLAYER> createFor(
            Game<STATE, ACTION, PLAYER> game) {
        return new MinimaxSearch<>(game);
    }

    public MinimaxSearch(Game<STATE, ACTION, PLAYER> game) {
        this.game = game;
    }

    @Override
    public ACTION makeDecision(STATE state, ArrayList<ChessPiece> allPieces) {
        expandedNodes = 0;
        int currentDepth = 0;
        ACTION result = null;
        double resultValue = Double.NEGATIVE_INFINITY;
        for (ACTION action : game.getActions(state)) {
            double value = minValue(game.getResult(state, action), currentDepth);
            if (value > resultValue) {
                result = action;
                resultValue = value;
            }
        }
        totalExpandedNodes += expandedNodes;
        return result;
    }

    public double maxValue(STATE state, int currentDepth) {
        expandedNodes++;
        currentDepth++;
        if (game.isTerminal(state)) {
            return game.getUtility(state);
        }
        if (currentDepth > 5) {
            return game.getUtility(state);
        }
        double value = Double.NEGATIVE_INFINITY;
        for (ACTION action : game.getActions(state)) {
            value = Math.max(value,
                    minValue(game.getResult(state, action), currentDepth));
        }
        return value;
    }

    public double minValue(STATE state, int currentDepth) {
        expandedNodes++;
        currentDepth++;
        if (game.isTerminal(state)) {
            return game.getUtility(state);
        }
        if (currentDepth > 5) {
            return game.getUtility(state);
        }
        double value = Double.POSITIVE_INFINITY;
        for (ACTION action : game.getActions(state)) {
            value = Math.min(value,
                    maxValue(game.getResult(state, action), currentDepth));
        }
        return value;
    }

    @Override
    public Metrics getMetrics() {
        Metrics result = new Metrics();
        result.set("expandedNodes", expandedNodes);
        return result;
    }

    @Override
    public int getTotalExpandedNodes() {
        return totalExpandedNodes;
    }
}