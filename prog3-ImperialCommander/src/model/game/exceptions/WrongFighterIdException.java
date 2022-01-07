package model.game.exceptions;

/**@author Marta Grimaldos López, 50507753Y
 * Clase de la excepción WrongFighterIdException.
 */
@SuppressWarnings("serial")
public class WrongFighterIdException extends java.lang.Exception {

	/** Identificador */
	private int id;
	
	/**
	 * Constructor de la excepcion 
	 *
	 * @param id the id
	 */
	public WrongFighterIdException(int id) {
		 super();
		 this.id = id;
	}
	
	/**
	 * Devuelve el mensaje de la excepcion
	 *
	 * @return the message
	 */
	public String getMessage() {
		return ("ERROR: wrong fighter id ");
	}
}
