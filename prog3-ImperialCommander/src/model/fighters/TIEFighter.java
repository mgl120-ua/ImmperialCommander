package model.fighters;

import model.Fighter;
import model.Ship;

/**@author Marta Grimaldos López, 50507753Y
 * The Class TIEFighter.
 */
public class TIEFighter extends Fighter{
	
	/**
	 * Constructor de TIEfighter.
	 *
	 * @param mother the mother
	 */
	public TIEFighter(Ship mother) {
		super(mother);
		addVelocity(10);
		addAttack(5);
		addShield(-10);
	}
	
	/**
	 * Constructor de TIEfighter.
	 *
	 * @param f the f
	 */
	private TIEFighter(TIEFighter f){
		super(f);
	}
	
	/**
	 * Copia TIEFighter
	 *
	 * @return the fighter
	 */
	@Override
	public Fighter copy() {
		return new TIEFighter(this);
	}
	
	/**
	 * Devuelve el simbolo
	 *
	 * @return the symbol
	 */
	@Override
	public char getSymbol() {
		return 'f';
	}
}
