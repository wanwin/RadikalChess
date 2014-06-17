package Aima.Search;

import Aima.Game;
import Aima.Metrics;
import Aima.RadikalChessState;
import Model.Movement;
import Model.Player;

public class MinimaxSearch<STATE, ACTION, PLAYER> implements
        AdversarialSearch<STATE, ACTION> {

    private Game<STATE, ACTION, PLAYER> game;
    private int expandedNodes;
    private static int totalExpandedNodes, maxDepth;
    private static int turn = 1;
    private double time;

    public static <STATE, ACTION, PLAYER> MinimaxSearch<STATE, ACTION, PLAYER> createFor(
            Game<STATE, ACTION, PLAYER> game, int difficulty) {
        maxDepth = difficulty;
        return new MinimaxSearch<>(game);
    }

    public MinimaxSearch(Game<STATE, ACTION, PLAYER> game) {
        this.game = game;
    }

    @Override
    public ACTION makeDecision(STATE state) {
        expandedNodes = 0;
        int currentDepth = 0;
        ACTION result = null;
        double resultValue = Double.NEGATIVE_INFINITY;
        PLAYER player = (PLAYER) new Player(((Player)game.getPlayer(state)).getPlayerName());
        System.out.println(turn + "º Vuelta");
        double t1 = System.currentTimeMillis();
        for (ACTION action : game.getActions(state)) {
            double value = minValue(game.getResult(state, action), currentDepth, player);
            if (value > resultValue) {
                result = action;
                resultValue = value;
            }
            System.out.println(((RadikalChessState) state).originCell((Movement) action).
                    getChessPiece().toString() + " " + value);
        }
        if (result == null) {
            result = game.getActions(state).get(0);
        }
        time = System.currentTimeMillis() - t1;
        turn++;
        System.out.println("");
        totalExpandedNodes += expandedNodes;
        return result;
    }

    public double maxValue(STATE state, int currentDepth, PLAYER player) {
        expandedNodes++;
        currentDepth++;
        if (game.isTerminal(state) || currentDepth > maxDepth) {
            return game.getUtility(state, player);
        }
        double value = Double.NEGATIVE_INFINITY;
        for (ACTION action : game.getActions(state)) {
            value = Math.max(value,
                    minValue(game.getResult(state, action), currentDepth, player));
        }
        return value;
    }

    public double minValue(STATE state, int currentDepth, PLAYER player) {
        expandedNodes++;
        currentDepth++;
        if (game.isTerminal(state) || currentDepth > maxDepth) {
            return game.getUtility(state, player);
        }
        double value = Double.POSITIVE_INFINITY;
        for (ACTION action : game.getActions(state)) {
            value = Math.min(value,
                    maxValue(game.getResult(state, action), currentDepth, player));
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

    @Override
    public double getTime() {
        return time;
    }

    @Override
    public int getExpandedNodes() {
        return expandedNodes;
    }
}