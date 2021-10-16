/**
 * @author Marta Grimaldos López, 50507753Y
 */
package model;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * Clase Coordinate 
 * Representa una coordenada en el tablero del juego
 */
public class Coordinate implements Comparable<Coordinate>{
	
	/** Atributos para cada coordenada */
	private int x,y;
		   
	/**
	 *Constructor 1
	 *
	 * @param x 
	 * @param y 
	 */
	public Coordinate(int x, int y){
		this.x = x; 
		this.y = y;
	}
	
   /**
    * Constructor 2
    *
    * @param c the c
    */
   public Coordinate(Coordinate c){
	   x = c.x; 
	   y = c.y;
   }
	
   /**
    * Devuelve x
    *
    * @return x
    */
   public int getX() { 
	   return x; 
   }
	   
   /**
    * Devuelve y
    *
    * @return y
    */
   public int getY() { 
	   return y; 
   }
	
   /**
    * Hash code
    *
    * @return int
    */
   @Override
   public int hashCode() {
	   return Objects.hash(x, y);
   }

	/**
	 * Compara una coordenada con otra
	 *
	 * @param obj
	 * @return true o false
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		return x == other.x && y == other.y;
	}
	
   /**
    * Escribe una cadena
    *
    * @return string
    */
   public String toString(){
	   String concatenation="";
	   concatenation += "[" + x + "," + y + "]";
	   return concatenation;
   }
	
   /**
    * Suma dos coordenadas
    *
    * @param c 
    * @return nueva coordenada
    */
   public Coordinate add(Coordinate c){
	   Coordinate new_c = new Coordinate(x+c.x,y+c.y);
	
      return new_c;
   }
	
   /**
    * Suma dos coordenadas
    *
    * @param x 
    * @param y 
    * @return nueva coordenada
    */
   public Coordinate add(int x,int y){
	   Coordinate new_c = new Coordinate(this.x+x, this.y+y);  
	   
	   return new_c;
   }
   
   /**
    * Compara la coordenada x con otra pasada por parametro y lo mismo con la y
    * 
    * @param c
    * @return negativo o positivo
    */ 
   @Override
   public int compareTo(Coordinate c){
	   if(this.getX()<c.getX()) 
		   return -1;
	   if(this.getX()>c.getX()) 
		   return 1;
	   if(this.getX()==c.getX()) { 
		   if(this.getY()<c.getY()) 
			   return -1;
		   if(this.getY()>c.getY()) 
			   return 1;
	   }
	   return 0;
   }
   
   /**
    * Busca las coordenadas vecinas
    * 
    * @return coordenadas vecinas
    */
   public Set<Coordinate> getNeighborhood(){
	   TreeSet<Coordinate> vecinos= new TreeSet<Coordinate>();
	   for(int i=-1; i<=1; i++){
		   for(int j=-1; j<=1; j++) {
			   if(i!=0 || j!=0)
				   vecinos.add(new  Coordinate(this.add(i,j)));
		   }
	   }
	   return vecinos;
   }

}
