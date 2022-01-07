package model;

import java.util.Objects;

import model.exceptions.FighterIsDestroyedException;

// TODO: Auto-generated Javadoc
/**
 * The Class Fighter.
 *
 * @author Marta Grimaldos López, 50507753Y
 * Clase Fighter
 * Creacion y comportamiento de los cazas
 */
public abstract class Fighter {
	
	/**  Velocidad del caza. */
	private int velocity;
	
	/**  Capacidad de ataque del caza. */
	private int attack;
	
	/**  Escudo del caza. */
	private int shield;
	
	/**  Identificador unico para cada caza. */
	private int id;
	
	/**  Valor para asignar al id de cada caza creado. */
	private static int nextId=1;
	
	/**  Coordenada para la posicion del caza en el tablero. */
	private Coordinate position;
	
	/**  Nave a la que pertenece el caza. */
	private Ship motherShip;

	/**
	 * Constructor de cazas.
	 *
	 * @param mother (nave a la que va a pertenecer)
	 */
	protected Fighter(Ship mother){
		Objects.requireNonNull(mother);
		id=nextId;
		nextId++;
		velocity=100;
		attack=80;
		shield=80;
		position=null;
		motherShip=mother;
	}
	
	/**
	 * Constructor de copia .
	 *
	 * @param f the f
	 */
	protected Fighter(Fighter f) {
		Objects.requireNonNull(f);
		this.velocity=f.getVelocity();
		this.attack=f.getAttack();
		this.shield=f.getShield();
		this.position=f.getPosition();
		this.motherShip=f.getMotherShip();
		this.id=f.getId();
	}
	
	/**
	 * inicializa el atributo nextId a 1.
	 */
	public static void resetNextId() {
		nextId=1;
	}
	
	/**
	 * Devuelve el tipo de caza.
	 *
	 * @return type
	 */
	public String getType() {
		return getClass().getSimpleName();
	}

	/**
	 * Devuelve la velocidad del caza.
	 *
	 * @return velocity
	 */
	public int getVelocity() {
		return velocity;
	}

	/**
	 * Devuelve la capacidad de ataque del caza.
	 *
	 * @return attack
	 */
	public int getAttack() {
		return attack;
	}

	/**
	 * Devuelve escudo del caza.
	 *
	 * @return shield
	 */
	public int getShield() {
		return shield;
	}

	/**
	 * Devuelve el id del caza.
	 *
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Devuelve la nave que pertenece el caza.
	 *
	 * @return side
	 */
	public Side getSide() {
		return motherShip.getSide();
	}
	
	/**
	 * Devuelve la posicion del caza.
	 *
	 * @return position
	 */
	public Coordinate getPosition() {
		return position;
	}
	
	/**
	 * Devuelve el bando al que pertenece el caza.
	 *
	 * @return motherShip
	 */
	public Ship getMotherShip() {
		return motherShip;
	}
	
	/**
	 * Cambia la coordenada que tiene como posicion el caza .
	 *
	 * @param p (la coordenada de la nueva posicion)
	 */
	public void setPosition(Coordinate p) {
		this.position=p;
	}
	
	/**
	 * Suma al ataque mas o menos ataque .
	 *
	 * @param at (ataque a añadir)
	 */
	public void addAttack(int at) {
		attack = attack + at;
		if(attack<0)
			attack=0;
	}

	/**
	 *  Suma a la velocidad mas o menos velocidad .
	 *
	 * @param vel (velocidad a añadir)
	 */
	public void addVelocity(int vel) {
		velocity = velocity + vel;
		if(velocity<0)
			velocity=0;
	}
	
	/**
	 * Suma a la escudo mas o menos escudo.
	 *
	 * @param sh (escudo a añadir)
	 */
	public void addShield(int sh) {
		shield = shield + sh;
	}
	
	/**
	 * Comprueba si esta destruido o no el caza.
	 *
	 * @return true si esta destruido o false si no esta destruido
	 */
	public boolean isDestroyed() {
		if(shield<=0)
			return true;
		else
			return false;
	}
	
	/**
	 * Devuelve el daño causado .
	 *
	 * @param n (umbral creado en la lucha)
	 * @param enemy (caza enemigo)
	 * @return daño
	 */
	public int getDamage(int n, Fighter enemy) {
		Objects.requireNonNull(n);
		Objects.requireNonNull(enemy);
		int damage;
		damage=(n*attack)/300;
		return damage;
	}
	
	/**
	 * Hace la lucha entre el caza y el caza enemigo.
	 *
	 * @param enemy (caza enemigo)
	 * @return 1 si el caza destruido es el enemigo, -1 si el caza invocado esta destruido
	 * @throws FighterIsDestroyedException the fighter is destroyed exception
	 */
	public int fight(Fighter enemy) throws FighterIsDestroyedException {
		Objects.requireNonNull(enemy);
		int umbral, n;
		if(enemy.isDestroyed()==true) 
			throw new FighterIsDestroyedException(enemy);
		
		if(this.isDestroyed()==true)
			throw new FighterIsDestroyedException(this);
		
		while(enemy.isDestroyed()==false && this.isDestroyed()==false) {
			n=RandomNumber.newRandomNumber(100);
			umbral=(100 * this.velocity) / (this.velocity + enemy.getVelocity());
			if(umbral<=n) {
				enemy.addShield(-(this.getDamage(n,enemy)));
				if(enemy.isDestroyed()==true)
					return 1;
			}
			else {
				this.addShield(-(enemy.getDamage(100-n, this)));
				if (this.isDestroyed()==true)
					return -1;
			}
		}
		return 0;
	}
	
	/**
	 * Crea una cadena con la descripcion del caza .
	 *
	 * @return descripcion del caza
	 */
	public String toString() {
		String concatenation="", position="";
		
		if(getPosition()==null) {
			position += "null";
		}else {
			position += getPosition().toString();
		}
		
		concatenation += "(" + getType() + " " + id + " " + getSide() + " " + position + " {"+ velocity + "," + attack + "," + shield + "})";
		return concatenation;
	}
	
	/**
	 * Hash code.
	 *
	 * @return int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true si es correcto
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fighter other = (Fighter) obj;
		return id == other.id;
	}
	
	/**
	 * Copia los cazas y se implementa en los tipos de caza.
	 *
	 * @return the fighter
	 */
	public abstract Fighter copy();
	
	/**
	 * Devuelve el simbolo de cada caza.
	 *
	 * @return the symbol
	 */
	public abstract char getSymbol();
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public int getValue() {
		return(velocity+attack);
	}
	
}
