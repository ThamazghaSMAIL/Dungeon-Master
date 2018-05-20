package tools;

public class Option<T> {
	private OptionEnum oe;
	private T content;
		
	public Option(OptionEnum oe, T content) {
		this.oe = oe;
		this.content =content;
	}
	
	public OptionEnum getOe() {
		return oe;
	}

	public T getT() {
		return content;
	}
	
}
