package edu.towson.cis.cosc455.MLeRoy.project1.implementation;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;
import edu.towson.cis.cosc455.mleroy1.project1.tokens.*;


/**
 * @author mleroy1
 *This Semantic analyzer checks that the items are used corectly and makes an html file
 */


public class MySemanticAnalyzer {
	private Stack <String> html=new Stack<String>();
	private Stack <String> parse;
	
	private static ADDRESSE ADDRESSE;
	private static AUDIO AUDIO;
	private static BOLD BOLD;
	private static DEFUSEE DEFUSEE;
	private static DOCB DOCB;
	private static DOCE DOCE;
	private static HEAD HEAD;
	private static ITALICS ITALICS;
	private static LISTITEMB LISTITEMB;
	private static LISTITEME LISTITEME;
	private static NEWLINE NEWLINE;
	private static PARAB PARAB;
	private static PARAE PARAE;
	private static TEXT TEXT;
	private static TITLEB TITLEB;
	private static TITLEE TITLEE;
	private static VIDEO VIDEO;
	
// starting  method and takes stack and gives it the the html file maker
	public void start() {
		makeHTML();
		this.parse=MyCompiler.parse;
		
		String output = "";
		while(!html.isEmpty())
			output=output+html.pop();
		try {
			createFile(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//creates the html stack from the syntaax anaylzer tokens
	private void makeHTML() {
		String t;
		while(!parse.isEmpty()){
			t=parse.pop();
			if(TITLEB.legal(t))
				html.push(TITLEB.html(true));
			else if(TITLEE.legal(t))
				html.push(TITLEE.html(true));
			else if(PARAB.legal(t))
				html.push(PARAB.html(true));
			else if(PARAE.legal(t))
				html.push(PARAE.html(true));
			else if(LISTITEMB.legal(t))
				html.push(LISTITEMB.html(true));
			else if(LISTITEME.legal(t))
				html.push(LISTITEME.html(true));
			else if(NEWLINE.legal(t))
				html.push(NEWLINE.html(true));
			else if(DOCB.legal(t))
				html.push(DOCB.html(true));
			else if(DOCE.legal(t))
				html.push(DOCE.html(true));
			else if(TEXT.legal(t))
				html.push(t);
			else if(DEFUSEE.legal(t)){
				html.push(DEFUSEE.html(true));
			}else if(HEAD.legal(t)){
				if(html.contains(HEAD.html(false)))
					html.push(HEAD.html(true));
				else 
					html.push(HEAD.html(false));
			}else if(ITALICS.legal(t)){
				if(html.contains(ITALICS.html(false)))
					html.push(ITALICS.html(true));
				else 
					html.push(ITALICS.html(false));
			}else if(BOLD.legal(t)){
				if(html.contains(BOLD.html(false)))
					html.push(BOLD.html(true));
				else 
					html.push(BOLD.html(false));
			}else if(ADDRESSE.legal(t)){
				String link,address=parse.pop();
				t=parse.pop();
				if(VIDEO.legal(t))
					link= "<iframe src=\"" + address + "\"/> "; 
				if(AUDIO.legal(t))
					link="<audio controls>  <source src=\"" + address + "\">    </audio> ";
				else{
					link= "<a href = \"" + address + "\">" + parse.pop() + "</a>";
				}
				html.push(link);
			}
				
		}
		
	}
	//creates a html file
	public void createFile(String sourceFile) throws IOException{
			String t = MyCompiler.fileName + ".html";
			File file = new File(t);
			if (!file.exists()) 
				file.createNewFile();
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(sourceFile);

			openHTMLFileInBrowser(t);
	}
	
	//displays the html file made in the google chrome browser
	public void openHTMLFileInBrowser(String htmlFileStr){
		File file= new File(htmlFileStr.trim());
		if(!file.exists()){
			System.err.println("File "+ htmlFileStr +" does not exist.");
			return;
		}
		try{
			Desktop.getDesktop().browse(file.toURI());
		} catch(IOException ioe){
			System.err.println("Failed to open file");
			ioe.printStackTrace();
		}
		return ;
	}

}
