/**
 * @author Camila Moreira Lopes
 * Faça um método recursivo que receba dois números inteiros e retorne a multiplicação do primeiro pelo segundo fazendo somas
 */

public class Ex01{
    public static int multiRec (int numA, int numB){
        if(numA > 1) return numB + multiRec(numA - 1, numB);
        else return numB;

    }
    public static void main (String[] args){
        int a = 5,
            b = 1;
        System.out.println("Teste " + a + " x " + b + " = " + multiRec(a, b));
    }
}