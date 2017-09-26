package Readers;
import Token.Token;

public class IntReader implements Reader
{
	public Token tryReadToken(String input) {
		if (!Character.isDigit(input.charAt(0)))
			return null;
		int i = 0;
		while (i < input.length() && Character.isDigit(input.charAt(i)))
			i++;
		return new Token("int", input.substring(0, i));
	}
}
