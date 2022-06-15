/* EXERCÍCIOS SOBRE MATRIZ */
public class ex04 {
    //Exercício 01
    // Faça um programa que leia os elementos de uma matriz com L linhas e C colunas e mostre na tela os elementos da matriz em formato de grid
    public static void printMatriz(){
        System.out.println("Insire os valores da linha e da coluna: ");
        int L = MyIO.readInt(),
            C = MyIO.readInt();
        double[][] matriz = new double[L][C];

        for(int i = 0; i < L; i++){
            for(int j = 0; j < C; j++){
                System.out.print("Insira o valor da posição [" + (i+1) + "][" + (j+1) + "] : ");
                matriz[i][j] = MyIO.readDouble();
            }
        }
        System.out.println();

        for(int i = 0; i < L; i++){
            for(int j = 0; j < C; j++){
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    //Exercício 02
    // Faça um programa que leia os elementos de uma matriz com L linhas e C colunas e mostre na tela os elementos da matriz Transposta
    public static void transposta(){
        System.out.println("Insire os valores da linha e da coluna: ");
        int L = MyIO.readInt(),
            C = MyIO.readInt();
        double[][] matriz = new double[L][C],
                   transp = new double[C][L];

        for(int i = 0; i < L; i++){
            for(int j = 0; j < C; j++){
                System.out.print("Insira o valor da posição [" + (i+1) + "][" + (j+1) + "] : ");
                matriz[i][j] = MyIO.readDouble();
            }
        }
        System.out.println("\nMATRIZ ORIGINAL");
        for(int i = 0; i < L; i++){
            for(int j = 0; j < C; j++){
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println("\n");
        }

        for(int i = 0; i < C; i++){ for(int j = 0; j < L; j++){ transp[i][j] = matriz[j][i]; } }
        System.out.println("\nMATRIZ TRANSPOSTA");
        for(int i = 0; i < C; i++){
            for(int j = 0; j < L; j++){
                System.out.print(transp[i][j] + "\t");
            }
            System.out.println("\n");
        }
    }

    //Exercício 03
    // Faça um programa que leia duas matrizes com os mesmos números de linhas e colunas, faça a soma das mesmas e imprima na tela a matriz resultante
    public static void soma(){
        System.out.println("Insire os valores da linha e da coluna: ");
        int L = MyIO.readInt(),
            C = MyIO.readInt();
        double[][] matriz = new double[L][C];

        System.out.println("LEITURA DA MATRIZ 1");
        for(int i = 0; i < L; i++){
            for(int j = 0; j < C; j++){
                System.out.print("Insira o valor da posição [" + (i+1) + "][" + (j+1) + "] : ");
                matriz[i][j] = MyIO.readDouble();
            }
        }

        System.out.println("\nLEITURA DA MATRIZ 2");
        for(int i = 0; i < L; i++){
            for(int j = 0; j < C; j++){
                System.out.print("Insira o valor da posição [" + (i+1) + "][" + (j+1) + "] : ");
                matriz[i][j] += MyIO.readDouble();
            }
        }

        System.out.println("\nSOMA DAS MATRIZES");
        for(int i = 0; i < L; i++){
            for(int j = 0; j < C; j++){
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println("\n");
        }
        
    }

    //Exercício 04
    // Faça um programa que leia os elementos de uma matriz quadrada com N linhas e N colunas e mostre as diagonais principal e secundária
    public static void diagonais(){
        System.out.println("Insire os valores da linha e da coluna: ");
        int N = MyIO.readInt(),
            posP = 0,
            posS = 0;
        double[][] matriz = new double[N][N];
        double[] principal = new double[N],
                 secundaria = new double[N];

        System.out.println("LEITURA DA MATRIZ");
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                System.out.print("Insira o valor da posição [" + (i+1) + "][" + (j+1) + "] : ");
                matriz[i][j] = MyIO.readDouble();
                if(i == j){
                    principal[posP++] = matriz[i][j];
                    secundaria[posS++] = matriz[i][N - posS]; 
                }
            }
        }

        System.out.println("\nESCRITA DA MATRIZ");
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println("\n");
        }

        System.out.println("\nESCRITA DIAGONAL PRINCIPAL");
        for(int i = 0; i < N; i++){
            System.out.print(principal[i] + "\t");
        }
        System.out.println("\nESCRITA DA MATRIZ");
        for(int i = 0; i < N; i++){
            System.out.print(secundaria[i] + "\t");
        }
        
    }

    //Exercício 05
    // Faça um programa que leia os elementos de uma matriz e mostre a média dos elementos
    public static void media(){
        System.out.println("Insire os valores da linha e da coluna: ");
        int L = MyIO.readInt(),
            C = MyIO.readInt();
        double[][] matriz = new double[L][C];
        double media = 0;

        System.out.println("LEITURA DA MATRIZ");
        for(int i = 0; i < L; i++){
            for(int j = 0; j < C; j++){
                System.out.print("Insira o valor da posição [" + (i+1) + "][" + (j+1) + "] : ");
                matriz[i][j] = MyIO.readDouble();
                media += matriz[i][j];
            }
        }
        System.out.println("\nA média é: " + media/(L*C));
    }
    
    //Exercício 06
    // Faça um programa que leia os elementos de uma matriz e mostre a média dos elementos de cada linha
    public static void mediaLinha(){
        System.out.println("Insire os valores da linha e da coluna: ");
        int L = MyIO.readInt(),
            C = MyIO.readInt();
        double[][] matriz = new double[L][C];
        double[] media = new double[L];

        System.out.println("LEITURA DA MATRIZ");
        for(int i = 0; i < L; i++){
            for(int j = 0; j < C; j++){
                System.out.print("Insira o valor da posição [" + (i+1) + "][" + (j+1) + "] : ");
                matriz[i][j] = MyIO.readDouble();
                media[i] += matriz[i][j];
            }
        }
        for(int i = 0; i < L; i++){
            System.out.println("\nA média da linha "+ (i+1) + " é: " + media[i]/C);
        }    
    }

    //Exercício 07
    // Faça um programa que leia os elementos de uma matriz e mostre a média dos elementos de cada coluna
    public static void mediaColuna(){
        System.out.println("Insire os valores da linha e da coluna: ");
        int L = MyIO.readInt(),
            C = MyIO.readInt();
        double[][] matriz = new double[L][C];
        double[] media = new double[C];

        System.out.println("LEITURA DA MATRIZ");
        for(int i = 0; i < L; i++){
            for(int j = 0; j < C; j++){
                System.out.print("Insira o valor da posição [" + (i+1) + "][" + (j+1) + "] : ");
                matriz[i][j] = MyIO.readDouble();
                media[j] += matriz[i][j];
            }
        }
        for(int i = 0; i < C; i++){
            System.out.println("\nA média da coluna "+ (i+1) + " é: " + media[i]/C);
        }    
    }
    public static void main(String[] argv){
        //printMatriz();
        //transposta();
        //soma();
        //diagonais();
        //media();
        //mediaLinha();
        mediaColuna();

    }
}
