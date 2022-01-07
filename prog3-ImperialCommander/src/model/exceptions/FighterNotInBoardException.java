package model.exceptions;

import model.Fighter;

/**@author Marta Grimaldos López, 50507753Y
 * Clase de la excepion  FighterNotInBoardException.
 */
@SuppressWarnings("serial")
public class FighterNotInBoardException extends java.lang.Exception{
    
    /** caza */
    private Fighter f;
    
    /**
     * Instancia de un caza que no está en el tablero.
     *
     * @param f the f
     */
    public FighterNotInBoardException(Fighter f) {
        super();
        this.f = f;
    }
    
    /**
     * Devuelve un mensaje
     *
     * @return the message
     */
    public String getMessage() {
        return "ERROR: fighter not in board "+f;
    }
}
