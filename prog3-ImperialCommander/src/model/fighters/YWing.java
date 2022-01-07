package model.fighters;

import model.Fighter;
import model.Ship;

/**@author Marta Grimaldos López, 50507753Y
 * The Class YWing.
 */
public class YWing extends Fighter {
	
	/**
	 * Constructor de Ywing.
	 *
	 * @param mother the mother
	 */
	public YWing(Ship mother) {
		super(mother);
		addVelocity(-20);
		addAttack(-10);
		addShield(30);
	}
	
	/**
	 * Constructor de Ywing.
	 *
	 * @param f the f
	 */
	private YWing(YWing f){
		super(f);
	}
	
	/**
	 * Copia Ywing
	 *
	 * @return the fighter
	 */
	@Override
	public Fighter copy() {
		return new YWing(this);
	}
	
	/**
	 * Devuelve el simbolo
	 *
	 * @return the symbol
	 */
	@Override
	public char getSymbol() {
		return 'Y' ;
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
		if(enemy.getType().equals("TIEFighter")||enemy.getType().equals("TIEInterceptor"))
			return super.getDamage(n,enemy)/3;
		
		if(enemy.getType().equals("TIEBomber"))
			return super.getDamage(n,enemy)/2;
		
		return super.getDamage(n,enemy);
	}
}
