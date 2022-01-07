package model.game.score;

import model.Side;

/**@author Marta Grimaldos López, 50507753Y
 * Clase Score.
 * Para crear un sistema de clasificacion 
 *
 * @param <T> the generic type
 */
public abstract class Score<T> implements Comparable<Score<T>>{
	
	/** Puntuacion */
	protected int score;
	
	/** Bando */
	private Side side;
	
	/**
	 * Constructor
	 *
	 * @param side el bando
	 */
	public Score(Side side) {
		this.side=side;
		score=0;
	}
	
	/**
	 * Devuelve la puntuacion 
	 *
	 * @return la puntuacion
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Compare to.
	 *
	 * @param other the other
	 * @return the int
	 */
	@Override
	public int compareTo(Score<T> other) {
		if(this.getScore()>other.getScore())
			return -1;
		if((this.getScore()<other.getScore()))
			return 1;
		else
			return this.side.compareTo(other.side);
	}
	
	/**
	 * Cadena con la puntuacion de la nave
	 *
	 * @return the string
	 */
	public String toString() {
		String concatenation;
		concatenation="Player "+side+": "+score;
		return concatenation; 
	}
	
	/**
	 * Score, implementada en las clases WinsScore y DestroyedFightersScore
	 *
	 * @param sc the sc
	 */
	public abstract void score(T sc);	
}
