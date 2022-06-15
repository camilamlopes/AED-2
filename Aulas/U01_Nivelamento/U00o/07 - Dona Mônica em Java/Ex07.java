/**
 * Dona Mônica em Java
 * @author Camila Moreira Lopes
 */

public class Ex07 {
    
    public static int maisVelho(int mae, int filho_1, int filho_2){
        int velho = mae - filho_1 - filho_2;
        
        if(velho < filho_1) velho = filho_1;
        if(velho < filho_2) velho = filho_2;

        return velho;
    }

    public static void main(String[] args){
        int[][] in = new int[1000][3];
        int pos = 0;

        // Leitura de todas as entradas

        

        for(int i = 0; i < pos; i++){
            
        }
    }
}
