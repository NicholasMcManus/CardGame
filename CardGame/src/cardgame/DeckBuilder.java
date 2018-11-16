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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DeckBuilder {
    private final int CARDS_REQUIRED;
    private Deck myDeck;
    private ImageView deckBack = null;
    
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
    
    public void buildDeck()
    {
        ImageView cardFront;
        while(deckBack == null)
        {
            getDeckBack();
        }
        
        for(int i = 0; i < CARDS_REQUIRED; i++)
        {
            do
            {
                cardFront = getCardFront();
            }while(cardFront == null);
            
            //Create a card with front and back images
            
            //Add the card to the deck
            cardsInDeck++;
        }
    }
    
    private boolean getDeckBack()
    {
        deckBack = new ImageView(getFile().getPath());
        return deckBack == null;
    }
    
    private ImageView getCardFront()
    {
        ImageView cardFront = new ImageView(getFile().getPath());
        return cardFront;
    }
    
    //Still doesn't work right, More testing is required
    //*/
    private File getFile()
    {
        Stage mainStage = new Stage();
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Image Files", "*.png",
                    "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(mainStage);
        
        return selectedFile;
    }
    //*/
}
