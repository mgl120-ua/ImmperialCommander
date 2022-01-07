package model.game.score;

import model.Side;

/**@author Marta Grimaldos López, 50507753Y
 * Clase WinsScore.
 * LLeva la cuenta de las victorias obtenidas por la nave del jugador
 */
public class WinsScore extends Score<Integer> {
	
	/**
	 * Constructor
	 *
	 * @param side el bando
	 */
	public WinsScore(Side side) {
		super(side);
	}
	
	/**
	 * Score, incrementa a uno el atributo que lleva la puntuacion 
	 *
	 * @param w the w
	 */
	@Override
	public void score(Integer w){
		if(w!=null) {
			if(w==1) 
				this.score++;
		}
	}

}
