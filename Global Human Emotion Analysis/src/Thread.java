import java.util.ArrayList;


public class Thread {
	public static String newline = System.getProperty("line.separator");
	ArrayList<Word> sentence = new ArrayList<Word>();
	ArrayList<Word> possibleSubjects = new ArrayList<Word>();
	ArrayList<Word> likelySubjects = new ArrayList<Word>();
	Word FinalSubjectGuess;
	int total_words = 0;
	int word_eleminated = 0;
	int word_eleminated2 = 0;
	public Thread(String[] wrd){
		total_words = wrd.length;
		sentence = Sentence.sentenceToArray(wrd); //Initalize sentence
		Sentence.IdentifyWordTypes(sentence);
		Sentence.IrrealisBlock(sentence);
		Sentence.TagPrepositionalPhrases(sentence); //tag each word if it is prepositional phrase
		possibleSubjects.addAll(sentence);
		
		for(int x = 0;x<possibleSubjects.size();x++)
			//LIST OF ALL THINGS THE SUBJECT CANT BE RIGHT HERE
			if(possibleSubjects.get(x).prepPhrase||(possibleSubjects.get(x).wrdTypes.get(0) == WordTypes.wordTypes.Adjective && possibleSubjects.get(x).wrdTypes.size()==1)||(possibleSubjects.get(x).wrdTypes.get(0) == WordTypes.wordTypes.Verb && possibleSubjects.get(x).wrdTypes.size()==1)||possibleSubjects.get(x).wrdTypes.contains(WordTypes.wordTypes.Adverb)||possibleSubjects.get(x).wrdTypes.contains(WordTypes.wordTypes.Article)||possibleSubjects.get(x).toString().equals("if")||possibleSubjects.get(x).wrdTypes.contains(WordTypes.wordTypes.Conjunction)||possibleSubjects.get(x).wrdTypes.contains(WordTypes.wordTypes.Comma)||possibleSubjects.get(x).wrdTypes.contains(WordTypes.wordTypes.Period)){
				possibleSubjects.remove(x);
				x--;
				word_eleminated++;
			}else if(!possibleSubjects.get(x).irrealisConditional){
				likelySubjects.add(possibleSubjects.get(x));
				word_eleminated2++;
			}
		FinalSubjectGuess = likelySubjects.get(0);
		System.out.println("done");
	}
	@Override
	public String toString(){
		String ret="";
		for(int i=0;i<sentence.size();i++){
			ret+=sentence.get(i).toString() + "\tPP: " + sentence.get(i).prepPhrase + "\tIP: " + sentence.get(i).irrealisConditional + "\t" + sentence.get(i).wordTypesToString() + newline;
		}
		ret+=newline+newline;
		ret+="Possible Subjects - " + word_eleminated + " Words Elimenated"+newline;
		for(int i=0;i<possibleSubjects.size();i++){
			ret+=possibleSubjects.get(i).toString()+newline;
		}
		ret+=newline+newline;
		ret+="Likely Subjects - " + (total_words - word_eleminated2) + " Words Elimenated"+newline;
		for(int i=0;i<likelySubjects.size();i++){
			ret+=likelySubjects.get(i).toString()+newline;
		}
		ret+=newline+newline;
		ret+="Final Subject Guess - " + FinalSubjectGuess;
		return ret;
	}
}
