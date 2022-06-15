/**
 * @author: Camila Moreira Lopes
 */

// Libraries used
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.Date;

class Filme{
    /* Atributos Privados */
    private String nome;
    private String tituloOriginal;
    private String genero;
    private String idiomaOriginal;
    private String situacao;

    private String[] palavraChave; 

    private int duracao;
    private float orcamento;

    private Date dataLancamento;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy"); // formato aderido para data

    /* Construtores */
    public Filme(){
        this.nome = "";
        this.genero = ""; 
        this.situacao = "";
        this.idiomaOriginal = "";
        this.tituloOriginal = "";
        this.duracao = 0;
        this.orcamento = 0;      
    }

    public Filme(String nome, String genero, String situacao, String idiomaOriginal, String tituloOriginal, String[] palavraChave, 
                 int duracao, float orcamento, Date dataLancamento){
        this.nome = nome;
        this.genero = genero; 
        this.duracao = duracao;
        this.situacao = situacao;
        this.orcamento = orcamento; 
        this.palavraChave = palavraChave;
        this.idiomaOriginal = idiomaOriginal;
        this.tituloOriginal = tituloOriginal; 
        this.dataLancamento = dataLancamento;
    }

    /* Getters */
    public String getNome() { 
        return nome; 
    }
    public int getDuracao() { 
        return duracao; 
    }
    public String getGenero() { 
        return genero;
    }
    public String getSituacao() { 
        return situacao;
    }
    public float getOrcamento() {
        return orcamento;
    }
    public Date getDataLancamento() {
        return dataLancamento; 
    }
    public String getTituloOriginal() {
        return tituloOriginal; 
    }
    public String getIdiomaOriginal() {
        return idiomaOriginal;
    }  
    public String[] getPalavraChave() {
        return palavraChave;
    }
    

    /* Setters */
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    public void setOrcamento(float orcamento) {
        this.orcamento = orcamento;
    }
    public void setPalavraChave(String[] palavraChave) { 
        this.palavraChave = palavraChave;
    }
    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
    public void setTituloOriginal(String tituloOriginal) {
        this.tituloOriginal = tituloOriginal;
    }    
    public void setIdiomaOriginal(String idiomaOriginal) {
        this.idiomaOriginal = idiomaOriginal;
    }
    
    /**
     * Clone creating a new Filme variable and copying it
     * @return Filme type
     */
    public Filme clonar(){
        Filme clone = new Filme();
        clone.setNome(nome);
        clone.setGenero(genero); 
        clone.setDuracao(duracao);
        clone.setSituacao(situacao);
        clone.setOrcamento(orcamento); 
        clone.setPalavraChave(palavraChave);
        clone.setIdiomaOriginal(idiomaOriginal);
        clone.setTituloOriginal(tituloOriginal); 
        clone.setDataLancamento(dataLancamento);

        return clone;
    }

    /**
     * remove all occurrences of a Substring in a String
     * @param str
     * @param remove
     * @return clear str
     */
    private String remove(String str, String remove){
        String clean = "", tmp = ""; // definindo as Strings que serão utilizadas
        int j = 0; // a posição na String a ser removida
        for(int i = 0; i < str.length(); i++){
            
            if(str.charAt(i) != remove.charAt(j)){
                clean += str.charAt(i); // enquanto não encontra a string a ser removida eu copio a string original para a limpa
            } else{
                while(j < remove.length() && str.charAt(i) == remove.charAt(j)){ // quando achar irá testar se é exatamente igual a string a ser retirada
                    tmp += str.charAt(i); 
                    i++;
                    j++;
                }

                if(j < remove.length()){
                    clean += tmp; //se não for exatamente igual irá concatenar a string tmp na string limpa
                } 
                
                i--;          // 
                j = 0;       // volto ao início da string a ser removida
                tmp = "";    // limpo a string temporária
            } // end else
        } //end for

        return clean;
    }

