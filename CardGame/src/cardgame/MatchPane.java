/*
 * file name: MatchPane.java
 * programmer name: Nick McManus
 * date created: 12-05-2018
 * date of last revision: 12/09/2018
 * details of last revision: Worked through algorithm to determine number of cards per set
 * short description: Make a class to handle creating a board, scoring, and interacting
 */

package cardgame;

import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class MatchPane extends BorderPane{
    private final int ROW, COL; //Defined in constructor
    private Date startTime, endTime;
    private GridPane cardPane = new GridPane();
    private BorderPane accessPane = this;
    private Deck deck;
    
    public MatchPane(Deck deck){
        
        this.deck = new Deck();
        startTime = new Date();
        endTime = new Date();
        
        //Make a copy of the deck, because it will be changed for boolean logic
        for(Card current: deck.getDeck())
        {
            this.deck.addCard(new Card(current));
        }
        
        int tempRow = (int)Math.sqrt(deck.getDeck().size()*2);
        
        //Figure out the optimal display size
        while((deck.getDeck().size() * 2) % tempRow != 0)
            tempRow--;
        
        //Assign the gloabl variables to the value
        ROW = tempRow;
        COL = ((deck.getDeck().size() * 2) / ROW);
        
        //Build the map with handlers, use the card score as its ID
        buildMap(true);
    }
    
    /**
     * Method used to develop the display algorithm
     * Can be used to determine if the deck is being build correctly
     * @param list A deck to be arranged
     */
    public static void checkClass(Deck list)
    {
        double sqrt = Math.sqrt(list.getDeck().size());
        int rows = (int)sqrt;
        
        System.out.println("Number of cards: " + list.getDeck().size());
        System.out.println("Square Root: " + sqrt);
        System.out.println("Base Rows: " + rows);
        while(list.getDeck().size() % rows != 0)
            rows--;
        
        System.out.println("Optimal Board: Rows: " + rows +
                " Cols: " + list.getDeck().size()/rows);
        
        //Test building a match board
        int matchDeck = list.getDeck().size()*2, matchRows;
        
        System.out.println("Number of cards: " + matchDeck);
        sqrt = Math.sqrt(matchDeck);
        matchRows = (int)sqrt;
        
        
        System.out.println("Square Root: " + sqrt);
        System.out.println("Base Rows: " + matchRows);
        while(matchDeck % matchRows != 0)
            matchRows--;
        
        System.out.println("Optimal Board: Rows: " + matchRows +
                " Cols: " + matchDeck/matchRows);
        
    }
    
    /** 
     * Construct the map and cards to play the matching game
     * @param cheat Whether or not to display a reference to the console output
     */
    private void buildMap(boolean cheat)
    {
        //Number and Duplicate the cards
        Deck secondDeck = new Deck();
        ArrayList<Card> finalDeck = new ArrayList();
        
        //Create a second deck sharing the same cards as the original deck
        for(int i = 0; i < deck.getDeck().size(); i++)
        {
            deck.getDeck().get(i).setValue(i+1);
            secondDeck.addCard(deck.getDeck().get(i));
        }
        
        //Shuffle both decks
        Collections.shuffle(deck.getDeck());
        Collections.shuffle(secondDeck.getDeck());
        
        //Build the matching deck
        for(int i = 0; i < deck.getDeck().size(); i++)
        {
            finalDeck.add(deck.getDeck().get(i));
            finalDeck.add(deck.getDeck().get(i));
        }
        
        //Shuffle one more time for good measure
        Collections.shuffle(finalDeck);
        
        //Set formatting on the gridpane
        cardPane.setVgap(15); 
        cardPane.setHgap(15); 
        cardPane.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: Green;");
        
        //Initialize variables
        //Hold the card buttons
        Button cards[][] = new Button[ROW][COL];   
        
        //Containers to make things work
        int[][] cardsInt = new int[ROW][COL];
        Card[][] cardArray = new Card[ROW][COL];        

        //Fill the Card Array from the ArrayList
        System.out.println("ROW: " + ROW + " COL: " + COL);
        int cardCount = 0;
        for(int row = 0; row < cardArray.length; row++)
        {
            for(int col = 0; col < cardArray[row].length; col++)
            {
                int index = cardCount++;
                cardArray[row][col] = finalDeck.get(index);
                System.out.println( row*cardArray.length + " " + "" + col + " = " + index);
            }
        }
        
        //Fill the representitive array with the value from the card
        for (int i = 0; i < ROW; i++){
            for (int j = 0; j < COL; j++)
                cardsInt[i][j] = cardArray[i][j].getValue();
        }
        
        //Containers to handle matches
        ArrayList<Integer> rows = new ArrayList(2);
        ArrayList<Integer> columns = new ArrayList(2);
        ArrayList<Integer> matches = new ArrayList();
        
        //Create a button for every card
        for (int i = 0; i < ROW; i++) 
        {
            for (int j = 0; j < COL; j++) 
            {                   
                cards[i][j] = new Button("");
                
                //Set card as the back graphic               
                cards[i][j].setGraphic(new ImageView(cardArray[i][j].getBack().getImage()));
                
                
                //Appeasing the demands of the lambda expression
                final int row = i;
                final int column = j;
                
                //Setup the event handler
                cards[i][j].setOnAction(new EventHandler <ActionEvent>(){                    
                    @Override
                    public void handle(ActionEvent event) {
                        
                        //Set the card as its "face"
                        cards[row][column].setGraphic(new ImageView(cardArray[row][column].getFront().getImage()));
                        System.out.println("row is " + row + " column is " + column);
                        
                        //Okay, this appears to add the coordinates of the button
                        //to an index-linked pair of arrays
                        rows.add(row);
                        columns.add(column);
                        
                        //Make the player unable to interact with the card again
                        cards[row][column].setDisable(true);
                        
                        //I think this is the match check code
                        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e ->{
                        //A match was made
                        if (rows.size() > 1){
                            if (cardsInt[rows.get(0)][columns.get(0)] == cardsInt [rows.get(1)][columns.get(1)]){
                                
                                //Indicate a match was made
                                matches.add(1);
                                
                                //Add code to remove cards outside the bounds
                                for(int i = 2; i < rows.size(); i++)
                                {
                                    cards[rows.get(i)][columns.get(i)].setGraphic(new ImageView(cardArray[rows.get(1)][columns.get(i)].getBack().getImage()));
                                    cards[rows.get(i)][columns.get(i)].setDisable(false);
                                }
                            }
                                
                            else {
                                
                                //Add code to enable out of bounds cards
                                for(int i = 0; i < rows.size(); i++)
                                {
                                    cards[rows.get(i)][columns.get(i)].setGraphic(new ImageView(cardArray[i][i].getBack().getImage()));
                                    cards[rows.get(i)][columns.get(i)].setDisable(false);
                                }
                            }
                            
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException ex) {
                                //Catch the interruption
                            }
                            
                            //Make sure coords are clear after a match is checked for
                            rows.clear();
                            columns.clear();
                            
                            //Check to see if the player has cleared the board
                            if (matches.size() == (ROW*COL/2)){
                                endTimer();
                                Label winLabel = new Label("Win Score: " + matchPoints());
                                winLabel.setFont(Font.font("Verdana", 70));
                                winLabel.setStyle("-fx-background-color: Yellow");
                                //pane.getChildren().add(winLabel);
                                accessPane.setTop(winLabel);
                            }
                        }
                        }));
                        timeline.setCycleCount(Animation.INDEFINITE);
                        timeline.play();
                    }
                  });
                //End of Event Handler
            }
        }
        
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                cardPane.add(cards[i][j],j,i);
                if(cheat)
                    System.out.print(cardArray[i][j].getValue() + " ");
            }
            if(cheat)
                System.out.println("");
        }
        startTimer();
        this.setCenter(cardPane);
        
        /*
        //Add this back in the main
        VBox informationBox = new VBox(10);
        Label playerLabel = new Label("Player: " + playerName);
        Label deckLabel = new Label("Deck: " + deckType);
        informationBox.getChildren().addAll(playerLabel, deckLabel);
        
        HBox bottomBox = new HBox(25);
        bottomBox.getChildren().addAll(returnButton, informationBox);
        bottomBox.setAlignment(Pos.CENTER);
        
        mainMenuPane.setCenter(boardPane);
        mainMenuPane.setBottom(bottomBox);  
        */
    }
    
    /**
     * Determine if a value exists in an array
     * @param arr The array to look in
     * @param value The value to look for
     * @param count The number of elements to look up to
     * @return Whether or not a value exists in the array
     * @deprecated 
     */
    private boolean exists(int arr[], int value, int count){

        for(int i = 0; i < count; i++){
            if (arr[i] == value)
                return true;
        }
        return false;
    }
    
    /**
     * Set the initial value for the timers
     */
    private void startTimer()
    {
        startTime = new Date();
        endTime.setTime(startTime.getTime());
    }
    
    /**
     * Set the final value for the time
     */
    private void endTimer()
    {
        endTime = new Date();
    }
    
    //Methods Dealing with Scorekeeping
    
    /**
     * Get the value of a finished game otherwise points are generated
     * up the time called, and the level will be called incomplete
     * @return 
     */
    public int matchPoints()
    {       
        //Variable declaration
        int score = 0;
        
        // Score = Base - Time + LevelBonus
        // Base - Time cannot have a negative result
        if(this.startTime.getTime() != this.endTime.getTime())
        {
            score += basePoints();
            System.out.println("Base Score: " + score);
            //Limit how many points can be lost to time penalties
            if(timePoints() > score)
                score = 0;
            else
                score -= timePoints();
            System.out.println("Time Penalty: " + timePoints());
            
            score += levelPoints();      
            System.out.println("Level Bonus: " + levelPoints());
        }
        
        return score;
    }
    
    /**
     * Determine the variable number of points to start with which depend on
     * the number of matches 
     * @return 
     */
    private int basePoints()
    {
        return (int)(1000*Math.round((200*Math.pow(12500*((COL*ROW)/2),
                1./3))/1000));
    }
    
    /**
     * Determine the score created based on the time taken to solve the puzzle
     * @return 
     */
    private int timePoints()
    {
        //Possibly have this modified by the number of cards
        //More lax time points during harder levels
        return (int)(endTime.getTime()-startTime.getTime())/10;
    }
    
    /**
     * Determine the points to be awarded for completing a level
     * @param deck
     * @return The number of points awarded for finishing a level
     */
    private int levelPoints()
    {
        return 250*((COL*ROW)/2);
    }
}
