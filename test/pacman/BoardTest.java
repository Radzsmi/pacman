package pacman;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {
	Board b;
	@Before
	public void setUp() throws Exception {
		b=new Board(null);
		b.t.stop();
		b.setVisible(false);
		//letrehoztam egy boardot es megallitottam a timert,igy a kezdo allapotban lesz.
	}

	@Test
	public void load() {
		b.loadMap();
		Assert.assertEquals("Az elso elem idje 19",b.mymap.get(0),Integer.valueOf(19));
		Assert.assertEquals("Az utolso elem idje 28",b.mymap.get(26*26-1),Integer.valueOf(28));
	}
	@Test
	public void initGameTest() {
		Assert.assertEquals("Pacman nem halt meg",true,b.checkPacDead());
		//azt irja hogy pacman halott mivel egy mezon tartozkodik Diabloval de 
		//mivel a program elobb lepteti pacmant aztan ellenorzi hogy halott-e nem okoz hibat,
		//viszont most jo vele ellenorizi az inicializalast.
	}
	@Test
	public void pacmove() {
		Field f=b.pac.f;
		b.pac.move();
		Assert.assertNotEquals("Pacman nem halt meg",f,b.pac.f);
		for(int i=0;i<23;i++)b.pac.move();
		Assert.assertEquals("elvette a pelletet",null,b.pac.f.pellet);
	}
	@Test
	public void checkPacDeadTest() {
		b.pac.move();
		Assert.assertEquals("Pacman nem halt meg",false,b.checkPacDead());
	}
	@Test
	public void checkGameEndTest() {
		Assert.assertEquals("A tele van a palya pelletekkel",false,b.checkGameEnd());
		b.map.clear();
		for(int i=0;i<26*23;i++)
		b.map.add(new Field(0,0,false));
		Assert.assertEquals("A tele van a palya pelletekkel",true,b.checkGameEnd());
	}
	

}
