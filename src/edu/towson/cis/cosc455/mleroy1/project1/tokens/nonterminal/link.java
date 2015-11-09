package edu.towson.cis.cosc455.mleroy1.project1.tokens.nonterminal;

import edu.towson.cis.cosc455.mleroy1.project1.tokens.terminal.ADDRESSB;
import edu.towson.cis.cosc455.mleroy1.project1.tokens.terminal.ADDRESSE;
import edu.towson.cis.cosc455.mleroy1.project1.tokens.terminal.LINKB;
import edu.towson.cis.cosc455.mleroy1.project1.tokens.terminal.LINKE;
import edu.towson.cis.cosc455.mleroy1.project1.tokens.terminal.TEXT;

public class link {
	public static final String text=LINKB.text+" "+TEXT.text+" "+LINKE.text+" "+ADDRESSB.text+" "+TEXT.text+" "+ADDRESSE.text;
}
