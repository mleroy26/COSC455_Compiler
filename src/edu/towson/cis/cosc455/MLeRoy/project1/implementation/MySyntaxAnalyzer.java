package edu.towson.cis.cosc455.MLeRoy.project1.implementation;

import edu.towson.cis.cosc455.MLeRoy.project1.interfaces.SyntaxAnalyzer;
import edu.towson.cis.cosc455.mleroy1.project1.tokens.ADDRESSB;
import edu.towson.cis.cosc455.mleroy1.project1.tokens.ADDRESSE;
import edu.towson.cis.cosc455.mleroy1.project1.tokens.BOLD;
import edu.towson.cis.cosc455.mleroy1.project1.tokens.DEFB;
import edu.towson.cis.cosc455.mleroy1.project1.tokens.DEFUSEE;
import edu.towson.cis.cosc455.mleroy1.project1.tokens.DOCB;
import edu.towson.cis.cosc455.mleroy1.project1.tokens.EQSIGN;
import edu.towson.cis.cosc455.mleroy1.project1.tokens.HEAD;
import edu.towson.cis.cosc455.mleroy1.project1.tokens.LINKB;
import edu.towson.cis.cosc455.mleroy1.project1.tokens.LINKE;
import edu.towson.cis.cosc455.mleroy1.project1.tokens.LISTITEME;
import edu.towson.cis.cosc455.mleroy1.project1.tokens.PARAE;
import edu.towson.cis.cosc455.mleroy1.project1.tokens.TEXT;
import edu.towson.cis.cosc455.mleroy1.project1.tokens.TITLEB;
import edu.towson.cis.cosc455.mleroy1.project1.tokens.TITLEE;

/**
 * @author mleroy1
 *This syntax analyzer implements the grammer from lexems provided by the lexical analyzer
 *and puts them in a stack for the semantic anaalyzer
 *throws errors if incorrect grammer
 */
