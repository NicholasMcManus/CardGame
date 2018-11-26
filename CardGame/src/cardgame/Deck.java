/*
 * file name: Deck.java
 * programmer name: Nick McManus
 * date created: 11-05-2018
 * date of last revision: 
 * details of last revision:
 * short description: 
 */

package mydeck;

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
    private ArrayList<Card> deck = new ArrayList();
    private final String defaultbackCard = "card/b1fv.png";
    
    public Deck(){
    };
    
    public Deck(String fileName) throws IOException, ClassNotFoundException
    {
        readDeck(fileName);
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
    
    public void addCard(String cardFront) throws FileNotFoundException
    {
//       for (int i = 0; i < deck.size(); i++){
//           if (deck.get(i).getFront() == new ImageView(cardFront)){
//               System.out.println("card already exists");
//               return;
//           }
//       }
       deck.add(new Card(cardFront, defaultbackCard));
    }
    
    public ArrayList<Card> getDeck(){
        return deck;
    }
    
    public void createDeck(String fileName) throws IOException
    {
        ObjectOutputStream outStream = new ObjectOutputStream (new FileOutputStream(fileName));
        outStream.writeObject(this);
    }
    
    public void readDeck(String fileName) throws IOException, ClassNotFoundException
    {
        ObjectInputStream inStream;
        inStream = new ObjectInputStream(new FileInputStream(fileName));
        
        this.deck = ((Deck)inStream.readObject()).getDeck();
        
    }
}
