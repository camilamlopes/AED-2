/**
 * @author Camila Moreira Lopes
 * Leia o nome de um arquivo e mostre seu conteúdo na tela
 */

public class Ex02 {
    public static void main(String[] args){
        // Leitura do nome de um arquivo
        String arqName = new String();
        arqName = MyIO.readLine();

        // Abrir o arquivo para leitura
        Arq.openRead(arqName);
        String str = Arq.readAll();
        MyIO.println(str);

        Arq.close();
    }
}
