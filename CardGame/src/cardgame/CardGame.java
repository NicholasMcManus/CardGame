/*
 * file name: CardGame.java
 * programmer name: Nick McManus
 * date created: 10-18-2018
 * date of last revision: 12/11/2018
 * details of last revision: Serialized the entire stack to make stuff work
 * short description: Run the main workings of the Matching Game
 */
package cardgame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CardGame extends Application {
    BorderPane mainMenuPane;
    Label mainMenuLabel;
    VBox playerOptions, labelBox;
    int rowDeck = 2, columnDeck = 2, index = 0;
    Scene scene;    
    String deckType, playerName;    
    ObservableList<String> playersList = FXCollections.observableArrayList();
    Stack<Player> officialPlayerList = new Stack<>();
    Button cards [][];
    String defaultFolder = "card";
    Player currentPlayer;
    int currentScore;
    
    //setting the deck to default deck which can be changed in options
    Deck myDeck;    
    
    @Override    
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
        
        //Declare the deck to play with
        myDeck = new Deck();
        
        //See if there are players to read
        readData();
        
        try{
            myDeck.buildDeck(defaultFolder);
        }
        catch(FileNotFoundException e){
            System.out.println("File Not Found Exception while building deck");
        }
        
        //Create the look and feel of the menu
        mainMenuPane = new BorderPane();        
        mainMenuPane.setStyle("-fx-background-color: Green");
        mainMenuLabel = new Label("Matching Game");
        mainMenuLabel.setFont(new Font("Arial", 69));        
        playerOptions = new VBox(20);     
        
        //Create the buttons that reside on the main menu
        Button startGameButton = new Button("Start Game");
        startGameButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
              startGame();
            }
        });
        
        Button leaderBoardButton = new Button("Leaderboard");
        leaderBoardButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
                leaderboardScreen(); 
            }
        
        });
        
        Button optionsButton = new Button("  Options  ");
        optionsButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
                optionsScreen();
            }
        });
        
        Button exitGameButton = new Button("Exit Game");
        exitGameButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
                 try {
                    saveData();
                } catch (java.io.IOException ex) {
                     System.out.println("Your data could not be written to the file. Data loss possible.");
                }
                Platform.exit();
                System.exit(0);
            }
        });
        
        mainMenuLabel.setAlignment(Pos.CENTER);
        labelBox = new VBox(10);
        labelBox.getChildren().add(mainMenuLabel);
        playerOptions.getChildren().addAll(startGameButton,leaderBoardButton, optionsButton, exitGameButton);        
        playerOptions.setAlignment(Pos.CENTER);
        labelBox.setAlignment(Pos.CENTER);
        mainMenuPane.setCenter(playerOptions);         
        mainMenuPane.setTop(labelBox);
        
        
        scene = new Scene(mainMenuPane, 850, 760);
        
        primaryStage.setTitle("Card Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Start the matching game
     */
    public void startGame()
    {        
        setDiffficulty();        
    }
    
    /**
     * Create and display the leader board
     */
    public void leaderboardScreen()
    {        
        //Create an arraylist called playerlist
        ArrayList<Player> playerList = new ArrayList(officialPlayerList);
        
        //Create the return button
        Button returnButton = new Button("Return");        
        returnButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
                   mainMenuPane.getChildren().clear();
                   mainMenuPane.setStyle("-fx-background-color: Green");
                   mainMenuPane.setCenter(playerOptions); 
                   mainMenuPane.setTop(labelBox);
            }        
        });
        returnButton.setAlignment(Pos.BOTTOM_LEFT);                         
        
        //Make the title look fabulous
        DropShadow leaderboardLabel = new DropShadow();
        leaderboardLabel.setOffsetY(3.0f);
        leaderboardLabel.setColor(Color.color(0.4f, 0.4f, 0.4f));
        
        //Actually make words appear
        Text t = new Text();
        t.setEffect(leaderboardLabel);
        t.setCache(true);
        t.setX(10.0f);
        t.setY(270.0f);
        t.setFill(Color.RED);
        t.setText("High Scores");
        t.setFont(Font.font(null, FontWeight.BOLD, 69));
        
        //Make the scoreboard using a class designed to do such things
        HBox boardBox = new HBox(10);               
        ScoreBoardGui scoreTable = new ScoreBoardGui(playerList);
        boardBox.getChildren().add(scoreTable);
        boardBox.setAlignment(Pos.BOTTOM_CENTER);
        
        //Add the text for the title
        VBox titleBox = new VBox(10);
        titleBox.getChildren().add(t);
        titleBox.setAlignment(Pos.CENTER);
        
        //Make the internal scoreboard look nice
        scoreTable.setHgap(40);
        scoreTable.setFont(Font.font(20));
        scoreTable.setColor(Color.BLUE);
        
        //Finalize the scoreboard
        mainMenuPane.getChildren().clear();
        mainMenuPane.setStyle("-fx-background-color: Orange;");
        mainMenuPane.setTop(titleBox);
        mainMenuPane.setBottom(returnButton);
        mainMenuPane.setCenter(boardBox);
        
    }
    
    /**
     * Create a method to display the option screen
     */
    public void optionsScreen()
    {
        //Add the return button to the option screen
        Button returnButton = new Button("Return");                
        returnButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
                   mainMenuPane.getChildren().clear();
                   mainMenuPane.setStyle("-fx-background-color: Green");
                   mainMenuPane.setCenter(playerOptions); 
                   mainMenuPane.setTop(labelBox);                   
            }        
        });
        returnButton.setAlignment(Pos.BOTTOM_LEFT);      
        
        //Make the title look pretty
        DropShadow optionsLabel = new DropShadow();       
        optionsLabel.setOffsetY(3.0f);
        optionsLabel.setColor(Color.color(0.4f, 0.4f, 0.4f));
        
        //Create the title
        Text t = new Text();
        t.setEffect(optionsLabel);
        t.setCache(true);
        t.setX(10.0f);
        t.setY(270.0f);
        t.setFill(Color.CADETBLUE);
        t.setText("Options");
        t.setFont(Font.font(null, FontWeight.BOLD, 69));        
        
        VBox optionsVBox = new VBox(10);
        
        //Make a deck modification button
        Button deckOptionButton = new Button("  Deck  ");
        deckOptionButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
                getDeckOptions();
            }            
        });
        
        //Make a player option button
        Button playerOptionButton = new Button("  Player  ");
        playerOptionButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
                    getPlayerOptions();
            }        
        });      
        
        //Add stuff to the optionPane
        optionsVBox.getChildren().addAll(deckOptionButton,playerOptionButton);                        
        optionsVBox.setAlignment(Pos.CENTER);
        
        //Make and add a title
        VBox titleBox = new VBox(10);
        titleBox.getChildren().add(t);
        titleBox.setAlignment(Pos.CENTER);
        
        mainMenuPane.getChildren().clear();
        mainMenuPane.setStyle("-fx-background-color: Blue;");
        mainMenuPane.setCenter(optionsVBox);
        mainMenuPane.setTop(titleBox);        
        mainMenuPane.setBottom(returnButton);
        
    }
    
    /**
     * Make the stuff that goes on the deck pane
     */
    public void getDeckOptions() {
        //Make the deck option box
        Button applyDeckType = new Button("Apply");

        ObservableList<String> deckList = FXCollections.observableArrayList(
                "Original Deck", "Second Deck", "Third Deck");

        final ComboBox deckComboBox = new ComboBox(deckList);
        mainMenuPane.setCenter(deckComboBox);
        mainMenuPane.setRight(applyDeckType);
        
        //Setup the apply deck button
        applyDeckType.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deckType = deckComboBox.getValue().toString();
            }
        });
    }
    
    /**
     * Make the player option button from the main menu work
     */
    public void getPlayerOptions() {

        //Start with some boxes and fields
        HBox playerOptionsBox = new HBox(20);
        TextField textFieldName = new TextField();
        Button applyNameButton = new Button("Apply");

        //Make sure nothing is lurking in the old playerList
        if(playersList != null)
            playersList.clear();

        for (Player current : officialPlayerList) {
            //Fill the playerlist with new values
            playersList.add(current.getName());
        }

        //Make a selection box
        ComboBox playerComboBox = new ComboBox(playersList);

        Button addNameButton = new Button("Add Name");
        addNameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!playersList.contains(String.valueOf(textFieldName.getText())))
                {
                    playersList.add(String.valueOf(textFieldName.getText()));
                    officialPlayerList.push(new Player(String.valueOf(textFieldName.getText())));
                }
                else
                {
                    textFieldName.setText("Invalid");
                }
            }
        });

        //Add stuff that looks pretty
        playerOptionsBox.getChildren().addAll(playerComboBox, textFieldName, addNameButton);
        playerOptionsBox.setAlignment(Pos.CENTER);
        mainMenuPane.setCenter(playerOptionsBox);
        mainMenuPane.setRight(applyNameButton);
        
        //Make the apply button actually do stuff
        applyNameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                playerName = playerComboBox.getValue().toString();
                for(Player current: officialPlayerList)
                {
                    if(current.getName().
                            equals(playerComboBox.getValue().toString()))
                        currentPlayer = current;
                }
            }
        });

    }
    
    /**
     * See if the player wants to make their lives difficult
     * Its the difficulty select screen and I don't want to bore you
     */
    public void setDiffficulty()
    {
        //Make an easy box, which makes the game easy
        HBox difficultyBox = new HBox(10);
        Button easyButton = new Button("Easy");
        easyButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
                setBoard(1);
            }
        });
        
        //Make a normal box, for the normal people
        Button mediumButton = new Button("Medium");
        mediumButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
                setBoard(2);
            }
        });
        
        //Make a hard box, because some people are Papa Bear
         Button hardButton = new Button("Hard");
        hardButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
                setBoard(3);
            }
        });
        
        //Add all of the boxes to the same screen
        difficultyBox.getChildren().addAll(easyButton, mediumButton, hardButton);
        difficultyBox.setAlignment(Pos.CENTER);
        mainMenuPane.setCenter(difficultyBox);
    }
    
    /**
     * Make a board that actually does stuff
     * @param difficulty The difficulty selected
     */
    public void setBoard(int difficulty)
    {        
        int numCards = 0;
        
        currentScore = 0;
        
        switch(difficulty)
        {
            case 1:
                numCards = 2;
                break;
            case 2:
                numCards = 6;
                break;
            case 3:
                numCards = 16;
        }
        
        nextBoard(numCards);
    }   

    /**
     * Make a board that actually does stuff
     * @param numCards The number of cards in the game
     */
    public void nextBoard(int numCards)
    {        
        //Add in a lot of things that I don't have the drive to do right now
        //Most of this is getting overhauled
        //Except for the teddy bear, cause why not
        //Commenting out instead of deleting in case I mess up somehow
        mainMenuPane.getChildren().clear();
        
        MatchPane gameBoard;
        Deck subDeck = new Deck();
        
        //Add the return button to the game screen
        Button returnButton = new Button("Return"); 
        
        returnButton.setAlignment(Pos.BOTTOM_LEFT); 
        
        //setup the base game
        Collections.shuffle(myDeck.getDeck());
        
        for(int i = 0; i < numCards; i++)
            subDeck.addCard(myDeck.getDeck().get(i));
        
        gameBoard = new MatchPane(subDeck);
        mainMenuPane.setCenter(gameBoard);
                
        VBox informationBox = new VBox(10);
        Label playerLabel = new Label("Player: " + currentPlayer.getName());
        Label deckLabel = new Label("Deck: " + deckType);
        informationBox.getChildren().addAll(playerLabel, deckLabel);
        
        HBox bottomBox = new HBox(25);
        bottomBox.getChildren().addAll(returnButton, informationBox);
        bottomBox.setAlignment(Pos.CENTER);
        mainMenuPane.setBottom(bottomBox);
        
        returnButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
                   mainMenuPane.getChildren().clear();
                   mainMenuPane.setStyle("-fx-background-color: Green");
                   mainMenuPane.setCenter(playerOptions); 
                   mainMenuPane.setTop(labelBox);
                   if(gameBoard.timePoints() != 0)
                   {
                       currentScore += gameBoard.matchPoints();
                   }
                   currentPlayer.addScore(currentScore);
            }        
        });
        
        gameBoard.winButton.setOnAction(e -> {
            //Press and you win
            currentScore += gameBoard.matchPoints();
            nextBoard(numCards+2);
        });
    }
    
    /**
     * Create a method to resolve existential crises
     * @param arr The array
     * @param value The value to search for
     * @param count How far to look in the array
     * @return Whether or not the value already exists in the array
     */
    public boolean exists(int arr[], int value, int count){

        for(int i = 0; i < count; i++){
            if (arr[i] == value)
                return true;
        }
        return false;
    }
    
    
    /**
     * Saving, Do Not Turn off the Power or Remove the Memory Card from Slot A
     * @throws FileNotFoundException
     * @throws java.io.IOException 
     */
    public void saveData() throws FileNotFoundException, java.io.IOException    
    {     
        //Make some variables
        FileOutputStream fStream = new FileOutputStream("PlayerReport.dat"); 
        ObjectOutputStream outputFile = new ObjectOutputStream(fStream);         
        
        //Record how many names that are being saved for accounting purposes
        System.out.println("Save Num of Names: " + officialPlayerList.size());

        //Write it down
        outputFile.writeObject(officialPlayerList);
        
        //Close the notebook
         outputFile.close();
    }
    
    
    
    /**
     * Reading, its kind of like saving, but in the opposite direction
     * @throws java.io.IOException
     * @throws ClassNotFoundException 
     */
    public void readData() throws java.io.IOException, ClassNotFoundException {
        //Make sure something can be done
        try {            
            //Try to make some variables
            FileInputStream fStream = new FileInputStream("PlayerReport.dat");
            ObjectInputStream inputStream = new ObjectInputStream(fStream);

            //Try to get stuff from the variables
            try {
                //While there is stuff for me to read
                while (true) {
                    officialPlayerList = (Stack<Player>) inputStream.readObject();
                }
                
            } catch (java.io.EOFException ex) {
                //When I run out of stuff to read
                System.out.println("Read Num of names: " + officialPlayerList.size());
                System.out.println("End of file");
                
                //Start with player 1
                if(officialPlayerList.isEmpty())
                    currentPlayer = new Player("Player 1");
                else
                    currentPlayer = officialPlayerList.get(0);
                inputStream.close();
            }
        } catch (FileNotFoundException ex) {
            //If someone misplaced my reading material
            System.out.print("File Not Found, New Data Will be Saved Later");
        }
    }       
}