import java.io.*;
import java.nio.charset.*;

public class TP01Q15 {
    // função que prevê se a linha possui a leitura FIM
    public static boolean isFim(String s){
    return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    /* FUNÇÃO VERIFICA SE A STRING É VOGAL OU NÃO */
    public static String isVogal(String str, int pos){    
        if(pos == str.length()){
            return "SIM";     
        } else if(str.charAt(pos) == 'A' || str.charAt(pos) == 'E' || str.charAt(pos) == 'I' || str.charAt(pos) == 'O' || str.charAt(pos) == 'U'
               || str.charAt(pos) == 'a' || str.charAt(pos) == 'e' || str.charAt(pos) == 'i' || str.charAt(pos) == 'o' || str.charAt(pos) == 'u'){
                   return isVogal(str, pos + 1);
        } else return "NAO";
    } 

    /* FUNÇÃO VERIFICA SE A STRING É CONSOANTE OU NÃO */
    public static String isConsoante(String str, int pos){
        if(pos == str.length()){
            return "SIM";     
        } else if(((int) str.charAt(pos) > 64 && (int) str.charAt(pos) < 91) || ((int) str.charAt(pos) > 96 && (int) str.charAt(pos) < 123)){
            if(str.charAt(pos) == 'A' || str.charAt(pos) == 'E' || str.charAt(pos) == 'I' || str.charAt(pos) == 'O' || str.charAt(pos) == 'U' 
            || str.charAt(pos) == 'a' || str.charAt(pos) == 'e' || str.charAt(pos) == 'i' || str.charAt(pos) == 'o' || str.charAt(pos) == 'u'){
                return "NAO";
            } else return isConsoante(str, pos + 1);
        } else return "NAO";
    }

    /* FUNÇÃO VERIFIVA SE A STRING É INTEIRO OU NÃO */
    public static String isInt(String str, int pos){
        if(pos == str.length()) return "SIM";     
        else if((int) str.charAt(pos) > 47 && (int) str.charAt(pos) < 58){
            return isInt(str, pos + 1);
        } else return "NAO";
    }

    /* FUNÇÃO VERIFICA SE A STRING É INTEIRO OU NÃO */
    public static String isFloat (String str, int pos, int countP){
        if(pos == str.length()) return "SIM";   
        if((int) str.charAt(pos) > 47 && (int) str.charAt(pos) < 58 || str.charAt(pos) == ',' || str.charAt(pos) == '.'){
            if(str.charAt(pos) == ',' || str.charAt(pos) == '.'){
                if(countP == 1) return "NAO";
                else return isFloat(str, pos + 1, countP + 1);
            } else return isFloat(str, pos + 1, countP);
        } else return "NAO";
    }

    public static void main(String[] args) throws IOException{
        //bufferReader para ler entradas array de string para receber todas
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));
        String[] entrada = new String[1000];
        int num = 0;

        entrada[num] = in.readLine();
        while(isFim(entrada[num]) == false){
            System.out.print(isVogal(entrada[num], 0) + " " + isConsoante(entrada[num], 0) + " ");
            System.out.print(isInt(entrada[num], 0) + " " + isFloat(entrada[num], 0, 0) + "\n");

            num++;
            entrada[num] = in.readLine();
        }
    }
}
