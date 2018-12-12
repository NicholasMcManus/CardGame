/*
 * file name: Deck.java
 * programmer name: Nick McManus
 * date created: 11-05-2018
 * date of last revision: 11-27-2018
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
    //private final String defaultbackCard = "card/b1fv.png";
    
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
        if(!deck.contains(newCard))
        {
            deck.add(newCard);
            return true;
        }
        return false;
    }
    
    public void addCard(String suit, String name, int value)
    {
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

        int value; 
        String suit;
        
        value = Integer.parseInt(cardFront.substring(cardFront.indexOf(File.separatorChar)+1, cardFront.indexOf(".")));
        
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
        
       
       deck.add(new Card(cardFront, cardBack, suit, value));
    }
    
    /**
     * Get the list of cards in the form of a deck
     * @return 
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
    
    public void buildDeck(String folder) throws FileNotFoundException{
        deck = new ArrayList();
        String fileName = "NULL", backName = "NULL";
        
        for (int i = 1; i <= 52; i++){
            
            try{
                fileName = folder + File.separatorChar + i + ".png";
                backName = folder + File.separatorChar + "back.png";
                this.addCard(fileName, backName);
            }catch(FileNotFoundException ex)
            {
                System.out.println("Done reading cards at " + i);
                System.out.println("File Name: " + fileName);
                throw ex;
            }
            
        }
    }
}
