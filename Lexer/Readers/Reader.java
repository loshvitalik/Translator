package Readers;
import Token.Token;

public interface Reader {
	public Token tryReadToken(String input);
}
