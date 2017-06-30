package dic.sentimentos;

import java.util.ArrayList;
import java.util.HashMap;

public class InvertedIndex {

	private HashMap<Integer, ArrayList<Integer>> escoresHMap;
	public InvertedIndex(){
		//Aqui a mesma coisa, mas as keys serao escores (por enquanto soh temos 1, -1 e 0) e as values sao listas
		// de todos os tweets que teem aquele escore
		 escoresHMap = new HashMap();
	}
	public HashMap returnHash(){
		return escoresHMap;
	}
	public void add(int score, int tweetIndex){
		escoresHMap.get(score).add(tweetIndex);
	}
	public ArrayList<Integer> get1(int escoreTeste){
		return escoresHMap.get(escoreTeste);
		
	}
}
