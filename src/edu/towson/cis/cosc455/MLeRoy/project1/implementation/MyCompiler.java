package edu.towson.cis.cosc455.MLeRoy.project1.implementation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MyCompiler {

	public static String currentToken="**";
	public static MyLexicalAnalyzer lexical;
	@SuppressWarnings({ "null", "resource" })
	public static void main(String[] args) throws CompilerException, FileNotFoundException {
		String[] input = null;
		String text="";
		
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

	}
//	private void openHTMLFileInBrowswer(String htmlFileStr){
//		File file= new File(htmlFileStr.trim());
//		if(!file.exists()){
//			System.err.println("File "+ htmlFileStr +" does not exist.");
//			return;
//		}
//		try{
//			Desktop.getDesktop().browse(file.toURI());
//		}
//		catch(IOException ioe){
//			System.err.println("Failed to open file");
//			ioe.printStackTrace();
//		}
//		return ;
//	}
}
