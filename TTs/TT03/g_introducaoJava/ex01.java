/* EXERCÍCIOS DE IF E ELSE */
import java.io.*;
import java.nio.charset.*;

public class ex01{
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));
    public static String readString(){
        String s = "";
        char tmp;
        try{
           do{
              tmp = (char)in.read();
              if(tmp != '\n' && tmp != ' ' && tmp != 13){
                 s += tmp;
              }
           }while(tmp != '\n' && tmp != ' ');
        }catch(IOException ioe){}
        return s;
    }

    // exercicio 01
    // ler três numeros reais representando os lados de um triângulo e informe o tipo do triângulo
    public static void triangulo(){
        System.out.printf("Insira o valor dos três lados do triângulo:\n");
        int a = Integer.parseInt(readString().trim()),
            b = Integer.parseInt(readString().trim()),
            c = Integer.parseInt(readString().trim());

        if(a == b && b == c){
            System.out.println("Triângulo Equilatero");
        } else if(a == b || b == c || a == c){
            System.out.println("Triângulo Isóceles");
        } else{
            System.out.println("Triângulo Escalenos");
        }
    }

    // exercício 02
    // ler 3 inteiros, selecionar o menor e o maior e imprime 
    public static void maiorMenor(){
        System.out.println("Insira o valor de três inteiros: ");
        int a = Integer.parseInt(readString().trim()),
            b = Integer.parseInt(readString().trim()),
            c = Integer.parseInt(readString().trim());        
        
        if(a > b){
            if(a > c){ 
                if(c > b){ // a > c > b
                    System.out.println("Maior: " + a + "\nMenor: "+ b);
                }else{ // a > b > c
                    System.out.println("Maior: " + a + "\nMenor: " + c);
                }
            } else{ // c > a > b
                System.out.println("Maior: " + c + "\nMenor: " + b);
            }
        } else if(b > c){ 
            if(c > a){ // b > c > a
                System.out.println("Maior: " + b + "\nMenor: " + a);
            } else { // b > a > c
                System.out.println("Maior: " + b + "\nMenor: " + a);
            }
        } else{ // c > b > a
            System.out.println("Maior: " + c + "\nMenor: " + a);
        }   
    }
    
    // exercicio 03
    /*  Leia dois números. Se um deles for maior que 45, realize a soma dos  mesmos. 
        Caso contrário, se os dois forem maior que 20, realize a subtração do maior pelo menor, senão, 
        se um deles for menor do que 10 e o outro  diferente de 0 realize a divisão do primeiro pelo segundo. 
        Finalmente, se nenhum dos casos solicitados for válido, mostre seu nome na tela.*/
    public static void soma(){
        System.out.println("Insira dois valores: ");
        int a = Integer.parseInt(readString().trim()),
            b = Integer.parseInt(readString().trim()),
            c;
        
        if(a > 45 || b > 45){
            c = a + b;
            System.out.println(c);
        } else if(a > 20 && b > 20){
            if(a > b){
                c = a - b;
                System.out.println(c);
            } else{
                c = b - a;
                System.out.println(c);
            }
        } else if(a < 10  && a != 0|| b < 10 && b != 0){
            c = a/b;
            System.out.println(c);
        } else{
            System.out.println("Camila");
        }
    }

    // exercício 04
    // ler num de gols do mandante e do visitante e imprimir o vencedor/empate
    public static void gols(){
        System.out.println("Insira o número de gols do mandante e do visitante:");

        int mandante = Integer.parseInt(readString().trim()),
            visitante = Integer.parseInt(readString().trim());
        
        if(mandante >= visitante){
            if(mandante != visitante){
                System.out.println("Vencedor: mandante por " + mandante + " X " + visitante);
            } else{
                System.out.println("Empate");
            }
        } else{
            System.out.println("Vencedor: visitante por " + mandante + " X " + visitante);
        }
    }

    // exercício 05
    // permita entrar com o salário bruto e o valor da prestação e informar se o empréstimo será concedido.
    public static void salario(){
        System.out.println("Insira o o valor do salário bruto e o valor da prestação:");
        float salarioBruto = Integer.parseInt(readString().trim()),
            prestacao = Integer.parseInt(readString().trim());
        
        if(prestacao > salarioBruto * 0.4){
            System.out.println("O empréstimo não será concedido!");
            System.out.println("O valor ultrapassa de 40% do salário bruto!");
        } else System.out.println("O empréstimo será concedido!");
    }
    
    // exercício 06
    // ler 2 reais e imprimir a raiz cúbica do menor e o logaritmo do menor considerando o maior como a base desse logaritmo
    public static void mat(){
        System.out.println("Insira dois números reais: ");

        float num_1 = Integer.parseInt(readString().trim()),
              num_2 = Integer.parseInt(readString().trim());

        if(num_1 > num_2){
            System.out.println("Raiz cúbica de "+ num_2 + " =" + Math.cbrt(num_2));
            System.out.println("Logaritmo de "+ num_2 + " na base " + num_1 + " =" + Math.log(num_2)/Math.log(num_1));
        }else{
            System.out.println("Raiz cúbica de "+ num_2 + " =" + Math.cbrt(num_1));
            System.out.println("Logaritmo de "+ num_2 + " na base " + num_1 + " =" + Math.log(num_1)/Math.log(num_2));
        }
    }

    public static void main(String[] argv){
        //triangulo();
        //maiorMenor()
        //soma();
        //gols();
        salario();
    }
}