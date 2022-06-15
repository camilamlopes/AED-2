/**
 * @author Camila Moreira Lopes
 * Faça um método recursivo que receba um string e retorne um número inteiro indicando a quantidade de caracteres 
 * NOT vogal AND NOT consoante maiúscula da string recebida como parâmetro
 */

public class Ex05 {
    public static int numVogal (String array, int pos){
        if(pos < array.length()){
            if(array.charAt(pos) < 97 || array.charAt(pos) == 'a'|| array.charAt(pos) == 'e'|| array.charAt(pos) == 'i' || array.charAt(pos) == 'o'|| array.charAt(pos) == 'u'){
                return 0 + numVogal(array, pos + 1);
            }
            return 1 + numVogal(array, pos + 1);
        }
        return 0;
    }
    public static void main (String[] args){
        System.out.println("A palavra 'Palavra' possui: " + numVogal("Palavra", 0) + " caracteres NOT vogal AND NOT consoante maiúscula");

    }
}
