package marmot.tokenize.preprocess;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.commons.compress.compressors.bzip2.*;

public class WikiReader {
	
	private LinkedList<String> untokenized;
	private LinkedList<String> tokenized;
	private int maxSentences;
	
	WikiReader(String untokenizedFile, String tokenizedFile, int maxSentences) {
		untokenized = new LinkedList<String>();
		tokenized = new LinkedList<String>();
		this.maxSentences = maxSentences*10;
		
		try {
			readFile(untokenizedFile, false);
			readFile(tokenizedFile, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(untokenized.size() == tokenized.size()) {
			System.out.println("Successfully read and compared files.");
		} else {
			System.err.println("Files not same size!");
		}
	}
	
	private void readFile(String filename, Boolean isTokenized) throws IOException {
		File file = new File(filename);
		BZip2CompressorInputStream bzip = new BZip2CompressorInputStream(new FileInputStream(file));
		Scanner scanner = new Scanner(bzip);
		scanner.useDelimiter(Pattern.compile("\\n"));
		
		String sentence;
		int count = 0;
		while(scanner.hasNext() && count<maxSentences) {
			sentence = scanner.next();
			//if(sentence.length() == 0) continue;
			if(isTokenized == false) {
				untokenized.push(sentence);
			} else {
				tokenized.push(sentence);
			}
			
			count++;
		}
		scanner.close();

	}
	
	public LinkedList<String> getUntokenized() {
		return untokenized;
	}
	
	public LinkedList<String> getTokenized() {
		return tokenized;
	}
}
