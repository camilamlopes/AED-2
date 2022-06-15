import java.io.*;
import java.nio.charset.*;

public class TP01Q03 {
    //função que prevê se a linha possui a leitura FIM
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static String cifraCesar(String str){
        String cifra = "";
        for(int i = 0; i < str.length(); i++){
            cifra = cifra + (char)(str.charAt(i) + 3);
        }
        return cifra;
    }

    /* FUNÇÃO PRINCIPAL */
    public static void main(String[] args) throws IOException{
        //bufferReader para ler entradas array de string para receber todas
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));
        String[] entrada = new String[1000];
        int num = 0;
        
        entrada[num] = in.readLine();
        while(isFim(entrada[num]) == false){
            MyIO.println(cifraCesar(entrada[num]));
            num++;
            entrada[num] = in.readLine();
        }
    }
}