    /**
     * convert the runtime of the film to minutes
     * @param str
     * @return int minutes
     */
    private int convertMinute(String str){
        int time = 0;
        String tmp = "";

        for(int i = 0; i < str.length(); i++){ 
            if( (str.charAt(i) >= 48 && str.charAt(i) <= 57) || str.charAt(i) == ' ')
                tmp += str.charAt(i);
        }

        String[] splitStr = tmp.split(" ");
        if(splitStr.length > 1){
            time += 60 * Integer.parseInt(splitStr[0]);
            time += Integer.parseInt(splitStr[1]);
        } else {
            if(str.contains("h")) time += 60 * Integer.parseInt(splitStr[0]);
            else time += Integer.parseInt(splitStr[0]);
        }
        return time;
    }

    /**
     * remove everything between the tags
     * @param line
     * @return clear string
     */
    private String removeTags(String line){
        String clean = "";

        for(int i = 0; i < line.length(); i++){
            if(line.charAt(i) == '<'){
                while(line.charAt(i) != '>') i++;
                if(i + 1 < line.length() && line.charAt(i + 1) == ' ') i++; // se a frase iniciar com espaço o mesmo é retirado
            } else if(line.charAt(i) == '&'){
                while(line.charAt(i) != ';') i++;
            } else{
                clean += line.charAt(i);
            }
        }
        return clean;
    }

    /**
     * read all atributes that are needed
     * @param endereco
     */
    public void ler(String endereco) {
        String line = "";

        try{
            BufferedReader br = new BufferedReader(new FileReader("/tmp/filmes/" + endereco));
            //BufferedReader br = new BufferedReader(new FileReader(endereco));

            /**
             * Tratamento do nome
             */
            do{
                line = br.readLine(); 
            } while(line.contains("<h2") == false);    //leitura da linha até a primeira a primeira ocorrência do h2
            line = br.readLine(); // leitura da linha onde está o nome "limpo"
            setNome(removeTags(line).trim());

            /**
             * Tratamento Data de Lançamento
             */

            while(line.contains("<span class=\"release\">") == false){ //leitura até a linha global da data de lançamento
                line = br.readLine();
            }
            line = br.readLine(); //leitura onde está a data

            if(line.contains("(BR)")) line = remove(line, "(BR)");       // retirada do selo se for a data no brasil
            else if(line.contains("(US)")) line = remove(line, "(US)");  // retirada do selo se for a data no estados unidos
            
            setDataLancamento(sdf.parse(line.trim()));                   // o sdf.parse converte type String para type Data

            /**
             * Tratamento do Gênero
             */
            while(line.contains("<span class=\"genres\">") == false){ //leitura até a linha global do Gênero
                line = br.readLine();
            }
    
            line = br.readLine(); 
            line = br.readLine();

            setGenero(removeTags(line).trim());


            /**
             * Tratamento de Duração
             */
            while(line.contains("runtime") == false){ //leitura até a linha global da Duração
                line = br.readLine();
            }
            line = br.readLine();
            line = br.readLine();
            
            setDuracao(convertMinute(line.trim()));  //chamada do conversor para minutos


            /**
             * Tratamento de Título Original incompleto
             */
            while(line.contains("T�tulo original") == false && line.contains("<bdi>Situa��o</bdi>") == false){
                line = br.readLine();
            }

            if(line.contains("<bdi>Situa��o</bdi>") == false){
                line = remove(line, "T�tulo original");
                setTituloOriginal(removeTags(line.trim()));

            } else{ setTituloOriginal(getNome()); }


            /**
             * Tratamento de Situação
            */
            while(line.contains("<bdi>Situa��o</bdi>") == false){
                line = br.readLine();
            }

            line = remove(line, "Situa��o");
            
            setSituacao(removeTags(line.trim()));

            /**
             * Tratamento de Idioma Original
            */
            while(line.contains("Idioma original") == false){
                line = br.readLine();
            }

            line = remove(line, "Idioma original");
            setIdiomaOriginal(removeTags(line.trim()));


            /**
             * Tratamento do Orçamento
             */
            while(line.contains("Or�amento") == false){
                line = br.readLine();
            }
            if(line.contains("-")){
                setOrcamento(0);
            } else{
                line = remove(line, "Or�amento");            
                line = remove(line, "$");
                line = remove(line, ",");

                setOrcamento(Float.parseFloat(removeTags(line.trim())));
            }
            


            /**
             * Tratamento das Palavras-Chave
             */
            
            String[] tmp = new String[100];
            int count = 0;

            while(line.contains("Palavras-chave") == false){
                line = br.readLine();
            }

            do{
                line = br.readLine();;
                if(line.contains("li")){
                    tmp[count] = removeTags(line).trim();
                    count++;
                }
            } while(line.contains("</section>") == false);

            if(count > 0){
                String[] pc = new String [count];
                for(int i = 0; i < count; i++){
                    pc[i] = tmp[i];
                }
                setPalavraChave(pc);
            } 

            br.close();
        } catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
        } catch (IOException ioe){
            ioe.printStackTrace();
        } catch (ParseException pe){
            pe.printStackTrace();
        }

    }

    /**
     * method of print
     */
    public void imprimir (){
        System.out.print(getNome() + " ");
        System.out.print(getTituloOriginal() + " ");

        System.out.print(sdf.format(getDataLancamento()) + " ");

        System.out.print(getDuracao() + " ");
        System.out.print(getGenero() + " ");
        System.out.print(getIdiomaOriginal() + " ");
        System.out.print(getSituacao() + " ");
        System.out.print(getOrcamento() + " ");

        if(getPalavraChave() != null){
            String[] pc = getPalavraChave(); 
            int i = 0;
            System.out.print("[");
            for(;i < pc.length - 1; i++){
                System.out.print(pc[i] + ", ");
            }
            System.out.println(pc[i] + "]");
        } else System.out.println("[]");
        
    }
}

