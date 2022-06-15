/**
 * @author Camila Moreira Lopes
 * Faça um método recursivo que receba um array de caracteres e retorne um valor booleano indicando se esse é um palíndromo
 */

public class Ex03{
    public static boolean isPalindromo (String array, int i, int f){
        if(i < f){
            if(array.charAt(i) != array.charAt(f)){
                return false;
            } else{
                return isPalindromo(array, i + 1, f - 1);
            }
        }
        
        return true;
    }

    public static void main(String[] args){
        String a = "bananab";
        System.out.println("A palavra " + a + " é palindromo: " + isPalindromo(a, 0, a.length() - 1));
    }
}
