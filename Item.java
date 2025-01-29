
/**
 * Escreva uma descrição da classe Item aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Item
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    private String description;
    private int weight;
    /**
     * Construtor para objetos da classe Item
     */
    public Item(String description, int weight)
    {
        // inicializa variáveis de  instância
        this.description = description;
        this.weight = weight;
    }

    public String getDescription(){
        return description;
    }
    
    public int giveWeight(){
        return weight;
    }
}
