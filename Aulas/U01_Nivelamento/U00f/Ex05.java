/**
 * @author Camila Moreira Lopes
 * Leia o nome de dois arquivos, abra o primeiro, converta seu conteúdo para letra maiúscula e salve o no segundo
 */

public class Ex05 {
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

        // Conversão para letra maiúscula
        String strFinal = "";
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) >= 'a' && str.charAt(i) <= 'z'){
                strFinal += (char)(str.charAt(i) - 32);
            } 
            else{
                strFinal += str.charAt(i);
            }

        }

        // Escrita para o arquivo de cópia
        Arq.openWrite(arqPaste);
        Arq.println(strFinal);
        Arq.close();
    }
}
