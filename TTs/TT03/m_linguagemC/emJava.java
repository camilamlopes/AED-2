/**
 * Faça um programa que leia n números inteiros, armazene-os em um arquivo,leia-os do arquivo e mostre-os na tela
 */
import java.io.*;
public class emJava{

    public static void armazenaLe() throws IOException{
        System.out.print("Insira a quantidade de números inteiros a serem lidos: ");
        int n = MyIO.readInt(),
            num = 0;
        RandomAccessFile arq = new RandomAccessFile("ex01.txt", "rw"); // abertura arquivo

        for(int i = 0; i < n; i++){
            num = MyIO.readInt();
            arq.writeInt(num);
        }

        arq.close();

        RandomAccessFile arquivo = new RandomAccessFile("ex01.txt", "rw"); // abertura arquivo

        for(int i = 0; i < n; i++){
            num = arquivo.readInt();
            MyIO.println(num);
        }
        arquivo.close(); // fecha arquivo
    }

    public static void inversa(int n){
        int num = MyIO.readInt();
        if(n == 1) System.out.println("\n" + num);
        else{
            inversa(n - 1);
            System.out.println(num);
        }
    }

    public static void main(String[] args) throws IOException{
        //armazenaLe();
        inversa(10);
    }
}