/*
 * file name: DeckBuilder.java
 * programmer name: Nick McManus
 * date created: 11-15-2018
 * date of last revision: 
 * details of last revision:
 * short description: 
 */

package cardgame;

public class DeckBuilder {
    private final int CARDS_REQUIRED;
    private Deck myDeck;
    
    public DeckBuilder(int cardsRequired)
    {
        CARDS_REQUIRED = cardsRequired;
    }
    
    public DeckBuilder()
    {
        this(52);
    }
    
    
}
