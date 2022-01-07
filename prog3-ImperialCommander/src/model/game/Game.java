package model.game;

import java.util.Objects;

import model.Side;
import model.exceptions.InvalidSizeException;
import model.game.score.DestroyedFightersScore;
import model.game.score.Ranking;
import model.game.score.WinsScore;

// TODO: Auto-generated Javadoc
/**
 * The Class Game.
 *
 * @author Marta Grimaldos López, 50507753Y
 * Clase Game
 * Gestiona una partida entre dos jugadores
 */
public class Game {
	
	/**  Tamaño del tablero. */
	private static final int BOARD_SIZE=10;
	
	/**  Tablero. */
	private GameBoard board;
	
	/**  Jugador REBEL. */
	private IPlayer rebel;
	
	/**  Jugador IMPERIAL. */
	private IPlayer imperial;

	/**
	 * Constructor.
	 *
	 * @param imperial the imperial
	 * @param rebel the rebel
	 */
	public Game (IPlayer imperial, IPlayer rebel){
		Objects.requireNonNull(imperial);
		Objects.requireNonNull(rebel);
		this.imperial=imperial;
		this.rebel=rebel;
		try{
			this.board=new GameBoard(BOARD_SIZE);
			this.imperial.setBoard(board);
			this.rebel.setBoard(board);
		}catch(InvalidSizeException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * Devuelve el tablero.
	 *
	 * @return the game board
	 */
	public GameBoard getGameBoard() {
		return board;
	}
	
	/**
	 * Funcion que inicializa, añade y muestra los rankings
	 *
	 * @return the string
	 */
	private String rankings(){
		String concatenation="";
		Ranking<WinsScore> rw = new Ranking<>();
		Ranking<DestroyedFightersScore> rd = new Ranking<>();
		
		rw.addScore(imperial.getWinsScore());
		rw.addScore(rebel.getWinsScore());
		rd.addScore(imperial.getDestroyedFightersScore());
		rd.addScore(rebel.getDestroyedFightersScore());
		
		concatenation+="RANKING WINS: "+rw.toString()+"\n RANKING DESTROYED: "+rd.toString();
		return concatenation;
	}
	/**
	 * Play.
	 *
	 * @return the side
	 */
	public Side play() {
		
		imperial.initFighters();
		rebel.initFighters();
		Side winner=null;
		
		do {
			System.out.println(rankings());
			System.out.println("BEFORE IMPERIAL\n"+board.toString()+"\n\n"+imperial.showShip()+"\n"+rebel.showShip());
			System.out.print("IMPERIAL("+board.numFighters(Side.IMPERIAL)+"): ");
			
			if(!imperial.nextPlay()) {
				winner=Side.REBEL;
				System.out.println(rankings());
				break;
			}
			
			System.out.println("AFTER IMPERIAL, BEFORE REBEL\n"+board.toString()+"\n\n"+imperial.showShip()+"\n"+rebel.showShip());
			
			if(imperial.isFleetDestroyed()) {
				winner=Side.REBEL;
				System.out.println(rankings());
				break;
			}
			if(rebel.isFleetDestroyed()) {
				winner=Side.IMPERIAL;
				System.out.println(rankings());
				break;
			}
			
			System.out.print("REBEL("+board.numFighters(Side.REBEL)+"):");
			
			if(!rebel.nextPlay()) {
				winner=Side.IMPERIAL;
				System.out.println(rankings());
				break;
			}
			
			System.out.println(" AFTER REBEL\n"+board.toString()+"\n\n"+imperial.showShip()+"\n"+rebel.showShip());
			
			imperial.purgeFleet();
			rebel.purgeFleet();
			
			if(imperial.isFleetDestroyed()) {
				winner=Side.REBEL;
				System.out.println(rankings());
			}
			if(rebel.isFleetDestroyed()) {
				winner=Side.IMPERIAL;
				System.out.println(rankings());
			}
			
		}while(!imperial.isFleetDestroyed() && !rebel.isFleetDestroyed());
		
		imperial.purgeFleet();
		rebel.purgeFleet();
		
		return winner;
	}
}
