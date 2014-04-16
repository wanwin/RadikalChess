package Model.aima;
  
import java.util.List; 
  
/** 
 * Provides an implementation of the Tic-tac-toe game which can be used for 
 * experiments with the Minimax algorithm. 
 *  
 * @author Ruediger Lunde 
 *  
 */
public class RadikalChessGame implements Game<RadikalChessState, XYLocation, String> { 
      
    RadikalChessState initialState = new RadikalChessState();

    @Override
    public RadikalChessState getInitialState() { 
        return initialState; 
    } 
  
    @Override
    public String[] getPlayers() { 
        return new String[] {RadikalChessState.X, RadikalChessState.O }; 
    } 
  
    @Override
    public String getPlayer(RadikalChessState state) { 
        return state.getPlayerToMove(); 
    } 
  
    @Override
    public List<XYLocation> getActions(RadikalChessState state) { 
        return state.getUnMarkedPositions(); 
    } 
  
    @Override
    public RadikalChessState getResult(RadikalChessState state, XYLocation action) { 
        RadikalChessState result = state.clone(); 
        result.mark(action); 
        return result; 
    } 
  
    @Override
    public boolean isTerminal(RadikalChessState state) { 
        return state.getUtility() != -1; 
    } 
  
    @Override
    public double getUtility(RadikalChessState state, String player) { 
        double result = state.getUtility(); 
        if (result != -1) { 
            if (player == RadikalChessState.O) 
                result = 1 - result; 
        } else { 
            throw new IllegalArgumentException("State is not terminal."); 
        } 
        return result; 
    } 
} 