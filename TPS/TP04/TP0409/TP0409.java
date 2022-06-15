/**
 * Árvore Binária de Pesquisa
 * @author Camila Moreira Lopes
 */

// Bibliotecas usadas 
import java.io.*;
import java.nio.charset.*;

class No {
    public char elemento;    // o conteúdo é um inteiro positivo
    public No esq, dir;     // Filhos da esquerda e da direita

    public No(char elemento) {
        this(elemento, null, null);
    }

    public No(char elemento, No esq, No dir) {
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

	/*************** MÉTODOS DE PESQUISA ***************/
	public boolean pesquisar(int x) {
		return pesquisar(x, raiz);
	}

	private boolean pesquisar(int x, No i) {
		boolean resp;
		if (i == null) {
			resp = false;
		} else if (x == i.elemento) {
		   	resp = true;
		} else if (x < i.elemento) {
		   	resp = pesquisar(x, i.esq);
		} else {
			resp = pesquisar(x, i.dir);
		}

		return resp;
	  }

    /*************** MÉTODOS DE ESCRITA ***************/
    //--------- Métodos de caminhar central ---------//
    /* Metodo publico iterativo */
	public void caminharCentral() {
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
		caminharPos(raiz);
		System.out.println();
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
	public void inserir(char x) throws Exception {
		raiz = inserir(x, raiz);
	}

	/**
	 * Metodo privado recursivo para inserir elemento.
	 * @param x Elemento a ser inserido.
	 * @param i No em analise.
	 * @return No em analise, alterado ou nao.
	 * @throws Exception Se o elemento existir.
	 */
	private No inserir(char x, No i) throws Exception {
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

public class TP0409 {
       
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));

        // Variáveis da questão
        String str = new String();
        ArvoreBinaria ab = new ArvoreBinaria();

        // Entrada
        while((str = br.readLine()) != null){
			if(str.length() == 3){
				if(str.charAt(0) == 'I'){
					ab.inserir(str.charAt(2));
				} else if(str.charAt(0) == 'P'){
					if(ab.pesquisar(str.charAt(2))) System.out.println(str.charAt(2) + " existe");
					else System.out.println(str.charAt(2) + " nao existe");
				}
			}
			else{
				if(str.equals("INFIXA")) ab.caminharCentral();
				else if(str.equals("PREFIXA")) ab.caminharPre();
				else if(str.equals("POSFIXA")) ab.caminharPos();
				else {
					System.out.println("ALGUMA COISA DEU ERRADO");
				}
			}
        }
    }
}