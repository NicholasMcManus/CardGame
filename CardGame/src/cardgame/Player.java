/*
 * file name: Player.java
 * programmer name: Nick McManus
 * date created: 11-08-2018
 * date of last revision: 
 * details of last revision:
 * short description: 
 */

package cardgame;

import java.util.ArrayList;
import java.util.Collections;

public class Player implements java.io.Serializable{
    private ArrayList<Score> scoreList;
    private String playerName;
    
    public Player(){
        this("Potato");
    }
    
    public Player(String playerName){
        this.playerName = playerName;
        scoreList = new ArrayList<>();
    }
    
    public Player(String playerName, int score)
    {
        this(playerName);
        scoreList.add(new Score(score));
    }
    
    public void addScore(int score){
        scoreList.add(new Score(score));
        Collections.sort(scoreList, Collections.reverseOrder());
        if(scoreList.size() > 10)
        {
            scoreList.remove(10);
        }
    }
    
    public Score getScore(int scoreNumber) 
            throws ArrayIndexOutOfBoundsException{
        if(scoreNumber >= scoreList.size())
            throw new ArrayIndexOutOfBoundsException();
        
        return scoreList.get(scoreNumber);
    }
    
    public int getNumScores()
    {
        return scoreList.size();
    }
    
    public String getName(){
        return playerName;
    }
    
    public void setName(String name){
        this.playerName = name;
    }
}
