package model.exceptions;

/**@author Marta Grimaldos López, 50507753Y
 * Clase de la excepion  InvalidSizeException.
 */
@SuppressWarnings("serial")
public class InvalidSizeException extends java.lang.Exception{
    
    /** tamaño  */
    private int size;
    
    /**
     * Crea una instancia de un tamaño no válido.
     *
     * @param size the size
     */
    public InvalidSizeException(int size) {
        super();
        this.size = size;
    }
    
    /**
     * Devuelve un mensaje
     *
     * @return the message
     */
    public String getMessage() {
        return "ERROR: invalid size "+size;
    }
}
