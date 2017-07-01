package dic.sentimentos;

import java.util.ArrayList;
import java.util.HashMap;

public class InvertedIndex {

	private int key;
	private HashTable2[] escoresHMap; //hashmap de int e arraylist 
	private static int SIZE = 12647; //prime number

	public InvertedIndex(){
		escoresHMap = new HashTable2[SIZE];
        for (int i = 0; i < SIZE; i++)
        	escoresHMap[i] = new HashTable2(); //cada indice do array agora tem uma lista encadeada
	}
	private int computeHash(int keyASerInserida){
		if(keyASerInserida == -1){
			keyASerInserida = 2;
		}
		return (keyASerInserida % SIZE); // Calculate the rest of key's division by prime number 151, returning it as hash index.
	}
	public void put(int keyASerInserida, int id){
		  int keyASerInseridaComoInt = computeHash(keyASerInserida);

		  	if(escoresHMap[keyASerInseridaComoInt] == null){ //add a new node in the linkedList
		  		escoresHMap[keyASerInseridaComoInt].add(new HashTable2(keyASerInseridaComoInt, id));
		  	
		  	}
		  	else { //collision has happened, so values have to be reset
		  		if(!(checkIfCollision( keyASerInserida,  id,  keyASerInseridaComoInt))){
		  			escoresHMap[keyASerInseridaComoInt].add(new HashTable2(keyASerInseridaComoInt, id)); //if there was no collision, it will add a new node at the end of the linked list
		  		}
		  		
		  	}
	}
	public boolean checkIfCollision(int keyASerInserida, int id, int keyASerInseridaComoInt){
		for(HashTable2 entry: escoresHMap[keyASerInseridaComoInt].returnList()){ //return the collision list
  			if (entry.getKey() == (keyASerInserida)){ //SE O VALOR JÁ TÁ LÁ, VOU ADICIONAR O ID NO ARRAYLIST
  	      		entry.addTweet(id);
  	      		return true;
  	      	}
  		}
		return false;
	}
	   public HashTable2 getValueFromKey(int keyASerBuscada) {

		   int keyASerInseridaComoInt = computeHash(keyASerBuscada);

            if (escoresHMap[keyASerInseridaComoInt] == null) //if the key does not exist is false
                  return null;
            else {
                  for(HashTable2 colisao: escoresHMap[keyASerInseridaComoInt].returnList()){ //check if a collision have happend, so it will search in the linked list associated to the key generated my computeHas()
                	  if(colisao.getKey() == (keyASerBuscada)){
                		  return colisao;
                	  }
                  }
                  return null; //if does the key is not in the liked list of keys it does not exist
            }

	   }


}
