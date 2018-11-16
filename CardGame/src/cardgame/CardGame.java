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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    VBox playerOptions;
    int rowDeck = 2, colummDeck = 2;
    Scene scene;
    @Override    
    public void start(Stage primaryStage) {
        
        mainMenuPane = new BorderPane();        
        mainMenuPane.setStyle("-fx-background-color: Green");
        mainMenuLabel = new Label("       Matching Game");
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
                
            }
        
        });
        playerOptions.getChildren().addAll(startGameButton,leaderBoardButton, optionsButton, exitGameButton);
        playerOptions.setAlignment(Pos.CENTER);
        mainMenuPane.setCenter(playerOptions); 
        mainMenuLabel.setAlignment(Pos.CENTER);
        mainMenuPane.setTop(mainMenuLabel);
        
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
        GridPane boardPane = new GridPane();
        Button returnButton = new Button("Return Main Menu");
        returnButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
                   mainMenuPane.getChildren().clear();
                   mainMenuPane.setCenter(playerOptions); 
                   mainMenuPane.setTop(mainMenuLabel);
            }        
        });
        returnButton.setAlignment(Pos.BOTTOM_LEFT);        
        boardPane.setVgap(15); 
        boardPane.setHgap(15); 
        boardPane.setStyle("-fx-background-color: Green;");
        
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
        mainMenuPane.setCenter(boardPane);
        mainMenuPane.setBottom(returnButton);        
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
                   mainMenuPane.setTop(mainMenuLabel);
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
        t.setText("       Leader Board");
        t.setFont(Font.font(null, FontWeight.BOLD, 69));
        
        VBox leaderBoardBox = new VBox(10);
        Label info = new Label("James                  100000                     Level 1                         25s"); 
        HBox hBox [] = new HBox[9];
        
        for (int i = 0; i < 9; i++) {
            hBox[i] = new HBox(10);
            hBox[i].getChildren().add(info);
        }
                
        for (int i = 0; i < 9; i++) {
            leaderBoardBox.getChildren().addAll(hBox[i]);
        }
        mainMenuPane.getChildren().clear();
        mainMenuPane.setStyle("-fx-background-color: Orange;");
        mainMenuPane.setTop(t);
        mainMenuPane.setBottom(returnButton);
        mainMenuPane.setCenter(leaderBoardBox);
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
                   mainMenuPane.setTop(mainMenuLabel);
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
        t.setText("           Options");
        t.setFont(Font.font(null, FontWeight.BOLD, 69));        
        
        VBox optionsVBox = new VBox(10);
        
        Button deckOptionButton = new Button("  Deck  ");
        deckOptionButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
                
            }            
        });
        Button playerOptionButton = new Button("  Player  ");
        playerOptionButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
             
            }        
        });
        Button difficultyOptionButton = new Button("Difficulty");
        difficultyOptionButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
              
            }        
        });
        
        optionsVBox.getChildren().addAll(deckOptionButton,playerOptionButton,difficultyOptionButton);                        
        optionsVBox.setAlignment(Pos.CENTER);
        
        mainMenuPane.getChildren().clear();
        mainMenuPane.setStyle("-fx-background-color: Blue;");
        mainMenuPane.setCenter(optionsVBox);
        mainMenuPane.setTop(t);        
        mainMenuPane.setBottom(returnButton);
        
    }
    
    
    
    
    
}
