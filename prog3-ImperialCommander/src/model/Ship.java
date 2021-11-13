package model;

import java.util.ArrayList;
import java.util.List;

import model.exceptions.NoFighterAvailableException;

/** @author Marta Grimaldos López, 50507753Y
 * Clase Ship 
 * Gestiona las naves de los dos bandos
 */
public class Ship {
	
	/** Nombre de la nave */
	private String name;
	
	/** Victorias de los cazas de la nave */
	private int wins;
	
	/** Derrotas de los cazas de la nave */
	private int losses;
	
	/** Bando al que pertence la nave */
	private Side side;
	
	/** Flota de cazas de la nave */
	private ArrayList<Fighter> fleet=new ArrayList<Fighter>();
	
	/**
	 * Constructor de una nave
	 *
	 * @param name (nombre del caza)
	 * @param side (bando del caza)
	 */
	public Ship(String name, Side side) {
		this.name=name;
		this.side=side;
		wins=0;
		losses=0;
	}
	
	/**
	 * Devuelv el nombre de la nave
	 *
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Devuelve el bando de la nave
	 *
	 * @return side
	 */
	public Side getSide() {
		return side;
	}
	
	/**
	 * Devuelve las victorias de la nave
	 *
	 * @return wins
	 */
	public int getWins() {
		return wins;
	}


	/**
	 * Devuelve las derrotas de la nave
	 *
	 * @return losses
	 */
	public int getLosses() {
		return losses;
	}

	/**
	 * Devuelve el array con la flota de cazas de la nave
	 *
	 * @return fleet 
	 */
	public List<Fighter> getFleetTest() {
		return fleet;
	}

	/**
	 * Añade cazas a la nave
	 *
	 * @param fd (cadena de cazas con el numero de cazas y el nombre del caza a añadir separado por : cada grupo de cazas y / el numero de cazas y el nombre)
	 */
	public void addFighters(String fd) {
		String[] str0 = fd.split(":");
		for(int i=0; i<str0.length; i++) {
			String[] str1 = str0[i].split("/");
			int num = Integer.parseInt(str1[0]);
			for(int j=0; j<num; j++) {
				Fighter f = FighterFactory.createFighter(str1[1], this);
				fleet.add(f);
			}
			
		}
	}
	
	/**
	 * Actualiza los resultados de las victorias y derrotas
	 *
	 * @param r (1 si ha ganado, -1 si ha perdido)
	 */
	public void updateResults(int r) {
		if(r==1)
			wins++;
		if(r==-1)
			losses++;
	}
	
	/**
	 * Devuelve el primer caza no destruido de la flota de tipo t
	 *
	 * @param t (type)
	 * @return primer caza no destruido
	 * @throws NoFighterAvailableException 
	 */
	public Fighter getFirstAvailableFighter(String t) throws NoFighterAvailableException {		
		
		for(int i=0; i<fleet.size(); i++) {
			if(t.equals(fleet.get(i).getType()) || t=="") {
				if(fleet.get(i).isDestroyed()==false)
				return fleet.get(i);
			}
		}
		
		throw new NoFighterAvailableException(t);
	}
	
	/**
	 * Elimina los cazas destruidos de la flota
	 */
	public void purgeFleet() {
		for(int i=0; i<fleet.size(); i++) {
			if(fleet.get(i).isDestroyed()==true) {
					fleet.remove(i);
					i--;
			}
		}
	}
	
	/**
	 * Devuelve una cadena con los cazas de la flota con una X en los que estan destruidos
	 *
	 * @return cadena con los cazas de la flota
	 */
	public String showFleet() {
		String concatenation="";
		for(int i=0; i<fleet.size(); i++) {
			concatenation += fleet.get(i).toString();
			if(fleet.get(i).isDestroyed()==true)
				concatenation += " (X)\n";
			else
				concatenation += "\n";
		}
		return concatenation;
	}
	
	/**
	 * Devuelve una cadena como la que recoge la funcion addFighters con los cazas de la flota
	 *
	 * @return cadena de la flota con cazas no destruidos
	 */
	public String myFleet() {
		int cont = 0;
		String concatenation="", t="";
		ArrayList<String> types=new ArrayList<String>();
		
		for(int i=0; i<fleet.size(); i++) {
			if(fleet.get(i).isDestroyed()==false && !types.contains(fleet.get(i).getType())) {
				if(!types.isEmpty()) {
					concatenation += ":";
				}
				
				t=fleet.get(i).getType();
				
				for(int j=i; j<fleet.size(); j++) {
					if(t.equals(fleet.get(j).getType())) {
						if(fleet.get(j).isDestroyed()==false)
							cont++;
					}
				}
				types.add(t);
				concatenation += cont + "/" + t ;
				cont=0;
			}
		}
		return concatenation;
	}
	
	/**
	 * Devuelve una cadena con la descripcion de la nave
	 *
	 * @return cadena de la nave
	 */
	public String toString() {
		String concatenation="";
		concatenation += "Ship [" + name + " " + wins + "/" + losses + "] " + myFleet();
		return concatenation;
	}
}
