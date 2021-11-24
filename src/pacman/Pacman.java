package pacman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Pacman extends Thing{
	int direction=1,moving=0;
	int lives=3;
	boolean playing=false;
	Pacman(Field fp){
		f=fp;
		y=12;
		x=12;
	}
	public int getMove() {return moving;}
	public void setD(int d) {direction=d;}
	public int getD() {return direction;}
	public int getLives() {return lives;}
	public boolean getGameState() {return playing;}
	public void setGameState(boolean s) {playing =s;}
	public void move() {
		if(moving==0) {
		if(f.checkMove(direction)) {
			if(direction==1) y--;
			if(direction==2) x--;
			if(direction==3) x++;
			if(direction==4) y++;
			f=f.getNeig(direction);
			moving++;
		}
		}else {
			moving++;
			if(moving==24) moving=0;
		}
	}

}
