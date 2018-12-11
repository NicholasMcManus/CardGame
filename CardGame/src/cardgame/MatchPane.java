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
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MatchPane extends javafx.scene.layout.GridPane{
    private final int ROW, COL; //Defined in constructor
    private Date startTime, endTime;
    
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
        
        int tempRow = (int)Math.sqrt(deck.getDeck().size());
        tempRow *= 2;
        
        //Figure out the optimal display size
        while(deck.getDeck().size() % tempRow != 0)
            tempRow--;
        
        //Assign the gloabl variables to the value
        ROW = tempRow;
        COL = (deck.getDeck().size()*2) - ROW;
        
        //Build the map with handlers, use the card score as its ID
        buildMap(false);
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
        
/** Start of code from old main with tweaks to adapt it to the current Class **/
        
        
        //Need to fix this, probably make a method so this can be handled
        //in the accessor class
        
        /*//
        Button returnButton = new Button("Return Main Menu");
        returnButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
                   mainMenuPane.getChildren().clear();
                   mainMenuPane.setCenter(playerOptions); 
                   mainMenuPane.setTop(labelBox);
            }        
        });
        
        returnButton.setAlignment(Pos.BOTTOM_LEFT);
        //*/
        
        //Set formatting on the gridpane
        this.setVgap(15); 
        this.setHgap(15); 
        this.setStyle("-fx-background-color: Green;");
        this.setAlignment(Pos.CENTER);
        
        //Initialize variables
        //Hold the card buttons
        Button cards[][] = new Button[ROW][COL];   
        
        //Containers to make things work
        int[][] cardsInt = new int[ROW][COL];
        Card[][] cardArray = new Card[ROW][COL];        
        
        //Add enough cards to do a matching game with a certain number of cards
        /**Depreciated**
        do{
            int choice = rand.nextInt(54)+1 ;
            if(!exists(arrayCards, choice, count)){
                //If a card is not represented in the array, add it
                arrayCards[count] = choice;
                count++;
            }
        }while(count != totalCards);
        //*/
        
        //New Implementation
        //Fill the Card Array from the ArrayList
        for(int row = 0; row < cardArray.length; row++)
        {
            for(int col = 0; col < cardArray[row].length; col++)
            {
                cardArray[row][col] = finalDeck.get(row*cardArray.length+col);
            }
        }
        
        /*** Depreciated***
        //Debug code: Print the currently used cards from the array
        System.out.println("Row deck is " + ROW + "and column deck is " + COL);
        System.out.println("This is the used cards in the deck ");
        for(int i = 0; i < totalCards; i++){
            System.out.print(arrayCards[i] + " ");
        }
        System.out.println();
        
        //Fill the representitive array with value 0
        for (int i = 0; i < ROW; i++){
            for (int j = 0; j < COL; j++)
                cardsInt[i][j] = 0;
        }
        //*/
        
        //New Implentation
        //Fill the representitive array with the value from the card
        for (int i = 0; i < ROW; i++){
            for (int j = 0; j < COL; j++)
                cardsInt[i][j] = cardArray[i][j].getValue();
        }
        
        //Put cards into random places
        /*** Depreciated ***
        count = 0;
        while (count != (ROW * COL)/2)
        {
            //Variable initialization
            int choice;
            int iIndex;
            int jIndex;
            
            //Choose from a card from the selected cards
            do{
                choice = rand.nextInt(arrayCards.length);
            }while (arrayCards[choice] == 0);
            
            System.out.println("the choice picked from the used cards is " + arrayCards[choice]);
            
            //Determine where to put the card as long as it is not occupied
            do {
               iIndex = rand.nextInt(ROW);
               jIndex = rand.nextInt(COL);
            }while(cardsInt[iIndex][jIndex] != 0);
            
            System.out.println("iIndex is " + iIndex);
            System.out.println("jIndex is " + jIndex);
            
            //Place the card in the unoccupied section
            cardsInt[iIndex][jIndex] = arrayCards[choice];
            
            //Find the index for the next card's placement
            do {
               iIndex = rand.nextInt(ROW);
               jIndex = rand.nextInt(COL);
               
            }while(cardsInt[iIndex][jIndex] != 0);
            System.out.println("the second iIndex is " + iIndex);
            System.out.println("the second jIndex is " + jIndex);
            
            cardsInt[iIndex][jIndex] = arrayCards[choice];
            
            //Indicate that the card has been used
            arrayCards[choice] = 0;
            
            //Debug: How many cards have been chosen
            count++;
            System.out.println("count is "+ count);
            
            //Debug: Show the current board
            System.out.println("This is what we have in it so far ");
            
            //Cheating code for future reference
            for (int i = 0; i < ROW; i++){
                for (int j = 0; j < COL; j++)
                    System.out.print(cardsInt[i][j] + " ");
                System.out.println();
            }
        }
        ***/
        
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
                //cards[i][j].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("card/b1fv.png"))));                
                cards[i][j].setGraphic(cardArray[i][j].getBack());
                
                
                //Appeasing the demands of the lambda expression
                final int row = i;
                final int column = j;
                
                //Setup the event handler
                cards[i][j].setOnAction(new EventHandler <ActionEvent>(){                    
                    @Override
                    public void handle(ActionEvent event) {
                        
                        //Set the card as its "face"
                        //cards[row][column].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("card/"+cardsInt[row][column] +".png"))));
                        cards[row][column].setGraphic(cardArray[row][column].getFront());
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
                                //Not really needed as cards are already disabled
                                //cards[rows.get(0)][columns.get(0)].setDisable(true);
                                //cards[rows.get(1)][columns.get(1)].setDisable(true);
                                matches.add(1);
                                //Add code to remove cards outside the bounds
                                //*Check to see if this is a problem*/
                            }
                                
                            else {
                                //cards[rows.get(0)][columns.get(0)].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("card/b1fv.png"))));
                                //cards[rows.get(1)][columns.get(1)].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("card/b1fv.png"))));
                                
                                //cards[rows.get(0)][columns.get(0)].setDisable(false);
                                //cards[rows.get(1)][columns.get(1)].setDisable(false);
                                
                                //Add code to enable out of bounds cards
                                for(int i = 0; i < rows.size(); i++)
                                {
                                    cards[rows.get(i)][columns.get(i)].setGraphic(cardArray[rows.get(i)][columns.get(i)].getBack());
                                    cards[rows.get(i)][columns.get(i)].setDisable(false);
                                }
                            }
                            
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException ex) {
                                //Logger.getLogger(CardGame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            //Make sure coords are clear after a match is checked for
                            rows.clear();
                            columns.clear();
                            
                            //Check to see if the player has cleared the board
                            if (matches.size() == (ROW*COL/2)){
                                endTimer();
                                BorderPane pane = new BorderPane(); 
                                pane.setStyle("-fx-background-color: Purple");
                                Label winLabel = new Label("You win!!!");
                                winLabel.setFont(Font.font("Verdana", 70));
                                winLabel.setStyle("-fx-background-color: Yellow");
                                //pane.getChildren().add(winLabel);
                                pane.setCenter(winLabel);
                                
                                Stage winStage = new Stage();
                                winStage.setTitle("win!!!");
                                winStage.setScene( new Scene(pane, 400, 200));
                                winStage.show();
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
                this.add(cards[i][j],j,i);
            }
        }
        startTimer();
        
        /*
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
            
            //Limit how many points can be lost to time penalties
            if(timePoints() > score)
                score = 0;
            else
                score -= timePoints();
            
            score += levelPoints();      
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
        return (int)(endTime.getTime()-startTime.getTime());
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
