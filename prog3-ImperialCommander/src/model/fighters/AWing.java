package model.fighters;

import model.Fighter;
import model.Ship;

/**@author Marta Grimaldos López, 50507753Y
 * The Class AWing.
 */
public class AWing extends Fighter{
	
	/**
	 * Constructor de Awing.
	 *
	 * @param mother the mother
	 */
	public AWing(Ship mother) {
		super(mother);
		addVelocity(40);
		addAttack(5);
		addShield(-50);
	}
	
	/**
	 * Constructor de Awing.
	 *
	 * @param f the f
	 */
	private AWing(AWing f){
		super(f);
	}
	
	/**
	 * Copia Awing
	 *
	 * @return the fighter
	 */
	@Override
	public Fighter copy() {
		return new AWing(this);
	}
	
	/**
	 * Devuelve el simbolo
	 *
	 * @return the symbol
	 */
	@Override
	public char getSymbol() {
		return 'A' ;
	}
	
	/**
	 * Devuelve el daño de la lucha 
	 *
	 * @param n the n
	 * @param enemy the enemy
	 * @return the damage
	 */
	@Override
	public int getDamage(int n, Fighter enemy) {
		if(enemy.getType().equals("TIEBomber"))
			return super.getDamage(n,enemy)*2;
		
		return super.getDamage(n,enemy);
	}
}
