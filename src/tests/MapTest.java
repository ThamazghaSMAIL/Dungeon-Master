package tests;

import contracts.MapContract;
import implm.MapImplem;

public  class MapTest extends AbstractMapTest {

	@Override
	public void beforeTests() {
		setMap(new MapContract(new MapImplem()));
	}
	
}
