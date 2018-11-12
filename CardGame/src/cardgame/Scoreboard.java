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
    
    final int NUM_SCORES;
    
    public Scoreboard(ArrayList<Player> players, int numScores)
    {
        NUM_SCORES = numScores;
        highScores = new ArrayList(NUM_SCORES);
        highScorePlayer = new ArrayList(NUM_SCORES);
    }
    
    public Scoreboard(ArrayList<Player> players)
    {
        this(players, 10);
    }
    
    private boolean addScore(String playerName, Score score)
    {
        if(highScores.isEmpty())
        {
            highScores.add(score);
            highScorePlayer.add(playerName);
            return true;
        }
        
        if(highScores.size() < NUM_SCORES)
        {
            int i;
            for(i = 0; i < highScores.size(); i++)
                if(highScores.get(i).compareTo(score) > 0)
                {
                    
                }
        }
        
        return false;
    }
}
