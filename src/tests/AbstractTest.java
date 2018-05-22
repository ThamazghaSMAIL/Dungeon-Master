package tests;

import java.util.concurrent.ThreadLocalRandom;

import tools.Cell;

public abstract class AbstractTest {
	
	protected Cell getNatureRandom() {
		int random = ThreadLocalRandom.current().nextInt(0,Cell.values().length);
		switch(random) {
		case 0 : 
			return Cell.DNC;
		case 1 : 
			return Cell.DNO;
		case 2 :
			return Cell.DWC;
		case 3 :
			return Cell.DWO;
		case 4 :
			return Cell.EMP;
		case 5 : 
			return Cell.IN;
		case 6 :
			return Cell.OUT;
		default :
			return Cell.EMP;
		}
	}
}
