package edu.towson.cis.cosc455.mleroy1.project1.tokens;

import edu.towson.cis.cosc455.MLeRoy.project1.interfaces.Legaltoken;

public class LINKB implements Legaltoken{
	public final static String text="[";

	@Override
	public boolean legal(String x) {
		if(x.equalsIgnoreCase(text))
			return true;
		return false;
	}

	@Override
	public String html(boolean open) {
		if(open)
			return "<a href=";
		return ">";
	}
}
