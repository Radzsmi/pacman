package pacman;

import java.util.*;

public class Field {
	int value;
	int id;
	HashMap<Integer,Field> neighbours;
	public Field(int i,int idp) {
		neighbours=new HashMap<Integer,Field>();
		value=i;
		id=idp;
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
		return neighbours.get(direction);
	}
}