class No {
    public char elemento;    // o conteúdo é um inteiro positivo
    public No esq, dir;     // Filhos da esquerda e da direita
    public No2 outro;

    public No(char elemento) {
        this.elemento = elemento;
		this.esq = this.dir = null;
        this.outro = null;
    }

    public No(char elemento, No esq, No dir) {
        this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
        this.outro = null;
    }
}

class No2 {
    public String elemento;    // o conteúdo é um inteiro positivo
    public No2 esq, dir;     // Filhos da esquerda e da direita

    public No2(String elemento) {
        this(elemento, null, null);
    }

    public No2(String elemento, No2 esq, No2 dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

class ArvoreBinaria {
    private No raiz;   // Raiz da Árvore
    public int compara = 0;
    /**
     * Construtor da classe
     */
    public ArvoreBinaria () throws Exception {
		raiz = null;
        inserir('D');
        inserir('R');
        inserir('Z');
        inserir('X');
        inserir('V');
        inserir('B');
        inserir('F');
        inserir('P');
        inserir('U');
        inserir('I');
        inserir('G');
        inserir('E');
        inserir('J');
        inserir('L');
        inserir('H');
        inserir('T');
        inserir('A');
        inserir('W');
        inserir('S');
        inserir('O');
        inserir('M');
        inserir('N');
        inserir('K');
        inserir('C');
        inserir('Y');
        inserir('Q');
	}

    /*************** MÉTODOS DE INSERÇÃO DO CHAR ***************/
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
    /*************** MÉTODOS DE INSERÇÃO DA STRING ***************/
    public void inserir(String s) throws Exception {
        inserir(s, raiz);
    }
  
    public void inserir(String s, No i) throws Exception {
        if (i == null) {
            throw new Exception("Erro ao inserir: caractere invalido!");
        } else if (s.charAt(0) < i.elemento) {
            inserir(s, i.esq);
        } else if (s.charAt(0) > i.elemento) {
           inserir(s, i.dir);
        } else {
           i.outro = inserir(s, i.outro);
        }
    }

    private No2 inserir(String y, No2 i) throws Exception {
        if (i == null) {
            i = new No2(y);
        } else if (y.compareTo(i.elemento) < 0) {
            i.esq = inserir(y, i.esq);
        } else if (y.compareTo(i.elemento) > 0) {
            i.dir = inserir(y, i.dir);
        } else {
            throw new Exception("Erro ao inserir: elemento existente!");
        }
		return i;
	}

    /*************** MÉTODOS DE PESQUISA ***************/
    /**
	 * Metodo publico iterativo para pesquisar elemento.
	 * @param x Elemento que sera procurado.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	public boolean pesquisar(String x) {
        System.out.println("=> " + x);
        System.out.print("raiz ");
        return pesquisar(x, raiz);
	}

    /**
	 * Metodo privado recursivo para pesquisar elemento.
	 * @param x Elemento que sera procurado.
	 * @param i No em analise.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	private boolean pesquisar(String x, No no) {
        boolean resp = false;
        
        if (no != null) {
            compara++;
            resp = pesquisarS2(x, no.outro);

            if (resp == false) {
                compara += 2;
                System.out.print(" ESQ ");
                resp = pesquisar(x, no.esq);
            }
            if (resp == false) {
                compara += 3;
                System.out.print(" DIR ");
                resp = pesquisar(x, no.dir);
            }
        } 
        

        return resp;
    }

    private boolean pesquisarS2(String x, No2 no) {
        boolean resp;
        if (no == null) {
            compara++;
            resp = false;
        } else if (x.compareTo(no.elemento) < 0) {
            compara += 2;
            System.out.print("esq ");
            resp = pesquisarS2(x, no.esq); 
        } else if (x.compareTo(no.elemento) > 0) {
            compara += 3;
            System.out.print("dir ");
            resp = pesquisarS2(x, no.dir); 
        } else {
            compara += 3; 
            resp = true; 
        }

        return resp;
    }
}

public class TP0402{
    /**
     * Receives a string and checks if it's equals to "FIM"
     * @param str
     * @return
     */
    public static boolean isFim (String str){
        return (str.length() == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M');
    } // ending method
    
    /**
     * 
     * @param tempo
     * @param compara
     */
    public static void log(double tempo, int compara){
        String str = "689172\t" + tempo + "\t" + compara;
        Arq.openWriteClose("689172_arvoreBinaria.txt", "ISO-8859-1", str);
    }

    /**
     * (MAIN) receives an input from keyborad and starts the program
     * @param String[] args
     * @throws ParseException
     * @return
    */
    public static void main (String[] args) throws Exception {
        // tempo inicial
        double inicio = new Date().getTime();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "ISO-8859-1"));
        String[] in = new String[1000];
        int count = 0;
        String line; 

        // Leitura dos endereços url
        while(isFim (line = br.readLine()) == false){
            in[count] = line;
            count++;
        }
        
        // Instanciamento dos objetos
        Filme f[] = new Filme[count];
        for(int i = 0; i < count; i++){
            f[i]= new Filme();
            f[i].ler(in[i]);
        }
        
        // Início da árvore
        ArvoreBinaria ab = new ArvoreBinaria();
        for(int i = 0; i < count; i++){
            ab.inserir(f[i].getTituloOriginal());
        }

        // Inserção e Remoção da Árvore
        Filme tmp = new Filme();
        int qntI = Integer.parseInt(br.readLine());
        String[] str = new String[3];

        for(int i = 0; i < qntI; i++){
            line = br.readLine();
            str = line.split(" ", 2);
            tmp.ler(str[1]);
            ab.inserir(tmp.getTituloOriginal());
        }

        // Pesquisa na Árvore
        while(isFim (line = br.readLine()) == false){
            if (ab.pesquisar(line)) System.out.println(" SIM");
            else System.out.println(" NAO");
        }
        
        // tempo final e criação do arquivo log
        double fim = new Date().getTime();
        log(fim - inicio, ab.compara);
    } // ending main
} // ending class