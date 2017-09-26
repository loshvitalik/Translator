import Languages.Java;
import Languages.Language;
import Languages.Pascal;
public class Main {
	public static void main(String[] args) {
		Language language = new Pascal();
		Lexer out = new Lexer("myprogram.txt", language);
		out.tokensMaker();
		Language language1 = new Java();
		Translator tr = new Translator(language,language1,"myprogram.txt" );
		System.out.println(tr.translate());
		Translator tr1 = new Translator(language1,language,"mp.txt" );
		System.out.println(tr1.translate());
	}
}