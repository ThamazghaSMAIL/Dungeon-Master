package decorators;


import servives.EditMapService;

public class EditMapDecorator extends MapDecorator implements EditMapService {

	public EditMapDecorator(EditMapService delegate) {
		super(delegate);
	}
	EditMapService serv ;
	
	@Override
	public boolean isReachable(int x1, int y1, int x2, int y2) {
		return serv.isReachable(x1, y1, x2, y2);
	}

	@Override
	public boolean isReady() {
		return serv.isReady();
	}

}
