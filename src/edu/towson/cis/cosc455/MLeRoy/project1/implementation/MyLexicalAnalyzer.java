package edu.towson.cis.cosc455.MLeRoy.project1.implementation;

import edu.towson.cis.cosc455.MLeRoy.project1.interfaces.LexicalAnalyzer;

/**
 * @author mleroy1
 *
 */
public class MyLexicalAnalyzer implements LexicalAnalyzer {
	String nextCharacter = "";
	int currentPosition = 0;
	private char[] lex=new char[100];
	private char next;
	private int length;
	private String source;
	
	public MyLexicalAnalyzer(String source){
		this.source=source;
	}
	public void getNextToken() {
		//syntax calls this and gets next 
	}
	public void getCharacter() {

	}
	public void addCharacter() {

	}
	public boolean isSpace(String c) {
		if(c.equals(" "))
			return true;
		return false;
	}
	public boolean lookupToken() {
		//check against every token or text
		return false;
	}

}
