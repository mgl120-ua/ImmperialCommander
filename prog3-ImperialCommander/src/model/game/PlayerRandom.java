package model.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.Coordinate;
import model.RandomNumber;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;
import model.game.score.DestroyedFightersScore;
import model.game.score.WinsScore;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayerRandom.
 *
 * @author Marta Grimaldos López, 50507753Y
 * Clase PlayerRandom
 * Simula un jugador que juega al azar
 */
public class PlayerRandom implements IPlayer{

	/**  Numero cazas. */
	private int numFighters;
	
	/**  Tablero. */
	private GameBoard board;
	
	/**  Nave. */
	private GameShip ship;
	
	/**
	 * Constructor.
	 *
	 * @param side el bando de la nave
	 * @param numFighters el numero de cazas
	 */
	public PlayerRandom(Side side, int numFighters) {
		Objects.requireNonNull(side);
		Objects.requireNonNull(numFighters);
		
		this.ship=new GameShip("PlayerRandom "+side+" Ship",side);
		this.numFighters=numFighters;
	}
	
	/**
	 * Asigna el tablero pasado como parametro.
	 *
	 * @param g el nuevo tablero
	 */
	@Override
	public void setBoard(GameBoard g) {
		Objects.requireNonNull(g);
		board=g;
	}
	
	/**
	 * Devuelve la nave del jugador.
	 *
	 * @return la nave del jugador
	 */
	@Override
	public GameShip getGameShip() {
		return this.ship;
	}
	
	/**
	 * Obtiene una cadena de cazas y los añade.
	 */
	@Override
	public void initFighters() {
		String concatenation="";
		
		if (ship.getSide()==Side.IMPERIAL) {
			for(int i=0; i<3; i++) {
				int n=RandomNumber.newRandomNumber(numFighters);
				if(n!=0) {
					switch(i){ 
					case 0: 
						concatenation+=n+"/"+"TIEFighter:";
						break;
					case 1:
						concatenation+=n+"/"+"TIEBomber:";
						break;
					case 2:
						concatenation+=n+"/"+"TIEInterceptor:";
						break;
					}
				}
			}
		}
		
		if (ship.getSide()==Side.REBEL) {
			for(int i=0; i<3; i++) {
				int n=RandomNumber.newRandomNumber(numFighters);
				if(n!=0) {
					switch(i){ 
					case 0: 
						concatenation+=n+"/"+"XWing:";
						break;
					case 1:
						concatenation+=n+"/"+"YWing:";
						break;
					case 2: 
						concatenation+=n+"/"+"AWing:";
						break;
					}
				}
			}
		}
		
		if(concatenation!="")
			ship.addFighters(concatenation);
	}
	
	/**
	 * Comprueba si se ha destruido la nave.
	 *
	 * @return true, if is fleet destroyed
	 */
	@Override
	public boolean isFleetDestroyed() {
		return ship.isFleetDestroyed();
	}
	
	/**
	 * Devuelve una cadena con la flota.
	 *
	 * @return the string
	 */
	@Override
	public String showShip() {
		String concatenation=ship.toString()+'\n'+ship.showFleet();
		return concatenation;		
	}
	
	/**
	 * Elimina los cazas destruidos de la flota.
	 */
	@Override
	public void purgeFleet() {
		ship.purgeFleet();
	}
	
	/**
	 * Hace una jugada .
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean nextPlay(){
		
		
		int op=RandomNumber.newRandomNumber(100);
		List<Integer> listId = new ArrayList<Integer>();
		
		if(op==99){
			
			return false;
			
		}else if(op>=85 && op<=98) {
			
			listId=ship.getFightersId("");
			if(listId.isEmpty()) {
				System.out.println("ERROR: Lista de id vacia");
			}else{
				
				int n=RandomNumber.newRandomNumber(listId.size());
				int	id=listId.get(n);
				
				try{
					ship.improveFighter(id,op,board);
				}catch(WrongFighterIdException e){
					System.out.println(e.getMessage());
				}
			}
			
		}else if(op>=25 && op<=84) {
			
			listId=ship.getFightersId("ship");
			if(listId.isEmpty()) {
				System.out.println("ERROR: Lista de id vacia");
			}else{
				
				int n=RandomNumber.newRandomNumber(listId.size());
				int id=listId.get(n);
				
				int x=RandomNumber.newRandomNumber(board.getSize());
				int y=RandomNumber.newRandomNumber(board.getSize());
				Coordinate c= new Coordinate(x,y);
				
				try {
					ship.launch(id, c, board);
				}catch( WrongFighterIdException e){
					throw new RuntimeException(e);
				}catch( FighterAlreadyInBoardException e) {
					throw new RuntimeException(e);
				}catch( OutOfBoundsException e) {
					throw new RuntimeException(e);
				}
			}
		
		}else if(op>=0 && op<=24) {
			
			listId=ship.getFightersId("board");
			if(listId.isEmpty()) {
				System.out.println("ERROR: Lista de id vacia");
			}else{
				int n=RandomNumber.newRandomNumber(listId.size());
				int id=listId.get(n);
				
				try {
					ship.patrol(id,board);
				}catch(FighterNotInBoardException e) {
					throw new RuntimeException(e);
				}catch(WrongFighterIdException e) {
					throw new RuntimeException(e);
				}
			}
		}
		
		return true;
	}
	
	
	/**
	 * Gets the wins score.
	 *
	 * @return the wins score
	 */
	public WinsScore getWinsScore() {
		return ship.getWinsScore();
	}
	
	/**
	 * Gets the destroyed fighters score.
	 *
	 * @return the destroyed fighters score
	 */
	public DestroyedFightersScore getDestroyedFightersScore() {
		return ship.getDestroyedFightersScore();
	}
}
