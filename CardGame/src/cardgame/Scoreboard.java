/*
 * file name: Scoreboard.java
 * programmer name: Nick McManus
 * date created: 11-12-2018
 * date of last revision: 
 * details of last revision:
 * short description: 
 */

package cardgame;

import java.util.ArrayList;

public class Scoreboard {
    private ArrayList<Score> highScores;
    private ArrayList<String> highScorePlayer;
    
    private final int NUM_SCORES;
    
    public Scoreboard(ArrayList<Player> players, int numScores)
    {
        NUM_SCORES = numScores;
        highScores = new ArrayList(NUM_SCORES);
        highScorePlayer = new ArrayList(NUM_SCORES);
        
        fillScoreBoard(players);
    }
    
    public Scoreboard(ArrayList<Player> players)
    {
        this(players, 10);
    }
    
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
                        highScores.remove(NUM_SCORES);
                        highScorePlayer.remove(NUM_SCORES);
                    }
                }
            }
        }
    }
    
    private boolean addScore(String playerName, Score score)
    {
        if(highScores.isEmpty())
        {
            highScores.add(score);
            highScorePlayer.add(playerName);
            return true;
        }
        
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
        
        if(highScores.size() < NUM_SCORES)
        {  
            highScores.add(score);
            highScorePlayer.add(playerName);
            return true;
        }
        
        return false;
    }

    public int getNumScores()
    {
        return highScores.size();
    }
    
    public String getName(int index)
                throws ArrayIndexOutOfBoundsException{
        if(index >= highScorePlayer.size())
            throw new ArrayIndexOutOfBoundsException();
        
        return highScorePlayer.get(index);
    }
    
    public Score getScore(int scoreNumber) 
            throws ArrayIndexOutOfBoundsException{
        if(scoreNumber >= highScores.size())
            throw new ArrayIndexOutOfBoundsException();
        
        return highScores.get(scoreNumber);
    }
}
