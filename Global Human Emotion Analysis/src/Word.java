import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.WordNetDatabase;


public class Word {
	
	RandomAccessFile fs;
	
	static WordNetDatabase database = WordNetDatabase.getFileInstance();
	private char newLine = System.getProperty("line.separator").toCharArray()[0];
	protected int location;
	private String wrd;
	public ArrayList<WordTypes.wordTypes> wrdTypes= new ArrayList<WordTypes.wordTypes>();
	public boolean prepPhrase = false;
	public boolean negateEmotion = false;
	public boolean irrealisConditional = false;
	public Word(){
		  
	}
	public Word(String word){
		wrd = word;
		try {
			ClassLoader classLoader = Word.class.getClassLoader();
			File classpathRoot = new File(classLoader.getResource("").getPath());
			fs  = new RandomAccessFile(classpathRoot.getCanonicalPath().replaceAll("%20", " ") + File.separator + "Unknown" + File.separator + "Words.txt","rw");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String toString(){
		return wrd.toLowerCase();
	}
	protected Word(String word, int loc){
		//System.out.println("Created new word: " + word);
		try {
			ClassLoader classLoader = Word.class.getClassLoader();
			File classpathRoot = new File(classLoader.getResource("").getPath());
			fs  = new RandomAccessFile(classpathRoot.getCanonicalPath().replaceAll("%20", " ") + File.separator + "Unknown" + File.separator + "Words.txt","rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wrd = word;
		location = loc;
	}
	public boolean checkPrepFile(String wrd){
		System.out.println("Checking Prep: " + wrd);
        FileReader fWriter = null;
        BufferedReader writer = null;
        try {
        	ClassLoader classLoader = Word.class.getClassLoader();
			File classpathRoot = new File(classLoader.getResource("").getPath());
			fWriter = new FileReader(classpathRoot.getCanonicalPath().replaceAll("%20", " ") + File.separator + "Unknown" + File.separator + "Prepositions.txt");
			writer = new BufferedReader(fWriter);
			char read = 0;
			int val=0;
			int i= 0;
			//FIX PREP CHECKER -- HERE IS WHERE WE READ THE PREPOSITION FILE AND COMPARE IT TO OUR WORD.
			do{
				val=writer.read();
				if((char)val==newLine)
					i=0; 
				else{
					if(i<wrd.length())
						if((char) val!=wrd.charAt(i)){
							i=0;
							while((char) val!=newLine&&val!=-1){
								val= writer.read();
							};
						}else{
							if((i+1)>=wrd.length()){
								read = (char) writer.read();
								if((!isLatinLetter(read))||Character.isWhitespace(read)){
									fWriter.close();
									writer.close();
									return true;
								}
							}
							i++;
						}
					}
				}
			while(val!=-1);
			fWriter.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean isLatinLetter(char c) { 
	    return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
	}
	
	public boolean checkAdverbFile(String wrd){
        FileReader fWriter = null;
        BufferedReader writer = null;
        try {
        	ClassLoader classLoader = Word.class.getClassLoader();
			File classpathRoot = new File(classLoader.getResource("").getPath());
			fWriter = new FileReader(classpathRoot.getCanonicalPath().replaceAll("%20", " ") + File.separator + "Unknown" + File.separator + "Adverbs.txt");
			writer = new BufferedReader(fWriter);
			
			int val=0;
			int i= 0;
			
			do{
				val=writer.read();
				if((char)val==newLine)
					i=0; //this an index to a character of a word
				else{
					if(i<wrd.length())
						if((char) val!=wrd.charAt(i)){
							i=0;
							while((char) val!=newLine&&val!=-1){
								val= writer.read();
							};
					}else{
						if((i+1)>=wrd.length())
							if((char) writer.read()==newLine){
								fWriter.close();
								writer.close();
								return true;
							}
						i++;
						
					}
				}
			} while(val!=-1);
			fWriter.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
	public void IDwordTypes(){
		if(wrdTypes.size()==0){
			if(wrd.equals(".")){
				wrdTypes.add(WordTypes.wordTypes.Period);
			}if (wrd.equals(",")){
				wrdTypes.add(WordTypes.wordTypes.Comma);
			}if (wrd.equals("the")||wrd.equals("a")||wrd.equals("an")){
				wrdTypes.add(WordTypes.wordTypes.Article);
			}if (checkPrepFile(wrd)){
				wrdTypes.add(WordTypes.wordTypes.Preposition);
			}if (checkAdverbFile(wrd)){
				wrdTypes.add(WordTypes.wordTypes.Adverb);
			}if (wrd.equals("for")||wrd.equals("and")||wrd.equals("nor")||wrd.equals("but")||wrd.equals("or")||wrd.equals("yet")||wrd.equals("so")){
				wrdTypes.add(WordTypes.wordTypes.Conjunction);
			}if (contains(PersonalPronouns,wrd)){
				wrdTypes.add(WordTypes.wordTypes.Personal_Pronoun);
			}if (contains(DemonstrativePronouns,wrd)){
				wrdTypes.add(WordTypes.wordTypes.Demonstrative_Pronoun);
			}if (contains(ReflexivePronouns,wrd)){
				wrdTypes.add(WordTypes.wordTypes.Reflexive_Pronoun);
			}if (contains(PossesivePronouns,wrd)){
				wrdTypes.add(WordTypes.wordTypes.Possesive_Pronoun);
			}if (contains(RelativePronouns,wrd)){
				wrdTypes.add(WordTypes.wordTypes.Relative_Pronoun);
			}if (contains(Indefinate,wrd)){
				wrdTypes.add(WordTypes.wordTypes.Indefinate_Pronoun);
			}if (contains(Intensive,wrd)){
				wrdTypes.add(WordTypes.wordTypes.Intensive_Pronoun);
			}
			///////////////////////////////////////////////////////////////////
			if (database.getSynsets(wrd).length>0){
		    	for(Synset bword : database.getSynsets(wrd)){
		    		if (bword.getType().toString().equals("1")){
		    			if(!wrdTypes.contains(WordTypes.wordTypes.Noun))
		    				wrdTypes.add(WordTypes.wordTypes.Noun);
		    		}else if (bword.getType().toString().equals("2")){
		    			if(wrd.endsWith("ing")){
		    				if(!wrdTypes.contains(WordTypes.wordTypes.Gerund))
		    					wrdTypes.add(WordTypes.wordTypes.Gerund);
		    			}else{
		    				if(!wrdTypes.contains(WordTypes.wordTypes.Verb))
		    					wrdTypes.add(WordTypes.wordTypes.Verb);
		    			}
					}else if (bword.getType().toString().equals("3")){
						if(!wrdTypes.contains(WordTypes.wordTypes.Adjective))
							wrdTypes.add(WordTypes.wordTypes.Adjective);
					}else if (bword.getType().toString().equals("4")){
						if(!wrdTypes.contains(WordTypes.wordTypes.Adverb))
							wrdTypes.add(WordTypes.wordTypes.Adverb);
					}else if (bword.getType().toString().equals("5")){
						//ADJECTIVE_SATELLITE
					}else{
						//ALL;
					}
		    	}
	    	}else{
	    		
	    	}
    	}
		if(wrdTypes.size()==0){
    		try {
    			boolean found = false;
    			String txt="";
				while (fs.getFilePointer()<fs.length()){
					txt=fs.readLine();
					if (txt.equals(wrd)){
						found = true;
						break;
					}
				}
				if (!found){
					fs.writeBytes(wrd);
					fs.writeBytes(String.valueOf(newLine));
					wrdTypes.add(WordTypes.wordTypes.Unknown_Placed_In_File);
					if(wrd.contains("-")){
						wrdTypes.add(WordTypes.wordTypes.Adjective);
					}else{
						wrdTypes.add(WordTypes.wordTypes.Noun);
					}
					System.out.println("Found unknown word! - Placed in File. (" + wrd + ")");//(" + location + ")");
				}else{
					wrdTypes.add(WordTypes.wordTypes.Unknown_Already_In_File);
					if(wrd.contains("-")){
						wrdTypes.add(WordTypes.wordTypes.Adjective);
					}else{
						wrdTypes.add(WordTypes.wordTypes.Noun);
					}
					System.out.println("Found unknown word! - Already in File. (" + wrd + ")");//(" + location + ")");
				}
				fs.seek(0);
				found=false;
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
	}
	public String wordTypesToString(){
		String ret = "<";
		for(int x=0;x<wrdTypes.size();x++){
			ret+="("+WordTypes.printType(wrdTypes.get(x))+")";
		}
		ret+=">";
		return ret;
	}
	private boolean contains(String[] list, String item){
		for(String x : list)
			if(x.equals(item))
				return true;
		return false;
	}
	
	String[] PersonalPronouns = {"I","We", "You", "Thou", "He", "She", "It", "They"
	};

	String[] DemonstrativePronouns = {"This", "These","That","Those" 
	};
	 
	String[] ReflexivePronouns = {"Myself", "Himself", "Herself", "Yourself" 
	};
	 
	String[] PossesivePronouns = {"My", "Your","Him", "His", "Her","Hers", "Our","Ours", "Their","Theirs" };
	
	 
	String[] RelativePronouns = {"Who","Whos", "Which", "That", "As"
	};
	 
	String[] Indefinate = {"Each", "One", "All", "Everyone", "Somebody","Sombodies","Somebodys", "Either", "Both", "Any", "Such",  "Interrogative","Which", "Who", "What"
	};

	String[] Intensive = {"myself","yourself","himself","herself","itself","ourselves","yourselves","themselves"
	};
}
