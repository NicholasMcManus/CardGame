/*
 * file name: Player.java
 * programmer name: Nick McManus
 * date created: 11-08-2018
 * date of last revision: 11-27-2018
 * details of last revision: Write JavaDoc Comments
 * short description: Describe a player object to contain scores and a name
 */

package cardgame;

import java.util.ArrayList;
import java.util.Collections;

public class Player implements java.io.Serializable{
    private ArrayList<Score> scoreList;
    private String playerName;
    
    /**
     * Default constructor for the Player Class
     */
    public Player(){
        this("Potato");
    }
    
    /**
     * Constructor allowing for a player name to be set on initialization
     * @param playerName The name for the player
     */
    public Player(String playerName){
        this.playerName = playerName;
        scoreList = new ArrayList<>();
    }
    
    /**
     * Constructor allowing for a player to be created with name and score
     * @param playerName Name of the player
     * @param score Score of the player
     */
    public Player(String playerName, int score)
    {
        this(playerName);
        scoreList.add(new Score(score));
    }
    
    /**
     * Add a score to the player's list of scores
     * @param score The score to be added
     */
    public void addScore(int score){
        scoreList.add(new Score(score));
        Collections.sort(scoreList, Collections.reverseOrder());
        if(scoreList.size() > 10)
        {
            scoreList.remove(10);
        }
    }
    
    /**
     * Getter for a specific score from the player's list of scores
     * @param scoreNumber The number of the score to retrieve
     * @return A score object representing the score of a player
     * @throws ArrayIndexOutOfBoundsException 
     */
    public Score getScore(int scoreNumber) 
            throws ArrayIndexOutOfBoundsException{
        if(scoreNumber >= scoreList.size())
            throw new ArrayIndexOutOfBoundsException();
        
        return scoreList.get(scoreNumber);
    }
    
    /**
     * Get the number of scores stored in the player object
     * @return The number of scores as an integer
     */
    public int getNumScores()
    {
        return scoreList.size();
    }
    
    /**
     * Get the name of the player
     * @return The name of the player
     */
    public String getName(){
        return playerName;
    }
    
    /**
     * Allow the name of the player to be changed
     * @param name The new name for the player
     */
    public void setName(String name){
        this.playerName = name;
    }
}
