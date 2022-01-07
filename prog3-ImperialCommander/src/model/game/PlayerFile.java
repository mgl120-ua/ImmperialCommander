package model.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

import model.Coordinate;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.NoFighterAvailableException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;
import model.game.score.DestroyedFightersScore;
import model.game.score.WinsScore;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayerFile.
 *
 * @author Marta Grimaldos López, 50507753Y
 * 
 * Clase PlayerFile
 * Lee los movimientos de un jugador desde un fichero
 */
public class PlayerFile implements IPlayer {

	/**  Buffer. */
	private BufferedReader br;
	
	/**  Tablero. */
	private GameBoard board;
	
	/**  Nave. */
	private GameShip ship;
	
	/**
	 * Constructor.
	 *
	 * @param side the side
	 * @param br the br
	 */
	public PlayerFile(Side side, BufferedReader br) {
		Objects.requireNonNull(side);
		Objects.requireNonNull(br);
		
		this.ship= new GameShip("PlayerFile "+side+" Ship",side);
		this.br=br;
		
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
	public void initFighters(){
		String concatenation="";
		try {
			concatenation+=br.readLine();
			if(concatenation!="") {
				ship.addFighters(concatenation);
			}
		}catch(IOException e){
			throw new RuntimeException(e);
		}
			
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
	public boolean nextPlay() {
		String concatenation="";
		try {
			concatenation+=br.readLine();
			String[] parts= concatenation.split(" ");
			
			if(parts[0].contentEquals("exit")) {
				
				return false;
			
			}else if(parts[0].contentEquals("improve")) {
				
				if(parts.length==3) {
					
					int id=Integer.parseInt(parts[1]);
					int qty=Integer.parseInt(parts[2]);
					
					if(qty<100) {
						
						try{
							ship.improveFighter(id, qty, board);
						}catch(WrongFighterIdException e){
							System.out.println(e.getMessage()); 
						}
						
					}else{
						System.out.println("ERROR:Faltan parametros");
					}
					
				}else{
					System.out.println("ERROR:Faltan parametros");
				}
			
			}else if(parts[0].contentEquals("patrol")){
				
				if(parts.length==2) {
					
					int id=Integer.parseInt(parts[1]);
					
					try{
						ship.patrol(id, board);
					}catch(WrongFighterIdException e){
						System.out.println(e.getMessage()); 
					}catch(FighterNotInBoardException e) {
						System.out.println(e.getMessage()); 
					}
				
				}else{
					System.out.println("ERROR:Faltan parametros");
				}
				
			}else if(parts[0].contentEquals("launch")) {
				
				if(parts.length==3){
					
					int x=Integer.parseInt(parts[1]);
					int y=Integer.parseInt(parts[2]);
					Coordinate c=new Coordinate(x,y);
					if(!ship.getFightersId("ship").isEmpty()) {
						int id=ship.getFightersId("ship").get(0);
						
						try {
							ship.launch(id, c, board);
						}catch( WrongFighterIdException e){
							System.out.println(e.getMessage());
						}catch( FighterAlreadyInBoardException e) {
							System.out.println(e.getMessage());
						}catch( OutOfBoundsException e) {
							System.out.println(e.getMessage());
						}
					}else {
						System.out.println("ERROR: Flota vacia");
					}
					
				}else if(parts.length==4){
					
					int x=Integer.parseInt(parts[1]);
					int y=Integer.parseInt(parts[2]);
					Coordinate c=new Coordinate(x,y);
					
					try {
						int id=Integer.parseInt(parts[3]);
						try {
							ship.launch(id, c, board);
						}catch( WrongFighterIdException e){
							System.out.println(e.getMessage());
						}catch( FighterAlreadyInBoardException e) {
							System.out.println(e.getMessage());
						}catch( OutOfBoundsException e) {
							System.out.println(e.getMessage());
						}
						
					}catch(NumberFormatException e) {
						
						try {
							int id=ship.getFirstAvailableFighter(parts[3]).getId();
							ship.launch(id, c, board);
						}catch( WrongFighterIdException i){
							System.out.println(i.getMessage()); 
						}catch( FighterAlreadyInBoardException i) {
							System.out.println(i.getMessage()); 
						}catch( OutOfBoundsException i) {
							System.out.println(i.getMessage()); 
						}catch(NoFighterAvailableException i) {
							System.out.println(i.getMessage()); 
						}
					}
					
				}else{
					System.out.println("ERROR:Faltan parametros");
				}
			}else {
				System.out.println("ERROR: Opcion no valida");
			}
				
		}catch(IOException e){
			throw new RuntimeException(e);
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
