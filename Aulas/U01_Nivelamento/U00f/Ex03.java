/**
 * @author Camila Moreira Lopes
 * Leia o nome de um arquivo e mostre seu conteúdo convertido para letras maiúsculas
 */

public class Ex03 {
    public static void main (String[] args){
        // Leitura do nome de um arquivo
        String arqName = new String();
        arqName = MyIO.readLine();

        // Abrir o arquivo para leitura
        Arq.openRead(arqName);
        String str = Arq.readAll();
        String strFinal = "";

        // Mostrar seu conteúdo convertido para letras maiúsculas
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) >= 'a' && str.charAt(i) <= 'z'){
                strFinal += (char)(str.charAt(i) - 32);
            } 
            else{
                strFinal += str.charAt(i);
            }

        }

        MyIO.println(strFinal);
        // Fechamento do aquivo
        Arq.close();
    }
}
