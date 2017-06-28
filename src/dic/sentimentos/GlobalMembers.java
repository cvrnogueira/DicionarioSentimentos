package dic.sentimentos;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.Scanner;

public class GlobalMembers
{
	public static void main(String args[]){
		
		HashTable table = new HashTable();//create hash table
	 
		Path path1 = Paths.get("pt.csv");
		try (BufferedReader reader = Files.newBufferedReader(path1, Charset.forName("utf8"))) {
			String line = null;
			int score = 0;
			String word = null;
			while ((line = reader.readLine()) != null) {
				Scanner sc = new Scanner(line).useDelimiter(","); // separador é ,
				String finalLine = sc.next(); //linha sem o peso
			    score = sc.nextInt(); // peso
			    Scanner sc2 = new Scanner(finalLine).useDelimiter(" "); //identify all individual strings
			    while(sc2.hasNext()){
			    	word = sc2.next().toLowerCase();
			    	word = Normalizer.normalize(word, Normalizer.Form.NFD);
			    	word = word.replaceAll("[^a-zA-Z\\s]", "").replaceAll("\\s+", " ");
			    	if(word.length() > 2){
			    	//table.put(word, score);
			    		System.out.println(word + " " + score);
			    	}
			    }
			  } 
		}
		catch (IOException x) {
			 System.err.format("Erro de E/S: %s%n", x);
		}
		
//		String keyASerInserida = "bananana98ijskslskj";
//		int key= 0;
//		boolean result = table.getValueFromKey("bananana98ijskslskj");
//		
//		System.out.println(result); // ta dando true sempre
	}
}
