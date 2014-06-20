package Aima.Heuristic;

import Aima.RadikalChessState;
import Model.Player;
import java.util.Random;

public class RandomHeuristic extends Heuristic{
   public double getHeuristic(RadikalChessState state, Player player){ 
       Random randomNumber = new Random(System.currentTimeMillis());
       return randomNumber.nextInt();
   }
}
