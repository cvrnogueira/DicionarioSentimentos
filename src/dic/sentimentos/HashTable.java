package dic.sentimentos;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HashTable
{

		private HashTable1[] hashTable;
		private static int SIZE = 1240; //prime number
		private static int NUMBOFKEYS = 0;

		public HashTable(){
			hashTable = new HashTable1[SIZE];
	            for (int i = 0; i < SIZE; i++)
	            	hashTable[i] = new HashTable1(); //cada indice do array agora tem uma lista encadeada
			}
		/* computeHash
		*  return an integer based on the input string
		*  used for index into the array in hash table
		*  TODO: esse valor do size, não sei qual a gente usaria, temos de pensar
		*/
		private int computeHash(String keyASerInserida){
			if(keyASerInserida.hashCode() % SIZE > 0){
				return keyASerInserida.hashCode() % SIZE;
			}
			else{
				return (keyASerInserida.hashCode() % SIZE * -1); // Calculate the rest of key's division by prime number 151, returning it as hash index.
			}
		}
		private HashTable1 a(int a){
			return hashTable[a];
		}

		public void setSize(int size){
			SIZE = size;
		}
//		public boolean isRehashNeeded(){
//			System.out.println(NUMBOFKEYS/SIZE);
//			if(NUMBOFKEYS/SIZE >= 8 ){
//				rehash();
//				return true;
//			}
//			else{
//				return false;
//			}
//		}
		public void resetNumbOfKeys(){
			NUMBOFKEYS = 0;
		}

		/* put
		*  input: string word and int score to be inserted
		*  First, look to see if word already exists in hash table
		*   if so, solveCollision with the score to the WordEntry
		*   if not, create a new Entry and push it on the list at the
		*   appropriate array index
		*/

		public void put(String keyASerInserida, int score){
		
			  int keyASerInseridaComoInt = computeHash(keyASerInserida);
			 
			  	if(hashTable[keyASerInseridaComoInt].returnList().isEmpty()){ //add a new node in the linkedList
			  		hashTable[keyASerInseridaComoInt].add(new HashTable1(keyASerInseridaComoInt, score, keyASerInserida));
			  		
			  		
			  	}
			  	else { //collision has happened, so values have to be reset
			  		if(!(checkIfCollision( keyASerInserida,  score,  keyASerInseridaComoInt))){
			  			hashTable[keyASerInseridaComoInt].add(new HashTable1(keyASerInseridaComoInt, score, keyASerInserida)); //if there was no collision, it will add a new node at the end of the linked list
			  			
			  		}
			  		
			  	}
//			  	boolean answer = isRehashNeeded();
//			  	if(answer == true){
//			  	System.out.println(answer);
//			  	}
			  	NUMBOFKEYS++;
		}
			  	
			  	
		public boolean checkIfCollision(String keyASerInserida, int score, int keyASerInseridaComoInt){
			for(HashTable1 entry: hashTable[keyASerInseridaComoInt].returnList()){
	  			if (entry.getWord().equals(keyASerInserida)){
	  	      		entry.setValue(score);
	  	      		entry.incAppearances();
	  	      		entry.setTotalScore(score);
	  	      		return true;
	  	      	}
	  		}
			return false;
		}
  	
		   public HashTable1 getValueFromKey(String keyASerBuscada) {

			   int keyASerInseridaComoInt = computeHash(keyASerBuscada);

	            if (hashTable[keyASerInseridaComoInt] == null) //if the key does not exist is false
	                  return null;
	            else {
	                  for(HashTable1 colisao: hashTable[keyASerInseridaComoInt].returnList()){ //check if a collision have happend, so it will search in the linked list associated to the key generated my computeHas()
	                	
	                	  if(colisao.getWord().equals(keyASerBuscada)){
	                		  return colisao;
	                	  }
	                  }
	                  return null; //if does the key is not in the liked list of keys it does not exist
	            }

		   }
		   public String giveWordOfList(int i){
			   System.out.println(hashTable[i]);
			   return hashTable[i].giveWordOfList(i);
		   }
		   public LinkedList<HashTable1> getValueFromInt(int num){
			   return hashTable[num].returnList();
		   }
		   
		   
		  	
			public boolean checkIfCollision2(String keyASerInserida, int score, int keyASerInseridaComoInt){
				for(HashTable1 entry: hashTable[keyASerInseridaComoInt].returnList()){
		  			if (entry.getWord().equals(keyASerInserida)){
		  	      		entry.setValue(score);
		  	      		entry.incAppearances();
		  	      		entry.setTotalScore(score);
		  	      		return true;
		  	      	}
		  		}
				return false;
			}
			
		   
		 public void testaTamanho(){
			 HashTable hashTable2 = this;
		 
			 	int maior = 0;
				int temp = 0;
				int o =0;
				int total = 0;
				
				for(int i =0; i< SIZE; i++){
					 temp = 0;
					 if(hashTable2.a(i).returnList().isEmpty()){
						 o++;
					 }
					
					for(HashTable1 entry: hashTable2.a(i).returnList()){
							temp++;
							total++;
					}

					if(temp > maior){
						maior = temp;
					}
				}
		 }
		 
		   public void salvaEmArquivo(String op){//append em caso de append e newFile em caso de overwrite
			   
			HashTable hashTable2 = this;
			Path path2 = Paths.get("dados.rtf");
			if(Files.exists(path2)) {
				if(op.equals("append")){
					try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path2, Charset.forName("utf8"), StandardOpenOption.APPEND))) {
						for(int i =0; i< SIZE; i++){
							for(HashTable1 entry: hashTable2.a(i).returnList()){
								writer.format("Chave= %s; Valor= %s; Escore Total= %s; Número de vezes em que aparece= %s%n",entry.getWord(), entry.getValue(), entry.getTotalScore(), entry.getNumAppearances());
							}
						}
					}
					catch (IOException x) {
						Stage dialogStage = new Stage();
						dialogStage.initModality(Modality.WINDOW_MODAL);
						VBox vbox = new VBox(new Text("Erro de E/S: " +  x));
						vbox.setAlignment(Pos.CENTER);
						vbox.setPadding(new Insets(46));
						dialogStage.setScene(new Scene(vbox));
						dialogStage.show();
					}
				}
				else if(op.equals("newFile")){
					try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path2, Charset.forName("utf8")))) {
						for(int i =0; i< SIZE; i++){
							for(HashTable1 entry: hashTable2.a(i).returnList()){
								writer.format("Chave= %s; Valor= %s; Escore Total= %s; Número de vezes em que aparece= %s%n",entry.getWord(), entry.getValue(), entry.getTotalScore(), entry.getNumAppearances());
							}
						}
					}
					catch (IOException x) {
						Stage dialogStage = new Stage();
						dialogStage.initModality(Modality.WINDOW_MODAL);
						VBox vbox = new VBox(new Text("Erro de E/S: " +  x));
						vbox.setAlignment(Pos.CENTER);
						vbox.setPadding(new Insets(46));
						dialogStage.setScene(new Scene(vbox));
						dialogStage.show();
					}
				}
			  }
			else{
				System.out.println("Esse arquivo nao existe!");
			}
		   }

		/*
		*TODO: temos de fazer o rehash, ou seja, se chegam em 50% de ocupação ele faz o rehash e o rehash pra quando n ta mto ocupado
		*/
//		  public void rehash(){
//			  int oldSize = SIZE;
//			  HashTable1[] oldHashTable = hashTable;
//			  resetNumbOfKeys();
//			  setSize(oldSize*2);
//			  HashTable newHashTable =  new HashTable();
//			  for (int i = 0; i < oldSize; i++){
//				  if(oldHashTable[i] != null){ //Tenho de oercorrer a lista encadeada agr pra ir colocando no novo array 
//					  newHashTable.put(oldHashTable[i].getWord(), oldHashTable[i].getValue()); 
//				  }
//			  }
//			  
//		  }  
}

