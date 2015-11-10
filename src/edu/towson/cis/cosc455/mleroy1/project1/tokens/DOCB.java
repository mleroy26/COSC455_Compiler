package edu.towson.cis.cosc455.mleroy1.project1.tokens;

import edu.towson.cis.cosc455.MLeRoy.project1.interfaces.Legaltoken;

public class DOCB implements Legaltoken{
	public final static String text="#BEGIN";

	@Override
	public boolean legal(String x) {
		if(x.equalsIgnoreCase(text))
			return true;
		return false;
	}
	public String html(boolean open) {
		return "<html>";
	}
}
