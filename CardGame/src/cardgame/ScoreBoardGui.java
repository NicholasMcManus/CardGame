/*
 * file name: ScoreBoardGui.java
 * programmer name: Nick McManus
 * date created: 11-26-2018
 * date of last revision: 11-26-2018
 * details of last revision: do things
 * short description: Create a gridpane to store scores
 */

package cardgame;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import java.util.ArrayList;

public class ScoreBoardGui extends GridPane{
    Scoreboard scoreboard;
    
    public ScoreBoardGui()
    {
        ArrayList<Player> playerList = new ArrayList();
         
         playerList.add(new Player());
         playerList.add(new Player("Bill"));
         playerList.add(new Player("Carrot"));
         
         for(Player current: playerList)
         {
             for(int i = 0; i < Math.round(Math.random()*4); i++)
             {
                 current.addScore((int)(Math.random()*10000));
             }
         }
         
         scoreboard = new Scoreboard(playerList);
         
         generateTable();
    }
    
    public ScoreBoardGui(ArrayList<Player> list)
    {
        this(list, 10);
    }
    
    public ScoreBoardGui(ArrayList<Player> list, int numScores)
    {
        super();
        scoreboard = new Scoreboard(list, numScores);
        
        generateTable();
    }
    
    
    private void generateTable()
    {      
        this.addRow(0, new Text("Rank"), new Text("Name"), new Text("Score"), new Text("Date"));
        
        for(int i = 0; i < scoreboard.getNumScores(); i++)
        {
            Text  number = new Text((i+1)+""),
                    name = new Text(scoreboard.getName(i)),
                    score = new Text(scoreboard.getScore(i).getScore() + ""),
                    date = new Text(scoreboard.getScore(i).getTime().toString());
            
            this.addRow(i+1, number, name, score, date);
        }
    }
}
