package edu.towson.cis.cosc455.MLeRoy.project1.implementation;

import java.util.Stack;
import edu.towson.cis.cosc455.mleroy1.project1.tokens.*;

public class MySemanticAnalyzer {
	private Stack <String> html=new Stack<String>();
	private Stack <String> parse;
	
	private static ADDRESSB ADDRESSB;
	private static ADDRESSE ADDRESSE;
	private static AUDIO AUDIO;
	private static BOLD BOLD;
	private static DEFB DEFB;
	private static DEFUSEE DEFUSEE;
	private static DOCB DOCB;
	private static DOCE DOCE;
	private static EQSIGN EQSIGN;
	

	public void start() {
		makeHTML();
		this.parse=MyCompiler.parse;
	}

	private void makeHTML() {
		String t;
		while(!parse.isEmpty()){
			t=parse.pop();
			if(ADDRESSB.legal("("))
				html.push(ADDRESSB.html(true));
			else if()
		}
		
	}

}
