import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class support
{
    public void drawCards(ArrayList<Card> deck,ArrayList<Card> hand,ArrayList<Card> discarded)
    {
        hand.clear();
        if(deck.size() < 5)
        {
            hand.addAll(deck);
            deck.clear();
            deck.addAll(discarded);
            discarded.clear();
        }
        hand.addAll(selectCards(deck,(5-hand.size())));
    }

    public ArrayList<Card> selectCards(ArrayList<Card> cards, int amount)
    {
        ArrayList<Integer> selectedIndices = new ArrayList<Integer>();
        
        Random rand=new Random();
        rand.setSeed(10012023);

        for(int i=0;i<amount; i++)
        {
            int randomIndex;
            while(true)
            {
                randomIndex = rand.nextInt(cards.size());
                if(!selectedIndices.contains(randomIndex)) break;                
            }
            selectedIndices.add(randomIndex);
        }

        ArrayList<Card> selectedCards = new ArrayList<Card>();

        for(Integer c : selectedIndices)
        {
            selectedCards.add(cards.get(c));

        }

        Collections.sort(selectedIndices,Collections.reverseOrder());

        for(Integer c : selectedIndices)
        {
            cards.remove((int)c);
        }
        return selectedCards;
    }

    public int randomLouseAmount()
    {
        return 5+(int)(Math.random() * 3);
    }
    
}
