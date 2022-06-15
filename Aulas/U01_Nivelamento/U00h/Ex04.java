/**
 * @author Camila Moreira Lopes
 * Faça um método recursivo que receba um array de caracteres e retorne um número inteiro indicando a 
 * quantidade de vogais do mesmo
 */

public class Ex04 {
    public static int numVogal (String array, int pos){
        if(pos < array.length()){
            if(array.charAt(pos) == 'a'|| array.charAt(pos) == 'e'|| array.charAt(pos) == 'i' || array.charAt(pos) == 'o'|| array.charAt(pos) == 'u'){
                return 1 + numVogal(array, pos + 1);
            }
            return 0 + numVogal(array, pos + 1);
        }
        return 0;
    }
    public static void main (String[] args){
        System.out.println("A palavra 'palavra' possui: " + numVogal("palavra", 0) + " vogais");

    }
}
