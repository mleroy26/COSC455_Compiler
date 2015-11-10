package edu.towson.cis.cosc455.MLeRoy.project1.implementation;

import java.util.Stack;

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
 *
 */
public class MySyntaxAnalyzer implements SyntaxAnalyzer {
	Stack<String> parse=new Stack<String>();
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

	public void markdown() throws CompilerException {
		if(MyCompiler.currentToken.equalsIgnoreCase(DOCB.text)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		} else
			throw new CompilerException("Missing DOCB tag");
		if(MyCompiler.currentToken.equalsIgnoreCase(DEFB.text))
			variableDefine();
		if(MyCompiler.currentToken.equalsIgnoreCase(HEAD.text))
			head();
		if(isBody(MyCompiler.currentToken))
			body();
	}

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
	public void body() throws CompilerException {
		if(isInnerText(MyCompiler.currentToken))
			innerText();
		if(PARAB.legal(MyCompiler.currentToken))
				paragraph();

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
	public void innerText() throws CompilerException {
		// TODO Auto-generated method stub

	}
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
	public void listitem() throws CompilerException {
		// TODO Auto-generated method stub

	}
	public void innerItem() throws CompilerException {
		// TODO Auto-generated method stub

	}
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
	public void newline() throws CompilerException {
		if(NEWLINE.legal(MyCompiler.currentToken)){
			addToParseTree();
			MyCompiler.lexical.getNextToken();
		}else
			throw new CompilerException("Missing newline tag");
	}
	public void addToParseTree(){
		parse.push(MyCompiler.currentToken);
	}
	private boolean isBody(String s){
		if(isInnerText(s) | PARAB.legal(s) |NEWLINE.legal(s))
			return true;
		return false;
	}
	private boolean isInnerText(String s){
		if(BOLD.legal(s) | ITALICS.legal(s) | LISTITEMB.legal(s) | AUDIO.legal(s) |VIDEO.legal(s) | LINKB.legal(s) |NEWLINE.legal(s)
				| TEXT.legal(s) |USEB.legal(s))
			return true;
		return false;
	}
	

}
