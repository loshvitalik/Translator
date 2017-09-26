package struckture;
import Token.Token;

public class IfWhileStruckture implements Struckture {
	public final Token[] condition;
	public final Token[] body;
	public Token type;
	public IfWhileStruckture  (Token  definedWord, Token[] condition, Token[] body) {
		this.type = definedWord;
		this.condition = condition;
		this.body = body;
	}
}