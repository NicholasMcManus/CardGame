/*
 * file name: Deck.java
 * programmer name: Nick McManus
 * date created: 11-05-2018
 * date of last revision: 
 * details of last revision:
 * short description: 
 */

package cardgame;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class Deck {
    ArrayList<Card> deck;
    
    
    Deck(){};
    
    Deck(String fileName) throws IOException
    {
        readDeck(fileName);
    }
    
    public void addCard(String suit, String name, int value)
    {
        deck.add(new Card(suit, name, value));
    }
    
    public void addCard(String cardFront)
    {
        
    }
    
    public void createDeck(String fileName)
    {
        
    }
    
    public void readDeck(String fileName) throws IOException
    {
        ObjectInputStream inStream;
    }
}
