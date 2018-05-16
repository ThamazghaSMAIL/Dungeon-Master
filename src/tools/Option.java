package tools;

public class Option<T> {
	private OptionEnum oe;
	private T t;
		
	public Option(OptionEnum oe, T t) {
		this.oe = oe;
		this.t = t;
	}
	
	public OptionEnum getOe() {
		return oe;
	}

	public T getT() {
		return t;
	}
	
}
