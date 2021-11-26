package pacman;

public class HpPellet extends Pellet {
	Pacman p;
	HpPellet(Pacman pp){
		p=pp;
		points=0;
	}
	public int remove() {
		if(p.getLives()==3) points=25;
		else {
			p.setLives(p.getLives()+1);
		}
		return points;
	}
}
