
/*
 * file name: CardGame.java
 * programmer name: Nick McManus
 * date created: 10-18-2018
 * date of last revision: 
 * details of last revision:
 * short description: 
 */
package cardgame;

import java.util.HashSet;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Nick McManus
 */
public class CardGame extends Application {
    BorderPane mainMenuPane;
    Label mainMenuLabel;
    VBox playerOptions, labelBox;
    int rowDeck = 2, colummDeck = 2;
    Scene scene;    
    String deckType, playerName;    
    ObservableList<String> playersList = FXCollections.observableArrayList("Player1", "Player2", "Player3");
    
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
        
        
        scene = new Scene(mainMenuPane, 700, 700);
        
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
        
        VBox boardBox = new VBox(10);       
        boardBox.setAlignment(Pos.CENTER);
        ScoreBoardGui scoreTable = new ScoreBoardGui();
        boardBox.getChildren().add(scoreTable);
        
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
        
        Button cards [][] = new Button[rowDeck][colummDeck];
        
        
        
        for (int i = 0; i < rowDeck; i++) {
            for (int j = 0; j < colummDeck; j++) {
                cards[i][j] = new Button("");                
                cards[i][j].setStyle("-fx-background-color: White;");                                
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
    
    
}
