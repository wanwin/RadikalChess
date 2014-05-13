package Aima.Search;

import Aima.Metrics;
import Model.ChessPiece;
import java.util.ArrayList;

public interface AdversarialSearch<STATE, ACTION> {
	ACTION makeDecision(STATE state, ArrayList<ChessPiece> allPieces);
	Metrics getMetrics();
	public int getTotalExpandedNodes();
}