package model;

import java.util.Objects;

public class Fighter {
	private String type;
	private int velocity;
	private int attack;
	private int shield;
	private int id;
	private static int nextId=1;
	private Coordinate position;
	private Ship motherShip;

	Fighter(String type, Ship mother){
		velocity=100;
		attack=80;
		shield=80;
		this.type=type;
		position=null;
		motherShip=mother;
	}
	
	public Fighter(Fighter f) {
		this.velocity=f.getVelocity();
		this.attack=f.getAttack();
		this.shield=f.getShield();
		this.type=f.getType();
		this.position=f.getPosition();
		this.motherShip=f.getMotherShip();
		this.id=f.getId();
	}
	
	public static void resetNextId() {
		nextId=1;
	}
	
	public String getType() {
		return type;
	}

	public int getVelocity() {
		return velocity;
	}

	public int getAttack() {
		return attack;
	}

	public int getShield() {
		return shield;
	}

	public int getId() {
		return id;
	}

	public Side getSide() {
		return motherShip.getSide();
	}
	
	public Coordinate getPosition() {
		return position;
	}
	
	public Ship getMotherShip() {
		return motherShip;
	}
	
	public void setPosition(Coordinate p) {
		this.position=p;
	}
	
	public void addAttack(int at) {
		attack = attack + at;
		if(attack<0)
			attack=0;
	}

	public void addVelocity(int vel) {
		velocity = velocity + vel;
		if(velocity<0)
			velocity=0;
	}
	
	public void addShield(int sh) {
		shield = shield + sh;
	}
	
	public boolean isDestroyed() {
		if(shield<=0)
			return true;
		else
			return false;
	}
	
	public int getDamage(int n, Fighter enemy) {
		int damage;
		damage=(n*attack)/300;
		return damage;
	}
	
	public int fight(Fighter enemy) {
		int umbral, n;
		while(enemy.isDestroyed()==false && this.isDestroyed()==false) {
			n=RandomNumber.newRandomNumber(99);
			umbral=(100 * this.velocity) / (this.velocity + enemy.velocity);
			if(umbral<=n) {
				enemy.addShield(this.getDamage(n,enemy));
				if(enemy.isDestroyed()==true)
					return 1;
			}
			else {
				this.addShield(enemy.getDamage(100-n, this));
				if (this.isDestroyed()==true)
					return -1;
			}
		}
		return 0;
	}
	
	public String toString() {
		String concatenation="";
		concatenation += "(" + type + " " + id + " " + getSide() + " " + getPosition().toString() + " {"+ velocity + "," + attack + "," + shield + "})";
		return concatenation;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
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
	
}
