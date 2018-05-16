package tools;

public enum Face {
	
	N("N"),S("N"),E("N"),W("W");
	
	private String s;
	
	Face(String s) {
		this.s=s;
	}
	
	public String toString() {
		return s;
	}
	

}
