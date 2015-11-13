package edu.towson.cis.cosc455.MLeRoy.project1.implementation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class MyCompiler {

	public static String currentToken="**";
	public static MyLexicalAnalyzer lexical;
	public static MySemanticAnalyzer semantic;
	static Stack<String> parse=new Stack<String>();
	
	@SuppressWarnings({ "null", "resource" })
	public static void main(String[] args) throws FileNotFoundException {
		String[] input = null;
		String text="";
		try{
		MySyntaxAnalyzer syntax=new MySyntaxAnalyzer();
		if (args.length < 1) {
			System.out.println("You must supply a filename.");
			System.exit(1);
		}
		String fileName = args[0];
		int point=fileName.indexOf(".");
		if(!fileName.substring(point).equals(".mkd"))
			throw new CompilerException("Wrong file type");
		else{
			Scanner fileScanner = new Scanner(new File(fileName));
			int i=0;
			while(fileScanner.hasNextLine()){
				input[i]=fileScanner.nextLine();
			}
			for(int j=0;j<input.length;j++){
				text=text+input[j];
			}
			lexical=new MyLexicalAnalyzer(text);
			syntax.markdown();
		}
		}catch (CompilerException e){
			e.printStackTrace();
		}

	}
}
