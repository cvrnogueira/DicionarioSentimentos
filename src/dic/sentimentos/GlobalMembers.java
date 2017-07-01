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
import java.util.LinkedList;
import java.util.Scanner;

public class GlobalMembers
{
	public static void main(String args[]){
		
		HashTable table = new HashTable();//create hash table
	//===================da lau	
		InvertedIndex escoresHMap = new InvertedIndex();
		Integer tweetIndex = 0;
	//===================== end
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
			    
			    //=======================lau		
				   escoresHMap.put(score, tweetIndex);
		   
			    //===============================end 
			    Scanner sc2 = new Scanner(finalLine).useDelimiter(" "); //identify all individual strings
			    while(sc2.hasNext()){
			    	word = sc2.next().toLowerCase();
			    	word = Normalizer.normalize(word, Normalizer.Form.NFD);
			    	word = word.replaceAll("[^a-zA-Z\\s]", "").replaceAll("\\s+", " ");
			    	if(word.length() > 2){
			    	table.put(word, score);
			    	
			    	//=====================da lau

			    		if (table.getValueFromKey(word).returnList() !=null){
			    			if(!(table.getValueFromKey(word).returnList().contains(tweetIndex))){
			    				table.getValueFromKey(word).addTweet(tweetIndex);
			    			}
			    		}
			    		else{
			    			table.getValueFromKey(word).addTweet(tweetIndex);
			    		}
			    					    		

			    	//===========================
			    	
			    	if(word.equals("samsung")){
			    		countParaTeste++;
			    	}
			    		//System.out.println(word + " " + score);
			    	}
			    }
			    tweetIndex = tweetIndex + 1;
			  } 
			
		}
		catch (IOException x) {
			 System.err.format("Erro de E/S: %s%n", x);
		}
		
		//-----------------------PARA TESTES---------------------
		HashTable1 result = table.getValueFromKey("samsung");
		System.out.println("======= Início do teste da cata=====");
		System.out.println(result + " -> countador é -> " + countParaTeste); 
		System.out.println("======= Fim do teste da cata=====");
		
	daLau(escoresHMap, table); //aqui que eu chamo tua funcção de busca, lau!
	}
	
	///=====================da lau
	

	public static void daLau(InvertedIndex escoresHMap, HashTable table){
		
		//nao consegui buscar um scanf no stackoverflow entao coloquei um exempl de entrada pra testar mesmo!
		
		
		Scanner terminalInput = new Scanner(System.in);
		System.out.println("Por favor, digite a palavra que deseja procurar: ");
		//read input
		
		String inputWord = terminalInput.nextLine();
		System.out.println("Por favor, digite o escore que deseja procurar, caso nao queira, ponha 9: ");
		//Integer  = System.in.read();
		Scanner in = new Scanner(System.in);
		int inputScore = in.nextInt();
       
            //System.out.print("You entered ");
            //System.out.println(inChar);
        
		
		String palavraTeste = inputWord;
		Integer escoreTeste = inputScore;
		LinkedList<Integer> tweets  = new LinkedList(); //vai armazenar a intersecao entre os indexes da palavra e os do escore
		
		
		//aqui to fazendo vendo os tweets que estao nos dois
		
		
		if (escoresHMap.getValueFromKey(escoreTeste) == null) {
			tweets = table.getValueFromKey(palavraTeste).returnTweetList();
			
			searchListOfTweets (tweets);
		}
		else {
			
			if (table.getValueFromKey(palavraTeste) == null) {
				//tweets = escoresHMap.get1(escoreTeste);
				
				System.out.print("Nao ha tweets com essa palavra!");
			}
			else {
				
				for(Integer tweetIndexTeste: escoresHMap.getValueFromKey(escoreTeste).returnTweetsList())
				{
					
					
					if (table.getValueFromKey(palavraTeste).returnTweetList().contains(tweetIndexTeste)){
						tweets.add(tweetIndexTeste);
						
					}
				}
				
				searchListOfTweets (tweets);
				//vai catar no arquivo
				
				
			}
			
		
		
		}	
		//==============================================
	}
	
	
	public static void searchListOfTweets(LinkedList tweets){
		

		System.out.println("======= Início do teste da Lau=====");
		
		System.out.println("Num de tweets = " + tweets.size());
		System.out.println(tweets);
		
		
		//vou abrir o arquivo pra prourar!
		
		Path path2 = Paths.get("pt.csv");
		try (BufferedReader reader = Files.newBufferedReader(path2, Charset.forName("utf8"))) {
			String line = null;
			int score = 0;
			Integer tweetIndex = 0;
			String word = null;
			ArrayList <String> retorno = new ArrayList <String>();
			while ((line = reader.readLine()) != null) {
				
				Scanner sc = new Scanner(line).useDelimiter(","); // separador é ,
				String finalLine = sc.next(); //linha sem o peso
				score = sc.nextInt(); // peso
			    Scanner sc2 = new Scanner(finalLine).useDelimiter(" "); //identify all individual strings
			    
			    //vejo se o index de agora eh algum dos que eu quero, se sim, dou append numa lista de retorno,
			    //que vai ter todos os tweets que teem aquela palavra e mais aquele escore
			    if (tweets.contains(tweetIndex)){
			    	retorno.add(finalLine);
			    }
			    
			    
			    tweetIndex = tweetIndex + 1;
			    
			  } 
			
		//aqui to printando todos os tweets que tem escore 0 e conteem a palavra samsung!
		for (String tweetRet: retorno){
			System.out.println(tweetRet);
		}
		System.out.println("======= Fim do teste da Lau=====");
			
		}
		catch (IOException x) {
			 System.err.format("Erro de E/S: %s%n", x);
		}
	}
}
