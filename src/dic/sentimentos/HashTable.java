package dic.sentimentos;

import java.util.LinkedList;

public class HashTable
{

		private HashTable1[] hashTable;
		private final static int SIZE = 54702;

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

		/* put
		*  input: string word and int score to be inserted
		*  First, look to see if word already exists in hash table
		*   if so, solveCollision with the score to the WordEntry
		*   if not, create a new Entry and push it on the list at the
		*   appropriate array index
		*/
		public void put(String keyASerInserida, int score){
			  int keyASerInseridaComoInt = computeHash(keyASerInserida);
			  
			  	if(hashTable[keyASerInseridaComoInt] == null){ //add a new node in the linkedList
			  		hashTable[keyASerInseridaComoInt].add(new HashTable1(keyASerInseridaComoInt, score, keyASerInserida));
			  	}
			  	else { //collision has happened, so values have to be reset
			  		if(!(checkIfCollision( keyASerInserida,  score,  keyASerInseridaComoInt))){
			  			hashTable[keyASerInseridaComoInt].add(new HashTable1(keyASerInseridaComoInt, score, keyASerInserida)); //if there was no collision, it will add a new node at the end of the linked list
			  		}
			  		
			  	}
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
}
		/*
		*TODO: temos de faze ro rehash, ou seja, se chegam em 50% de ocupação ele faz o rehash
		*/




