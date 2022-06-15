/* EXERCÍCIOS DE ARRAY */
public class ex03 {
    //Exercício 01
    // Faça um programa que leia n números e mostre quais deles são maiores que a média
    public static void maiorMedia(){
        System.out.print("Insira a quantidade de números a serem lidos: ");
        int n = MyIO.readInt(),
            media = 0;
        int[] num = new int[n];

        for(int i = 0; i < n; i++){
            System.out.print("Insira um número: ");
            num[i] = MyIO.readInt();
            media += num[i];
        }
        media /= n;

        for(int i = 0; i < n; i++){
            if(num[i] > media) System.out.println("O número " + num[i] + " é maior que a média");
        }
        
    }

    //Exercício 02
    // Faça um programa que leia n números e mostre a soma do i-ésimo com o (2*i+1)-ésimo termo até que (2*i+1) < n
    public static void somaI(){
        System.out.print("Insira a quantidade de números: ");
        int n = MyIO.readInt(),
            soma = 0;
        int[] num = new int[n];

        for(int i = 0; i < n; i++){
            System.out.print("Insiara um valor: ");
            num[i] = MyIO.readInt();
        }

        for(int i = 0; i*2+1 < n; i++){
            soma = num[i] + num[i*2+1];
            System.out.println("O valor final: "+ soma);
        }
    }

    //Exercício 03
    // Faça um programa que leia os elementos de um array de tamanho n e mostre o maior elemento do array
    public static void maior(){
        int n = MyIO.readInt(),
        maior = 0;
        int[] array = new int[n];

        for(int i = 0; i < n; i++){
            System.out.print("Insira o número da posição " + (i+1) + ": ");
            array[i] = MyIO.readInt();
            if(array[i] > maior) maior = array[i];
        }

        System.out.println("O maior número é: " + maior);
    }

    //Exercício 04
    // Faça um programa que leia os elementos de um array de tamanho n e mostre a posição do menor elemento do array
    public static void menor(){
        int n = MyIO.readInt(),
        menor = 0;
        int[] array = new int[n];

        for(int i = 0; i < n; i++){
            System.out.print("Insira o número da posição " + (i+1) + ": ");
            array[i] = MyIO.readInt();
            if(array[i] < menor) menor = i;
        }

        System.out.println("O menor número está na posição: " + menor);
    }

    //Exercício 05
    // Faça um programa que receba um array e ordene os elementos desse array
    public static void ordena(){

    }
    
    //Exercício 07
    // Declare um vetor com contendo os elementos 10, 5, 8, 2 e 8. Em seguida, mostre os elementos contidos no array
    public static void sequenciaA(){
        int[] elementos = {10, 5, 8, 2, 8};
        for(int i = 0; i < elementos.length; i++){
            System.out.println(elementos[i] + "\t");
        }
    }
    
    //Exercício 08
    // Faça um programa para ler a nota de cinco alunos, calcular e mostrar: a soma e a média das mesmas e a menor nota
    public static void nota(){
        float[] notas = new float[5];
        float soma = 0,
              menor = 9999;
        
        for(int i = 0; i < 5; i++){
            notas[i] = MyIO.readFloat();
            soma += notas[i];
            if(notas[i] < menor) menor = notas[i];
        }
        
        System.out.println("A soma das notas: " + soma);
        System.out.println("A média das notas: " + soma/5);
        System.out.println("A menor nota: " + menor);
    }
    
    //Exercício 09
    // Faça um programa para ler um número inteiro N e N elementos de um array. Em seguida, se N for par mostrar na tela a soma do 1o e 2o elemento, 3o e 4o
    public static void soma(){
        System.out.print("Insira a quantidade de elementos a serem lidos: ");
        int N = MyIO.readInt(),
            aux = 0;
        int[] array = new int[N];
        
        for(int i = 0; i < N; i++) array[i] = MyIO.readInt();
        for(int i = 0; i < N; i++) {
            aux = i+1;
            System.out.println(array[i] + " + " + array[aux] + " = " + (array[i] + array[aux]));
            i++;
        }
    }

    //Exercício 10
    //Faça um programa para ler um número inteiro N e N elementos de um array. Em seguida, calcular e mostrar o número de elementos pares e o de divisíveis por três.
    public static void parTres(){
        System.out.print("Insira a quantidade de elementos a serem lidos: ");
        int N = MyIO.readInt();
        int[] array = new int[N];

        for(int i = 0; i < N; i++) array[i] = MyIO.readInt();

        System.out.println();
        for(int i = 0; i < N; i++){
            if(array[i] % 6 == 0) System.out.println("O número " + array[i] + " é par e divisível por 3!");
            else if(array[i] % 2 == 0) System.out.println("O número " + array[i] + " é par!");
            else if(array[i] % 3 == 0) System.out.println("O número " + array[i] + " é divisível por 3!");
        }
    }

    //Exercício 11
    // Faça um programa que leia N números inteiros e mostre na tela a soma do primeiro e do último, a do segundo e do penúltimo, a do terceiro e do antepenúltimo, ...
    public static void somaPrimeiroUltimo(){
        System.out.print("Insira a quantidade de elementos a serem lidos: ");
        int N = MyIO.readInt();
        int[] array = new int[N];
        
        for(int i = 0; i < N; i++) array[i] = MyIO.readInt();
        for(int i = 0; i < N/2; i++) System.out.println(array[i] + " + " + array[N - 1 - i] + " = " + (array[i] + array[N - 1 - i]));
        
    }
    
    //Exercício 12
    // Faça um programa que leia N números inteiros e mostre na tela a soma daqueles que forem múltiplos de três
    public static void somaTres(){
        System.out.print("Insira a quantidade de elementos a serem lidos: ");
        int N = MyIO.readInt(),
            aux = 0, i = 0;
        int[] array = new int[N];
        
        for(i = 0; i < N; i++) array[i] = MyIO.readInt();
        for(i = 0; i < N - 1; i++){
            if(array[i] % 3 == 0){
                aux += array[i];
                System.out.print(array[i] + " + ");
            }
        }

        if(array[i] % 3 == 0) System.out.print(array[i] + " = " + (aux + array[i]));
        else System.out.print("0 = " + (aux + array[i]));
    }

    public static void main(String[] argv){
        //maiorMedia();
        //somaI();
        //maior();
        //menor();
        //sequenciaA();
        //nota();
        //soma();
        //parTres();
        //somaPrimeiroUltimo();
        somaTres();
    }
}
