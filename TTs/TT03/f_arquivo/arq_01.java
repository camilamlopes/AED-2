public class arq_01 {
    public static void main(String[] argv){
        //Abrir o arquivo texto
        Arq.openWrite("exemplo.txt");

        //Ler o arquivo texto
        String str = Arq.readAll();

        //fechamento do arquivo texto
        Arq.close();

        //copia
        Arq.openWrite("copia.txt");
        Arq.print(str);
        Arq.close();
    }

}
