package pacman;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;


public class FieldTest {

	Field f1;
	Field f2;
	Field f3;
	Field f4;
	@Before
	public void setUp() {
		f1=new Field(19,1,true);//bal fent
		f2=new Field(25,2,false);//bal lent
		f3=new Field(22,3,false);//jobb fent
		f4=new Field(28,4,false);//jobb lent
		f1.init(3, f2);
		f1.init(4, f3);
		f2.init(1, f1);
		f2.init(3, f4);
		f3.init(2, f1);
		f3.init(4, f4);
		f4.init(2, f2);
		f4.init(1, f3);
		//itt negy mezot raktam egymas melle egy negyzetet kialakitva es az f1 a bal felso erem.
	}
	@Test
	public void initTest() {
		Assert.assertEquals("Mellette az f2 van jobbra",f1.getNeig(3),f2);
		Assert.assertEquals("Mellette az f3 van lent",f1.getNeig(4),f3);
	}
	@Test
	public void lepesTest() {
	Assert.assertEquals("fel nem lephetunk",f1.checkMove(1),false);
	Assert.assertEquals("balra nem lephetunk",f1.checkMove(2),false);
	Assert.assertEquals("jobbra igen",f1.checkMove(3),true);
	Assert.assertEquals("le igen",f1.checkMove(4),true);
	}
	@Test
	public void pelletTest() {
		Assert.assertEquals("itt van 1 pontot ero pellet",f1.removePellet(),1);
		Assert.assertEquals("mar nincsen itt a pellet",f1.getPellet(),null);
		Assert.assertEquals("mar nincsen itt a pellet",f1.removePellet(),0);
	}

}
