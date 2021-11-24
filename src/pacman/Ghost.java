package pacman;
public class Ghost extends Thing {
	Field f;
	int lastdir=0,moving=0,direction=0;
	Ghost(Field fp,int xp,int yp){
		f=fp;
		x=xp;
		y=yp;
	}
	public int getMoving() {return moving;}
	public Field getField() {return f;}
	public int getDirection() {return direction;}
	public void move() {
		if(moving==0) {
		boolean didntmove=true;
		int rand=0;
		while(didntmove) {
		rand = (int)(Math.random()*(4-1+1)+1);  
		if(lastdir!=0) {
			rand=lastdir;
		}
			if(f.checkMove(rand)) {
				if(rand==1) {
					y=y-1;
					didntmove=false;
				}
				if(rand==4) {
					y=y+1;
					didntmove=false;
				}
				if(rand==2) {
					x=x-1;
					didntmove=false;
				}
				if(rand==3) {
					x=x+1;
					didntmove=false;
				}
				direction=rand;
			}
		}
		f=f.getNeig(rand);
		moving++;
	}else {
		moving++;
		if(moving==24) { 
			moving=0;
		
		}
	}
	}
}
