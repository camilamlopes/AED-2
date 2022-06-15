
public class Exercicio_Array{

    public static boolean isIn(int[] array, int num){
        boolean resp = false;
        for(int i = 0; i < array.length; i++) if(array[i] == num) resp = true;
        return resp;
    }

    // 01 - Faça um programa que leia n números inteiros, calcule a média desses valores e mostre aqueles que forem maiores que a média
    public static void ex01(){
        int n = MyIO.readInt(), 
            media = 0;
        int[] num = new int[n];

        for(int i = 0; i < n; i++){
            num[i] = MyIO.readInt();
            media += num[i];
        }
        media /= n;
        MyIO.print("A media e: " + media + "\nOs numeros maiores que a media sao: ");
        for(int i = 0; i < n; i++){
            if(num[i] > media) MyIO.print(num[i] + " ");
        }
    }

    // 02 - Faça um programa que leia n números e mostre a soma do i-ésimo com o (2*i+1)-ésimo termo até que (2*i+1) < n
    public static void ex02(){
        int n = MyIO.readInt(),
            j = 0;
        int[] num = new int[n];

        for(int i = 0; i < n; i++){
            num[i] = MyIO.readInt();
        }

        while(2 * j + 1 < n){
           MyIO.print((num[j] + num[2 * j + 1]) + " ");
           j++;
        }
    }

    // 03 - Faça um programa que leia os elementos de um array de tamanho n e mostre a posição do menor elemento do array
    public static void ex03(){
        int n = MyIO.readInt(),
            j = 0;
        int[] num = new int[n];

        for(int i = 0; i < n; i++){
            num[i] = MyIO.readInt();
        }

        for(int i = 0; i < n; i++){
            if(num[i] < num[j]) j = i;
        }

        MyIO.print("O menor numero esta na posicao: " + j + "\n");
    }

