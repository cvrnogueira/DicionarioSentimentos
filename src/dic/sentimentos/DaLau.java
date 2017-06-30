package dic.sentimentos;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DaLau
{
	public static void main(String args[]){
		
		HashTable table = new HashTable();//create hash table
//	//===================da lau	
//		HashMap<String, ArrayList<Integer>> hmap = new HashMap<String,ArrayList<Integer>>();
//		
//		//Aqui a mesma coisa, mas as keys serao escores (por enquanto soh temos 1, -1 e 0) e as values sao listas
//		// de todos os tweets que teem aquele escore
//		HashMap<Integer, ArrayList<Integer>> escoresHMap = new HashMap<Integer, ArrayList<Integer>>();
//		Integer tweetIndex = 0;
//	//===================== end
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
			    
//			    //=======================lau
//				//Aqui eu checko se eu ja salvei esse novo escore no tweet
//				// se sim, soh dou apend na lista de tweets dele com esse novo tweet
//				
//			    if (escoresHMap.keySet().contains(score)){
//			    	escoresHMap.get(score).add(tweetIndex);
//			    	//System.out.println (escoresHMap.get(score));
//			    }
//			    
//			    //se nao, eu salvo, com uma lista de 1 elemento soh
//			    else{
//			    	ArrayList<Integer> TweetList = new ArrayList<Integer>();
//			    	TweetList.add(tweetIndex);
//			    	escoresHMap.put(score, TweetList);
//			    }
//			    
			    //===============================end 
			    Scanner sc2 = new Scanner(finalLine).useDelimiter(" "); //identify all individual strings
			    while(sc2.hasNext()){
			    	word = sc2.next().toLowerCase();
			    	word = Normalizer.normalize(word, Normalizer.Form.NFD);
			    	word = word.replaceAll("[^a-zA-Z\\s]", "").replaceAll("\\s+", " ");
			    	if(word.length() > 2){
			    	table.put(word, score);
//			    	
//			    	//=====================da lau
//			    	
//			    	//aqui eh o mesmo de ante, eu vejo se a palavra ta no meu hash
//			    	// se ta, dou append desse novo tweet. o if a mais ali eh pra ver se a lista ja tem esse index
//			    	// pra evitar casos em que a pessoa digite samsung samsung samsung dai iria slavar 3x o mesmo index
//			    		
//			    	if (hmap.keySet().contains(word)){
//			    		if (!(hmap.get(word).contains(tweetIndex))){
//			    			hmap.get(word).add(tweetIndex);
//			    		}
//			    	}
//			    	else {
//			    		ArrayList<Integer> myList = new ArrayList<Integer>();
//			    		myList.add(tweetIndex);	  
//			    		hmap.put(word, myList);
//			    	}
//			    	//===========================
			    	
			    	if(word.equals("chandler")){
			    		countParaTeste++;
			    	}
			    		//System.out.println(word + " " + score);
			    	}
			    }
			   // tweetIndex = tweetIndex + 1;
			  } 
			
		}
		catch (IOException x) {
			 System.err.format("Erro de E/S: %s%n", x);
		}
		
		//-----------------------PARA TESTES---------------------
		HashTable1 result = table.getValueFromKey("chandler");
		
		System.out.println(result + " -> countador é -> " + countParaTeste); 
		
	//daLau(escoresHMap, hmap); //aqui que eu chamo tua funcção de busca, lau!
	}
	
	///=====================da lau
//	
//	public static void daLau(HashMap<Integer, ArrayList<Integer>> escoresHMap, 		HashMap<String, ArrayList<Integer>> hmap){
//		
//		
//		//===================da lau	
//
//
//		//nao consegui buscar um scanf no stackoverflow entao coloquei um exempl de entrada pra testar mesmo!
//		
//		String palavraTeste = "samsung";
//		Integer escoreTeste = 0;
//		ArrayList tweets  = new ArrayList(); //vai armazenar a intersecao entre os indexes da palavra e os do escore
//		
//		
//		//aqui to fazendo vendo os tweets que estao nos dois
//		for(Integer tweetIndexTeste: escoresHMap.get(escoreTeste))
//		{
//			if (hmap.get(palavraTeste).contains(tweetIndexTeste)){
//				tweets.add(tweetIndexTeste);
//				
//			}
//		}
//		
//		System.out.println(tweets);
//		
//		
//		//vou abrir o arquivo pra prourar!
//		
//		Path path2 = Paths.get("pt.csv");
//		try (BufferedReader reader = Files.newBufferedReader(path2, Charset.forName("utf8"))) {
//			String line = null;
//			int score = 0;
//			Integer tweetIndex = 0;
//			String word = null;
//			ArrayList <String> retorno = new ArrayList <String>();
//			while ((line = reader.readLine()) != null) {
//				
//				Scanner sc = new Scanner(line).useDelimiter(","); // separador é ,
//				String finalLine = sc.next(); //linha sem o peso
//				score = sc.nextInt(); // peso
//			    Scanner sc2 = new Scanner(finalLine).useDelimiter(" "); //identify all individual strings
//			    
//			    //vejo se o index de agora eh algum dos que eu quero, se sim, dou append numa lista de retorno,
//			    //que vai ter todos os tweets que teem aquela palavra e mais aquele escore
//			    if (tweets.contains(tweetIndex)){
//			    	retorno.add(finalLine);
//			    }
//			    
//			    
//			    tweetIndex = tweetIndex + 1;
//			    
//			  } 
//			
//		//aqui to printando todos os tweets que tem escore 0 e conteem a palavra samsung!
//		for (String tweetRet: retorno){
//			System.out.println(tweetRet);
//		}
//			
//		}
//		catch (IOException x) {
//			 System.err.format("Erro de E/S: %s%n", x);
//		}
//		
//		
//		
		
		
		//==============================================
	//}
}
