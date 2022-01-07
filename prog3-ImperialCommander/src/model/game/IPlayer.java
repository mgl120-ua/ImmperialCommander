package model.game;

import model.game.score.DestroyedFightersScore;
import model.game.score.WinsScore;

// TODO: Auto-generated Javadoc
/**
 * The Interface IPlayer.
 *
 * @author Marta Grimaldos López, 50507753Y
 * Interfaz IPlayer
 * Modela un jugador
 */
public interface IPlayer {
	
	/**
	 * Asigna el tablero pasado como parametro.
	 *
	 * @param gb el nuevo tablero
	 */
	public void setBoard(GameBoard gb);
	
	/**
	 * Devuelve la nave del jugador.
	 *
	 * @return the game ship
	 */
	public GameShip getGameShip();
	
	/**
	 * Obtiene una cadena de cazas y los añade.
	 */
	public void initFighters();
	
	/**
	 * Comprueba si se ha destruido la nave.
	 *
	 * @return true, if is fleet destroyed
	 */
	public boolean isFleetDestroyed();
	
	/**
	 * Devuelve una cadena con la flota.
	 *
	 * @return the string
	 */
	public String showShip();
	
	/**
	 * Elimina los cazas destruidos de la flota.
	 */
	public void purgeFleet();
	
	/**
	 * Hace una jugada .
	 *
	 * @return true, if successful
	 */
	public boolean nextPlay();
	
	/**
	 * Gets the wins score.
	 *
	 * @return the wins score
	 */
	public WinsScore getWinsScore();
	
	/**
	 * Gets the destroyed fighters score.
	 *
	 * @return the destroyed fighters score
	 */
	public DestroyedFightersScore getDestroyedFightersScore();
}
