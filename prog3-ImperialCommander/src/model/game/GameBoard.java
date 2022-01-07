package model.game;

import java.util.Set;

import model.Board;
import model.Coordinate;
import model.Side;
import model.exceptions.InvalidSizeException;

/**@author Marta Grimaldos López, 50507753Y
 * Clase GameBoard
 * Subclase de Board
 */
public class GameBoard extends Board {
	

	/**
	 * Constructor 
	 *
	 * @param size the size
	 * @throws InvalidSizeException 
	 */
	public GameBoard(int size) throws InvalidSizeException {
		super(size);
	}
	
	/**
	 * Devuelve una cadena con una representacion del tablero
	 *
	 * @return string
	 */
	public String toString() {

		String concatenation = "  ";
		for(int num=0; num<getSize(); num++){
			concatenation+=num;
		}
		
		concatenation+="\n"+"  ";
		
		for(int num=0; num<getSize(); num++){
			concatenation+="-";
		}
		
		for(int i=0; i<getSize(); i++){
			
			concatenation+="\n";
			concatenation+= i + "|";
			
			for(int j=0; j<getSize();j++) {
				
				Coordinate c = new Coordinate(j,i);
				if(board.containsKey(c)) {
					concatenation+=board.get(c).getSymbol();
				}else {
					concatenation+=" ";
				}
				
			}
		}
		
		return concatenation;
	}
	
	/**
	 * Devuelve el número de cazas que hay en el tablero 
	 *
	 * @param side the side
	 * @return int
	 */
	public int numFighters(Side side) {
		
		int fighters=0;
		
		Set<Coordinate> coordenadas = board.keySet();
		for(Coordinate c:coordenadas) {
			if(board.get(c).getSide()==side)
				fighters++;
		}
		
		return fighters;
	}
}
