package model.exceptions;

/**@author Marta Grimaldos López, 50507753Y
 * Clase de la excepion NoFighterAvailableException.
 */
@SuppressWarnings("serial")
public class NoFighterAvailableException extends java.lang.Exception{
    
    /** tipo */
    private String type;
    
    /**
     * Crea una nueva excepción por luchador disponible.
     *
     * @param type the type
     */
    public NoFighterAvailableException(String type) {
        super();
        this.type = type;
    }
    
    /**
     * Devuelve un mensaje
     *
     * @return the message
     */
    public String getMessage() {
        return "ERROR: fighter no avilable "+type;
    }
}
