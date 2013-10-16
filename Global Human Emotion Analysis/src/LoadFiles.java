import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class LoadFiles {
	public static String[] LoadFile(String FileLocation) throws IOException{
	    RandomAccessFile fs = new RandomAccessFile(FileLocation,"rw");//'r'=read   -    'rw'=read/write
	    int Val  = 0;
	    int WordCount  = 0;
	
	    while (Val != -1){
	        while (whtSpace(Val) == 0x18){//0x20 is the Hexi-decimal version of the character " " - the space character
	        	try {
					Val = fs.readByte();
				} catch (EOFException e) {
					//e.printStackTrace();
					Val=-1;
					WordCount--;
					break;
				}
	        }
	        while (whtSpace(Val) != 0x18){//0x20 is the Hexi-decimal version of the character " " - the space character
	        	
	        	try {
					Val = fs.readByte();
				} catch (EOFException e) {
					//e.printStackTrace();
					Val=-1;
					break;
				}
        	}
	        
	        WordCount++;
	    }
	    //Read in the words
	    String[] CountedWords = new String[WordCount];
	    for(int i=0;i<WordCount;i++)
	    		CountedWords[i]="";
	    
	    WordCount = 0;
	    fs.seek(0);
	    Val = fs.readByte();
	    while (Val != -1){
	        while (whtSpace(Val) == 0x18){
	        	try {
					Val = fs.readByte();
				} catch (EOFException e) {
					//e.printStackTrace();
					Val=-1;
					break;
				}
	        }
	        while (whtSpace(Val) != 0x18){
	        	if(fs.getFilePointer()==fs.length()){
	        		fs.close();
	        		return CountedWords;
	        	}
	        	CountedWords[WordCount] += (char) Val;
	        	//CountedWords[WordCount]=CountedWords[WordCount].toLowerCase();
	        	try {
					Val = fs.readByte();
				} catch (EOFException e) {
					//e.printStackTrace();
					Val=-1;
					break;
				}
	    	}
	        //System.out.println(CountedWords[WordCount]);
	        CountedWords[WordCount].trim();
	        WordCount++;
	    }
	    fs.close();
	
	    return CountedWords;
	}
	private static int whtSpace(int Val){
		//Return a space if Val is any one of the whitespace chars
		char[] WhiteSpaceChars = {',', '.', ';', ':', '?', '!', '<', '>', '{', '}', '-','_', '|', (char) 92/*(char) Integer.parseInt("5C",16)*/, '[', ']', '(', ')', '*', '^', '%', '$', '#', '@', '+', '=', '`', '~', '\"' , (char) 10/*(char) Integer.parseInt("0A",16)*/, (char) 13/*(char) Integer.parseInt("0D",16)*/};
		
		for(int W = 0; W<WhiteSpaceChars.length;W++){
		    if ((char) Val == WhiteSpaceChars[W]){
		        Val = 0x18;
		        break;
		    }
		}
		return Val;
	}
}
