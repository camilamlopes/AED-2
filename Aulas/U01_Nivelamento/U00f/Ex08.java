/**
 * @author Camila Moreira Lopes
 * Leia o nome de um arquivo contendo uma mensagem criptografada com o Ciframento de César (k = 3), 
 * descriptografe a mensagem e mostre-a na tela
 */

public class Ex08 {
    public static void main (String[] args){
        // Leitura do nome do arquivo
        String  arq = new String();
        arq = MyIO.readLine();

        // Leitura do arquivo de cópia
        Arq.openRead(arq);
        String str = Arq.readAll();
        Arq.close();

        // Criptografar
        String strFinal = "";
        for(int i = 0; i < str.length(); i++){
            strFinal += (char)(str.charAt(i) - 3);
        }

        MyIO.println(strFinal);
    }
}
