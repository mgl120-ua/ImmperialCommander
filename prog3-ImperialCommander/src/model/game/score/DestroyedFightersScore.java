package model.game.score;

import model.Fighter;
import model.Side;

/**@author Marta Grimaldos López, 50507753Y
 * Clase DestroyedFightersScore.
 * LLeva la cuenta del valor de los cazas destruidos por la nave del jugador 
 */
public class DestroyedFightersScore extends Score<Fighter> {
	
	/**
	 * Constructor
	 *
	 * @param side the side
	 */
	public DestroyedFightersScore(Side side){
		super(side);
	}
	
	/**
	 * Score, suma el valor del caza destruido al atributo de la puntuacion 
	 *
	 * @param f the f
	 */
	@Override
	public void score(Fighter f) {
		if(f!=null)
			this.score+=f.getValue();
	}
}
