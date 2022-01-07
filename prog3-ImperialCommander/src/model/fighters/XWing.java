package model.fighters;

import model.Fighter;
import model.Ship;

/**@author Marta Grimaldos López, 50507753Y
 * Clase XWing
 */
public class XWing extends Fighter {
	
	/**
	 * Constructor de Xwing
	 *
	 * @param mother the mother
	 */
	public XWing(Ship mother) {
		super(mother);
		addVelocity(10);
		addAttack(20);
	}
	
	/**
	 * Constructor de Xwing
	 *
	 * @param f the f
	 */
	private XWing(XWing f){
		super(f);
	}
	
	/**
	 * Copia Xwing
	 *
	 * @return the fighter
	 */
	@Override
	public Fighter copy() {
		return new XWing(this);
	}
	
	/**
	 * Devuelve el simbolo
	 *
	 * @return the symbol
	 */
	@Override
	public char getSymbol() {
		return 'X';
	}
	
}
