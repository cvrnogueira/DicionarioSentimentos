package dic.sentimentos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class GlobalMembers
{
	public static void main(String args[]){
		
		Scanner scanner = new Scanner(System.in);
		HashTable table = new HashTable();//create hash table
		//===================arquivos invertidos
		InvertedIndex minusOne = new InvertedIndex(-1);
		InvertedIndex zero = new InvertedIndex(0);
		InvertedIndex one = new InvertedIndex(1);
		Integer tweetIndex = 0;
		//===================== end
		
		boolean stay = false;
		do{
			System.out.println("O que deseja fazer?");
			System.out.println("Carregar arquivo ao dicionário digite 1");
			System.out.println("Armazenar mais tweets aos existentes 2");
			System.out.println("Determinar a polaridade de tweets 3");
			System.out.println("funcionalidade adiconal 4");
			int op = scanner.nextInt();
			switch(op){
			case 01: {
				System.out.println("Nome do arquivo ou deseja usar o default?(ta usando o default)");
//				 int countParaTeste=0;
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
						    	word = word.toLowerCase(); //reforce this ideia
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
							    	default: System.out.println("peso inválido");
							    }

						    	if (table.getValueFromKey(word).returnList() !=null){
						    			if(!(table.getValueFromKey(word).returnList().contains(tweetIndex))){
						    				table.getValueFromKey(word).addTweet(tweetIndex);
						    			}
						    	}
						    	else{
						    			table.getValueFromKey(word).addTweet(tweetIndex);
						    	}
						    					    		
									    	
//									    	if(word.equals("samsung")){
//									    		countParaTeste++;
//									    	}
						    }
						  }
						    tweetIndex = tweetIndex + 1;
						} 
					}
					catch (IOException x) {
						 System.err.format("Erro de E/S: %s%n", x);
					}
//					//-----------------------PARA TESTES---------------------
//					HashTable1 result = table.getValueFromKey("samsung");
//					System.out.println("======= Início do teste da cata=====");
//					System.out.println(result + " -> countador é -> " + countParaTeste); 
//					System.out.println("======= Fim do teste da cata=====");
			}
			break;
			case 02:{
				
				System.out.println("Nome do arquivo ou deseja fazer um append no já existente?(escreve newFile ou append)");
				scanner.nextLine();	
				String inputWord = scanner.nextLine();
					
					if(inputWord.equals("newFile")){
						table.salvaEmArquivo("newFile");
					}
					else if(inputWord.equals("append")){
						table.salvaEmArquivo("append");
					}
					else{
						System.out.println("opcao Invalida");
					}
			}
			break;
			case 03:{
				InvertedIndex newPolaridade = new InvertedIndex();
				Integer tweetIndex2 = 0;
				System.out.println("Seu arquivo será salvo no de nome novo.txt");
				Path path3 = Paths.get("teste.txt");
				Path path4 = Paths.get("novo.txt");
				int soma = 0;
				try (BufferedReader reader = Files.newBufferedReader(path3, Charset.forName("utf8"))) {
					String line = null;
					int score = 0;
					String word = null;
					while ((line = reader.readLine()) != null) {
						Scanner sc = new Scanner(line);
						String finalLine = sc.next();
						
					    Scanner sc2 = new Scanner(finalLine).useDelimiter(" "); //identify all individual strings
					    soma = 0;
					    while(sc2.hasNext()){
					    	word = sc2.next().toLowerCase();
					    	word = Normalizer.normalize(word, Normalizer.Form.NFD);
					    	word = word.replaceAll("[^a-zA-Z\\s]", "").replaceAll("\\s+", " ");
					    	if(word.length() > 2){
					    		if(table.getValueFromKey(word) != null ){
					    			soma +=table.getValueFromKey(word).getTotalScore();
					    		}
					    	}
					    }
					    if(tweetIndex2 == 0){
					    	try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path4, Charset.forName("utf8")))) {
								writer.format(" %s;,%s%n",line, soma);
							}
							catch (IOException x) {
								System.err.format("Erro de E/S: %s%n", x);
							}
					    }
					    else {
					    	try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path4, Charset.forName("utf8"), StandardOpenOption.APPEND))) {
								writer.format(" %s;,%s%n",line, soma);
								}
								catch (IOException x) {
									System.err.format("Erro de E/S: %s%n", x);
								}
					    }
					  //escreve o tweet e a polaridade no arquivo
					    tweetIndex2 = tweetIndex2 + 1;
					}
					    
					} 
				catch (IOException x) {
					 System.err.format("Erro de E/S: %s%n", x);
				}
				
			}
			break;
			case 04: {
				daLau(minusOne, zero, one, table);
			}
			break;
			default: System.out.println("No option");
			}
			System.out.println("again?(booleano true ou false)");
			 stay = scanner.nextBoolean();
		}while(stay);
		

		

	}
	

//FUNCIONALIDADE ADICONAL
	public static void daLau(InvertedIndex minusOne,InvertedIndex zero, InvertedIndex one, HashTable table){

		String palavraTeste = new String();
		Integer escoreTeste = 0;
		LinkedList<Integer> tweets  = new LinkedList(); //vai armazenar a intersecao entre os indexes da palavra e os do escore
		
		
		Scanner terminalInput = new Scanner(System.in);
		String hashName= null;
		System.out.println("Por favor, digite a palavra que deseja procurar: ");
		
		String inputWord = terminalInput.nextLine();
		inputWord = Normalizer.normalize(inputWord, Normalizer.Form.NFD);
		inputWord = inputWord.replaceAll("[^a-zA-Z\\s]", "").replaceAll("\\s+", " ");
		inputWord = inputWord.toLowerCase();
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
	
	public static void searchListOfTweets(LinkedList<Integer> tweets){
		
		
		System.out.println("Num de tweets = " + tweets.size());
		System.out.println(tweets);
		
		
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
			
		}
		catch (IOException x) {
			 System.err.format("Erro de E/S: %s%n", x);
		}
	}
}