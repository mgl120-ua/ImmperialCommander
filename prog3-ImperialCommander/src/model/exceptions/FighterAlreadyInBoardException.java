package model.exceptions;

import model.Fighter;

/**@author Marta Grimaldos López, 50507753Y
 * Clase de la excepión FighterAlreadyInBoardException.
 */
@SuppressWarnings("serial")
public class FighterAlreadyInBoardException extends java.lang.Exception{
    
    /** caza */
    private Fighter f;
    
    /**
     * Crea una instancia de un caza que ya está en el tablero
     *
     * @param f the f
     */
    public FighterAlreadyInBoardException(Fighter f) {
        super();
        this.f = f;
    }
    
    /**
     * Devuelve un mensaje
     *
     * @return the message
     */
    public String getMessage() {
        return "ERROR: fighter already in board "+f;
    }
}
