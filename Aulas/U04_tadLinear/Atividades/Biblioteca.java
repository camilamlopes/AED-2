import java.io.IOException;
import java.io.*;
import java.nio.charset.*;

class Ordena{
    private int qnt;
    private String[] livros;


    Ordena(){
        qnt = 0;
    }

    Ordena(int qnt, String[] livros){
        this.qnt = qnt;
        this.livros = livros;
    }

    /* ordena */
    private void quicksort(int esq, int dir){
        int i = esq, j = dir;
        int pivo = Integer.parseInt(livros[(dir + esq) / 2]);

        while(i <= j){
            while (Integer.parseInt(livros[i]) < pivo) i++;
            while (Integer.parseInt(livros[j]) > pivo) j--;
            if (i <= j){
                String tmp = livros[i];
                livros[i] = livros[j];
                livros[j] = tmp;

                i++;
                j--;
            }
        }

        if (esq < j) quicksort(esq, j);
        if (i < dir) quicksort (i, dir);
    }


    private void selecao(){
        for(int i = 0; i < qnt - 1; i++){
            int menor = i;
            for(int j = i + 1; j < qnt; j++){
                if(Integer.parseInt(livros[menor]) > Integer.parseInt(livros[j])){
                    menor = j;
                }
            }

            String tmp = livros[menor];
            livros[menor] = livros[i];
            livros[i] = tmp;
        }
    }


    private void insercao(){
        for(int i = 1; i < qnt; i++){
            String tmp = livros[i];

            int j = i - 1;
            while((j >= 0) && (Integer.parseInt(livros[j]) > Integer.parseInt(tmp))){
                livros[j + 1] = livros[j];
                j--;
            }

            livros[j + 1] = tmp;
        }
    }


    /* imprimir */
    public void imprimir(){
        quicksort(0, qnt-1);

        for(int i = 0; i < qnt; i++){
            System.out.println(livros[i]);
        }
    }
}

public class Biblioteca {
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));
        
        String qntN = "";
        String[] idLivro;
        int N;

        while((qntN = in.readLine()) != null){
            // Leitura dos ids
            N = Integer.parseInt(qntN); 
            idLivro = new String[N];

            for(int i = 0; i < N; i++){
                idLivro[i] = in.readLine();
            }

            Ordena o = new Ordena(N, idLivro);
            o.imprimir();
        }
                      
   } 
}
