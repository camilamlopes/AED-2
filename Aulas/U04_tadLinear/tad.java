
class Lista{
    private int[] array;
    private int num;

    /* MÉTODOS CONSTRUTORES */
    Lista(){
        this.num = 0;
    }
    
    /**
     * Construtor de classe
     * @param tamanho
     */
    Lista(int tamanho){
        this.array = new int[tamanho];
        this.num = 0;
    }

    /* MÉTODOS DE INSERÇÃO */
    /**
     * Insere um elemento na última posição da lista e 
     * move os demais elementos para o fim da fila
     * @param x
     * @throws Exception
     */
    void inserirFim(int x) throws Exception{
        // validar inserção
        if(num >= array.length){
            throw new Exception("Erro ao inserir!");
        }

        array[num] = x;
        num++;
    }

    void inserirInicio(int x) throws Exception{
        if(num >= array.length){
            throw new Exception("Erro ao inserir!");
        }

        for(int i = num; i > 0; i--){
            array[i] = array[i-1];
        }

        array[0] = x;
        num++;
        
    }
    
    void inserir(int x, int pos) throws Exception{
        // validar inserção
        if(num >= array.length || pos < 0 || pos > num){
            throw new Exception("Erro ao inserir!");
        }

        // levar elementos para o fim do array
        for(int i = num; i > pos; i--){
            array[i] = array[i - 1];
        }
        array[pos] = x;
        num++;
    }

    /* MÉTODOS DE REMORÇÃO */
    /*int removerFim(){

    }
    int removerInicio(){

    }
    int remover(int pos){

    }*/

    void write(){

    }

}


public class tad{
    public static void main(String[] args){

    }
}