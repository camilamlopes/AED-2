public class ex01{
    //Exercício 03
    // Faça um método recursivo que receba dois números inteiros e retorne a multiplicação do primeiro pelo segundo fazendo somas
    public static int multiplicaçãoRec(int a, int b){
        int resp = 0;
        if(b > 0) resp = a + multiplicaçãoRec(a, b-1);

        return resp;
    }

    //Exercício 04
    // Faça um método recursivo que receba um array de números inteiros e um número inteiro n indicando o tamanho do array e retorne o maior elemento
    public static int maiorRec(int vet[], int n, int i){
        int resp;
        if (i == n - 1) resp = vet[n - 1];
        else{
            resp = maiorRec(vet, n, i + 1);
            if (resp < vet[i]){
                resp = vet[i];
            }
        }
        return resp;
    }

    //Exercício 05
    // Faça um método recursivo que receba um array de caracteres e retorne um valor booleano indicando se esse é um palíndromo
    public static boolean isPalindromo(String str, int i){
        boolean resp;
        if(i >= str.length()/2) resp = true;
        else if(str.charAt(i) != str.charAt(str.length()-1-i)) resp = isPalindromo(str, i+1);
        else resp = isPalindromo(str, i+1);

        return resp;
    }

    //Exercício 06
    // Faça um método recursivo que receba um array de caracteres e retorne um número inteiro indicando a quantidade de vogais do mesmo
   public static boolean isUpper(char c){ return (c >= 'a' && c <= 'z') ? true : false ; }
   
    public static int contMaiusculoRec(String s, int i){
        int cont = 0;
        if (i < s.length()){
            if (isUpper(s.charAt(i)) == true) cont++;
            cont += contMaiusculoRec(s, i + 1);
        }
        return cont;
    }

    //Exercício 07
    // Faça um método recursivo que receba um string e retorne um número  inteiro indicando a quantidade de caracteres NOT vogal AND NOT  consoante maiúscula da string recebida como parâmetro
    public static int countNotLetra(String str, int i){
        int count = 0;
        if(i < str.length()){
            if((str.charAt(i) > 64 && str.charAt(i) < 91) || (str.charAt(i) > 96 && str.charAt(i) < 123)) count++;
            count += countNotLetra(str, i + 1);
        }
        return count;
    }
    //Exercício 08
    // Faça um método recursivo que receba um array de inteiros e os ordene
    public static void ordenaArray(int[] array, int i, int pos){
        int aux = 0;

        while(i < array.length){
            if(array[pos] < array[pos - 1]){
                aux = array[pos];
                array[pos] = array[pos - 1];
                array[pos - 1] = aux;
            }
        }
    }

    //Exercício 09
    // Faça um método recursivo para cada um dos problemas abaixo
    public static int funcao01(int n){
        if(n == 0) return 1;
        else if(n == 1) return 2;
        else return funcao01(n - 1) * funcao01(n - 2) - funcao01(n - 1);
    }

    public static int funcao02(int n){
        if(n == 0) return 1;
        else return funcao02(n - 1) * funcao02(n - 1);
    }
    public static void main(String[] args){

        //
    }
}