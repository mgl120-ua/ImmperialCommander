package model.exceptions;

import model.Coordinate;

/**@author Marta Grimaldos López, 50507753Y
 * Clase de la excepion  OutOfBoundsException.
 */
@SuppressWarnings("serial")
public class OutOfBoundsException extends java.lang.Exception{
    
    /** coordenada */
    private Coordinate c;
    
    /**
     * Crea una instancia de una excepción fuera de límites.
     *
     * @param c the c
     */
    public OutOfBoundsException(Coordinate c) {
        super();
        this.c = c;
    }
    
    /**
     * Devuelve un mensaje
     *
     * @return the message
     */
    public String getMessage() {
        return "ERROR: out of bounds "+c;
    }
}
