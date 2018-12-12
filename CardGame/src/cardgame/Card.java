/*
 * file name: Card.java
 * programmer name: Nick, Josue, Helia
 * date created: 10-24-2018
 * date of last revision: 12-12-2018
 * details of last revision: Check for JavaDoc Comments
 * short description: Describe a card so it can be used in a deck
 */

package cardgame;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.io.*;

public class Card implements java.io.Serializable{
    //Class Variables
    private ImageView cardBack = null;
    private ImageView cardFront = null;
    private int cardValue = 0;
    private String cardSuit = "The Crown", cardName = "Joker";
    
    /**
     * Default constructor
     */
    public Card(){
    }
    
    /**
     * Create a card, such as one used in a text based card game
     * @param suit The suit of the card
     * @param name The name of the card such as Queen, Ace, 7
     * @param value The value of the card to be used in the scoring system
     */
    public Card(String suit, String name, int value){
        cardSuit = suit;
        cardName = name;
        cardValue = value;
    }
    
    /**
     * Create a new card from another card
     * @param toCopy The card to make a copy of
     */
     public Card(Card toCopy)
    {
        this.cardName = toCopy.getName();
        this.cardSuit = toCopy.getSuit();
        this.cardValue = toCopy.getValue();
        this.cardFront = new ImageView(toCopy.getFront().getImage());
        this.cardBack = new ImageView(toCopy.getBack().getImage());
    }
     
    /**
     * Create a card to be used in a GUI
     * @param front The file path to the front picture
     * @param back  The file path to the back picture 
     */
    public Card(String front, String back) throws FileNotFoundException {
        cardFront = new ImageView(new Image(new FileInputStream (front)));
        cardBack = new ImageView(new Image(new FileInputStream (back)));
        
        //Tell the user if something was wrong attempting to load the files
        if(cardFront.getImage().isError())
            System.out.println("There was an issue loading the "
                    + "front of the card " + front);
        
        if(cardBack.getImage().isError())
            System.out.println("There was an issue loading the "
                    + "back of the card " + back);
    }
    
    /**
     * Create a basic card
     * @param front the file representing the front of the card 
     * @param back the file representing the back of the card
     * @param suit the suit of the card
     * @param value the value to be stored in the card
     */
    public Card(String front, String back, String suit, int value) throws FileNotFoundException{
        this(front, back);
        cardSuit = suit;
        cardValue = value;
    }
    
    /**
     * @return The image from the back of the card
     */
    public ImageView getBack(){
        return cardBack;
    }

    /**
     * @return The image from the front of the card
     */
    public ImageView getFront(){
        return cardFront;
    }
    
    /**
     * @param value The value to become the new score value
     */
    public void setValue(int value){
        cardValue = value;
    }
    
    /**
     * @return The scoring value to use in a game
     */
    public int getValue(){
        return cardValue;
    }
    
    /**
     * @param name The new name for the card
     */
    public void setName(String name){
        cardName = name;
    }
    
    /**
     * @return The name for the card
     */
    public String getName(){
        return cardName;
    }
    
    /**
     * @param suit The new suit for the card
     */
    public void setSuit(String suit){
        cardSuit = suit;
    }
    
    /**
     * @return The suit of the card in the form of a string
     */
    public String getSuit(){
        return cardSuit;
    }
    
    /**
     * @return The value of the object in the form of a string
     */
    @Override
    public String toString(){
        return cardName + " of " + cardSuit;
    }
}
