import java.util.ArrayList;

import Languages.Language;
import Readers.CommentReader;
import Readers.FloatReader;
import Readers.IdentifierReader;
import Readers.IntReader;
import Readers.Reader;
import Readers.StringReader;
import Readers.WhitespaceReader;
import Readers.WordReader;
import Token.Token;

public class Lexer {
	private Language language;
	private String[] code;
	public Token[] t;
	public Reader[] readers;

	public Lexer (String fileName, Language language) {
		this.language = language;
		FileRead fileReader = new FileRead(fileName);
		this.code = fileReader.strings;
		CommentReader comReader = new CommentReader(this.language.commentSymbol);
		FloatReader floatReader = new FloatReader();
		IntReader intReader = new IntReader();
		IdentifierReader identReader = new IdentifierReader();
		StringReader stringReader = new StringReader();
		WhitespaceReader whitespaceReader = new WhitespaceReader();
		WordReader wordReader = new WordReader(this.language.lexems);
		Reader[] readers  = {comReader, floatReader, intReader, identReader, stringReader, whitespaceReader, wordReader};
		this.readers = readers;
	}
				
	public void tokensMaker() {
		ArrayList <Token> tokens = new ArrayList<>();
		for (int i = 0; i< code.length; i++)
		{
			while (!code[i].equals(""))
			{
				int l = 0;
				Token token = null;
				for (Reader r: readers)
				{
					if (r.tryReadToken(code[i])!= null && r.tryReadToken(code[i]).getText().length() >= l)
					{		
							l = r.tryReadToken(code[i]).getText().length();
							token = r.tryReadToken(code[i]);	
					}
				}
				tokens.add(token);
				if (l == code[i].length())
					break;
				code[i] = code[i].substring(l,code[i].length());
			}
		}
		t = tokens.toArray(new Token[tokens.size()]);
	}
}
