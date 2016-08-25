import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner; 

class Card
{
    private short rank, suit;

    private static String[] suits = { "hearts", "spades", "diamonds", "clubs" };
    private static String[] ranks  = { "Ace", "2", "3", "4", "5", "6", "7", 
                                       "8", "9", "10", "Jack", "Queen", "King" };

    public static String rankAsString( int __rank )
	{
        return ranks[__rank];
    }

    Card(short suit, short rank)
    {
        this.rank=rank;
        this.suit=suit;
    }

    public @Override String toString()
    {
          return ranks[rank] + " of " + suits[suit];
    }

    public short getRank() {
         return rank;
    }

    public short getSuit() {
        return suit;
    }
}

class Deck 
{
    private ArrayList<Card> cards;

    Deck()
    {
        cards = new ArrayList<Card>();
        int index_1, index_2;
        Random generator = new Random();
        Card temp;

        for (short a=0; a<=3; a++)
        {
            for (short b=0; b<=12; b++)
             {
               cards.add( new Card(a,b) );
             }
        }

        int size = cards.size() -1;
    }

    public Card drawFromDeck()
    {  
    	if(cards.isEmpty())
    	{
    		System.out.println("The card deck is empty. Start a new game!");
    		return null;
    	}
        return cards.remove( cards.size()-1 );
    }

    public int getTotalCards()
    {
        return cards.size();
    }
    
    public ArrayList<Card> GetDeck()
    {
        return cards;
    }


	//shuffle
	public void ShuffleDeck()
	{
		int n = cards.size();
		
		for (int i = 0; i < n; i++)
		{
			int r = i + (int) (Math.random() * (n-i));
			Card temp = cards.get(r);
			cards.set(r, cards.get(i));
			cards.set(i,temp);
		}
	}
    
	public void PrintDeck()
	{
		System.out.println("START DECK PRINT...");
		int n = cards.size();
		System.out.println("Size of Deck - " + n);
		for (int i = 0; i < n; i++)
		{
			System.out.print(cards.get(i) + " ,");
			if(i%5 == 0)
			{
				System.out.println("");
			}
		}
		
		System.out.println("END DECK PRINT...");
	}
}

public class CardGame {

    public static void main(String[] args) 
    {
    	Scanner reader = new Scanner(System.in);
    	System.out.println("Do you want to (S)huffle or (D)eal?: ");
    	System.out.println("If you want to end the game enter (Q)uit. ");
    	String resp;
    	Deck deck = new Deck ();
    	deck.PrintDeck();
		do
    	{
    		resp = reader.next();
        	if(resp.startsWith("S") || resp.startsWith("s"))
        	{
        		deck.ShuffleDeck();
                deck.PrintDeck();
        	}
        	
        	if(resp.startsWith("D") || resp.startsWith("d"))
        	{
        		Card card = deck.drawFromDeck();
        		if(card == null)
        		{
        			System.out.println("Deck is empty. Initializing a new one!");
        			deck = new Deck();
        		}
        		else
        		{
        			System.out.println(card);
        		}
        	}
    	}
    	while(resp.startsWith("S") || resp.startsWith("s") || resp.startsWith("D") || resp.startsWith("d"));
		
		System.out.println("Thanks for Playing !");
    }

}