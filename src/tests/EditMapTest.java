package tests;

import contracts.EditMapContract;
import implm.EditMapImplem;

public class EditMapTest extends AbstractEditMapTest {

	@Override
	public void beforeTests() {
		setMap(new EditMapContract(new EditMapImplem()));
	}
	
	
}

