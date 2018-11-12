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
    ArrayList<Score> highScores;
    ArrayList<String> highScorePlayer;
    
    final int NUM_SCORES;
    
    public Scoreboard(ArrayList<Player> players, int numScores)
    {
        NUM_SCORES = numScores;
        highScores = new ArrayList(NUM_SCORES);
    }
    
    public Scoreboard(ArrayList<Player> players)
    {
        this(players, 10);
    }
}
