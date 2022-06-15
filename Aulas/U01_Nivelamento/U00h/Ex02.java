/**
 * @author Camila Moreira Lopes
 * Faça um método recursivo que receba um array de números inteiros e um número inteiro n indicando o tamanho do array 
 * e retorne o maior elemento
 */

public class Ex02{
    public static int maiorNum (int[] array, int n){        
        if(n - 1 > 0){
            int pos = maiorNum(array, n - 1);
            if(array[n - 1] < pos){
                array[n - 1] = pos;
            }
        }
        return array[n - 1];
    }

    public static void main (String[] args){
        int[] array = {50, 12, 7, 17 ,5};
        System.out.println("Maior número: " + maiorNum(array, 5));
    }
}