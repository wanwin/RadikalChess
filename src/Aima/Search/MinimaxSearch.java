package Aima.Search;

import Aima.Game;
import Aima.Metrics;
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
        PLAYER player = game.getPlayer(state);
        for (ACTION action : game.getActions(state, allPieces)){
            double value = minValue(game.getResult(state, action, allPieces), player, allPieces, currentDepth);
            if (value > resultValue){
                result = action;
                resultValue = value;
            }
        }
        currentDepth++;
        totalExpandedNodes += expandedNodes;
        return result;
    }

    public double maxValue(STATE state, PLAYER player, ArrayList<ChessPiece> allPieces, int currentDepth) {
        expandedNodes++;
        currentDepth++;
        if (game.isTerminal(allPieces)) {
            return game.getUtility(state);
        }
        if (currentDepth == 5)
            return game.getUtility(state);
        double value = Double.NEGATIVE_INFINITY;
        for (ACTION action : game.getActions(state, allPieces)){
            currentDepth++;
            value = Math.max(value,
                    minValue(game.getResult(state, action, allPieces), player, allPieces, currentDepth));
        }
        return value;
    }

    public double minValue(STATE state, PLAYER player, ArrayList<ChessPiece> allPieces, int currentDepth) {
        expandedNodes++;
        currentDepth++;
        if (game.isTerminal(allPieces)) {
            return game.getUtility(state);
        }
        if (currentDepth == 5)
            return game.getUtility(state);
        double value = Double.POSITIVE_INFINITY;
        for (ACTION action : game.getActions(state, allPieces)) {
            currentDepth++;
            value = Math.min(value,
                    maxValue(game.getResult(state, action, allPieces), player, allPieces, currentDepth));
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