package model.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.Board;
import model.Coordinate;
import model.Fighter;
import model.Ship;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;
import model.game.score.DestroyedFightersScore;
import model.game.score.WinsScore;

// TODO: Auto-generated Javadoc
/**
 * The Class GameShip.
 *
 * @author Marta Grimaldos López, 50507753Y
 * Clase GameShip.
 * Gestiona una nave en el juego
 */
public class GameShip extends Ship{
	
	/** The wins score. */
	private WinsScore winsScore;
	
	/** The destroyed fighters score. */
	private DestroyedFightersScore destroyedFightersScore;
	
	/**
	 * Constructor.
	 *
	 * @param name the name
	 * @param side the side
	 */
	public GameShip(String name, Side side) {
		super(name,side);
		winsScore=new WinsScore(side);
		destroyedFightersScore=new DestroyedFightersScore(side);
	}
	
	/**
	 * Comprueba si todos los cazas estan destruidos.
	 *
	 * @return true si el caza ha sido destruido
	 */
	public boolean isFleetDestroyed() {
		int destroyed=0;
		
		for(int i=0; i<fleet.size(); i++) {
			if(fleet.get(i).isDestroyed()==true) {
					destroyed++;
			}
		}
		
		if(fleet.size()==destroyed) 
			return true;
		else
			return false;
	}
	
	/**
	 * Devuelve el caza con el identificador pasado por parametro.
	 *
	 * @param id the id
	 * @return the fighter
	 * @throws WrongFighterIdException the wrong fighter id exception
	 */
	private Fighter getFighter(int id) throws WrongFighterIdException {
		
		boolean fighter=false;
		for(int i=0; i<fleet.size(); i++) {
			if(fleet.get(i).getId()==id && fleet.get(i).isDestroyed()==false) {
				fighter=true;
				return fleet.get(i);
			}
		}
		
		if(!fighter) {
			throw new WrongFighterIdException(id);
		}
		
		return null;		
	}
	
	/**
	 * Devuelve una lista con los id de los cazas.
	 *
	 * @param where the where
	 * @return the fightersld
	 */
	public List<Integer> getFightersId(String where){
		
		List<Integer> listId = new ArrayList<Integer>();
		
		for(int i=0; i<fleet.size(); i++) {
			if(!fleet.get(i).isDestroyed()) {
					
					int id=fleet.get(i).getId();
					
					if(where=="board" ) {
						if(fleet.get(i).getPosition()!=null)
							listId.add(id);
					}
					else if(where=="ship") {
						if(fleet.get(i).getPosition()==null)
							listId.add(id);
					}
					else
						listId.add(id);
			}
		}
		
		return listId;

	}
	
	/**
	 * Lanza el caza con id y lo lanza al tablero b a la cordenada c.
	 *
	 * @param id the id
	 * @param c the c
	 * @param b the b
	 * @throws WrongFighterIdException the wrong fighter id exception
	 * @throws FighterAlreadyInBoardException the fighter already in board exception
	 * @throws OutOfBoundsException the out of bounds exception
	 */
	public void launch(int id, Coordinate c, Board b) throws WrongFighterIdException, FighterAlreadyInBoardException, OutOfBoundsException {
		Objects.requireNonNull(id);
		Objects.requireNonNull(c);
		Objects.requireNonNull(b);
		b.launch(c, getFighter(id));
	}
	
	/**
	 * Pone a patrullar al caza con id en el tablero b.
	 *
	 * @param id the id
	 * @param b the b
	 * @throws FighterNotInBoardException the fighter not in board exception
	 * @throws WrongFighterIdException the wrong fighter id exception
	 */
	public void patrol(int id, Board b) throws FighterNotInBoardException, WrongFighterIdException {
		Objects.requireNonNull(id);
		Objects.requireNonNull(b);
		b.patrol(getFighter(id));
	}
	
	/**
	 * Mejora el caza.
	 *
	 * @param id the id
	 * @param qty the qty
	 * @param b the b
	 * @throws WrongFighterIdException the wrong fighter id exception
	 */
	public void improveFighter(int id, int qty, Board b) throws WrongFighterIdException {
		Objects.requireNonNull(id);
		Objects.requireNonNull(qty);
		Objects.requireNonNull(b);
		try{
			b.removeFighter(getFighter(id));
		}catch(FighterNotInBoardException e){}
		
		int x=qty/2;
		if(qty%2==0) {
			getFighter(id).addAttack(x);
			getFighter(id).addShield(x);
		}else {
			getFighter(id).addAttack(x);
			getFighter(id).addShield(x+1);
		}	
		
	}
	
	/**
	 * Gets the wins score.
	 *
	 * @return the wins score
	 */
	public WinsScore getWinsScore() {
		return this.winsScore;
	}
	
	/**
	 * Gets the destroyed fighters score.
	 *
	 * @return the destroyed fighters score
	 */
	public DestroyedFightersScore getDestroyedFightersScore() {
		return this.destroyedFightersScore;
	}
	
	/**
	 * Update results.
	 *
	 * @param r the r
	 * @param f the f
	 */
	@Override
	public void updateResults(int r, Fighter f) {
		super.updateResults(r, f);
		
		if(r==1) {
			getWinsScore().score(r);
			getDestroyedFightersScore().score(f);
		}
			
	}
}
