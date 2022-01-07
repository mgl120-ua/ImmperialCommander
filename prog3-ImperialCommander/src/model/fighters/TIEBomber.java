package model.fighters;

import model.Fighter;
import model.Ship;

/**@author Marta Grimaldos López, 50507753Y
 * The Class TIEBomber.
 */
public class TIEBomber extends Fighter{
	
	/**
	 * Constructor de TIEbomber.
	 *
	 * @param mother the mother
	 */
	public TIEBomber(Ship mother) {
		super(mother);
		addVelocity(-30);
		addAttack(-50);
		addShield(35);
	}
	
	/**
	 * Constructor de TIEbomber.
	 *
	 * @param f the f
	 */
	private TIEBomber(TIEBomber f){
		super(f);
	}
	
	/**
	 * Copia TIEbomber
	 *
	 * @return the fighter
	 */
	@Override
	public Fighter copy() {
		return new TIEBomber(this);
	}
	
	/**
	 * Devuelve el simbolo
	 *
	 * @return the symbol
	 */
	@Override
	public char getSymbol() {
		return 'b';
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
		if(enemy.getType().equals("XWing")||enemy.getType().equals("YWing"))
			return super.getDamage(n,enemy)/2;
		
		if(enemy.getType().equals("AWing"))
			return super.getDamage(n,enemy)/3;
		
		return super.getDamage(n,enemy);
	}
}
