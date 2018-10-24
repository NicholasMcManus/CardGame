/*
 * file name: Card.java
 * programmer name: Nick McManus
 * date created: 10-24-2018
 * date of last revision: 
 * details of last revision:
 * short description: 
 */

package cardgame;

import javax.swing.ImageIcon;

public class Card {
    private ImageIcon cardBack = null, cardFront = null;
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
     * Create a card to be used in a GUI
     * @param front The file path to the front picture
     * @param back  The file path to the back picture 
     */
    public Card(String front, String back){
        cardFront = new ImageIcon(front);
        cardBack = new ImageIcon(back);
        
        //Tell the user if something was wrong attempting to load the files
        if(cardFront.getImageLoadStatus() == 4)
            System.out.println("There was an issue loading the "
                    + "front of the card " + front);
        
        if(cardBack.getImageLoadStatus() == 4)
            System.out.println("There was an issue loading the "
                    + "back of the card " + back);
    }
    
    /**
     * @return The image from the back of the card
     */
    public ImageIcon getBack(){
        return cardBack;
    }

    /**
     * @return The image from the front of the card
     */
    public ImageIcon getFront(){
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