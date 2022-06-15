public class arq_02 {
    // exercício 01 - OK
    // leia o nome de um arquivo e uma frase e salva essa frase nesse arquivo
    public static void lerSalva(){
        String nomeArq = MyIO.readLine();
        String frase = MyIO.readLine();

        Arq.openWrite(nomeArq);
        Arq.println(frase);
        Arq.close();
    }   

    // exercício 02 - OK
    //leia o nome de um arquivo e mostre seu conteúdo na tela
    public static void lerTela(){
        String nomeArq = MyIO.readLine();

        Arq.openRead(nomeArq);

        String str = Arq.readAll();
        System.out.println(str);
        Arq.close();
    }

    // exercício 03 - OK
    //leia o nome de um arquivo e mostre seu conteúdo convertido para letras maiúsculas
    public static void lerMaiuscula(){
        String nomeArq = MyIO.readLine();

        Arq.openRead(nomeArq);

        String str = Arq.readAll();
        System.out.println(str.toUpperCase());
        Arq.close();
    }

    // exercício 04 - OK
    // leia o nome de dois arquivos e copie o conteúdo do primeiro para o último
    public static void lerCopia(){
        String arq_01 = MyIO.readLine();
        String arq_02 = MyIO.readLine();

        Arq.openRead(arq_01);
        String conteudo = Arq.readAll();
        Arq.close();

        Arq.openWrite(arq_02);
        Arq.print(conteudo);
        Arq.close();
    }

    // exercício 05 - OK
    // leia o nome de dois arquivos, converta o conteúdo do primeiro para maiúscula e salve no segundo
    public static void lerMaiusculaCopia(){
        String arq_01 = MyIO.readLine();
        String arq_02 = MyIO.readLine();

        Arq.openRead(arq_01);
        String conteudo = Arq.readAll();
        Arq.close();

        Arq.openWrite(arq_02);
        Arq.print(conteudo.toUpperCase());
        Arq.close();
    }

    // exercicio 06 - OK
    // leia o nome de dois arquivos e copie o conteudo do primeiro para o segundo invertendo a ordem dos caracteres.
    public static void lerInverte(){
        String arq_01 = MyIO.readLine();
        String arq_02 = MyIO.readLine();

        Arq.openRead(arq_01);
        String conteudo = Arq.readAll();
        Arq.close();

        Arq.openWrite(arq_02);
        StringBuilder inverte = new StringBuilder();
        inverte.append(conteudo);
        inverte.reverse();

        conteudo = inverte.toString();

        Arq.print(conteudo);
        Arq.close();
    }

    // exercício 07 - OK
    // leia o nome de um arquivo e mostre na tela o conteúdo desse arquivo criptografado usando o ciframento de césar (k = 3)
    public static void escreveCifraCesar(){
        String arq_01 = MyIO.readLine();
        String resposta = new String();

        Arq.openRead(arq_01);
        String conteudo = Arq.readAll();
        Arq.close();

        for(int i = 0; i < conteudo.length(); i++){
            if(conteudo.charAt(i) != 32){
                resposta = resposta + (char)(conteudo.charAt(i) + 3);
            } else{
                resposta = resposta + (char)conteudo.charAt(i);
            }
        }

        System.out.println(resposta);
    }

    // exercício 08 - OK
    // leia o nome de um arquivo e descriptografe (ciframento de cesar) a mensagem e mostre-a na tela
    public static void lerCifraCesar(){
        String arq_01 = MyIO.readLine();
        String resposta = new String();

        Arq.openRead(arq_01);
        String conteudo = Arq.readAll();
        Arq.close();

        for(int i = 0; i < conteudo.length(); i++){
            if(conteudo.charAt(i) != 32){
                resposta = resposta + (char)(conteudo.charAt(i) - 3);
            } else{
                resposta = resposta + (char)conteudo.charAt(i);
            }
        }

        System.out.println(resposta);
    }

    public static void main(String[] argv){
        //lerSalva();
        //lerTela();
        //lerMaiuscula();
        //lerCopia();
        //lerMaiusculaCopia();
        //lerInverte();
        //escreveCifraCesar();
        //lerCifraCesar();
    }
}
