package Readers;
import Token.Token;

public class FloatReader implements Reader{
	public Token tryReadToken (String input) {
		if (!Character.isDigit(input.charAt(0))) return null;
		int i = 0;
		int len = input.length();
		boolean point = false;
		while (i < len && (Character.isDigit(input.charAt(i)) || input.charAt(i) == '.')) {
			i++;
			if (input.charAt(i) == '.')
				point = true;
		}
		return  (!point) ? null : new Token("float", input.substring(0, i));
	}
}
