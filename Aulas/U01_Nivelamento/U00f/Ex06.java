/**
 * @author Camila Moreira Lopes
 * Leia o nome de dois arquivos e copie o conteúdo do primeiro para o segundo invertendo a ordem dos caracteres. 
 * O último caractere no arquivo de entrada será o primeiro do de saída, o penúltimo caractere será o segundo, ... 
 */

public class Ex06 {
    public static void main (String[] args){
        // Leitura dos nomes de dois arquivos
        String  arqCopy = new String(),
                arqPaste = new String();
        arqCopy = MyIO.readLine();
        arqPaste = MyIO.readLine();

        // Leitura do arquivo de cópia
        Arq.openRead(arqCopy);
        String str = Arq.readAll();
        Arq.close();

        // Inversão dos characteres
        String strFinal = "";
        for(int i = str.length() - 1; i >= 0; i--){
            strFinal += str.charAt(i);
        }

        // Escrita para o arquivo de cópia
        Arq.openWrite(arqPaste);
        Arq.println(strFinal);
        Arq.close();
    }
}
