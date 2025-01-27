
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
    /**
     * Construtor para objetos da classe Item
     */
    public Item(String description)
    {
        // inicializa variáveis de  instância
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
