package struckture;
import Token.Token;

public class DeclarationStructure implements Struckture {
	public Token type;
	public Token[] variables;
	public DeclarationStructure(Token  type, Token[] variables) {
		this.type = type;
		this.variables = variables;
	}
}
