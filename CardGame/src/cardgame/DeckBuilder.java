/*
 * file name: DeckBuilder.java
 * programmer name: Nick McManus
 * date created: 11-15-2018
 * date of last revision: 11-15-2018
 * details of last revision: Add methods for getting the front and back of cards
 * short description: Be able to construct a deck with a certain number of
                        cards
 */

package cardgame;

import java.io.File;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeckBuilder {
    private final int CARDS_REQUIRED;
    private Deck myDeck;
    private ImageView deckBack = null;
    private final FileChooser fileChooser = new FileChooser();
    
    //Keep track of cards in Deck until a
    //method is added to Deck
    int cardsInDeck = 0;
    
    public DeckBuilder(int cardsRequired)
    {
        CARDS_REQUIRED = cardsRequired;
        myDeck = new Deck();
    }
    
    public DeckBuilder()
    {
        this(52);
    }
    
    public void buildDeck(Stage stage)
    {
        ImageView cardFront;
        while(deckBack == null)
        {
            getDeckBack(stage);
        }
        
        for(int i = 0; i < CARDS_REQUIRED; i++)
        {
            do
            {
                cardFront = getCardFront(stage);
            }while(cardFront == null);
            
            //Create a card with front and back images
            
            //Add the card to the deck
            cardsInDeck++;
        }
    }
    
    private boolean getDeckBack(Stage stage)
    {
        fileChooser.setTitle("Back of Card");
        File backOfCard = fileChooser.showOpenDialog(stage);
        Image cardBack;
        
        try {
            cardBack = new Image(new FileInputStream(backOfCard));
        } catch (FileNotFoundException ex) {
            System.out.println("The back of the file could not be read.");
            return false;
        }
        
        deckBack = new ImageView(cardBack);
        return deckBack == null;
    }
    
    private ImageView getCardFront(Stage stage)
    {
        ImageView cardFront = new ImageView(getFile(stage).getPath());
        return cardFront;
    }
    
    //Still doesn't work right, More testing is required
    //*/
    private File getFile(Stage mainStage)
    {
        //Stage mainStage = new Stage();
        
        //FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Picture");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Image Files", "*.png",
                    "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(mainStage);
        
        return selectedFile;
    }
    //*/
}
