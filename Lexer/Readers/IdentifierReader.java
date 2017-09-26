package Readers;
import Token.Token;

public class IdentifierReader implements Reader {
	public Token tryReadToken(String input) {
		if (!Character.isJavaIdentifierStart(input.charAt(0))) return null;
		int i = 0;
		while (i < input.length() && Character.isJavaIdentifierStart(input.charAt(i)))
			i++;
		return new Token("identifier", input.substring(0, i));
	}
}
