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
	 int countParaTeste=0;
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
			    	table.put(word, score);
			    	if(word.equals("chandler")){
			    		countParaTeste++;
			    	}
			    		//System.out.println(word + " " + score);
			    	}
			    }
			  } 
		}
		catch (IOException x) {
			 System.err.format("Erro de E/S: %s%n", x);
		}
		
		//-----------------------PARA TESTES---------------------
		HashTable1 result = table.getValueFromKey("chandler");
		
		System.out.println(result + " -> countador é -> " + countParaTeste); 
	}
}
