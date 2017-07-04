package dic.sentimentos;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class GlobalMembers
{
	public static void main(String args[]){
		
		HashTable table = new HashTable();//create hash table
	//===================arquivos invertidos
		InvertedIndex minusOne = new InvertedIndex(-1);
		InvertedIndex zero = new InvertedIndex(0);
		InvertedIndex one = new InvertedIndex(1);
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
			    
			    
			    Scanner sc2 = new Scanner(finalLine).useDelimiter(" "); //identify all individual strings
			    while(sc2.hasNext()){
			    	word = sc2.next().toLowerCase();
			    	word = Normalizer.normalize(word, Normalizer.Form.NFD);
			    	word = word.replaceAll("[^a-zA-Z\\s]", "").replaceAll("\\s+", " ");
			    	if(word.length() > 2){
			    	table.put(word, score);
			    	 //=======================arquivos invertidos	
			    	switch(score){
				    	case -1: {
				    		minusOne.put(word, tweetIndex);
				    	}
				    	break;
				    	case 1: {
				    	 	one.put(word, tweetIndex);
				    	}
				    	break;
				    	case 0: {
				    		 zero.put(word, tweetIndex);
				    	}
				    	break;
				    }

			    	if (table.getValueFromKey(word).returnList() !=null){
			    			if(!(table.getValueFromKey(word).returnList().contains(tweetIndex))){
			    				table.getValueFromKey(word).addTweet(tweetIndex);
			    			}
			    	}
			    	else{
			    			table.getValueFromKey(word).addTweet(tweetIndex);
			    	}
			    					    		

			    	//===========================end
						    	
						    	if(word.equals("samsung")){
						    		countParaTeste++;
						    	}
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
		
	daLau(minusOne, zero, one, table); //aqui que eu chamo tua funcção de busca, lau!
	}
	
	///=====================da lau
	

	public static void daLau(InvertedIndex minusOne,InvertedIndex zero, InvertedIndex one, HashTable table){
		
		//nao consegui buscar um scanf no stackoverflow entao coloquei um exempl de entrada pra testar mesmo!
		String palavraTeste = new String();
		Integer escoreTeste = 0;
		LinkedList<Integer> tweets  = new LinkedList(); //vai armazenar a intersecao entre os indexes da palavra e os do escore
		
		
		Scanner terminalInput = new Scanner(System.in);
		String hashName= null;
		System.out.println("Por favor, digite a palavra que deseja procurar: ");
		//read input
		
		String inputWord = terminalInput.nextLine();
		palavraTeste = inputWord;
		
		System.out.println("Deseja pesquisar um escore? 1 - sim. 0 - nao:");
		int op = terminalInput.nextInt();
		if (op == 1){ 
			System.out.println("Digite seu escore: ");
			int inputScore = terminalInput.nextInt ();

			switch(inputScore){
	    	case -1: {
	    		if(minusOne.getValueFromKey(palavraTeste) == null){
					System.out.println("Nao ha tweets com essa palavra nesse escore!");
				}
				else if(minusOne.getValueFromKey(palavraTeste).returnTweetsList() ==null){
					System.out.println("Nao ha tweets com esse escore!");
				}
				else{
					searchListOfTweets (minusOne.getValueFromKey(palavraTeste).returnTweetsList());
				}
	    	}
	    	break;
	    	case 1: {
	    		if(one.getValueFromKey(palavraTeste) == null){
					System.out.println("Nao ha tweets com essa palavra nesse escore!");
				}
				else if(one.getValueFromKey(palavraTeste).returnTweetsList() ==null){
					System.out.println("Nao ha tweets com esse escore!");
				}
				else{
					searchListOfTweets (one.getValueFromKey(palavraTeste).returnTweetsList());
				}
	    	}
	    	break;
	    	case 0: {
	    		if(zero.getValueFromKey(palavraTeste) == null){
					System.out.println("Nao ha tweets com essa palavra nesse escore!");
				}
				else if(zero.getValueFromKey(palavraTeste).returnTweetsList() ==null){
					System.out.println("Nao ha tweets com esse escore!");
				}
				else{
					searchListOfTweets (zero.getValueFromKey(palavraTeste).returnTweetsList());
				}
	    	}
	    	break;
	    }

	}
	else{
			
		if (table.getValueFromKey(palavraTeste) == null) {
			
			System.out.print("Nao ha tweets com essa palavra!");
		}
		else{
			tweets = table.getValueFromKey(palavraTeste).returnTweetList();
			searchListOfTweets (tweets);
			}
		}
	}
		//==============================================
	
	
	public static void searchListOfTweets(LinkedList<Integer> tweets){
		

		System.out.println("======= Início do teste da Lau=====");
		
		System.out.println("Num de tweets = " + tweets.size());
		System.out.println(tweets);
		
		
		//vou abrir o arquivo pra prourar!
		
		Path path2 = Paths.get("pt.csv");
		try (BufferedReader reader = Files.newBufferedReader(path2, Charset.forName("utf8"))) {
			String line = null;
			Integer tweetIndex = 0;
			ArrayList <String> retorno = new ArrayList <String>();
			while ((line = reader.readLine()) != null) {
				
				Scanner sc = new Scanner(line).useDelimiter(","); // separador é ,
				String finalLine = sc.next(); //linha sem o peso
				sc.nextInt(); // peso
			    
			    //vejo se o index de agora eh algum dos que eu quero, se sim, dou append numa lista de retorno,
			    //que vai ter todos os tweets que teem aquela palavra e mais aquele escore
			    if (tweets.contains(tweetIndex)){
			    	retorno.add(finalLine);
			    }
			    tweetIndex = tweetIndex + 1;
			  } 

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