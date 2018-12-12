/*
 * file name: ScoreBoardGui.java
 * programmer name: Nick McManus
 * date created: 11-26-2018
 * date of last revision: 11-27-2018
 * details of last revision: Add JavaDoc Comments
 * short description: Create a gridpane to represent a scoreboard object
 */

package cardgame;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ScoreBoardGui extends GridPane{
    //Declare Variables
    Scoreboard scoreboard;
    private int fontSize = 20;
    /**
     * Default constructor which creates a random scoreboard
     * Good for checking formatting before a player list is created
     */
    public ScoreBoardGui()
    {
        ArrayList<Player> playerList = new ArrayList(); //Creates ArrayList
         
         playerList.add(new Player());//Adds nothingness
         playerList.add(new Player("Bill"));//Adds Bill
         playerList.add(new Player("Carrot"));//Adds Carrot
         
        //Loop for a random score to be generated and assign
         for(Player current: playerList)
         {
             for(int i = 0; i < Math.round(Math.random()*4); i++)
             {
                 current.addScore((int)(Math.random()*10000));
             }
         }
         
         scoreboard = new Scoreboard(playerList); //
         
         generateTable();
    }
    
    /**
     * Construct a scoreboard GUI element from a player list
     * @param list The list of players
     */
    public ScoreBoardGui(ArrayList<Player> list)
    {
        this(list, 10);
    }
    
    /**
     * Construct a scoreboard GUI element from a player list up to a 
     * specific length
     * @param list The list of players
     * @param numScores The number of scores to store
     */
    public ScoreBoardGui(ArrayList<Player> list, int numScores)
    {
        super();
        scoreboard = new Scoreboard(list, numScores);//Constructs the Scoreboard with the given parameters
        
        generateTable();//Calls the generateTable for the GUI Components 
    }
    
    /**
     * The method which is called to generate the scoreboard GUI element
     */
    private void generateTable()
    {      
        this.addRow(0, new Text("Rank"), new Text("Name"), new Text("Score"), new Text("Date")); //Adds to the row new texts 
        
        //Creates number, name, score and date texts and gets it from the scoreboard object and then adds a row with the new texts
        for(int i = 0; i < scoreboard.getNumScores(); i++)
        {
            Text  number = new Text((i+1)+""),
                    name = new Text(scoreboard.getName(i)),
                    score = new Text(scoreboard.getScore(i).getScore() + ""),
                    date = new Text(scoreboard.getScore(i).getTime().toString());
            
            this.addRow(i+1, number, name, score, date);
        }
    }
    
    /**
     * Allow a programmer to set the font used for the text via the Font class
     * @param newFont The font to be applied to the text
     */
    public void setFont(Font newFont)
    {
        //Set each of the internal text nodes to the passed Font
        for(int i = 0; i < this.getChildren().size(); i++)
            ((Text)(this.getChildren().get(i))).setFont(newFont);
    }
    
    /**
     * Allow a programmer to set the style of the text via CSS
     * @param formatting The formatting to be applied to the text
     */
    public void setCSS(String formatting)
    {
        //Set each of the internal text nodes to the passed Font
        for(int i = 0; i < this.getChildren().size(); i++)
            this.getChildren().get(i).setStyle(formatting);
    }
    
    /**
     * Allow for the color to be changed by passing a color object
     * @param newColor The color to make the text
     */
    public void setColor(Color newColor)
    {
        //Set each of the internal text nodes to the passed Font
        for(int i = 0; i < this.getChildren().size(); i++)
            ((Text)(this.getChildren().get(i))).setFill(newColor);
    }
}
