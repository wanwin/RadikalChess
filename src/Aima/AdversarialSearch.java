package Aima;

public interface AdversarialSearch<STATE, ACTION> {
	ACTION makeDecision(STATE state);
	Metrics getMetrics();
	public int getTotalExpandedNodes();
}