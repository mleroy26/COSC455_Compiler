package edu.towson.cis.cosc455.mleroy1.project1.tokens.nonterminal;

import edu.towson.cis.cosc455.mleroy1.project1.tokens.terminal.ADDRESSB;
import edu.towson.cis.cosc455.mleroy1.project1.tokens.terminal.ADDRESSE;
import edu.towson.cis.cosc455.mleroy1.project1.tokens.terminal.TEXT;
import edu.towson.cis.cosc455.mleroy1.project1.tokens.terminal.VIDEO;

public class video {
	public static final String text=VIDEO.text+" "+ADDRESSB.text+" "+TEXT.text+" "+ADDRESSE.text;
}
