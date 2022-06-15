import java.io.*;
import java.nio.charset.*;
import java.util.Random;

public class TP01Q04 {
    //função que prevê se a linha possui a leitura FIM
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static String geraAleatorio(String str, char letra, char troca){
        String resp = "";
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == letra){
                resp += troca;
            } else{
                resp += str.charAt(i);
            } 
        }
        return resp;
    }   

    public static void main(String[] args) throws IOException{
        //
        Random gerador = new Random();
        gerador.setSeed(4);

        //bufferReader para ler entradas array de string para receber todas
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));
        String[] entrada = new String[1000];
        int num = 0;
       
        entrada[num] = in.readLine();
        while(isFim(entrada[num]) == false){
            char letra = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
            char troca = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
            
            MyIO.println(geraAleatorio(entrada[num], letra, troca));
            num++;
            entrada[num] = in.readLine();
        }
    }
}
