package pacman;

public class KillerPellet extends Pellet{
	Pacman p;
	KillerPellet(Pacman pp){
		points=10;
		p=pp;
	}
	public int remove() {
		p.setKiller(true);
		return points;
	}
}