public class MySyntaxAnalyzer implements SyntaxAnalyzer {
	private static ADDRESSB ADDRESSB;
	private static TEXT TEXT;
	private static BOLD BOLD;
	private static edu.towson.cis.cosc455.mleroy1.project1.tokens.ITALICS ITALICS;
	private static LISTITEME LISTITEMB;
	private static edu.towson.cis.cosc455.mleroy1.project1.tokens.AUDIO AUDIO;
	private static edu.towson.cis.cosc455.mleroy1.project1.tokens.VIDEO VIDEO;
	private static LINKB LINKB;
	private static edu.towson.cis.cosc455.mleroy1.project1.tokens.NEWLINE NEWLINE;
	private static edu.towson.cis.cosc455.mleroy1.project1.tokens.PARAB PARAB;
	private static DEFB DEFB;
	private static PARAE PARAE;
	private static ADDRESSE ADDRESSE;
	private static LINKE LINKE;
	private static edu.towson.cis.cosc455.mleroy1.project1.tokens.USEB USEB;
	private static DEFUSEE DEFUSEE;
	private static edu.towson.cis.cosc455.mleroy1.project1.tokens.DOCE DOCE;
	private static DOCB DOCB;
	private static LISTITEME LISTITEME;
	
	
	//start of the grammer
	public void markdown() throws CompilerException {
		if(DOCB.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		} else
			throw new CompilerException("Missing DOCB tag");
		if(DEFB.legal(MyCompiler.currentToken))
			variableDefine();
		if(MyCompiler.currentToken.equalsIgnoreCase(HEAD.text))
			head();
		if(isBody(MyCompiler.currentToken))
			body();
		if(DOCE.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.semantic.start();
		} else
			throw new CompilerException("Missing DOCE tag");
		
		
	}
//grammer for the head
	public void head() throws CompilerException {
		if(MyCompiler.currentToken.equalsIgnoreCase(HEAD.text)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		} else
			throw new CompilerException("Missing HEAD beginning tag");
		if(MyCompiler.currentToken.equalsIgnoreCase(TITLEB.text))
			title();
		if(MyCompiler.currentToken.equalsIgnoreCase(HEAD.text)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		} else
			throw new CompilerException("Missing HEAD end tag");
	}

//grammer for the title
	public void title() throws CompilerException {
		if(MyCompiler.currentToken.equalsIgnoreCase(TITLEB.text)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		} else
			throw new CompilerException("Missing TITLE beginning tag");
		if(TEXT.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		} else
			throw new CompilerException("Missing text");
		if(MyCompiler.currentToken.equalsIgnoreCase(TITLEE.text)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		} else
			throw new CompilerException("Missing TITLE end tag");
	}
	//grammer for the body
	public void body() throws CompilerException {
		if(isInnerText(MyCompiler.currentToken))
			innerText();
		if(PARAB.legal(MyCompiler.currentToken))
				paragraph();
		if(NEWLINE.legal(MyCompiler.currentToken))
			newline();
	}
	public void paragraph() throws CompilerException {
		if(PARAB.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}else
			throw new CompilerException("Missing para beginning tag");
		if(DEFB.legal(MyCompiler.currentToken))
			variableDefine();
		if(isInnerText(MyCompiler.currentToken))
			innerText();
		if(PARAE.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}else
			throw new CompilerException("Missing para end tag");
	}
	//grammer for the inner text
	public void innerText() throws CompilerException {
		if(USEB.legal(MyCompiler.currentToken))
			variableUse();
		if(BOLD.legal(MyCompiler.currentToken))
			bold();
		if(ITALICS.legal(MyCompiler.currentToken))
			italics();
		if(LISTITEMB.legal(MyCompiler.currentToken))
			listitem();
		if(AUDIO.legal(MyCompiler.currentToken))
			audio();
		if(VIDEO.legal(MyCompiler.currentToken))
			video();
		if(LINKB.legal(MyCompiler.currentToken))
			link();
		if(NEWLINE.legal(MyCompiler.currentToken))
			newline();
		if(TEXT.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}
		if(isInnerText(MyCompiler.currentToken))
			innerText();
	}
	//grammer for variable define
	public void variableDefine() throws CompilerException {
		if(MyCompiler.currentToken.equalsIgnoreCase(DEFB.text)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
			if(TEXT.legal(MyCompiler.currentToken)){
				addToParseTree();
				MyCompiler.lexical.getNextToken();
				if(MyCompiler.currentToken.equalsIgnoreCase(EQSIGN.text)){
					addToParseTree();
					MyCompiler.lexical.getNextToken();
					if(TEXT.legal(MyCompiler.currentToken)){
						addToParseTree();
						MyCompiler.lexical.getNextToken();
						if(MyCompiler.currentToken.equalsIgnoreCase(DEFUSEE.text)){
							addToParseTree();
							MyCompiler.lexical.getNextToken();
							if(MyCompiler.currentToken.equalsIgnoreCase(DEFB.text)){
								variableDefine();
							}		
						} else
							throw new CompilerException("Missing def end tag");
					} else
						throw new CompilerException("Missing TEXT");
				} else
					throw new CompilerException("Missing Equal sign");
			} else
				throw new CompilerException("Missing TEXT");
		} else
			throw new CompilerException("Missing DEFB tag");
	}
	//grammer for using a variable
	public void variableUse() throws CompilerException {
		if(USEB.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}else
			throw new CompilerException("Missing use beginning tag");
		if(TEXT.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}else
			throw new CompilerException("Missing text");
		if(DEFUSEE.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}else
			throw new CompilerException("Missing use end tag");
	}
	//grammer for bold
	public void bold() throws CompilerException {
		if(BOLD.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}else
			throw new CompilerException("Missing bold beginning tag");
		if(TEXT.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}else
			throw new CompilerException("Missing text");
		if(BOLD.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}else
			throw new CompilerException("Missing bold end tag");
	}
	//grammer for italics 
	public void italics() throws CompilerException {
		if(ITALICS.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}else
			throw new CompilerException("Missing italics beginning tag");
		if(TEXT.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}else
			throw new CompilerException("Missing text");
		if(ITALICS.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}else
			throw new CompilerException("Missing italics end tag");
	}
	//grammer for list items
	public void listitem() throws CompilerException {
		if(LISTITEMB.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}else
			throw new CompilerException("Missing LIST beginning tag");	
		if(USEB.legal(MyCompiler.currentToken) | BOLD.legal(MyCompiler.currentToken) | ITALICS.legal(MyCompiler.currentToken) 
				| LINKB.legal(MyCompiler.currentToken) | TEXT.legal(MyCompiler.currentToken))
			innerItem();
		if(LISTITEME.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}else
			throw new CompilerException("Missing LIST end tag");	
		if(LISTITEMB.legal(MyCompiler.currentToken))
			listitem();
	}
	//grammer for inner items
	public void innerItem() throws CompilerException {
		if(USEB.legal(MyCompiler.currentToken))
			variableUse();
		if(BOLD.legal(MyCompiler.currentToken))
			bold();
		if(ITALICS.legal(MyCompiler.currentToken))
			italics();
		if(LINKB.legal(MyCompiler.currentToken))
			link();
		if(TEXT.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}
		if(USEB.legal(MyCompiler.currentToken) | BOLD.legal(MyCompiler.currentToken) | ITALICS.legal(MyCompiler.currentToken) 
				| LINKB.legal(MyCompiler.currentToken) | TEXT.legal(MyCompiler.currentToken))
			innerItem();
		}
	//grammer for links
	public void link() throws CompilerException {
		if(LINKB.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}else
			throw new CompilerException("Missing link beginning tag");
		if(TEXT.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}else
			throw new CompilerException("Missing text");
		if(LINKE.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}else
			throw new CompilerException("Missing link end tag");
		if(ADDRESSB.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}else
			throw new CompilerException("Missing address beginning tag");
		if(TEXT.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}else
			throw new CompilerException("Missing text");
		if(ADDRESSE.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}else
			throw new CompilerException("Missing address end tag");

	}
	// grammer for audio links
	public void audio() throws CompilerException {
		if(AUDIO.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}else
			throw new CompilerException("Missing audio tag");
		if(ADDRESSB.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}else
			throw new CompilerException("Missing address beginning tag");
		if(TEXT.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}else
			throw new CompilerException("Missing text");
		if(ADDRESSE.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}else
			throw new CompilerException("Missing address end tag");
	}
	//grammer for video links 
	public void video() throws CompilerException {
		if(VIDEO.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}else
			throw new CompilerException("Missing video tag");
		if(ADDRESSB.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}else
			throw new CompilerException("Missing address beginning tag");
		if(TEXT.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}else
			throw new CompilerException("Missing text");
		if(ADDRESSE.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}else
			throw new CompilerException("Missing address end tag");
	}
	//grammer for a newline
	public void newline() throws CompilerException {
		if(NEWLINE.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}else
			throw new CompilerException("Missing newline tag");
	}
	//add tokens to the stack in my compiler for the semantic analyzer to use
	public void addToParseTree(){
		MyCompiler.parse.push(MyCompiler.currentToken);
	}
	//checks is a string belongs to the body
	private boolean isBody(String s){
		if(isInnerText(s) | PARAB.legal(s) |NEWLINE.legal(s))
			return true;
		return false;
	}
	//checks if a string belongs to inner text
	private boolean isInnerText(String s){
		if(BOLD.legal(s) | ITALICS.legal(s) | LISTITEMB.legal(s) | AUDIO.legal(s) |VIDEO.legal(s) | LINKB.legal(s) |NEWLINE.legal(s)
				| TEXT.legal(s) |USEB.legal(s))
			return true;
		return false;
	}
	

}
