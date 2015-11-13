package edu.towson.cis.cosc455.MLeRoy.project1.implementation;

import edu.towson.cis.cosc455.MLeRoy.project1.interfaces.LexicalAnalyzer;
import edu.towson.cis.cosc455.mleroy1.project1.tokens.TEXT;

/**
 * @author mleroy1
 *The lexical analyzer checks that the characters are parts of legal lexems and passes them
 *to the syntax analyzer
 */
public class MyLexicalAnalyzer implements LexicalAnalyzer {
	String nextCharacter = "";
	int currentPosition = 0;
	private char[] lex=new char[100];
	private char next;
	private int length=0;
	private String source;
	private static TEXT TEXT;

	//constructor
	public MyLexicalAnalyzer(String source){
		this.source=source;
		getCharacter();
		getNextToken();
	}
	
	//gets the next token from the source and is called by syntax analyzer
	public void getNextToken() {
		try{
			if(currentPosition >= source.length())
				MyCompiler.currentToken="";
			else{
				if(TEXT.legal(String.valueOf(next))){
					addCharacter();
					addText();
				}else if(next=='#'){
					addCharacter();
					getCharacter();
					addHashTag();
				}else if(next=='$'){
					addCharacter();
					getCharacter();
					addVariable();
				}else if(next=='*'){
					addCharacter();
					getCharacter();
					if(next=='*'){
						addCharacter();
						getCharacter();
					}
				}else if(lookupToken()){
					addCharacter();
				}else {
					throw new CompilerException("No legal lexemes");
				}
			}
		} catch(CompilerException e){
			e.printStackTrace();
			System.exit(0);
		}
		setCurrentToken();
		getCharacter();
	}
	//sets the current token in the compiler
	private void setCurrentToken(){
		String text="";
		for(int i=0;i<length;i++){
			text=text+lex[i];
		}
		MyCompiler.currentToken=text;
		lex=new char[100];
		length=0;
	}
//gets the next character
	public void getCharacter() {
		next=source.charAt(currentPosition);
		String a=""+next;
		if(isSpace(a)){
			currentPosition++;
			getCharacter();
		}
	}
	//adds a charachter to the lexeme
	public void addCharacter() {
		lex[length]=next;
		length++;
		currentPosition++;
	}
	public boolean isSpace(String c) {
		if(c.equals(" "))
			return true;
		return false;
	}
	//looks up a token to see if it is legal
	public boolean lookupToken() {
		String lookup=""+next;
		String[] tokens={"~","*","**","^","@","+",";","%"
				,"[","]","{","}","(",")","<",">"};
		for(String a:tokens){
			if(lookup.equals(a))
				return true;
		}
		return false;
	}
	//checks that text is used right and groups it together
	private void addText(){
		char c=source.charAt(currentPosition);
		while(TEXT.legal(String.valueOf(c))){
			next=c;
			addCharacter();
			c=source.charAt(currentPosition);
			while(isSpace(String.valueOf(c))){
				next = c;
				addCharacter();
				c = source.charAt(currentPosition);
			}
		}
	}
	
	//chacks that hashtag tags arre used right
	private void addHashTag() {
		if("b".equalsIgnoreCase(String.valueOf(next))){
			addCharacter();
			getCharacter();
		}if("e".equalsIgnoreCase(String.valueOf(next))){
			addCharacter();
			getCharacter();
		}if("g".equalsIgnoreCase(String.valueOf(next))){
			addCharacter();
			getCharacter();
		}if("i".equalsIgnoreCase(String.valueOf(next))){
			addCharacter();
			getCharacter();
		}if("n".equalsIgnoreCase(String.valueOf(next))){
			addCharacter();
			getCharacter();
		}else{
			if("e".equalsIgnoreCase(String.valueOf(next))){
				addCharacter();
				getCharacter();
			}
			if("n".equalsIgnoreCase(String.valueOf(next))){
				addCharacter();
				getCharacter();
			}if("d".equalsIgnoreCase(String.valueOf(next))){
				addCharacter();
				getCharacter();
			}
		}

	}
	
	//checks that veriable tags are used right
	private void addVariable() {
		if("d".equalsIgnoreCase(String.valueOf(next))){
			addCharacter();
			getCharacter();
		}
		if("e".equalsIgnoreCase(String.valueOf(next))){
			addCharacter();
			getCharacter();
		}if("f".equalsIgnoreCase(String.valueOf(next))){
			addCharacter();
			getCharacter();
		}else{
			if("u".equalsIgnoreCase(String.valueOf(next))){
				addCharacter();
				getCharacter();
			}
			if("s".equalsIgnoreCase(String.valueOf(next))){
				addCharacter();
				getCharacter();
			}if("e".equalsIgnoreCase(String.valueOf(next))){
				addCharacter();
				getCharacter();
			}else {
				if("e".equalsIgnoreCase(String.valueOf(next))){
					addCharacter();
					getCharacter();
				}
				if("n".equalsIgnoreCase(String.valueOf(next))){
					addCharacter();
					getCharacter();
				}if("d".equalsIgnoreCase(String.valueOf(next))){
					addCharacter();
					getCharacter();
				}
			}
		}
	}
}
