package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterIsDestroyedException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.InvalidSizeException;
import model.exceptions.OutOfBoundsException;

/** @author Marta Grimaldos López, 50507753Y
 * Clase Board
 * Representa el tablero cuadrado donde se desarrolla el juego
 */
public class Board {
	
	/** Tamaño del tablero */
	private int size;
	
	/** Mapa para alamacenar los cazas en posiciones del tablero */
	private Map<Coordinate, Fighter> board;
	
	/**
	 * Constructor del tablero
	 *
	 * @param size (tamaño del tablero)
	 * @throws InvalidSizeException 
	 */
	public Board(int size) throws InvalidSizeException {
		if(size<5)
			throw new InvalidSizeException(size);
		this.size=size;
		board=new HashMap<Coordinate, Fighter>();
	}
	
	/**
	 * Devuelve el caza que esta en la coordenada c
	 *
	 * @param c 
	 * @return caza
	 */
	public Fighter getFighter(Coordinate c) {
		Objects.requireNonNull(c);
		if(board.get(c)==null)
			return null;
		return board.get(c).copy();
	}

	/**
	 * Devuelve el tamaño del tablero
	 *
	 * @return size
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Elimina caza del tablero 
	 *
	 * @param f (caza)
	 * @return true si se ha borrado, false si no existe el caza en el mapa
	 * @throws FighterNotInBoardException 
	 */
	public boolean removeFighter(Fighter f) throws FighterNotInBoardException {
		Objects.requireNonNull(f);
		
		if(board.containsKey(f.getPosition())) {
			if(board.get(f.getPosition()).equals(f)) {
				board.remove(f.getPosition());
				f.setPosition(null);
				return true;	
			}
			if(!(board.containsValue(f)) || !(board.get(f.getPosition()).equals(f)))
					throw new FighterNotInBoardException(f);
		}else 
			throw new FighterNotInBoardException(f);
		
		return false;
	}
	
	/**
	 * Comprueba si la coordenada c esta dentro del tablero
	 *
	 * @param c (coordenada)
	 * @return true si esta dentro
	 */
	public boolean inside(Coordinate c) {
		Objects.requireNonNull(c);
		
		if(c.getX()<0 || c.getX()>=getSize() || c.getY()<0 || c.getY()>=getSize())
			return false;
		
		return true;
	}
	
	/**
	 * Devuelve los vecinos de la coordenada c que estan dentro del tablero
	 *
	 * @param c (coordenada)
	 * @return vecions
	 * @throws OutOfBoundsException 
	 */
	public Set<Coordinate> getNeighborhood(Coordinate c) throws OutOfBoundsException{
		Objects.requireNonNull(c);
		
		if(!(inside(c)))
			throw new OutOfBoundsException(c);
		
		TreeSet<Coordinate> vecinos= (TreeSet<Coordinate>) c.getNeighborhood();
		for(Coordinate cord: c.getNeighborhood()) {
			 if(!inside(cord)){
				vecinos.remove(cord);
			}	
		}
		
		return vecinos;
	}
	
	/**
	 * Intenta colocar un caza f en la coordenada c
	 *
	 * @param c (coordenada)
	 * @param f (caza f)
	 * @return resultado de la lucha con el enemigo si donde quiere moverse hay un enemigo 
	 * 			y 0 si no hay ningun caza, el caza que hay es amigo o la coordenada no esta en el tablero
	 * @throws FighterAlreadyInBoardException 
	 * @throws OutOfBoundsException 
	 */
	public int launch(Coordinate c, Fighter f) throws FighterAlreadyInBoardException, OutOfBoundsException {
		Objects.requireNonNull(c);
		Objects.requireNonNull(f);
		
		if(board.containsValue(f))
			throw new FighterAlreadyInBoardException(f);
		
		if(inside(c)) {
			if(board.containsKey(c)) {
				if(f.getSide()!=board.get(c).getSide()) {
					try {
						int result=f.fight(board.get(c));
						f.getMotherShip().updateResults(result);
						board.get(c).getMotherShip().updateResults(result*-1);
						if(result==1) {
							board.put(c,f);
							f.setPosition(c);
						}
						return result;
					}catch(FighterIsDestroyedException e) {
						throw new RuntimeException();
					}
				}
			}else{
				board.put(c,f);
				f.setPosition(c);
				return 0;
			}
		}else
			throw new OutOfBoundsException(c);
		return 0;
	}
	
	/**
	 * El caza patrulla recorriendo las coordenadas vecinas 
	 * y pelea con los cazas enemigos que se encuentre
	 *
	 * @param f (caza que patrulla)
	 * @throws FighterNotInBoardException 
	 */
	public void patrol(Fighter f) throws FighterNotInBoardException {
		Objects.requireNonNull(f);
		
		if(board.containsValue(f)) {
			try {
				for(Coordinate cord: getNeighborhood(f.getPosition())) {
					if(board.containsKey(cord)) {
						if(f.getSide()!=board.get(cord).getSide()) {
							try {
							int result=f.fight(board.get(cord));
							f.getMotherShip().updateResults(result);
							board.get(cord).getMotherShip().updateResults(result*-1);
							if(result==-1) { 
								try {
								removeFighter(f);
								}catch(FighterNotInBoardException e) {
									throw new FighterNotInBoardException(f);
								}
								break;
							}else if(result==1) 
								try {
								removeFighter(board.get(cord));
								}catch(FighterNotInBoardException e) {
									throw new FighterNotInBoardException(f);
								}
							}catch(FighterIsDestroyedException e) {
								throw new RuntimeException();
							}
						}
					}	
				}
			}catch(OutOfBoundsException e){
				throw new RuntimeException();
			}
		}else{
			throw new FighterNotInBoardException(f);
		}
	}
}
