import java.io.*;
import java.nio.charset.*;

public class TP01Q11 {
    //função que prevê se a linha possui a leitura FIM
    public static boolean isFim(String s){ 
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static boolean isPalindromo(String str, int pos){
        if(pos == (str.length()/2)) return true;
        else if(str.charAt(pos) != str.charAt(str.length() - pos - 1)){
            return false;
        } else return isPalindromo(str, pos + 1);
    }

    public static void main(String[] args) throws IOException{
        //bufferReader para ler entradas array de string para receber todas
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));
        String[] entrada = new String[1000];
        int num = 0;
        
        entrada[num] = in.readLine();
        while(isFim(entrada[num]) == false){
            if(isPalindromo(entrada[num], 0)){
                System.out.println("SIM");
            }
            else{
                System.out.println("NAO");
            }
            num++;
            entrada[num] = in.readLine();
        }
    }  
}
