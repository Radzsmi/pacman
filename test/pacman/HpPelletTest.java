package pacman;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HpPelletTest {
	Field f1;
	Field f3;
	Pacman p;
	@Before
	public void setUp() throws Exception {
		f1=new Field(19,1,false);//bal fent
		f3=new Field(22,3,true);//jobb fent
		f1.init(4, f3);
		f3.init(2, f1);
		p=new Pacman(f1);
		p.direction=4;
		f3.setPellet(new HpPellet(p));
	}

	@Test
	public void test() {
		p.move();
		
	}

}
