package tests;

import contracts.CelluleContract;
import implm.CelluleImplem;

public class CelluleTest extends AbstractCelluleTest{
	
	@Override
	public void beforeTests() {
		setCellule(new CelluleContract(new CelluleImplem()));
	}
}
