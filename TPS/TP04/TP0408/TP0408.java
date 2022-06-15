/**
 * Árvore Binária de Pesquisa
 * @author Camila Moreira Lopes
 */

// Bibliotecas usadas 
import java.io.*;
import java.nio.charset.*;

class MyIO {

    public static int readInt() {
        return 0;
    }

    public static String readLine() {
        return null;
    }
    
}

class No {
    public int elemento;    // o conteúdo é um inteiro positivo
    public No esq, dir;     // Filhos da esquerda e da direita

    public No(int elemento) {
        this(elemento, null, null);
    }

    public No(int elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

class ArvoreBinaria {
    private No raiz;   // Raiz da Árvore

    /**
     * Construtor da classe
     */
    public ArvoreBinaria () {
		raiz = null;
	}

    /*************** MÉTODOS DE ESCRITA ***************/
    //--------- Métodos de caminhar central ---------//
    /* Metodo publico iterativo */
	public void caminharCentral() {
		System.out.print("In..: ");
		caminharCentral(raiz);
        System.out.println();
	}
	
    /**
	 * Metodo privado recursivo.
	 * @param i No em analise.
	 */
	private void caminharCentral(No i) {
		if (i != null) {
			caminharCentral(i.esq); // Elementos da esquerda.
			System.out.print(i.elemento + " "); // Conteudo do no.
			caminharCentral(i.dir); // Elementos da direita.
		}
	}

    //--------- Métodos de caminhar pre ---------//
    /* Metodo publico iterativo */
	public void caminharPre() {
		System.out.print("Pre.: ");
		caminharPre(raiz);
		System.out.println("");
	}

	/**
	 * Metodo privado recursivo.
	 * @param i No em analise.
	 */
	private void caminharPre(No i) {
		if (i != null) {
			System.out.print(i.elemento + " "); // Conteudo do no.
			caminharPre(i.esq); // Elementos da esquerda.
			caminharPre(i.dir); // Elementos da direita.
		}
	}

    //--------- Métodos de caminhar pos ---------//
    /* Metodo publico iterativo */
	public void caminharPos() {
		System.out.print("Post: ");
		caminharPos(raiz);
		System.out.println("\n");
	}

	/**
	 * Metodo privado recursivo
	 * @param i No em analise.
	 */
	private void caminharPos(No i) {
		if (i != null) {
			caminharPos(i.esq); // Elementos da esquerda.
			caminharPos(i.dir); // Elementos da direita.
			System.out.print(i.elemento + " "); // Conteudo do no.
		}
	}

    /*************** MÉTODOS DE INSERÇÃO ***************/
    /**
	 * Metodo publico iterativo para inserir elemento.
	 * @param x Elemento a ser inserido.
	 * @throws Exception Se o elemento existir.
	 */
	public void inserir(int x) throws Exception {
		raiz = inserir(x, raiz);
	}

	/**
	 * Metodo privado recursivo para inserir elemento.
	 * @param x Elemento a ser inserido.
	 * @param i No em analise.
	 * @return No em analise, alterado ou nao.
	 * @throws Exception Se o elemento existir.
	 */
	private No inserir(int x, No i) throws Exception {
        if (i == null) {
            i = new No(x);
        } else if (x < i.elemento) {
            i.esq = inserir(x, i.esq);

        } else if (x > i.elemento) {
            i.dir = inserir(x, i.dir);
        } else {
            throw new Exception("Erro ao inserir!");
        }
		return i;
	}
}

public class TP0408 {
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));
    private static String charset = "ISO-8859-1";

    public static String readString(String str) {
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.print(str);
        } catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
        return readString();
    }

    private static String readString() {
        String str = "";
        char tmp;
        try {
            do {
                tmp = (char)in.read();
                if(tmp != '\n' && tmp != ' ' && tmp != 13) {
                    str += tmp;
                }
            } while (tmp != '\n' && tmp != ' ');

            if(tmp == ' '){
                while (tmp != '\n') tmp = (char)in.read();
            }
        } catch( IOException ioe) {
            System.out.println("lerPalavra: " + ioe.getMessage());
        }

        return str;
    }

    private static String readLine() {
        String s = "";
        char tmp;
        try {
           do {
               tmp = (char)in.read();
                if(tmp != '\n' && tmp != 13){
                    s += tmp;
                }
           } while (tmp != '\n');
        } catch (IOException ioe){
            System.out.println("lerPalavra: " + ioe.getMessage());
        }
        return s;
    }
    
     public static void main (String[] args) throws Exception {       
        // Variáveis da questão
        int C = Integer.parseInt(readString().trim());
        int N, num;
        String str = new String();
        String[] tmp;
        ArvoreBinaria[] ab = new ArvoreBinaria[C];

        // Repetição para entrada
        
        for(int i = 0; i < C; i++){
            ab[i] = new ArvoreBinaria();
            N = Integer.parseInt(readString().trim());

            str = readLine();
            tmp = str.split(" ");
            for(int j = 0; j < N; j++){
                num = Integer.parseInt(tmp[j]);
                ab[i].inserir(num);
            }
            
        }

        for(int i = 0; i < C; i++){
            System.out.println("Case " + (i+1) + ":");
            ab[i].caminharPre();
            ab[i].caminharCentral();
            ab[i].caminharPos();
        }


    }
}