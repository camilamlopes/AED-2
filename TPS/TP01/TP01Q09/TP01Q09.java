import java.io.*;

public class TP01Q09{
    /* MÉTODO DE LEITURA E ESCRITA DO ARQUIVO ALEATÓRIO */
    public static void leituraArq(int n) throws IOException{
        RandomAccessFile arq = new RandomAccessFile("exemplo.txt", "rw"); // abre o arquivo
        Double num = 0.0;

        for(int i = 0; i < n; i++){
            num = MyIO.readDouble();
            arq.writeDouble(num); // escreve no arquivo
        }

        arq.close(); // fecha o arquivo
    }

    /* MÉTODO DE ESCRITA DO ARQUIVO */
    public static void escritaArq(int n) throws IOException{
        RandomAccessFile arq = new RandomAccessFile("exemplo.txt", "rw"); // abertura arquivo novo
        double num = 0.0;
        
        for(int i = n - 1; i >= 0; i--){
            arq.seek(i*8);
            num = arq.readDouble();

            if(num != (int) num) MyIO.println(num);
            else MyIO.println((int)num);
        }

        arq.close(); // fecha arquivo
    }

    public static void main(String[] args) throws IOException{
        int numN = 0;
        numN = MyIO.readInt();
        leituraArq(numN);
        escritaArq(numN);
    }
}

/*
Faça um programa que leia um número inteiro n indicando o número de valores reais que devem ser lidos 
e salvos sequencialmente em um arquivo texto. 

* Após a leitura dos valores, devemos fechar o arquivo. 

* Em seguida, reabri-lo e fazer a leitura de trás para frente usando os métodos getFilePointer e seek da classe RandomAccessFile e mostre todos os valores lidos na tela. 

* Nessa questão, você não pode usar, arrays, strings ou qualquer estrutura de dados.

* A entrada padrão é composta por um número inteiro n e mais n números reais. 

* A saída padrão corresponde a n números reais mostrados um por linha de saída.
*/