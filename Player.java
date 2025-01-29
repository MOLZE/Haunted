import java.util.ArrayList;

/**
 * Escreva uma descrição da classe Player aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Player
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    
    private int maximumWeight = 3, currentWeight = 0;
    private ArrayList<Item> invPlayer = new ArrayList<>();
    
    /**
     * Construtor para objetos da classe Player
     */
    public Player()
    {
        // inicializa variáveis de instância
    }
    public void getItem(Item item){
        if(item.giveWeight() + currentWeight > maximumWeight){
            System.out.println("You can't carry items anymore..., go to the bathroom");
        }else{
            invPlayer.add(item);
            currentWeight = item.giveWeight() + currentWeight;
        }
    }
    public Item dropItem(){
        currentWeight = currentWeight - invPlayer.get(0).giveWeight();
        Item drop = invPlayer.get(0);
        invPlayer.remove(0);
        return drop;
    }

    public int totalItems(){
        return invPlayer.size();
    }

    public void bagSize(){
        maximumWeight += 2;
    }

    public void listAllItemsWithPlayer(){
        for(int i = 0; i < invPlayer.size(); i++){
            System.out.println(invPlayer.get(i).getDescription());
        }
    }
}
