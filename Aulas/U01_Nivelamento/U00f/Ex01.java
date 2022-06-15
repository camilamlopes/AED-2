/**
 * @author Camila Moreira Lopes
 * Leia o nome de um arquivo e uma frase e salve essa frase nesse arquivo
 */

public class Ex01 {
    public static void main (String[] args){
        // Leitura do nome de um arquivo
        String arqName = new String();
        arqName = MyIO.readLine();

        // Leitura da frase
        String frase = new String();
        frase = MyIO.readLine();

        // Abrir o arquivo
        Arq.openWrite(arqName);

        // Escrita da frase no arquivo
        Arq.println(frase);
        Arq.close();
    }
}
