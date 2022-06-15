/**
 * Matriz em Java
 * @author Camila Moreira Lopes
 */

public class Ex01{
    public static void main (String[] args){
        int x = MyIO.readInt();
        int[][] matriz = new int[x][x];
        int[] soma = new int[x];

        for(int i = 0; i < x; i++){
            for(int j = 0; j < x; j++){
                matriz[i][j] = MyIO.readInt();
            }
        }

        for(int i = 0; i < x; i++){
            soma[i] = 0;
            for(int j = 0; j < x; j++){
                soma[i] += matriz[j][i];
            }
        }
        for(int i = 0; i < x; i++){
            System.out.print(soma[i] + " ");
        }
    }
}
