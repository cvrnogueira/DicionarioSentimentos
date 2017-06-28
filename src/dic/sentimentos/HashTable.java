package dic.sentimentos;

public class HashTable
{

		private HashTable1[] hashTable;
		private final static int SIZE = 547;

		public HashTable(){
			hashTable= new HashTable1[20000];
			}
		/* computeHash
		*  return an integer based on the input string
		*  used for index into the array in hash table
		*  TODO: esse valor do size, não sei qual a gente usaria, temos de pensar
		*/
		private int computeHash(String keyASerInserida){
			return keyASerInserida.hashCode() % SIZE; // Calculate the rest of key's division by prime number 151, returning it as hash index.

		}

		/* put
		*  input: string word and int score to be inserted
		*  First, look to see if word already exists in hash table
		*   if so, solveCollision with the score to the WordEntry
		*   if not, create a new Entry and push it on the list at the
		*   appropriate array index
		*/
		public void put(String keyASerInserida, int score){
			  int keyASerInseridaComoInt = computeHash(keyASerInserida.trim());
			  	if(hashTable[keyASerInseridaComoInt] == null){
			  	//adiciona o nodo dai
			  		//array[keyASerInseridaComoInt] =  new HashTable1(keyASerInseridaComoInt, score);
			  	}
			  	if(hashTable[keyASerInseridaComoInt].getWord().equals(keyASerInserida)){
			  		//atualiza  pois já existe
			  	}
			  	else if(hashTable[keyASerInseridaComoInt].returnList() == null){
			  	//adiciona na lista
			  		// solveCollision(keyASerInseridaComoInt, score);
			  	}
			  	else if(hashTable[keyASerInseridaComoInt].returnList() != null){
			  	//percorre a lista encadeada do nodo e adiciona no final
			  		// solveCollision(keyASerInseridaComoInt, score);
			  	}
		}
		

		
		public boolean getValueFromKey(String keyASerBuscada){
			int keyASerBuscadaComoInt = computeHash(keyASerBuscada);
			boolean result = false;
			if(hashTable[keyASerBuscadaComoInt] == null){
				//retorna que nao tem essa no array
			}
			else if(hashTable[keyASerBuscadaComoInt].equals(keyASerBuscada)){
				//achou e ja devolve
			}
			else{//se chegou aqui é porque existe essa chave, lgoo deve estar encadeada OU ele foi um dia encadeada e removida, logo nao existe mais
				for(HashTable1 s: hashTable[keyASerBuscadaComoInt].returnList()){
					if(s.getWord().equals(keyASerBuscada)){
						//retorna ela porque achei
					}
				}
				//retorna falso porque nao achei
			}
			return result;
	         
		}
		/*
		*TODO: temos de faze ro rehash, ou seja, se chegam em 50% de ocupação ele faz o rehash
		*/
		public void solveCollision(int keyASerInseridaComoInt, int score, String word){
			hashTable[keyASerInseridaComoInt].returnList().add(new HashTable1(keyASerInseridaComoInt, score, word));
		}
		//TODO: o caso que ta tudo oupado, e dai é uma lista encadeada circular:
		//pontos de parada é ou estar ocupado == false OU chegar no prórpio elemento dnv
		//VER SE JAVA TEM ESTRUTURA DE LISTA ENCADEADA CIRCULAR
//		public boolean buscaIfProbablyCollision(int keyASerBuscadaComoInt){
//			System.out.println(array[keyASerBuscadaComoInt].getWord(keyASerBuscadaComoInt));
//			for(int i = keyASerBuscadaComoInt; i< hashTable.size(); i++){ //retorna a posição da onde deu possível colisão
//				if(array[i].getOcupado() == false){ //se ninguém nunca ocupou ele, é porque não existe
//					return false;
//				}
//			 }
//			return true;
//		}
//		   
}


