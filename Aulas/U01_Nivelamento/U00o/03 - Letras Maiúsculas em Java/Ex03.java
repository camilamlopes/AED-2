/**
 * Letras Maiúsculas em Java
 * @author Camila Moreira Lopes
 */

public class Ex03 {
    public static boolean isMaiusculo (char c){
        return (c >= 'A' && c <= 'Z');
    }

    public static int countMaiusculas (String str){
        int answ = 0;
        for(int i = 0; i < str.length(); i++){
            if(isMaiusculo(str.charAt(i)) == true){
                answ++;
            }
        }
        return answ;
    }

    // Teste de 
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
            MyIO.println(countMaiusculas(in[i]));
        }
    }
}
