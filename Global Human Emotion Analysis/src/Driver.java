import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Hashtable;

public class Driver {
	
	final static String NegationWords[] = {"not", "no", "don't", "dont", "never", "none", "nobody", "doesnt", "doesn't", "non", "wasn't", "wasnt", "no one", "no-one"};//un-happy the un part dont forget to add
	final static String BoosterWords[] = {"very", "really", "extremely", "super", "alot"};
	
	static String[] posFile;
	 static String[] nutFile;
	 static String[] negFile;
	 static Thread t;
	
	public static void  maindisplay(String[] wrd){
		    int totalWordCount = 0;
		    for (String x : wrd)
		    	if (!x.equals(","))
		    		totalWordCount++;
		    
		    t = new Thread(wrd);
		    
		    GUI.textbox1 = t.toString();
		    	
		    GUI.totalWordCount = totalWordCount;
		    
		    	@SuppressWarnings("rawtypes")
				Hashtable positiveHashtable = CompareTextToFile(t.sentence, posFile);
		    	@SuppressWarnings("rawtypes")
				Hashtable NuetralHashtable = CompareTextToFile(t.sentence, nutFile);
		    	@SuppressWarnings("rawtypes")
				Hashtable NegativeHashtable = CompareTextToFile(t.sentence, negFile);
		    	
		    	GUI.positive_Normal = (Integer) positiveHashtable.get("Normal");
			    GUI.positive_Negated = (Integer) positiveHashtable.get("Negated");
			    GUI.positive_DoubleNegated = (Integer) positiveHashtable.get("Double-Negated");
			    
			    GUI.nuetral_Normal = (Integer) NuetralHashtable.get("Normal");
			    GUI.nuetral_Negated = (Integer) NuetralHashtable.get("Negated");
			    GUI.nuetral_DoubleNegated = (Integer) NuetralHashtable.get("Double-Negated");
			    
			    GUI.negative_Normal = (Integer) NegativeHashtable.get("Normal");
			    GUI.negative_Negated = (Integer) NegativeHashtable.get("Negated");
			    GUI.negative_DoubleNegated = (Integer) NegativeHashtable.get("Double-Negated");
			    
			    //words like VERY and ALOT should magnify the value of polarity around 1.5 times.
			    
			    //## Example Sentences ##\\
			    /*/
			    * i really didn't like the touch screen
			    * 
			    *
			    */
			    //## End Example of the Sentences ##\\
			    
			    DecimalFormat df = new DecimalFormat("#.0");
			    
			    GUI.totalPolarity =  Float.parseFloat(df.format((float)((GUI.positive_Normal) + ((float)GUI.positive_DoubleNegated / 2) + ((float)GUI.negative_Negated / 2) - (GUI.negative_Normal) - ((float)GUI.negative_DoubleNegated / 2) - ((float)GUI.positive_Negated / 1.5))));
			    
		        GUI.wieghtedPolarity = Float.parseFloat(df.format(((GUI.totalPolarity ) / totalWordCount)));
		        
		        //tst=tst;
		        //TextBox12.Text = "NaN - Error"
		        //TextBox13.Text = "NaN % Error"
	}
	
    public static String[] StringtoStringArray(String textBoxString){
    	int counter=1;
	    for (char x : textBoxString.toCharArray()){
	    	if (x=='.'){
	    		counter++;
	    		break;
	    	}
	        if (x==' '||x==',')
	        	counter++;
	    }
    	String[] Words = new String[counter];
	    for(int i=0;i<counter;i++){
	    	Words[i]="";
	    }
	    counter=0;
	    for (char x : textBoxString.toCharArray()){
	    	if (x=='.'){
	    		counter++;
	    		Words[counter]+=x;
	    		Words[counter] = Words[counter].toLowerCase();
	    		break;
	    	}
	    	if (x==' '){
	        	counter++;
	    	}else if (x==','){
	    		counter++;
	    		Words[counter]+=x;
	    		Words[counter] = Words[counter].toLowerCase();
	        }else{
	            Words[counter]+=x;
	            Words[counter] = Words[counter].toLowerCase();
	        }
	    }
	    return Words;
    }
    private static ArrayList<String> parsePhrase(String phrase){
    	phrase = phrase.trim();
    	ArrayList<String> phraseL = new ArrayList<String>();
    	int prev=0;
    	for(int i=0; i<phrase.length(); i++){
    	    if(phrase.charAt(i)==' '){
    	        phraseL.add(phrase.substring(prev,i));
    	        //System.out.print(phrase.substring(prev,i)+" ");
    	        prev=i+1;
    	    }
    	    if(i==phrase.length()-1){
    	    	phraseL.add(phrase.substring(prev,i+1));
    	    }
    	}
    	if(phraseL.size()>1){
    		prev++;
    	}
    	if(phraseL.isEmpty())
    		phraseL.add(phrase);
    	return phraseL;
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private static Hashtable CompareTextToFile(ArrayList<Word> sentence, String[] TempDictionary){
    	int negposcount = 0;
    	int poscount = 0;
	    boolean foundnegation = false;
	    int doubleNegs = 0;
	    ArrayList<String> phrase = new ArrayList<String>();
	    for (int x = 0; x<sentence.size(); x++){
            for (String L: TempDictionary){
            	phrase = parsePhrase(L);
            	for(int i=0;i<phrase.size();i++){
            		if(!phrase.get(i).toLowerCase().equals(sentence.get(x+i).toString().toLowerCase())){
            			break;
            			//NO MATCH
            		}else if(i==(phrase.size()-1)){
            			//
            			for(int j=0;j<NegationWords.length;j++){
            				if(x>1)
	            				if(sentence.get(x-(phrase.size()-1)-1).toString().toLowerCase().equals((NegationWords[j].toLowerCase()))){
	                				negposcount++;
	                				foundnegation=true;
	                			}
            			}
            			//BoosterWords
            			for(int j=0;j<BoosterWords.length;j++){
            				if(x>1)
	            				if(sentence.get(x-(phrase.size()-1)-1).toString().toLowerCase().equals((BoosterWords[j].toLowerCase()))){
	                				//ADD BOOSTER CODE
	            					//negposcount++;
	                				//foundnegation=true;
	                			}
            			}
            			if(!foundnegation){
            			poscount++;
            			poscount+=(i/2);
            			}
            			//PHRASE MATCH
            		//}else //if(phrase.get(i).equals(sentence.get(x+i).toString())){
            		//{
            			//WORD MATCH
            		}
            	}
            }
	    }
    	
    	String[] K = {"Normal", "Negated", "Double-Negated"};
    	int[] V = {poscount,negposcount,doubleNegs};
    	Hashtable HS = new Hashtable();
    	for(int i=0;i<3;i++)
    		HS.put(K[i], V[i]);
    	return HS;
    }
}
