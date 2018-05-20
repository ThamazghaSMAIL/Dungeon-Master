package tools;

public class PostConditionError extends ContractError {
	private static final long serialVersionUID = 1989924888153266862L;

	public PostConditionError(String message) {
		super("Precondition failed: "+message);
	}
}
