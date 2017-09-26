package Readers;
import Token.Token;

public class CommentReader implements Reader {
	public String commentSymbol;
	public CommentReader(String commentSymbol) {this.commentSymbol = commentSymbol;}
	public Token tryReadToken(String input) {
		return  (input.startsWith(this.commentSymbol)) ? new Token("comment", input) : null;
	}
}
