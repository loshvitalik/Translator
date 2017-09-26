package Readers;
import Token.Token;

public class WhitespaceReader implements Reader {
	public Token tryReadToken(String input) {
		int i = 0;
		while (i < input.length() && Character.isWhitespace(input.charAt(i)))
			i++;
		return  (i==0) ? null : new Token("ws", input.substring(0, i));
	}
}
