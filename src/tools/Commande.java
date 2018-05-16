package tools;

public enum Commande {

	FF("move forward"),BB("move backward"),RR("strafe to right"),LL("strafe to left"),TL("turn left"),TR("turn right");
	String s;
	Commande(String st) {
		s=st;
	}
}
