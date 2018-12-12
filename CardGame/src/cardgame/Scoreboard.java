/*
 * file name: Scoreboard.java
 * programmer name: Nick McManus
 * date created: 11-12-2018
 * date of last revision: 11-27-2018
 * details of last revision: Add java documentation to the class
 * short description: Be able to create a scoreboard to display a number of
                        high scores
 */

package cardgame;

import java.util.ArrayList;

public class Scoreboard {
    private ArrayList<Score> highScores; //ArrayList that stores Score objects
    private ArrayList<String> highScorePlayer;//ArrayList that stores a String of the name of the highest score player's name
    //Class variables
    private final int NUM_SCORES;
    
    /**
     * Construct a scoreboard from a list of players and
     * direct a number of scores
     * @param players A list containing a number of players to be used to construct
     * the list
     * @param numScores The number of scores to keep on the scoreboard
     */  
    public Scoreboard(ArrayList<Player> players, int numScores)
    {
        NUM_SCORES = numScores; //Assign number of scores 
        highScores = new ArrayList(NUM_SCORES); //Creates a new ArrayList with the number of scores passed 
        highScorePlayer = new ArrayList(NUM_SCORES); //Creates a new ArrayList for names with the number of scores passed
        
        fillScoreBoard(players);//Sends the array list of players to the fillScoreBoard 
    }
    
    /**
     * A constructor to create a scoreboard with 10 scores and a list of players
     * @param players The list of players to use when constructing a scoreboard
     */
    public Scoreboard(ArrayList<Player> players)
    {
        this(players, 10);//sends the varibles to the previous constructor
    }
    
    /**
     * A method to actually fill the scoreboard with members
     * @param players The list of players to add to the scoreboard
     */
    private void fillScoreBoard(ArrayList<Player> players)
    {
        //For every player
        for(int i = 0; i < players.size(); i++)
        {
            //For every score in a player
            for(int scoreNum = 0; scoreNum < players.get(i).getNumScores();
                    scoreNum++)
            {
                //If the score was added successfully
                if(addScore(players.get(i).getName(), players.get(i).getScore(scoreNum)))
                {
                    //Check the number of scores
                    if(highScores.size() > NUM_SCORES)
                    {
                        highScores.remove(NUM_SCORES); //Removes that particular score
                        highScorePlayer.remove(NUM_SCORES);//Removes that name with that particular score
                    }
                }
            }
        }
    }
    
    /**
     * Method to add scores to the two array lists
     * @param playerName The name of the player to add
     * @param score The score of the player to add
     * @return 
     */
    private boolean addScore(String playerName, Score score) //Takes name and a Score object
    {
        //Checks if the highScore ArrayList is empty
        if(highScores.isEmpty()) 
        {
            highScores.add(score); //Adds the first score number 
            highScorePlayer.add(playerName); //Adds the first player name
            return true;
        }
        
        //The loop puts the score and name to where the highscore is less than or equal to 0 when comparing the score passed 
        for(int i = 0; i < highScores.size(); i++)
        {
            //test = highScores.get(i).compareTo(score);
            if(highScores.get(i).compareTo(score) <= 0)
            {
                highScores.add(i, score);
                highScorePlayer.add(i, playerName);
                return true;
            }
        }
        
      //Checks if the size of the highscore is less than the maximum amounf of scores to add the score and name passed
        if(highScores.size() < NUM_SCORES)
        {  
            highScores.add(score);
            highScorePlayer.add(playerName);
            return true; //Returns true if there is space
        }
        
        return false; //Returns false if none of the conditions passed
    }

    /**
     * Retrieve the number of scores stored in the scoreboard
     * @return The number of scores on the scoreboard
     */
    public int getNumScores()
    {
        return highScores.size();
    }
    
    /**
     * Get a name from the scoreboard at a specific index
     * @param index The index to pull the name from
     * @return The name of a player stored in a particular index
     * @throws ArrayIndexOutOfBoundsException 
     */
    public String getName(int index)
                throws ArrayIndexOutOfBoundsException{
        if(index >= highScorePlayer.size())
            throw new ArrayIndexOutOfBoundsException();
        
        return highScorePlayer.get(index);
    }
    
    /**
     * Get a score from the scoreboard at a specific index
     * @param scoreNumber The index to pull the score from
     * @return The score of a player stored in a particular index
     * @throws ArrayIndexOutOfBoundsException 
     */
    public Score getScore(int scoreNumber) 
            throws ArrayIndexOutOfBoundsException{
        if(scoreNumber >= highScores.size())
            throw new ArrayIndexOutOfBoundsException();
        
        return highScores.get(scoreNumber);
    }
}
