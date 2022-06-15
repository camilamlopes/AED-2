/* EXERCÍCIOS DE STRING */
import java.io.*;
import java.nio.charset.*;

public class ex05 {
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
    //Exercício 01
    //Faça um programa que leia uma String e um caractere e mostre na tela quantas vezes esse caractere aparece na String
    public static void countChar() throws IOException{
        String str = readString();
        char c = (char) in.read();
        
        int count = 0;

        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == c) count++;
        }

        System.out.println("A letra '" + c + "' aparece " + count + " vezes na string: " + str);
    }

    //Exercício 02
    // Faça um programa que leia uma string e mostre se ela é composta apenas por dígitos
    public static void isDigito(){
        String str = readString();   
        boolean resp = true;
        for (int i = 0; i < str.length(); i++){
            if ((str.charAt(i) >= '0' && str.charAt(i) <= '9') == false){
                resp = false;
                i = str.length();
            }
        }
        if (resp == true)  System.out.println("SÓ TEM DÍGITO");
        else  System.out.println("NÃO TEM SÓ DIGITO"); 
    }

    //Exercício 03
    // Faça um programa que leia uma string e a converta para número inteiro
    public static void conversorInt(){
        String str = readString(); 
        int num = 0, tmp;
        for (int i = 0; i < str.length(); i++){
            tmp = (int)(str.charAt(i) - 48);
            tmp *= (int) Math.pow(10, str.length() - i - 1);
            num += tmp;
        }
        
        System.out.println(num);
    }

    //Exercício 04
    // Faça um programa que leia uma string e mostre se ela é um palíndromo
    public static void isPalindromo(){
        String str = readString(); //"ROMA E AMOR”, “ABCDDCBA”, “ABXDDCBA”
        boolean resp = true;
        for (int i = 0; i < str.length()/2; i++){
            if (str.charAt(i) != str.charAt(str.length() - i - 1)){
                resp = false;
                i = str.length();
            }
        }
        System.out.println(resp);
    }

    //Exercício 05
    // Faça um programa para ler uma palavra. Em seguida, mostre o número de caracteres da mesma e seu número de caracteres maiúsculos
    public static void countString() throws IOException{
        String str = readString();
        int countC = 0,
            countM = 0;

        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) > 40 && str.charAt(i) < 91) countM++;
            countC++;
        }

        System.out.println("A String possui " + countC + " caracteres, sendo que " + countM + " são maiusculas!");
    }
    
    //Exercício 06
    // Faça um programa para ler uma palavra. Em seguida, mostre a primeira ocorrência da letra A
    public static void letraA(){
        String str = readString();
        int pos = 0;
        while(str.charAt(pos) != 'A' && pos < str.length()) pos++;

        if(pos == str.length()) System.out.println("Não há a ocorrência da letra A");
        else System.out.println("A primeira ocorrência da letra A é na posição: " + pos);

    }

    //Exercício 07
    // Faça um programa para ler uma palavra. Em seguida, mostre os números de caracteres,  letras,  não letras, vogais e consoantes.
    //questão do tp 01


    public static void main(String[] argv){
        
    }
}
