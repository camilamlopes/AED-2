/**
 * Palíndromo em Java
 * @author Camila Moreira Lopes
 */

public class Ex05 {
    public static boolean isPalindromo(String str){
        boolean resp = true;

        for(int i = 0; i < str.length()/2; i++){
            if(str.charAt(i) != str.charAt(str.length() - 1 - i)){
                resp = false;
            }
        }
        return resp;
    }

    // Teste se é FIM
    public static boolean isFim (String str){
        return (str.length() == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M');
    }
    public static void main(String[] args){
        String[] in = new String[1000];
        int numIn = 0;

        // Leitura de todas as entradas
        do{
            in[numIn] = MyIO.readLine();
        } while(isFim(in[numIn++]) == false);
        numIn--; // É necessário desconsiderar a última linha que contém a palavra FIM
        
        // Escrita da saída
        for(int i = 0; i < numIn; i++){
            if(isPalindromo(in[i])){
                MyIO.println("SIM");
            } else MyIO.println("NAO");
            
        }
    }
}
