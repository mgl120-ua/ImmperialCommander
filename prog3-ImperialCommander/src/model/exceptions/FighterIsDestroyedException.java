package model.exceptions;

import model.Fighter;

/**@author Marta Grimaldos López, 50507753Y
 * Clase de la excepion FighterIsDestroyedException.
 */
@SuppressWarnings("serial")
public class FighterIsDestroyedException extends java.lang.Exception{
    
    /** caza */
    private Fighter f;
    
    /**
     * Instancia un caza que esta destruido
     *
     * @param f the f
     */
    public FighterIsDestroyedException(Fighter f) {
        super();
        this.f = f;
    }
    
    /**
     * Devuelve un mensaje
     *
     * @return the message
     */
    public String getMessage() {
        return "ERROR: fighter is destroyed"+f;
    }
}
