/* EXERCÍCIOS DE ESTRUTURA DE REPETIÇÃO*/
public class ex02 {
    /* EXERCÍCIOS DE WHILE */
    //exercício 01
    //Faça um programa que leia a nota de 5 alunos e mostre na tela a média das mesmas    
    public static void ex01(){
        float soma = 0;
        int i = 0;
        
        while(i < 5){
            soma += MyIO.readFloat();
            i++;
        }
        System.out.println("A média de notas dos 5 alunos é: " + soma/5);
    }
    
    //exercício 02
    //Faça um programa que mostre os 10 primeiros números inteiros positivos.
    public static void inteiros_10(){
        int pos = 0;
        while(pos < 10) System.out.println(pos++);
    }

    //exercício 03
    //Faça um programa que leia um número inteiro N e mostre na tela os N  primeiros números inteiros ímpares.
    public static void ex03(){
        int n = MyIO.readInt(),
            pos = 0;
        while(n > 0){
            System.out.println(pos++);
            n--;
        }
    }

    //exercício 04
    //Faça um programa que leia um número inteiro N e mostre na tela os N primeiros números da sequência 1, 5, 12, 16, 23, 27 34.
    public static void ex04(){
        /*
            1, 5, 12, 16, 23, 27 34
            1, 4,  7,  4,  7,  4, 7   
        */
        int n = MyIO.readInt(),
            pos = 1;
        while(n > 0){
            System.out.println(pos);
            if((n % 2) == 0) pos += 7;
            else pos += 4;
            n--;
        }
    }

    /*
    Faça um programa que leia um número inteiro N indicando a nota máxima  em uma prova P. 
    Em seguida, leia a nota de 20 alunos (entre 0 e N) e  mostre na tela: 
        (i) a média da turma, 
        (ii) o número de  alunos cuja nota foi  menor que a média da Universidade (suponha 60%)
        (iii) a quantidade de alunos com conceito A (mais de 90%).
    */
    public static void notaTurma(){
        System.out.print("Insira a nota máxima: ");
        int nMax = MyIO.readInt(), pos = 0;
        float countM = 0,
              countA = 0,
              media  = 0,
              nota = 0;

        while(pos++ < 20){
            System.out.print("Insira a nota do aluno " + pos + ": ");
            nota = MyIO.readFloat();
            if(nota < nMax*0.6) countM++;
            else if(nota >= nMax*0.9) countA++;
            media += nota;
        }

        System.out.println("Quantidade de alunos abaixo da média: " + countM);
        System.out.println("Quantidade de alunos com notas A: " + countA);
        System.out.println("Média da turma: " + media/20);
    }

    public static void main(String[] argv){
        //ex01();
        //inteiros_10();
        //ex03();
        //ex03();
        //ex04();
        notaTurma();
    }
}
