package pacman;
public class Ghost extends Thing {
	int lastdir=0,moving=0,direction=0;
	Ghost(Field fp,int xp,int yp){
		f=fp;
		x=xp;
		y=yp;
	}
	public int getMoving() {return moving;}
	public int getDirection() {return direction;}
	public int getId() {return 1;}
	public void move() {
		if(moving==0) {
		boolean didntmove=true;
		int rand=0;
		while(didntmove) {
			if(lastdir!=0 && f.checkMove(lastdir)) {
				rand=lastdir;
				if(lastdir==1) {
					y=y-1;
					didntmove=false;
				}
				if(lastdir==4) {
					y=y+1;
					didntmove=false;
				}
				if(lastdir==2) {
					x=x-1;
					didntmove=false;
				}
				if(lastdir==3) {
					x=x+1;
					didntmove=false;
				}
			}else {
				rand = (int)(Math.random()*(4-1+1)+1);  
				if(!((lastdir==1 && rand==4 )||(lastdir==2 && rand==3)||(lastdir==3 && rand==2)||(lastdir==4 && rand==1)))
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
					lastdir=rand;
				}
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
