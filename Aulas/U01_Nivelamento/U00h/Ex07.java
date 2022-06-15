/**
 * @author Camila Moreira Lopes
 * Faça um método recursivo para cada um dos problemas abaixo 
 * a.) T(0) = 1
	   T(1) = 2
	   T(n) = T(n-1) * T(n-2) - T(n-1)
 *
 * b.) T(0) = 1
	   T(n) = T(n-1)²
 */

public class Ex07 {
    public static int problemA (int num){
        if(num == 0) return 1;
        else if(num == 1) return 2;
        else return problemA(num - 1) * problemA(num - 2) - problemA(num - 1);
    }

    public static int problemB (int num){
        if(num == 0) return 1;
        else return problemB(num - 1) * problemB(num - 1);
    }
    public static void main (String[] args){
        System.out.println("Problema A: " + problemA(5));
        System.out.println("Problema B: " + problemB(5));

    }
}
