package cc.projectnexus.adapters.java.exceptions;

public class TokenNotAuthorizedException extends Exception {
	public TokenNotAuthorizedException(String message) {
		super("The provided token was never authorized.");
	}
}