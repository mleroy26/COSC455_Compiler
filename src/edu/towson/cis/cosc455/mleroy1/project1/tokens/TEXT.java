package edu.towson.cis.cosc455.mleroy1.project1.tokens;

import edu.towson.cis.cosc455.MLeRoy.project1.interfaces.Legaltoken;

public class TEXT implements Legaltoken{
	public final static String text="ABCDEFGHIJKLMNOPQRSTUVWXYZ,.':?_!/0123456789";

	@Override
	public boolean legal(String x) {
		for(int i=0; i<text.length();i++){
			if(x.equalsIgnoreCase(text.substring(i, i+1)))
				return true;
		}
		return false;
	}

	public String html(boolean open) {
		return "";
	}
}
