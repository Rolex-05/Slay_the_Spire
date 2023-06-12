import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.javatuples.Pair;

class Card
{

    public Card(){}

    public int getDamageAmount()
    {
        return 0;
    }

    public int getBlock()
    {
        return 0;
    }

    public int getEnergyCost()
    {
        return 1;
    }

    public Map<String,Integer> getStatusModifier()
    {
        return Collections.emptyMap();
    }

    public String getName()
    {
        return "Card";
    }

    public String getDescription()
    {
        return "A Card";
    }

    public boolean requiresTarget()
    {
        return true;
    }

    public String __str__() 
    {
        return this.getName()+": "+this.getDescription(); 
    }

    public String __repr__()
    {
        return this.getName()+"()";
    }

    public String toString()
    {
        return this.getName();
    }
}

class Strike extends Card
{
    public int getDamageAmount()
    {
        return 6;
    }

    public String getName()
    {
        return "Strike";
    }

    public String getDescription()
    {
        return "Deal 6 damage.";
    }

}

class Defend extends Card
{
    public int getBlock()
    {
        return 5;
    }

    public String getName()
    {
        return "Defend";
    }

    public boolean requiresTarget()
    {
        return false;
    }

    public String getDescription()
    {
        return "Gain 5 Block";
    }

}

class Bash extends Card
{
    public int getDamageAmount()
    {
        return 7;
    }

    public int getBlock()
    {
        return 5;
    }

    public int getEnergyCost()
    {
        return 2;
    }

    public String getName()
    {
        return "Bash";
    }

    public String getDescription()
    {
        return "Deal 7 damage. Gain 5 Block.";
    }

}

class Neutralize extends Card
{
    public int getDamageAmount()
    {
        return 3;
    }

    public int getEnergyCost()
    {
        return 0;
    }

    public Map<String,Integer> getStatusModifier()
    {
        Map<String,Integer> ans = new HashMap<String,Integer>();
        ans.put("weak",1);
        ans.put("vulnerable",2);
        return ans;
    } 

    public String getName()
    {
        return "Neuralize";
    }

    public String getDescription()
    {
        return "Deal 3 damage. Apply 1 weak. Apply 2 vulnerable.";
    }

}

class Survivor extends Card
{
    public int getBlock()
    {
        return 8;
    }

    public Map<String,Integer> getStatusModifer()
    {
        Map<String,Integer> ans = new HashMap<String,Integer>();
        ans.put("strength",1);
        return ans;
    } 

    public boolean requiresTarget()
    {
        return false;
    }

    public String getName()
    {
        return "Survivor";
    }

    public String getDescription()
    {
        return " Gain 8 block and 1 strength.";
    }

}

class Entity
{
    public int currentHp,strength,block,weak,vulnerable,maxHp;
    public Entity(int MaxHp){
        this.currentHp=MaxHp;
        this.block=0;
        this.strength=0;
        this.weak=0;
        this.vulnerable=0;
        this.maxHp=MaxHp;
    }

    public int getHp()
    {
        return this.currentHp;
    }
    
    public int getMaxHp()
    {
        return this.maxHp;
    }

    public int getBlock()
    {
        return this.block;
    }

    public int getStrength()
    {
        return this.strength;
    }

    public int getWeak()
    {
        return this.weak;
    }

    public int getVulnerable()
    {
        return this.vulnerable;
    }

    public String getName()
    {
        return "Entity";
    }

    public void reduceHp(int x)
    {
        if(this.block>x)
        {
            this.block-=x;
            return;
        }
        else 
        {
            this.currentHp=this.currentHp-x+this.block;
            this.block=0;
        }
        if(this.currentHp<0) this.currentHp=0;
    }

    public boolean isDefeated()
    {
        if(this.currentHp>0) return false;
        else return true;
    }

    public void addBlock(int x)
    {
        this.block+=x;
        return;
    }

    public void addStrength(int x)
    {
        this.strength+=x;
        return;
    }

    public void addWeak(int x)
    {
        this.weak+=x;
        return;
    }

    public void addVulnerable(int x)
    {
        this.vulnerable+=x;
        return;
    }

    public void newTurn()
    {
        this.block=0;
        if(this.weak>0) --this.weak;
        if(this.vulnerable>0) --this.vulnerable;
        return;
    }

