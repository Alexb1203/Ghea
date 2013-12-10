import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Sentence extends Word{
	public static ArrayList<Word> sentenceToArray(String[] words){ //done
		
		ArrayList<Word> sentenc = new ArrayList<Word>();
		
		for(int i=0;i<words.length;i++){
			sentenc.add(new Word(words[i],i));
		}
		return sentenc;
	}
	public static void IdentifyWordTypes(ArrayList<Word> sentence){
		for(int index=0;index<sentence.size();index++){
			sentence.get(index).IDwordTypes();
			if(index>0)
				if(sentence.get(index-1).wrdTypes.size()==1&&sentence.get(index-1).wrdTypes.get(0)==WordTypes.wordTypes.Personal_Pronoun)
					if(sentence.get(index).wrdTypes.contains(WordTypes.wordTypes.Noun))
						sentence.get(index).wrdTypes.remove(WordTypes.wordTypes.Noun);
			
		}
	}
	
	public static void TagPrepositionalPhrases(ArrayList<Word> sentence){
		ArrayList<WordTypes.wordTypes> subHold = new ArrayList<WordTypes.wordTypes>();
		
		boolean foundPrep =false; //so if we havent found a preposition yet we cant have started...or in this case be searching for an end (meaning finding a NOUN, PRONOUN, or GERUND
    	for (int i=0;i<sentence.size();i++){
    		subHold=sentence.get(i).wrdTypes;
    		if(foundPrep){
				sentence.get(i).prepPhrase=true;
			}
    		for(int w=0;w<sentence.get(i).wrdTypes.size();w++){
    			if((!foundPrep)&&subHold.get(w)==WordTypes.wordTypes.Preposition){
	    			foundPrep=true;
	    			sentence.get(i).prepPhrase=true;
    	    	}else if(foundPrep){
    	    		//sentence.get(i).prepPhrase=true;
    	    		//if the word after a noun isn't a noun.
	    			if(subHold.get(w)==WordTypes.wordTypes.Noun){/*||subHold[w].equals("(Pronoun)")*///||subHold.get(w)==WordTypes.wordTypes.Gerund){
	    				if(sentence.size()>(i+1)){
	    					if(!sentence.get(i+1).wrdTypes.contains(WordTypes.wordTypes.Noun)){
	    						sentence.get(i).prepPhrase=true;
	    	    				foundPrep=false; //now that we have a full set (start & end) we reset the boolean so we can continue searching for more prepositional phrases.
	    					}
	    				}else{
	    					sentence.get(i).prepPhrase=true;
    	    				foundPrep=false; //now that we have a full set (start & end) we reset the boolean so we can continue searching for more prepositional phrases.
	    				}
	    			}
	    		}
    			
    		}
    	}
    		
	}
	public static void IrrealisBlock(ArrayList<Word> sentence){
		String conditional = "";
		if(sentence.get(0).toString().equals("if")){
			sentence.get(0).irrealisConditional=true;
			boolean foundComma = false;
			boolean negate = false;
			for(int i=1;i<sentence.size();i++){
				if(foundComma)
					sentence.get(i).negateEmotion=negate;
				else{
					conditional+=sentence.get(i).toString()+" ";
					sentence.get(i).irrealisConditional=true;
				}
				if(sentence.get(i).wrdTypes.contains(WordTypes.wordTypes.Comma)){
					foundComma=true;
					negate = !(JOptionPane.showConfirmDialog(null, conditional.substring(0,conditional.length()-3)+"?", "Question",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION);
				}
			}
		}
		//sentnece starts with 'IF' all the way to the next 'COMMA' LEAVE EMOTION AND ANYTHING AFTER THE COMMA IS NEGATED
		//sentence starts with probable word(would. could, should) then there is a COMMA and then IF CONDITIONAL NEGATE BEFORE THE COMMA
		
		//If the main character died, the movie would have been good.
		//I would have liked the movie, if the main character died.
		
		//If [condition], then [consequence].
		//Using previous information or general opinion on condition determine if it is true so we know whether or not to negate the consequence.
	}
	
}
