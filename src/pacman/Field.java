package pacman;

import java.util.*;

public class Field {
	int value;
	int id;
	Pellet pellet=null;
	HashMap<Integer,Field> neighbours;
	public Field(int i,int idp,boolean haspellet) {
		neighbours=new HashMap<Integer,Field>();
		value=i;
		id=idp;
		if(haspellet) pellet=new Pellet();
	}
	public Pellet getPellet() {return pellet;}
	public void setPellet(Pellet p) {pellet=p;}
	public int removePellet() {
		int i=0;
		if(pellet!=null)i=pellet.remove();
		pellet=null; 
		return i;
	}
	public void init(int i,Field f) {
		neighbours.put(i,f);
	}
	public String toString() {
		return "Az ertekem: " +value; 
	}
	public int getValue() {return value;}
	public boolean checkMove(int direction) {
		if(neighbours.get(direction)!=null) {
			if(direction==1 && this.getNeig(direction).getValue()!=0) return true;//fel
			if(direction==3 && this.getNeig(direction).getValue()!=0 && id%26!=25) return true;//jobbra
			if(direction==2 && this.getNeig(direction).getValue()!=0 && id%26!=0) return true;//balra
			if(direction==4 && this.getNeig(direction).getValue()!=0) return true;//le
		}
		return false;
	}
	public HashMap<Integer,Field> getNeig(){
		return neighbours;
	}
	public Field getNeig(int direction) {
		if(direction==0) return null;
		return neighbours.get(direction);
	}
}