    public String __str__()
    {
        return this.getName()+": " + Integer.toString(this.getHp()) + "/" + Integer.toString(this.getMaxHp()) + " HP";
    }

    public String __repr__()
    {
        return this.getName()+"()";
    }

}

class Player extends Entity
{
    int energy;
    ArrayList<Card> hand,deck,discarded;

    public Player(int MaxHp,ArrayList<Card> cards) {
        super(MaxHp);
        this.energy=3;
        this.hand=new ArrayList<Card>();
        if(cards.isEmpty()) this.deck=new ArrayList<Card>();
        else this.deck=cards;
        this.discarded=new ArrayList<Card>();        
    }


    public String getName()
    {
        return "Player";
    }

    public int getEnergy()
    {
        return this.energy;
    }

    public ArrayList<Card> getHand()
    {
        return this.hand;
    }

    public ArrayList<Card> getDeck()
    {
        return this.deck;
    }

    public ArrayList<Card> getDiscarded()
    {
        return this.discarded;
    }

    public void startNewEncounter()
    {
        if(this.hand.isEmpty()) return;
        else 
        {
            this.deck=this.discarded;
            this.discarded.clear();
        }
    }

    public void endTurn()
    {
        this.discarded.addAll(this.hand);
        this.hand.clear();
    }

    public void newTurn()
    {
        super.newTurn();
        support s=new support();
        s.drawCards(deck, hand, discarded);
        this.energy=3;
        //support file draw cards
    }

    public Card playCard(String cardName)
    {
        for(Card patta:this.hand)
        {
            if(patta.getName()==cardName && patta.getEnergyCost()<=this.energy)
            {
                this.energy-=patta.getEnergyCost();
                this.discarded.add(patta);
                this.hand.remove(patta);
                return patta;
            }
        }
        return null;
    }

}

class IronClad extends Player
{
    ArrayList<Card> cards;
    
    public IronClad()
    {
        super(80,new ArrayList<Card>(){
            {
                add(new Strike());
                add(new Strike());
                add(new Strike());
                add(new Strike());
                add(new Strike());
                add(new Defend());
                add(new Defend());
                add(new Defend());
                add(new Defend());
                add(new Bash());
            }
        });
    }

    public String getName()
    {
        return "IronClad";
    }

}

class Silent extends Player
{    
    public Silent()
    {
        super(70,new ArrayList<Card>(){
            {
                add(new Strike());
                add(new Strike());
                add(new Strike());
                add(new Strike());
                add(new Strike());
                add(new Strike());
                add(new Defend());
                add(new Defend());
                add(new Defend());
                add(new Defend());
                add(new Defend());
                add(new Neutralize());
            }
        });
    }

    public String getName()
    {
        return "Silent";
    }
}

class Monster extends Entity
{
    int uniqueId=0,id;
    public Monster(int MaxHp)
    {
        super(MaxHp);
        this.id=this.uniqueId;
        uniqueId++;
    }

    public int getId()
    {
        return this.id;
    }
    public String getName()
    {
        return "Monster";
    }
    public Map<String,Integer> action()
    {
        throw new java.lang.UnsupportedOperationException("NotImplementedError");
    }

}

class Louse extends Monster
{
    support s=new support();
    int amount= s.randomLouseAmount();
    public Louse(int MaxHp)
    {
        super(MaxHp);
    }

    public Map<String,Integer> action()
    {
        
        Map<String,Integer> temp=new HashMap<String,Integer>();
        temp.put("damage",amount);
        return temp;
    }

    public String getName()
    {
        return "Louse";
    }
}

class Cultist extends Monster
{
    private int numCalls=0;
    public Cultist(int MaxHp)
    {
        super(MaxHp);
    }

    public String getName()
    {
        return "Cultist";
    }

    public Map<String,Integer> action()
    {
        Map<String,Integer> temp=new HashMap<String,Integer>();
        
        if(this.numCalls==0) 
            temp.put("damage",this.numCalls++); 
        else
        {
            temp.put("damage",6+this.numCalls); 
            this.numCalls++;
        }

        temp.put("weak",(this.numCalls-1)%2);
        
        return temp;
    }
}

class JawWorm extends Monster
{
    public JawWorm(int MaxHp)
    {
        super(MaxHp);
    }

