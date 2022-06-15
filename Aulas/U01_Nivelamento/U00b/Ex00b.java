import java.util.*;

/**
 * Exercício da Unidade 00 b
 * @author Camila Moreira Lopes
 * @version 1 08/2022
 */

class Ex00b {
    public static boolean ex01(int[] array, int x){
        boolean answ = false;
        for(int i = 0; i < array.length; i++){
            if(array[i] == x) answ = true;
        }
        return answ;
    }

    public static boolean ex02(int[] array, int x){
        int num = array.length / 2 - 1;

        while(array[num] != x && num < array.length - 1 && num > 0){
            if(array[num] < x) num++;
            else num--;
        }

        if(array[num] == x) return true;
        else return false;
    }

    public static void ex03(int[] array){
        int maior = array[0];
        int menor = array[0];

        for(int i = 1; i < array.length; i++){
            if(array[i] > maior) maior = array[i];
            else if(array[i] < menor) menor = array[i];
        }

        System.out.println("Maior = " + maior);
        System.out.println("Menor = " + menor);
    }

    public static void ex04(int[] array){
        int maior = array[0];
        int menor = array[0];

        for(int i = 1; i < array.length; i++){
            if(array[i] > maior) maior = array[i];
            else if(array[i] < menor) menor = array[i];
        }

        System.out.println("Maior = " + maior);
        System.out.println("Menor = " + menor);
    }

    public static void test(){
        byte b = 0;
        short s = 0;
        int i = 0;
        long l = 0;
        while(true){
            b++; s++; i++; l++;
            System.out.println(b + " "+ s + " " + i + " " + l);
        }
        

    }
    public static void main (String[] args){
        //Declaração da classe scanner
        Scanner sc = new Scanner (System.in);

        System.out.println("Entre com um número presente na tabela abaixo: ");
        System.out.println("- Exercício 01");
        System.out.println("- Exercício 02");
        System.out.println("- Exercício 03");
        System.out.println("- Exercício 04");
        System.out.println("- Exercício teste 05");

        int inteiro = sc.nextInt();
        int[] arrayI = new int[5];
        int numX = 0;

        switch (inteiro){
            case 1:
                System.out.println("Insira 5 números inteiros: ");
                for(int i = 0; i < 5;i++) arrayI[i] = sc.nextInt();
                
                System.out.println("Insira 1 número inteiro: ");
                numX = sc.nextInt();

                System.out.println(ex01(arrayI, numX));
                break;
            case 2:
                System.out.println("Insira 5 números inteiros ordenados: ");
                for(int i = 0; i < 5;i++) arrayI[i] = sc.nextInt();
                
                System.out.println("Insira 1 número inteiro: ");
                numX = sc.nextInt();

                System.out.println(ex02(arrayI, numX));
            break;

            case 3:
                System.out.println("Insira 5 números: ");
                for(int i = 0; i < 5;i++) arrayI[i] = sc.nextInt();

                ex03(arrayI);
            break;

            case 4:

            break;

            case 5:
                test();
            break;
            default:
                break;
        }// fim switch
    }// fim main
} // fim classe