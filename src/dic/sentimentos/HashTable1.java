package dic.sentimentos;

import java.util.LinkedList;

public class HashTable1 {
	
    private int key;
    private String word;
    private int value;
    private int totalScore;
//    private boolean ocupado = false;
//    private boolean usado = false;
    private static int numAppearances= 0;
    LinkedList<HashTable1> colisions;

    
	public HashTable1(int key, int value, String word){
		this.key = key;
		this.value = value;
		this.word = word;
		this.totalScore = value;
//		this.setUsado(); //se tem alguem ali, no momento
//		this.setOcupado(); //se algum dia já foi ou se está com alguem ali
		numAppearances = 1;
		colisions = new LinkedList<>();
	}
	

	public int getKey() {
        return key;
     }
	public String getWord() {
        return word;
     }
//	public void addColision(HashTable1 tweet){
//		this.colisions.add(tweet);
//	}
	public LinkedList<HashTable1> returnList(){
		return this.colisions;
	}

	public int getValue() {
        return value;
    } 
	public void incAppearances(){
		numAppearances++;
	}
//	public boolean getUsado(){
//		return usado;
//	}
//	public boolean getOcupado(){
//		return ocupado;
//	}
//	public void setOcupado(){
//		this.ocupado = true;
//	}
//	public void setUsado(){
//		this.usado = true;
//	}
	public void setTotalScore(int score){
		totalScore+=score;
	}
	@Override
	public String toString() {
		return key + " [" + value+  "]";
	}
 
}
