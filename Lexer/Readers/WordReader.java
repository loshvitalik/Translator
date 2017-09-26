package Readers;
import java.util.HashMap;

import Token.Token;

public class WordReader implements Reader {
	private HashMap<String,String> lexems;

	public WordReader(HashMap<String,String> lexems) {
		super();
		this.lexems = lexems;
	}

	public String getKeyByValue (HashMap<String,String> lexems, String value) {
		for (String key : lexems.keySet()) {
		    if (lexems.get(key).equals(value))
		    	return key;
		}
		return null;
	}
	
	public Token tryReadToken(String input) {
		int i = 0;
		String type = "";
		String word = "";
		for (String keyword : this.lexems.values()) {
			if (input.startsWith(keyword) && keyword.length() > i) {
				word = keyword;
				type = this.getKeyByValue(lexems, keyword);
				i = keyword.length();
			}
		}
		return  (i > 0) ? new Token(type, word) : null;
	}
}
