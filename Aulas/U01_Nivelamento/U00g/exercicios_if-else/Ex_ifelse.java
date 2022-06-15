
public class Ex_ifelse{
    // 01 - Faça um programa que leia três números reais representando os lados de um triângulo e informe se seu triângulo é Equilátero, Isósceles ou Escaleno
    public static void ex01(){
        int a = MyIO.readInt(),
            b = MyIO.readInt(),
            c = MyIO.readInt();

        if(a == b && a == c && b == c) MyIO.println("Um triângulo equilatero");
        else if(a == b || a == c || b == c) MyIO.println("Um triangulo isosceles");
        else MyIO.println("Um triangulo escaleno");
    }

    // 02 - Leia 3 números inteiros, selecione o menor e o maior e imprima os seus respectivos valores na tela.
    public static void ex02(){
        int a = MyIO.readInt(),
            b = MyIO.readInt(),
            c = MyIO.readInt();

        if(a > b){
            if(b > c){
                 MyIO.println("Maior: " + a + "\nMenor: " + c);
            } else if(c > a){
                MyIO.println("Maior: " + c + "\nMenor: " + b);
            } else{
                MyIO.println("Maior: " + a + "\nMenor: " + b);
            }
        } else if(c > b){
            MyIO.println("Maior: " + c + "\nMenor: " + a);
        } else if(c > a){
            MyIO.println("Maior: " + b + "\nMenor: " + a);
        } else{
            MyIO.println("Maior: " + b + "\nMenor: " + c);
        }
    }

     /*03 - Leia dois números. Se um deles for maior que 45, realize a soma dos mesmos. 
      Caso contrário, se os dois forem maior que 20, realize a subtração do maior pelo menor, 
      senão, se um deles for menor do que 10 e o outro diferente de 0 realize a divisão do primeiro pelo segundo. 
      Finalmente, se nenhum dos casos solicitados for válido, mostre seu nome na tela.*/
     public static void ex03(){
        int a = MyIO.readInt(),
            b = MyIO.readInt();
        
        if(a > 45 || b > 45){
            MyIO.println((a + b));
        } else if(a > 20 && b > 20){
            if(a > b){ 
                MyIO.println((a + b));
            }
            else{ MyIO.println((b + a)); }
        } else if(a < 10 && b != 0 || b < 10 && a != 0){
            if(a < 10){
                MyIO.println((a / b));
            } else {MyIO.println((b / a));}

        } else MyIO.println("Camila Moreira Lopes");

    }

    // 04 - Leia 10 números inteiros, selecione o maior e imprima seu valor na tela.
    public static void ex04(){
        int maior = MyIO.readInt();
        int num;
        for(int i = 0; i < 9; i++){
            num = MyIO.readInt();
            if(maior < num) maior = num;
        }

        MyIO.println(maior);
    }

    //05 - Seja uma partida de futebol, leia os números de gols do mandante e do visitante e imprima quem foi o vencedor ou se foi empate.
    public static void ex05(){
        int gols_M = MyIO.readInt(),
            gols_V = MyIO.readInt();
        if(gols_M > gols_V){
            MyIO.println("O mandante foi o vencedor");
        } else if(gols_V > gols_M){
            MyIO.println("O visitor foi o vencedor");
        } else MyIO.println("O jogo terminou empatado");
    }

    /* 06 - O banco do Zé abriu uma linha de crédito para os seus clientes. 
    O valor máximo da prestação não poderá ultrapassar 40% do salário bruto. 
    Fazer um algoritmo que permita entrar com o salário bruto e o valor da prestação e informar se o empréstimo será concedido.*/
    public static void ex06(){
        float salario = MyIO.readFloat(),
              emprestimo = MyIO.readFloat();
        
        if(emprestimo / salario > 0.4) MyIO.println("O EMPRESTIMO NÃO SERÁ CONCEDIDO");
        else MyIO.println("O EMPRESTIMO NÃO SERÁ CONCEDIDO");
    }

    // 07 - Leia 10 números inteiros, selecione o menor e o maior e imprima os seus respectivos valores na tela.
    public static void ex07(){
        int maior = MyIO.readInt(),
            menor = maior;
        int num;
        for(int i = 0; i < 9; i++){
            num = MyIO.readInt();
            if(maior < num) maior = num;
            else if(menor > num) menor = num;
        }

        MyIO.println("Maior: " + maior + "\nMenor: " + menor);
    }
    public static void main (String[] args){
        //ex01();
    }
}