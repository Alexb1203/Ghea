
public class WordTypes {
	public enum wordTypes{
		Noun,
		Verb,
		Adjective,
		Article,
		Preposition,
		Comma,
		Period,
		Conjunction,
		Gerund,
		Adverb,
		Personal_Pronoun,
		Demonstrative_Pronoun,
		Reflexive_Pronoun,
		Possesive_Pronoun,
		Relative_Pronoun,
		Indefinate_Pronoun,
		Intensive_Pronoun,
		Unknown_Already_In_File,
		Unknown_Placed_In_File
		
	}
	public static String printType(wordTypes wordType){
		switch(wordType){
		case Noun:
			return "Noun";
		case Verb:
			return "Verb";
		case Adjective:
			return "Adjective";
		case Article:
			return "Article";
		case Preposition:
			return "Preposition";
		case Comma:
			return "Comma";
		case Period:
			return "Period";
		case Conjunction:
			return "Conjunction";
		case Gerund:
			return "Gerund";
		case Adverb:
			return "Adverb";
		case Personal_Pronoun:
			return "Pronoun";
		case Demonstrative_Pronoun:
			return "Pronoun";
		case Reflexive_Pronoun:
			return "Pronoun";
		case Possesive_Pronoun:
			return "Pronoun";
		case Relative_Pronoun:
			return "Pronoun";
		case Indefinate_Pronoun:
			return "Pronoun";
		case Intensive_Pronoun:
			return "Pronoun";
		case Unknown_Already_In_File:
			return "Old-Unknown";
		case Unknown_Placed_In_File:
			return "New-Unknown";
		}
		return null;
	}
	public static int How_Many_Word_Types(){
		return 19;
	}
}
