package edu.towson.cis.cosc455.mleroy1.project1.tokens.nonterminal;

import edu.towson.cis.cosc455.mleroy1.project1.tokens.terminal.ADDRESSB;
import edu.towson.cis.cosc455.mleroy1.project1.tokens.terminal.ADDRESSE;
import edu.towson.cis.cosc455.mleroy1.project1.tokens.terminal.AUDIO;
import edu.towson.cis.cosc455.mleroy1.project1.tokens.terminal.TEXT;

public class audio {
	public static final String text=AUDIO.text+" "+ADDRESSB.text+" "+TEXT.text+" "+ADDRESSE.text;
}
