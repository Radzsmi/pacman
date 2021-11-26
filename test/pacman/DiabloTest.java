package pacman;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DiabloTest {
	ArrayList<Field> f;
	Pacman p;
	Ghost g;
	@Before
	public void setUp() throws Exception {
		//epitek egy egyenest ami 10 field bal szelerol a pacman jobbra megy.
		//Jelen esetben mind1 a pacman koordinatai
		f=new ArrayList<Field>();
		for(int i =0;i<10;i++) {
			f.add(new Field(26,i,false));
		}for(int i =0;i<9;i++) {
			if(f.get(i+1)!=null) f.get(i).init(3, f.get(i+1));
		}
		p=new Pacman(f.get(0));
		g=new Diablo(f.get(0),0,0,p);
		p.direction=3;
	}

	@Test
	public void test() {
		Assert.assertSame("Ugyanazon mezon kezdenek.",g.f,p.f);
		while(p.f!=f.get(8)) {
		p.move();
		g.move();
		}
		Assert.assertNotSame("Masik mezon vannak.",g.f,p.f);
		p.direction=1;//itt a pacman megall,mert felfele nem tud menni
		while(g.f!=f.get(8)) { // itt a szellem utoleri
		p.move();
		g.move();
		}
		Assert.assertSame("Szellem utolerte pacmant.",g.f,p.f);
	}

}
