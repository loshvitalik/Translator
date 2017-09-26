package Languages;
import java.util.ArrayList;
import java.util.HashMap;

import Token.Token;
import struckture.Struckture;

public abstract class Language {
	public String commentSymbol;
	public HashMap<String, String> lexems;
	public String[] structuresIfWhile = {"condition", "cycle while"};
	public String[] declarations = {"float type","int type", "str type"};
	public abstract ArrayList<Struckture> parseArray (Token[] tokens);
	public abstract String concat (ArrayList<Struckture> strings);
}