    public String getName()
    {
        return "JawWorm";
    }

    public Map<String,Integer> action()
    {
        double tempDamage = this.getMaxHp() - this.getHp();
        tempDamage/=2;
        Map<String,Integer> temp=new HashMap<String,Integer>();
        temp.put("damage",(int)(Math.floor(tempDamage)));
        this.addBlock((int)(Math.ceil(tempDamage)));
        return temp;
    }
}

class Encounter
{
    Player player;
    ArrayList<Monster> monster=new ArrayList<Monster>();
    public Encounter(Player player,ArrayList<Pair<String,Integer>> monsters)
    {
        this.player =player;
        for(Pair<String,Integer> p:monsters)
        {   
            String name=p.getValue0();
            int maxHp=p.getValue1();
            if(name.equals("Louse")) 
            {
                Louse l1=new Louse(maxHp);
                monster.add(l1);
            }
            else if(name.equals("Cultist")) {
                Cultist c1=new Cultist(maxHp);
                monster.add(c1);
            }
            else if(name.equals("JawWorm")) 
            {
                JawWorm j1=new JawWorm(maxHp);
                monster.add(j1);
            }
        }
        this.player.startNewEncounter();
        this.startNewTurn();
    }

    public void startNewTurn()
    {
        this.player.newTurn();
    }

    public void endPlayerTurn()
    {
        this.player.endTurn();
        for(Monster m:monster) m.newTurn();
    }

    public Player getPlayer()
    {
        return this.player;
    }

    public ArrayList<Monster> getMonster()
    {
        return this.monster;
    }

    public boolean isActive()
    {
        return monster.isEmpty();
    }

    public boolean playerApplyCard(String cardName,int targetId)
    {
        
        if(player.hand.isEmpty()) return false;
        
        boolean x=false;
        // for(Card c:player.hand)
        // {
        //     if(c.getName()==cardName)
        //     {
        //         x=true;
        //         break;
        //     }
        // }
        // if (x==false) return false;
        // x=false;
        
        for(Monster m:monster)
        {
            if(m.getId()==targetId)
            {
                x=true;

                break;
            }
        }
        if(x==false) return false;

        Card temp = player.playCard(cardName);
        if(temp==null) return false;

        player.block += temp.getBlock();

        if(temp.getStatusModifier().containsKey("Strength"))
            player.strength += temp.getStatusModifier().get("Strength");

        int tDamage=temp.getDamageAmount();

        if(player.weak > 0 )
        {
            tDamage*=0.75;
        } 

        for(Monster m:monster)
        {
            if(m.getId()==targetId)
            {
                if(temp.getStatusModifier().containsKey("weak"))
                    m.weak += temp.getStatusModifier().get("weak");
                if(temp.getStatusModifier().containsKey("vulnerable"))
                    m.vulnerable += temp.getStatusModifier().get("vulnerable");

                if(m.vulnerable>0)
                {
                    tDamage*=1.75;
                }
                m.reduceHp(tDamage);
                if(m.isDefeated()) monster.remove(m);
                break;
            }
        }
        return true;
    }

    public boolean playerApplyCard(String cardName)
    {
        if(player.hand.isEmpty()) return false;
        boolean x=false;
        for(Card c:player.hand)
        {
            if(c.getName()==cardName)
            {
                x=true;
                if(c.requiresTarget()) return false;
                player.block += c.getBlock();
                if(c.getStatusModifier().containsKey("Strength"))
                    player.strength += c.getStatusModifier().get("Strength");
                break;
            }
        }
        if (x==false) return false;
        return true;
    }

    public void enemyTurn()
    {
        if(!player.hand.isEmpty()) return;
        int tDamage=0;
        for(Monster c:monster)
        {
            Map<String,Integer> temp = c.action();
            tDamage += temp.get("damage") ;
            tDamage += c.strength;
            if(temp.containsKey("weak") && temp.get("weak")>0)
            {
                player.weak += temp.get("weak");
            }
            if(c.getWeak() > 0)
            {
                tDamage *=0.75;
            }
            if(player.getVulnerable() > 0)
            {
                tDamage *=1.5;
            }
            player.reduceHp(tDamage);
        }
        startNewTurn();
    }
}


