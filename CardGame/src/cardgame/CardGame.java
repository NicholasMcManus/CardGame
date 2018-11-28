
/*
 * file name: CardGame.java
 * programmer name: Nick McManus
 * date created: 10-18-2018
 * date of last revision: 
 * details of last revision:
 * short description: 
 */
package cardgame;

import java.util.ArrayList;
import javafx.util.Duration;
import java.util.HashSet;
import java.util.Random;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.*;

/**
 *
 * @author Nick McManus
 */
public class CardGame extends Application {
    BorderPane mainMenuPane;
    Label mainMenuLabel;
    VBox playerOptions, labelBox;
    int rowDeck = 2, colummDeck = 2, index = 0;
    Scene scene;    
    String deckType, playerName;    
    ObservableList<String> playersList = FXCollections.observableArrayList("Player1", "Player2", "Player3");    
    Button cards [][];
    
    @Override    
    public void start(Stage primaryStage) {
        
        mainMenuPane = new BorderPane();        
        mainMenuPane.setStyle("-fx-background-color: Green");
        mainMenuLabel = new Label("Matching Game");
        mainMenuLabel.setFont(new Font("Arial", 69));        
        playerOptions = new VBox(20);        
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
    
    public void startGame()
    {        
        setDiffficulty();        
    }
    
    public void leaderboardScreen()
    {
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
        
        DropShadow leaderboardLabel = new DropShadow();
        leaderboardLabel.setOffsetY(3.0f);
        leaderboardLabel.setColor(Color.color(0.4f, 0.4f, 0.4f));
        Text t = new Text();
        t.setEffect(leaderboardLabel);
        t.setCache(true);
        t.setX(10.0f);
        t.setY(270.0f);
        t.setFill(Color.RED);
        t.setText("High Scores");
        t.setFont(Font.font(null, FontWeight.BOLD, 69));
        
        HBox boardBox = new HBox(10);               
        ScoreBoardGui scoreTable = new ScoreBoardGui();
        scoreTable.setHgap(40);
        scoreTable.setFont(Font.font(20));
        //scoreTable.setCSS("-fx-base: white; -fx-font-size: 20px;");
        scoreTable.setColor(Color.BLUE);
        boardBox.getChildren().add(scoreTable);
        boardBox.setAlignment(Pos.BOTTOM_CENTER);
        
        VBox titleBox = new VBox(10);
        titleBox.getChildren().add(t);
        titleBox.setAlignment(Pos.CENTER);
        
        mainMenuPane.getChildren().clear();
        mainMenuPane.setStyle("-fx-background-color: Orange;");
        mainMenuPane.setTop(titleBox);
        mainMenuPane.setBottom(returnButton);
        mainMenuPane.setCenter(boardBox);
        
    }
    
    public void optionsScreen()
    {
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
        
        DropShadow optionsLabel = new DropShadow();       
        optionsLabel.setOffsetY(3.0f);
        optionsLabel.setColor(Color.color(0.4f, 0.4f, 0.4f));
        Text t = new Text();
        t.setEffect(optionsLabel);
        t.setCache(true);
        t.setX(10.0f);
        t.setY(270.0f);
        t.setFill(Color.CADETBLUE);
        t.setText("Options");
        t.setFont(Font.font(null, FontWeight.BOLD, 69));        
        
        VBox optionsVBox = new VBox(10);
        
        Button deckOptionButton = new Button("  Deck  ");
        deckOptionButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
                getDeckOptions();
            }            
        });
        Button playerOptionButton = new Button("  Player  ");
        playerOptionButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
             getPlayerOptions();
            }        
        });      
        optionsVBox.getChildren().addAll(deckOptionButton,playerOptionButton);                        
        optionsVBox.setAlignment(Pos.CENTER);
        
        VBox titleBox = new VBox(10);
        titleBox.getChildren().add(t);
        titleBox.setAlignment(Pos.CENTER);
        
        mainMenuPane.getChildren().clear();
        mainMenuPane.setStyle("-fx-background-color: Blue;");
        mainMenuPane.setCenter(optionsVBox);
        mainMenuPane.setTop(titleBox);        
        mainMenuPane.setBottom(returnButton);
        
    }
    
    public void getDeckOptions()
    {        
      Button applyDeckType = new Button("Apply");
      ObservableList<String> deckList = FXCollections.observableArrayList(
    	"Original Deck", "Second Deck", "Third Deck");      
    	final ComboBox deckComboBox = new ComboBox(deckList);
        mainMenuPane.setCenter(deckComboBox);      
        mainMenuPane.setRight(applyDeckType);
        applyDeckType.setOnAction(new EventHandler<ActionEvent>(){
          @Override
          public void handle(ActionEvent event) {
            deckType = deckComboBox.getValue().toString();      
          }
      });
        
    }
    
    public void getPlayerOptions()
    {
       HBox playerOptionsBox = new HBox(20);
       TextField textFieldName = new TextField();
       Button applyNameButton = new Button("Apply");       
       ComboBox playerComboBox = new ComboBox(playersList); 
       
       Button addNameButton = new Button("Add Name");
       addNameButton.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent event) {
              playersList.add(String.valueOf(textFieldName.getText()));              
           }
       });       
             
       playerOptionsBox.getChildren().addAll(playerComboBox,textFieldName, addNameButton);
       playerOptionsBox.setAlignment(Pos.CENTER);
       
       mainMenuPane.setCenter(playerOptionsBox);   
       mainMenuPane.setRight(applyNameButton);
       applyNameButton.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent event) {
               playerName = playerComboBox.getValue().toString();
           }
       });
        
    }
    
    public void setDiffficulty()
    {
        HBox difficultyBox = new HBox(10);
        Button easyButton = new Button("Easy");
        easyButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
                setBoard(1);
            }
        });
        Button mediumButton = new Button("Medium");
        mediumButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
                setBoard(2);
            }
        });
         Button hardButton = new Button("Hard");
        hardButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
                setBoard(3);
            }
        });
        difficultyBox.getChildren().addAll(easyButton, mediumButton, hardButton);
        difficultyBox.setAlignment(Pos.CENTER);
        mainMenuPane.setCenter(difficultyBox);
                    
    }
    
    public void setBoard(int difficulty)
    {        
        GridPane boardPane = new GridPane();
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
        boardPane.setVgap(15); 
        boardPane.setHgap(15); 
        boardPane.setStyle("-fx-background-color: Green;");
        boardPane.setAlignment(Pos.CENTER);
        
        switch (difficulty) {
            case 1:
                rowDeck = 2;
                colummDeck = 2;
                break;
            case 2:
                rowDeck = 3;
                colummDeck = 4;
                break;
            case 3:
                rowDeck = 4;
                colummDeck = 8;
                break;
            default:
                break;
        }
        
        cards = new Button[rowDeck][colummDeck];   
        
        int totalCards = (rowDeck * colummDeck)/2, found = 0; 
        int arrayCards[] = new int[totalCards]; 
        int[][] cardsInt = new int[rowDeck][colummDeck];
        Random rand = new Random();
        boolean valid = false;
        index = -1;
        
//        for (int i = 0; i < totalCards; i++) {
//            while(!valid)
//            {    
//                int choice = rand.nextInt(54)+1;
//                for (int j = 0; j < totalCards; j++) {
//                    if(arrayCards[j] == choice)                    
//                        valid = false;                    
//                }            
//                valid = true;
//                if(valid == true)
//                    arrayCards[i] = choice;
//            }            
//        }
        
        int count = 0;
        
        do{
            int choice = rand.nextInt(54)+1 ;
            if(!exists(arrayCards, choice, count)){
                arrayCards[count] = choice;
                count++;
            }
            
        }while(count != totalCards);
        
        System.out.println("Row deck is " + rowDeck + "and culumn deck is " + colummDeck);
        System.out.println("This is the used cards in the deck ");
        for(int i = 0; i < totalCards; i++){
            System.out.print(arrayCards[i] + " ");
        }
        System.out.println();
        
        for (int i = 0; i < rowDeck; i++){
            for (int j = 0; j < colummDeck; j++)
                cardsInt[i][j] = 0;
        }
        
        
        count = 0;
        while (count != (rowDeck * colummDeck)/2){
            int choice;
            int iIndex;
            int jIndex;
            do{
                choice = rand.nextInt(arrayCards.length);
            }while (arrayCards[choice] == 0);
            
            System.out.println("the choice picked from the used cards is" + arrayCards[choice]);
            
            do {
               iIndex = rand.nextInt(rowDeck);
               jIndex = rand.nextInt(colummDeck);
            }while(cardsInt[iIndex][jIndex] != 0);
            
            System.out.println("iIndex is" + iIndex);
            System.out.println("jIndex is" + jIndex);
            
            cardsInt[iIndex][jIndex] = arrayCards[choice];
            
            do {
               iIndex = rand.nextInt(rowDeck);
               jIndex = rand.nextInt(colummDeck);
               
            }while(cardsInt[iIndex][jIndex] != 0);
            System.out.println("the second iIndex is" + iIndex);
            System.out.println("the second jIndex is" + jIndex);
            
            cardsInt[iIndex][jIndex] = arrayCards[choice];
            arrayCards[choice] = 0;
            
            count++;
            System.out.println("count is"+ count);
            
            System.out.println("This is what we have in it so far");
            for (int i = 0; i < rowDeck; i++){
                for (int j = 0; j < colummDeck; j++)
                    System.out.print(cardsInt[i][j] + " ");
                System.out.println();
            }
            
        }

        ArrayList<Integer> rows = new ArrayList(2);
        ArrayList<Integer> columns = new ArrayList(2);
        ArrayList<Integer> matches = new ArrayList((rowDeck*colummDeck)/2);
         //int[] rows = new int[2];
        //int[] columns = new int[2];
        
        
        for (int i = 0; i < rowDeck; i++) {
            for (int j = 0; j < colummDeck; j++) {                
                //System.out.print(i+ "" + j + " ");     
                cards[i][j] = new Button("");
                cards[i][j].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("card/b1fv.png"))));                
                final int row = i;
                final int columm = j;
                int count2 = 0;
                cards[i][j].setOnAction(new EventHandler <ActionEvent>(){                    
                    @Override
                    public void handle(ActionEvent event) {
                        
                        cards[row][columm].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("card/"+cardsInt[row][columm] +".png"))));
                        System.out.println("row is " + row + " column is" + columm);
                        
                        rows.add(row);
                        columns.add(columm);
                        
                        cards[row][columm].setDisable(true);
                        
                        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e ->{
                        if (rows.size() > 1){
                            if (cardsInt[rows.get(0)][columns.get(0)] == cardsInt [rows.get(1)][columns.get(1)]){
                                cards[rows.get(0)][columns.get(0)].setDisable(true);
                                cards[rows.get(1)][columns.get(1)].setDisable(true);
                                matches.add(1);
                            }
                                
                            else {
                                cards[rows.get(0)][columns.get(0)].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("card/b1fv.png"))));
                                cards[rows.get(1)][columns.get(1)].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("card/b1fv.png"))));
                                
                                cards[rows.get(0)][columns.get(0)].setDisable(false);
                                cards[rows.get(1)][columns.get(1)].setDisable(false);
                            }
                            
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException ex) {
                                //Logger.getLogger(CardGame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            rows.clear();
                            columns.clear();
                            
                            if (matches.size() == (rowDeck*colummDeck/2)){
                            
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
            }
        }
        
        for (int i = 0; i < rowDeck; i++) {
            for (int j = 0; j < colummDeck; j++) {
                boardPane.add(cards[i][j],j,i);
            }
        }
        
        VBox informationBox = new VBox(10);
        Label playerLabel = new Label("Player: " + playerName);
        Label deckLabel = new Label("Deck: " + deckType);
        informationBox.getChildren().addAll(playerLabel, deckLabel);
        
        HBox bottomBox = new HBox(25);
        bottomBox.getChildren().addAll(returnButton, informationBox);
        bottomBox.setAlignment(Pos.CENTER);
        
        mainMenuPane.setCenter(boardPane);
        mainMenuPane.setBottom(bottomBox);                              
    }


    public boolean exists(int arr[], int value, int count){

        for(int i = 0; i < count; i++){
            if (arr[i] == value)
                return true;
        }
        return false;
    }
}       
 
