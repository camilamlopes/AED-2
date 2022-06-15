import java.io.*;
import java.nio.charset.*;

public class TP01Q06 {
    // função que prevê se a linha possui a leitura FIM
    public static boolean isFim(String s){
    return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    /* FUNÇÃO VERIFICA SE A STRING É VOGAL OU NÃO */
    public static String isVogal(String str){ 
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == 'A' || str.charAt(i) == 'E' || str.charAt(i) == 'I' || str.charAt(i) == 'O' || str.charAt(i) == 'U'
            || str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o' || str.charAt(i) == 'u'){
                continue;
            } else return "NAO";
        }
        return "SIM";
    } 

    /* FUNÇÃO VERIFICA SE A STRING É CONSOANTE OU NÃO */
    public static String isConsoante(String str){
        for(int i = 0; i < str.length(); i++){
            if((int) str.charAt(i) > 64 && (int) str.charAt(i) < 91){
                if(str.charAt(i) == 'A' || str.charAt(i) == 'E' || str.charAt(i) == 'I' || str.charAt(i) == 'O' || str.charAt(i) == 'U' 
                || str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o' || str.charAt(i) == 'u'){
                return "NAO";
                }
            } else return "NAO";
        }
        return "SIM";
    }

    public static String isInt(String str){
        for(int i = 0; i < str.length(); i++){
            if((int) str.charAt(i) > 47 && (int) str.charAt(i) < 58){
                continue;
            } else return "NAO";
        }
        return "SIM";
    }

    public static String isFloat (String str){
        int countP = 0;
        for(int i = 0; i < str.length(); i++){
            if((int) str.charAt(i) > 47 && (int) str.charAt(i) < 58 || str.charAt(i) == ',' || str.charAt(i) == '.'){
                if(str.charAt(i) == ',' || str.charAt(i) == '.'){
                    if(countP == 1) return "NAO";
                    else countP++;
                }
            } else return "NAO";
        }
        return "SIM";
    }

    public static void main(String[] args) throws IOException{
        //bufferReader para ler entradas array de string para receber todas
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));
        String[] entrada = new String[1000];
        int num = 0;

        entrada[num] = in.readLine();
        while(isFim(entrada[num]) == false){
            
            System.out.print(isVogal(entrada[num]) + " " + isConsoante(entrada[num]) + " ");
            System.out.print(isInt(entrada[num]) + " " + isFloat(entrada[num]) + "\n");

            num++;
            entrada[num] = in.readLine();
        }
    }
}
