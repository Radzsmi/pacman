package pacman;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GhostTest {
	Field f1;
	Field f3;
	Ghost g;
	@Before
	public void setUp() throws Exception {
		f1=new Field(19,1,true);//bal fent
		f3=new Field(22,3,false);//jobb fent
		f1.init(4, f3);
		f3.init(2, f1);
		g=new Ghost(f1, 0, 0);
	}

	@Test
	public void moveTest() {
		g.move();
		Assert.assertEquals("atlep",f3,g.getField());
		for(int i=0;i<23;i++) {
		g.move();
		Assert.assertEquals("23db pixel aterni a masik mezore",f3,g.getField());
		}
		g.move();
		Assert.assertEquals("itt er vissza az elso mezore",f1,g.getField());
	}

}
