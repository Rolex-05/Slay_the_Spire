import java.util.ArrayList;

public class main {
    public static void main(String args[])
    {
        //Card cards = new Card();

        // System.out.println(cards.getDamageAmount());
        // System.out.println(cards.getBlock());
        // System.out.println(cards.getEnergyCost());
        // System.out.println(cards.getStatusModifier());
        // System.out.println(cards.getName());
        // System.out.println(cards.getDescription());
        // System.out.println(cards.requiresTarget());
        // System.out.println(cards.__str__());
        // System.out.println(cards.__repr__());

        //Entity entity = new Entity(20);

        // System.out.println(entity.getName());
        // System.out.println(entity.getHp());
        // System.out.println(entity.getMaxHp());
        // System.out.println(entity.getBlock());
        // System.out.println(entity.getStrength());
        // System.out.println(entity.getWeak());
        // System.out.println(entity.getVulnerable());
        // System.out.println(entity.__str__());
        // entity.reduceHp(2);
        // System.out.println(entity.getHp());
        // entity.addBlock(5);
        // System.out.println(entity.getBlock());
        // entity.reduceHp(10);
        // System.out.println(entity.getHp());
        // System.out.println(entity.getBlock());
        // System.out.println(entity.isDefeated());
        // entity.addStrength(2);
        // entity.addWeak(3);
        // entity.addVulnerable(4);
        // System.out.println(entity.getStrength());
        // System.out.println(entity.getWeak());
        // System.out.println(entity.getVulnerable());
        // entity.addBlock(5);
        // System.out.println(entity.getBlock());
        // entity.newTurn();
        // System.out.println(entity.getStrength());
        // System.out.println(entity.getWeak());
        // System.out.println(entity.getVulnerable());
        // System.out.println(entity.getBlock());
        // System.out.println(entity.getHp());
        // entity.reduceHp(15);
        // System.out.println(entity.getHp());
        // System.out.println(entity.isDefeated());
        // System.out.println(entity.__str__());

        Player p1= new Player(50,new ArrayList<Card>(){
            {
                add(new Strike());
                add(new Strike());
                add(new Strike());
                add(new Defend());
                add(new Defend());
                add(new Defend());
                add(new Bash());
            }
        });
        
        // System.out.println(p1.getName());
        // System.out.println(p1.getEnergy());
        // System.out.println("\nHand - " + p1.getHand());
        // System.out.println("Discarded - "+p1.getDiscarded());
        // System.out.println("Deck"+p1.getDeck());

        // p1.newTurn();
        // System.out.println("\nHand - "+p1.getHand());
        // System.out.println("Discarded - "+p1.getDiscarded());
        // System.out.println("Deck"+p1.getDeck());
        // p1.endTurn();
        // System.out.println("\nHand - " + p1.getHand());
        // System.out.println("Discarded - "+p1.getDiscarded());
        // System.out.println("Deck"+p1.getDeck());

        // p1.playCard("Neutralize");
        // System.out.println("\nHand - "+p1.getHand());
        // System.out.println("Discarded - "+p1.getDiscarded());
        // System.out.println("Deck"+p1.getDeck());

        Monster ab9 = new Cultist(20);
        System.out.println(ab9.action());
        System.out.println(ab9.action());
        System.out.println(ab9.action());
        System.out.println(ab9.action());
        System.out.println(ab9.action());
        
        Monster viks = new Cultist(30);
        // System.out.println(viks.action());
        System.out.println(viks.action());
        System.out.println(viks.action());

    }
}
