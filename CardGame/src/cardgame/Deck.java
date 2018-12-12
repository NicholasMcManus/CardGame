/*
 * file name: Deck.java
 * programmer name: Josue, Helia, Nick
 * date created: 11-05-2018
 * date of last revision: 12-12-2018
 * details of last revision: Add JavaDoc Comments
 * short description: Describe a collection of cards called a deck
 */

package cardgame;

import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

import javafx.scene.image.ImageView;

public class Deck implements java.io.Serializable{
    //Declare Variables
    private ArrayList<Card> deck = new ArrayList();
    
    /**
     * Default Constructor
     */
    public Deck(){
    };
    
    /**
     * Construct a deck when passed a filename
     * @param fileName The name of the file
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public Deck(String fileName) throws IOException, ClassNotFoundException
    {
        readDeck(fileName);
    }
    
    /**
     * Method for adding cards that already exist to a deck
     * @param newCard The card to be added to the deck
     * @return If the operation was successful
     */
    public boolean addCard(Card newCard)
    {
        //Add a new card to the deck as long as it already doesn't exist in the deck
        if(!deck.contains(newCard))
        {
            deck.add(newCard);
            return true;
        }
        return false;
    }
    
    /**
     * Add a card such that it can be used from the console window
     * @param suit The suit of the card
     * @param name The name for the card
     * @param value The value of the card
     */
    public void addCard(String suit, String name, int value)
    {
        //Make sure the card does not already exist when adding it to the deck
        for (int i = 0; i < deck.size(); i++){
           if (deck.get(i).getSuit().equals(suit)
               && deck.get(i).getValue() == value
               && deck.get(i).getName().equals(name)){
               System.out.println("card already exists");
               return;
           } 
       }
        deck.add(new Card(suit, name, value));
    }
    
    /**
     * Add a card when given a path to the front of a card
     * @param cardFront The path to the image
     * @param cardBack The path to the back of the card
     * @throws FileNotFoundException 
     */
    public void addCard(String cardFront, String cardBack) throws FileNotFoundException{
        //Declare local variables
        int value; 
        String suit;
        
        value = Integer.parseInt(cardFront.substring(cardFront.indexOf(File.separatorChar)+1, cardFront.indexOf(".")));
        
        //Determine the suit of the card
        if (value <= 13)
            suit = "spades";
        else if (value <= 26){
            suit = "hearts";
            value -= 13;
        }
        else if (value <= 39){
            suit = "diamonds";
            value -= 26;
        }
        else {
            suit = "clubs";
            value -= 39;
        }
        
       //Add a new card with the values determined above
       deck.add(new Card(cardFront, cardBack, suit, value));
    }
    
    /**
     * Get the list of cards in the form of a deck
     * @return A reference to the list of cards
     */
    public ArrayList<Card> getDeck(){
        return deck;
    }
    
    /**
     * Create a deck from a filename
     * @param fileName the filename to build the deck from
     * @throws IOException 
     */
    public void createDeck(String fileName) throws IOException
    {
        ObjectOutputStream outStream = new ObjectOutputStream (new FileOutputStream(fileName));
        outStream.writeObject(this);
    }
    
    /**
     * Method to read a deck from a file when the filename is passed
     * @param fileName The path to the file
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void readDeck(String fileName) throws IOException, ClassNotFoundException
    {
        ObjectInputStream inStream;
        inStream = new ObjectInputStream(new FileInputStream(fileName));
        
        this.deck = ((Deck)inStream.readObject()).getDeck();
    }
    
    /**
     * Build a deck of cards given a certain filename
     * @params folder The folder which contains at least 53 cards
     */
    public void buildDeck(String folder) throws FileNotFoundException{
        //Overwrite the current deck when called
        deck = new ArrayList();
        String fileName = "NULL", backName = "NULL";
        
        //Try to read in 52 cards with backs
        for (int i = 1; i <= 52; i++){
            
            try{
                //Generate the filenames
                fileName = folder + File.separatorChar + i + ".png";
                backName = folder + File.separatorChar + "back.png";
                this.addCard(fileName, backName);
            }catch(FileNotFoundException ex)
            {
                //Tell the console line how many cards were read in if there was a problem
                System.out.println("Done reading cards at " + i);
                System.out.println("File Name: " + fileName);
                throw ex;
            }
            
        }
    }
}
