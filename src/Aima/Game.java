package Aima;

import Model.ChessPiece;
import java.util.ArrayList;
import java.util.List;

public interface Game<STATE, ACTION, PLAYER> {
	STATE getInitialState();
	PLAYER[] getPlayers();
	PLAYER getPlayer(STATE state);
	List<ACTION> getActions(STATE state, ArrayList<ChessPiece> allPieces);
	STATE getResult(STATE state, ACTION action, ArrayList<ChessPiece> allPieces);
	boolean isTerminal(ArrayList<ChessPiece> state);
	double getUtility(STATE state);
}