    // 04 - Faça um programa que receba um array e ordene os elementos desse array
    public static void ex04(){
        int n = MyIO.readInt();
        int[] num = new int[n];

        for(int i = 0; i < n; i++){
            num[i] = MyIO.readInt();
        }

        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                int tmp = 0;
                if(num[i] > num[j]){
                    tmp = num[i];
                    num[i] = num[j];
                    num[j] = tmp;
                }
            }
        }

        for(int i = 0; i < n; i++){
            MyIO.print("[" + num[i] + "] ");
        }
    }

    // 05 - Faça um programa que leia dois Introdução ao Java de int e mostre a união e a intercessão entre os elementos desses arrays
    public static void ex05(){    
        int n = MyIO.readInt(),
            pos = 0;
        int[] intJava_01 = new int[n],
              intJava_02 = new int[n],
              union = new int[n*2],
              inter = new int[n*2];

        MyIO.println("Insira o primeiro array: ");
        for(int i = 0; i < n; i++){
            intJava_01[i] = MyIO.readInt();
        }
        MyIO.println("Insira o segundo array: ");
        for(int i = 0; i < n; i++){
            intJava_02[i] = MyIO.readInt();
        }

        MyIO.print("\nUniao: ");
        for(int element : intJava_01){
            union[pos] = element;
            pos++;
        }

        for(int element : intJava_02){
            if(!isIn(union, element)){
                union[pos] = element;
                pos++;
            }
        }

        for(int i = 0; i < pos; i++){
            MyIO.print("[" + union[i] + "] ");
        }

        MyIO.print("\nIntersecao: ");
        pos = 0;
        for(int element_1 : intJava_01){
            for(int element_2 : intJava_02){
                if(element_1 == element_2 && !isIn(inter, element_2)){
                    inter[pos] = element_2;
                    pos++;
                }
            }
        }

        for(int i = 0; i < pos; i++){
            MyIO.print("[" + inter[i] + "] ");
        }
        
    }

    // 06 - Declare um vetor com contendo os elementos 10, 5, 8, 2 e 8. Em seguida, mostre os elementos contidos no array
    public static void ex06(){
        int[] vetor = {10, 5, 8, 2, 8};
        for(int i = 0; i < vetor.length; i++){
            MyIO.println(vetor[i]);
        }
    }

    // 07 - Faça um programa para ler a nota de cinco alunos, calcular e mostrar: a soma e a média das mesmas e a menor nota
    public static void ex07(){
        float[] nota = new float[5];
        for(int i = 0; i < 5; i++){
            nota[i] = MyIO.readFloat();
        }

        float soma = 0, menor = nota[0];

        for(int i = 0; i < 5; i++){
            soma += nota[i];
            if(nota[i] < menor) menor = nota[i];
        }

        MyIO.println("Soma = " + soma + "\nMedia = " + (soma/5) + "\nMenor = " + menor);
    }

    // 08 - Faça um programa para ler N números inteiros, calcular a média deles e mostrar aqueles que forem maiores que a média
    public static void ex08(){
        int n = MyIO.readInt(),
            media = 0;
        int[] array = new int[n];
        for(int i = 0; i < n; i++){
            array[i] = MyIO.readInt();
            media += array[i];
        }
        media /= n;

        MyIO.println("\nNumeros maiores que a media: ");
        for(int i = 0; i < n; i++){
            if(array[i] > media){
                MyIO.println(array[i]);
            }
        }
        
    }

    // 09 - Faça um programa para ler um número inteiro N e N elementos de um array. Em seguida, se N for par mostrar na tela a soma do 1o e 2o elemento, 3o e 4o, ...
    public static void ex09(){
        int n = MyIO.readInt();
        int[] array = new int[n];
        for(int i = 0; i < n; i++){
            array[i] = MyIO.readInt();            
        }

        if(n % 2 == 0){
            for(int i = 0; i < n; i+=2) MyIO.println(array[i] + " + " + array[i+1] + " = " + (array[i] + array[i+1]));
        }
    }

    // 10 - Faça um programa para ler um número inteiro N e N elementos de um array. Em seguida, encontre a posição do menor elemento
    public static void ex10(){
        int n = MyIO.readInt();
        int[] array = new int[n];
        for(int i = 0; i < n; i++){
            array[i] = MyIO.readInt();            
        }

        int menor = 0;
        for(int i = 1; i < n; i++){
            if(array[i] < array[menor]) menor = i;
        }
    }

    // 11 - Faça um programa para ler um número inteiro N e N elementos de um array. Em seguida, calcular e mostrar o número de elementos pares e o de divisíveis por três.
    public static void ex11(){
        int n = MyIO.readInt();
        int pares = 0, tres = 0;
        int[] array = new int[n];
        for(int i = 0; i < n; i++){
            array[i] = MyIO.readInt();   
            if(array[i] % 2 == 0) pares++;
            if(array[i] % 3 == 0) tres++;         
        }
        MyIO.println("Pares: " + pares + "\nDividido por tres: " + tres);        
    }
   
    //12 - Faça um programa que leia N números inteiros e mostre na tela a soma do primeiro e do último, a do segundo e do penúltimo, a do terceiro e do antepenúltimo, ...
    public static void ex12(){
        int n = MyIO.readInt();
        int[] array = new int[n];
        for(int i = 0; i < n; i++){
            array[i] = MyIO.readInt();       
        }
       
        for(int i = 0; i < n/2; i++){
            MyIO.println(array[i] + " + " + array[n - i - 1] + " = " + (array[i] + array[n - i - 1]));
        }        
    }

    // 13 - Faça um programa que leia N números inteiros e mostre na tela a soma daqueles que forem múltiplos de três
    public static void ex13(){
        int n = MyIO.readInt();
        int[] array = new int[n];
        for(int i = 0; i < n; i++){
            array[i] = MyIO.readInt();       
        }

        int soma = 0;
        for(int i = 0; i < n; i++){
            if(array[i] % 3 == 0) soma += array[i];
        }   
        MyIO.println("A soma dos multiplos de tres: " + soma);
    }


    // 14 - Faça um programa para ler dois vetores contendo N elementos cada. Em seguida, mostre os elementos de forma intercalada
    public static void ex14(){
        int n = MyIO.readInt();
        int[] vetor_1 = new int[n],
              vetor_2 = new int[n],
              vetor_i = new int[n*2];
        for(int i = 0; i < n; i++){
            vetor_1[i] = MyIO.readInt();       
        }
        for(int i = 0; i < n; i++){
            vetor_2[i] = MyIO.readInt();       
        }
    }
 
    // 15 - Faça um programa para ler um vetor contendo N elementos e outro contendo M elementos. Em seguida, mostre os elementos de forma intercalada
    public static void ex15(){
        int n = MyIO.readInt(),
            m = MyIO.readInt();
        int[] vetor_1 = new int[n],
              vetor_2 = new int[m],
              vetor_i = new int[n+m];
        for(int i = 0; i < n; i++){
            vetor_1[i] = MyIO.readInt();       
        }
        for(int i = 0; i < m; i++){
            vetor_2[i] = MyIO.readInt();       
        }
    }

    public static void main (String[] args){
        //ex01();
        //ex02();
        //ex03();
        //ex04();
        //ex05();
        //ex06();
        //ex07();
        //ex08();
        //ex09();
        //ex10();
        //ex11();
        //ex12();
        //ex13();
        //ex14();
        //ex15();


    }
}