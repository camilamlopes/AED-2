/**
 * @author Camila Moreira Lopes
 * Leia o nome de dois arquivos e copie o conteúdo do primeiro para o último
 */

public class Ex04 {
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

        // Escrita para o arquivo de cópia
        Arq.openWrite(arqPaste);
        Arq.println(str);
        Arq.close();
    }
}
