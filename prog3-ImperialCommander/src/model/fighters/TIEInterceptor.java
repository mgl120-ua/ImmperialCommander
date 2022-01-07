package model.fighters;

import model.Fighter;
import model.Ship;


/** @author Marta Grimaldos López, 50507753Y
 * Clase TIEInterceptor
 */
public class TIEInterceptor extends Fighter{
	
	/**
	 * Constructor de TIEInterceptor
	 *
	 * @param mother the mother
	 */
	public TIEInterceptor(Ship mother) {
		super(mother);
		addVelocity(45);
		addAttack(5);
		addShield(-20);
	}
	
	/**
	 * Constructor de TIEInterceptor
	 *
	 * @param f the f
	 */
	private TIEInterceptor(TIEInterceptor f){
		super(f);
	}
	
	/**
	 * Copia TIEInterceptor
	 *
	 * @return the fighter
	 */
	@Override
	public Fighter copy() {
		return new TIEInterceptor(this);
	}
	
	/**
	 * Devuelve el simbolo
	 *
	 * @return the symbol
	 */
	@Override
	public char getSymbol() {
		return 'i';
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
		if(enemy.getType().equals("YWing"))
			return super.getDamage(n,enemy)*2;
		
		if(enemy.getType().equals("AWing"))
			return super.getDamage(n,enemy)/2;
		
		return super.getDamage(n,enemy);
	}
}
