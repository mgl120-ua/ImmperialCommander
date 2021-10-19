package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Board {
	private int size;
	private Map<Coordinate, Fighter> board;
	
	public Board(int size) {
		this.size=size;
		board=new HashMap<Coordinate, Fighter>();
	}
	
	public Fighter getFighter(Coordinate c) {
		Objects.requireNonNull(c);
		
		return board.get(c);
	}

	public int getSize() {
		return size;
	}
	
	public boolean removeFighter(Fighter f) {
		Objects.requireNonNull(f);
		
		if(board.get(f.getPosition())==f) {
			board.remove(f.getPosition());
			return true;	
		}
				
		return false;
	}
	
	public boolean inside(Coordinate c) {
		Objects.requireNonNull(c);
		
		if(c.getX()<0 || c.getX()>getSize() || c.getY()<0 || c.getY()>getSize())
			return false;
		
		return true;
	}
	
	public Set<Coordinate> getNeighborhood(Coordinate c){
		Objects.requireNonNull(c);
		
		TreeSet<Coordinate> vecinos= (TreeSet<Coordinate>) c.getNeighborhood();
		for(Coordinate cord: c.getNeighborhood()) {
			 if(inside(cord)){
				vecinos.remove(cord);
			}	
		}
		
		return vecinos;
	}
	
	public int launch(Coordinate c, Fighter f) {
		Objects.requireNonNull(c);
		Objects.requireNonNull(f);
		
		if(inside(c)) {
			if(board.containsKey(c)) {
				if(f.getSide()!=board.get(c).getSide()) {
					int result=f.fight(board.get(c));
					f.getMotherShip().updateResults(result);
					board.get(c).getMotherShip().updateResults(result*-1);
					if(result==1) {
						board.put(c,f);
						f.setPosition(c);
					}
					return result;
				}
			}
			board.put(c,f);
			f.setPosition(c);
			return 0;
		}
		else
			return 0;
	}
	
	public void patrol(Fighter f) {
		Objects.requireNonNull(f);
		
		if(board.containsValue(f)) {
			for(Coordinate cord: getNeighborhood(f.getPosition())) {
				if(board.containsKey(cord)) {
					if(f.getSide()!=board.get(cord).getSide()) {
						int result=f.fight(board.get(cord));
						f.getMotherShip().updateResults(result);
						board.get(cord).getMotherShip().updateResults(result*-1);
						if(result==-1) { 
							board.remove(f.getPosition());
							break;
						}else if(result==1) 
							board.remove(cord);	
					}
				}	
			}
		}		
	}
}
