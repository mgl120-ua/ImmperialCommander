package model.game.score;

import java.util.SortedSet;
import java.util.TreeSet;

/**@author Marta Grimaldos López, 50507753Y
 * Clase Ranking.
 * Una lista ordenada de las puntuaciones 
 *
 * @param <ScoreType> the generic type
 */
public class Ranking<ScoreType extends Score<?>>{
	
	/** The score set. */
	private SortedSet<ScoreType> scoreSet;
	
	/**
	 * Constructor
	 */
	public Ranking() {
		scoreSet = new TreeSet<>();
	}
	
	/**
	 * Añade puntuaciones al ranking
	 *
	 * @param st the st
	 */
	public void addScore(ScoreType st) {
		getSortedRanking().add(st);
	}
	
	/**
	 * Devuelve el ranking de las puntuaciones
	 *
	 * @return the sorted ranking
	 */
	public SortedSet<ScoreType> getSortedRanking(){
		return scoreSet;
	}
	
	/**
	 * Devuelve el ganador
	 *
	 * @return the winner
	 */
	public ScoreType getWinner() {
		if(scoreSet.isEmpty())
			throw new  RuntimeException ("ERROR: SCORE VACIO");
		
		return scoreSet.first();
	}
	
	/**
	 * Crea una cadena con las puntuaciones de las naves de los dos jugadores ordenada con la nave del jugador con mas puntuacion primero
	 *
	 * @return the string
	 */
	public String toString() {
		String concatenation = "";
		for(ScoreType sc: scoreSet) {
			concatenation+="|"+sc.toString();
		}
		concatenation+="|";
		return concatenation;		
	}
}
