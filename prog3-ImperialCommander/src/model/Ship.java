package model;

import java.util.ArrayList;
import java.util.List;

public class Ship {
	private String name;
	private int wins;
	private int losses;
	private Side side;
	private ArrayList<Fighter> fleet=new ArrayList<Fighter>();
	
	public Ship(String name, Side side) {
		this.name=name;
		this.side=side;
		wins=0;
		losses=0;
	}
	
	public String getName() {
		return name;
	}
	
	public Side getSide() {
		return side;
	}
	
	public int getWins() {
		return wins;
	}


	public int getLosses() {
		return losses;
	}

	public List<Fighter> getFleetTest() {
		return fleet;
	}

	public void addFighters(String fd) {
		String[] str = fd.split("/:");
		for(int i=0; i<str.length; i++) {
			if(i%2==0) {
				int num = Integer.parseInt(str[i]);
				for(int j=0; j<num; j++) {
					Fighter f = new Fighter(str[i+1], this);
					fleet.add(f);
				}
			}
		}
	}
	
	public void updateResults(int r) {
		if(r==1)
			wins++;
		if(r==-1)
			losses++;
	}
	
	public Fighter getFirstAvailableFighter(String t) {		
		for(int i=0; i<fleet.size(); i++) {
			if(t==fleet.get(i).getType() || t=="") {
				if(fleet.get(i).isDestroyed()==false)
					return fleet.get(i);
			}
		}
		
		return null;
	}
	
	public void purgefleet() {
		for(int i=0; i<fleet.size(); i++) {
			if(fleet.get(i).isDestroyed()==true)
					fleet.remove(i);
		}
	}
	
	public String showFleet() {
		String concatenation="";
		for(int i=0; i<fleet.size(); i++) {
			concatenation=fleet.get(i).toString();
			if(fleet.get(i).isDestroyed()==true)
				concatenation += "(X)";
		}
		return concatenation;
	}
	
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
					if(t==fleet.get(i).getType()) {
						if(fleet.get(i).isDestroyed()==false)
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
	
	public String toString() {
		String concatenation="";
		concatenation += "Ship [" + name + " " + wins + "/" + losses + "] " + myFleet();
		return concatenation;
	}
}
