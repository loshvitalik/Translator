package Languages;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import Token.Token;
import struckture.CommentStruckture;
import struckture.DeclarationStructure;
import struckture.IfWhileStruckture;
import struckture.SimpleStruckture;
import struckture.Struckture;

public class Java extends Language{
	public Java() {
		lexems =   new HashMap<String, String>();
		lexems.put("declaration","var");
		lexems.put("float type", "float"); 
		lexems.put("int type", "int"); 
		lexems.put("str type", "String"); 
		lexems.put("of type", ":"); 
		lexems.put("acquire", "="); 
		lexems.put("close", "}"); 
		lexems.put("open", "{"); 
		lexems.put("condition", "if"); 
		lexems.put("cycle while", "while"); 
		lexems.put("else chain", "else"); 
		lexems.put("function left", "("); 
		lexems.put("function right", ")"); 
		lexems.put("lower", "<"); 
		lexems.put("greater", ">"); 
		lexems.put("sum", "+"); 
		lexems.put("sub", "-"); 
		lexems.put("div", "/"); 
		lexems.put("mod", "%"); 
		lexems.put("equals", "=="); 
		lexems.put("semicolon", ";");
		lexems.put("goa", ">=");
		lexems.put("loa", "<=");
		lexems.put("comma", ",");
		commentSymbol = "//";
	}
	
	public ArrayList<Struckture> parseArray (Token[] tokens) {
		ArrayList <Struckture> strucktures = new ArrayList<>();
		for (int i = 0; i< tokens.length; i++)
		{
			if (Arrays.binarySearch(structuresIfWhile, tokens[i].getType()) >=0)
			{
				Token keyWord = tokens[i];
				ArrayList <Token> condition = new ArrayList<>();
				ArrayList <Token> body = new ArrayList<>();
				i++;
				while (!tokens[i].getType().equals("function right"))
				{
					if ((!tokens[i].getType().equals( "function left")) && (!tokens[i].getType().equals("ws")))
						condition.add(tokens[i]);
					i++;
				}
				i++;
				while (!tokens[i].getType().equals("close"))
				{
					if ((!tokens[i].getType().equals("open")) && (!tokens[i].getType().equals("ws")))
						body.add(tokens[i]);
					i++;
				}
				Struckture newS = new IfWhileStruckture(keyWord, condition.toArray(new Token[condition.size()]),body.toArray(new Token[body.size()]) );
				strucktures.add(newS);
			}
			else if (Arrays.binarySearch(declarations, tokens[i].getType()) >=0)
			{
				Token keyWord = tokens[i];
				ArrayList <Token> variables = new ArrayList<>();
				ArrayList <Token> body = new ArrayList<>();
				i++;
				while (tokens[i].getType().equals("identifier") || tokens[i].getType().equals("ws") || tokens[i].getType().equals("comma") )
				{
					if ((!tokens[i].getType().equals("comma")) && (!tokens[i].getType().equals("ws")))
						variables.add(tokens[i]);
					i++;
				}
				if (tokens[i].getType().equals("acquire"))
				{
					body.add(variables.get(variables.size()-1));
					while (!tokens[i].getType().equals("semicolon"))
					{
						body.add(tokens[i]);
						i++;
					}
					body.add(tokens[i]);
				}
				strucktures.add(new DeclarationStructure (keyWord,variables.toArray(new Token[variables.size()])));
				if (body.size() > 0)
					strucktures.add(new SimpleStruckture (body.toArray(new Token[body.size()])));
			}
			else if (tokens[i].getType().equals("comment"))
			{
				strucktures.add(new CommentStruckture (tokens[i].getText()));
			}
			else if ((!tokens[i].getType().equals("close")) && (!tokens[i].getType().equals("semicolon")))
			{
				ArrayList <Token> body = new ArrayList<>();
				while (!tokens[i].getType().equals("semicolon"))
				{
					body.add(tokens[i]);
					i++;
				}
				strucktures.add(new SimpleStruckture (body.toArray(new Token[body.size()])));
			}
		}
		return strucktures;
	}
	
	public String concat (ArrayList<Struckture> strings) {
		String answer;
		answer = "public class Main {  public static void main(String[] args) { ";
		for (int i =0; i <strings.size(); i++)
		{
			if (strings.get(i) instanceof DeclarationStructure)
			{
				DeclarationStructure dec = (DeclarationStructure) strings.get(i);
				answer = answer + lexems.get(dec.type.getType())+" ";
				for (int j = 0; j< dec.variables.length; j++) {
					if (j!=dec.variables.length-1)
						answer = answer+ dec.variables[j].getText()+", ";
					else
						answer = answer+ dec.variables[j].getText()+"; \n";
				}
			}
			else  if (strings.get(i) instanceof IfWhileStruckture)
			{
				IfWhileStruckture dec = (IfWhileStruckture) strings.get(i);
				answer = answer + lexems.get(dec.type.getType())+" (";
				for (int j =0; j<dec.condition.length ; j++)
				{
					 if (lexems.containsKey(dec.condition[j].getType()))
						 answer = answer + lexems.get(dec.condition[j].getType())+" ";
					 else
						 answer = answer + dec.condition[j].getText()+" ";
				}
				answer = answer + ") \n  {";
				for (int j =0; j<dec.body.length ; j++)
				{
					 if (lexems.containsKey(dec.body[j].getType()))
						 answer = answer + lexems.get(dec.body[j].getType())+" ";
					 else
						 answer = answer + dec.body[j].getText()+" ";
				}
				answer = answer + "} \n";
				
			}
			else  if (strings.get(i) instanceof CommentStruckture)
			{
				CommentStruckture dec = (CommentStruckture) strings.get(i);
				answer = answer +  dec.string+"\n";
					
			}
			else 
			{
				SimpleStruckture dec = (SimpleStruckture) strings.get(i);
				for (int j =0; j<dec.body.length ; j++)
				{
					 if (lexems.containsKey(dec.body[j].getType()))
						 answer = answer + lexems.get(dec.body[j].getType())+" ";
					 else
						 answer = answer + dec.body[j].getText()+" ";
				}
				answer = answer + "; \n";
			}
		}
		return answer + "} }";
	}
}
