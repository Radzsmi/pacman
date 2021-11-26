package pacman;

import java.util.ArrayList;

public class Diablo extends Ghost {
	Pacman p;
	ArrayList<Integer> pacdir,pacx,pacy;
	ArrayList<Field> pacfield;
	int pacdelay=120,counter=0;
	Diablo(Field fp, int xp, int yp,Pacman pp) {
		super(fp, xp, yp);
		p=pp;
		pacdir=new ArrayList<>();
		pacx=new ArrayList<>();
		pacy=new ArrayList<>();
		pacfield=new ArrayList<>();
		f=fp;
	}
	public int getId() {return 2;}
	public void move() {
		if(pacdelay!=0) pacdelay--;
		if(counter==0) {
			if(!p.getStaying())
			pacdir.add(p.getD());
			else pacdir.add(0);
			pacx.add(p.getX());
			pacy.add(p.getY());
			pacfield.add(p.getField());
			counter++;
			System.out.println(pacdir);
			System.out.println(pacdir);
			System.out.println(pacdir);
			System.out.println(pacdir);
			if(pacdelay==0) {
				x=pacx.get(0);
				y=pacy.get(0);
				f=pacfield.get(0);
				direction=pacdir.get(0);
				pacdir.remove(0);
				pacx.remove(0);
				pacy.remove(0);
				pacfield.remove(0);
			}
		}else {
			counter++;
		}
		if(counter==24) counter=0;
		moving=counter;
	}
}
