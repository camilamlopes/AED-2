public class Exercicio_Matriz{

    //01 - Faça um programa que leia os elementos de uma matriz com L linhas e C colunas e mostre na tela os elementos da matriz em formato de grid
    public static void ex01(){
        int l = MyIO.readInt(),
            c = MyIO.readInt();
        int[][] matriz = new int[l][c];

        for(int i = 0; i < l; i++){
            for(int j = 0; j < c; j++){
                matriz[i][j] = MyIO.readInt();
            }
        }

        for(int i = 0; i < l; i++){
            for(int j = 0; j < c; j++){
                MyIO.print(matriz[i][j] + " ");
            }
            MyIO.println("");
        }
    }

    //02 - Faça um programa que leia os elementos de uma matriz com L linhas e C colunas e mostre na tela os elementos da matriz Transposta
    public static void ex02(){
        int l = MyIO.readInt(),
            c = MyIO.readInt();
        int[][] matriz = new int[l][c],
                trans = new int[c][l];

        for(int i = 0; i < l; i++){
            for(int j = 0; j < c; j++){
                matriz[i][j] = MyIO.readInt();
                trans[j][i] = matriz[i][j];
            }
        }

        for(int i = 0; i < l; i++){
            for(int j = 0; j < c; j++){
                MyIO.print(matriz[i][j] + " ");
            }
            MyIO.println("");
        }

        for(int i = 0; i < c; i++){
            for(int j = 0; j < l; j++){
                MyIO.print(trans[i][j] + " ");
            }
            MyIO.println("");
        }
    }

    //03 - Faça um programa que leia duas matrizes com os mesmos números de linhas e colunas, faça a soma das mesmas e imprima na tela a matriz resultante
    public static void ex03(){
        int l = MyIO.readInt(),
            c = MyIO.readInt();
        int[][] matriz_1 = new int[l][c],
                matriz_2 = new int[l][c];

        for(int i = 0; i < l; i++){
            for(int j = 0; j < c; j++){
                matriz_1[i][j] = MyIO.readInt();
            }
        }
        
        for(int i = 0; i < l; i++){
            for(int j = 0; j < c; j++){
                matriz_2[i][j] = MyIO.readInt();
            }
        }

        for(int i = 0; i < l; i++){
            for(int j = 0; j < c; j++){
                MyIO.print((matriz_1[i][j] + matriz_2[i][j]) + " ");
            }
            MyIO.println("");
        }
    }

    //04 - Faça um programa que leia os elementos de uma matriz quadrada com N linhas e N colunas e mostre as diagonais principal e secundária
    public static void ex04(){
        int x = MyIO.readInt();
        int[][] matriz = new int[x][x];

        for(int i = 0; i < x; i++){
            for(int j = 0; j < x; j++){
                matriz[i][j] = MyIO.readInt();
            }
        } 


        MyIO.print("\nDiagonal principal: ");
        for(int i = 0; i < x; i++){
            MyIO.print(matriz[i][i] + " ");
        }
        MyIO.println("");

        MyIO.print("\nDiagonal secundaria: ");
        for(int i = 0; i < x; i++){
            MyIO.print(matriz[i][x - i - 1] + " ");
        }
        MyIO.println("");
    }
        
    //05 - Faça um programa que leia os elementos de uma matriz e mostre a média dos elementos
    public static void ex05(){
        int l = MyIO.readInt(),
            c = MyIO.readInt();
        float media = 0;

        for(int i = 0; i < l; i++){
            for(int j = 0; j < c; j++){
                media += MyIO.readFloat();
            }
        }
        media /= (l*c);

        MyIO.println("A media e: " + media);        
    }

    //06 - Faça um programa que leia os elementos de uma matriz e mostre a média dos elementos de cada linha
    public static void ex06(){
        int l = MyIO.readInt(),
            c = MyIO.readInt();
        float[] media = new float[l];

        for(int i = 0; i < l; i++){
            for(int j = 0; j < c; j++){
                media[i] += MyIO.readFloat();
            }
            media[i] /= c;
        }

        for(int i = 0; i < l; i++){
            MyIO.println("A media da linha " + i + ": " + media[i]);
        }
    }

    //07 - Faça um programa que leia os elementos de uma matriz e mostre a média dos elementos de cada coluna
    public static void ex07(){
        int l = MyIO.readInt(),
            c = MyIO.readInt();
        float[] media = new float[l];

        for(int i = 0; i < l; i++){
            for(int j = 0; j < c; j++){
                media[j] += MyIO.readFloat();
            }
        }

        for(int i = 0; i < c; i++){
            MyIO.println("A media da linha " + i + ": " + (media[i]/c));
        }
    }
    public static void main(String[] args){
        //ex01();
        //ex02();
        //ex03();
        //ex04();
        //ex05();
        //ex06();
        ex07();
    }
} 