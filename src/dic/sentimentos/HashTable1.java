package dic.sentimentos;

import java.util.ArrayList;
import java.util.LinkedList;

public class HashTable1 {
	
    private int key;
    private String word;
    private int value;
    private int totalScore;
    private int numAppearances= 0;
    LinkedList<HashTable1> colisions;
    ArrayList<Integer> idTweets;

    public HashTable1(){
    	colisions = new LinkedList<>();
    }
    
	public HashTable1(int key, int value, String word){
		this.key = key;
		this.value = value;
		this.word = word;
		this.totalScore = value;
		this.numAppearances = 1;
		this.idTweets= new ArrayList();
	}
	public int getKey() {
        return key;
     }
	public String getWord() {
        return word;
     }
	public void addId(int tweetIndex){
		idTweets.add(tweetIndex);
	}
	public LinkedList<HashTable1> returnList(){
		return this.colisions;
	}

	public int getValue() {
        return value;
    } 
	public void incAppearances(){
		this.numAppearances +=1;
	}
	 public void setValue(int value) {
         this.value = this.totalScore/this.numAppearances;
	 }
	 public int getNumAppearances(){
		 return this.numAppearances;
	 }
	 public double getTotalScore(){
		 return this.totalScore;
	 }
	 public void setTotalScore(int score){
		totalScore+=score;
	 }
	 public void add(HashTable1 hashTable1) {
		this.colisions.add(hashTable1);	
	}
	 @Override
	 public String toString() {
		return (" === key é: " + this.getKey() + "\n ==== [" + "value é" +  "] "  + this.getValue() +  "\n ==== [" + "Total value é" +  "] "  + this.getTotalScore() + "\n ==== Word é " + this.getWord() 
		+ "\n ==== Apareceu "  + this.getNumAppearances() + " vezes \n");
	}

 
}
