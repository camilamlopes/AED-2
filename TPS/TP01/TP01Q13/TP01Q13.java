import java.io.*;
import java.nio.charset.*;

public class TP01Q13 {
    //função que prevê se a linha possui a leitura FIM
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static String cifraCesar(String str, int pos, String cifra){
        if(pos == str.length()) return cifra;
        else{
            cifra = cifra + (char)(str.charAt(pos) + 3);
            return cifraCesar(str, pos + 1, cifra);
        }
    }

    /* FUNÇÃO PRINCIPAL */
    public static void main(String[] args) throws IOException{
        //bufferReader para ler entradas array de string para receber todas
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));
        String[] entrada = new String[1000];
        int num = 0;
        
        entrada[num] = in.readLine();
        while(isFim(entrada[num]) == false){
            //String starter = new String();
            MyIO.println(cifraCesar(entrada[num], 0, ""));
            num++;
            entrada[num] = in.readLine();
        }
    }
}
