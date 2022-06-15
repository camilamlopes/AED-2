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
            while(line.contains("Título original") == false && line.contains("<bdi>Situação</bdi>") == false){
                line = br.readLine();
            }

            if(line.contains("<bdi>Situação</bdi>") == false){
                line = remove(line, "Título original");
                setTituloOriginal(removeTags(line.trim()));

            } else{ setTituloOriginal(getNome()); }


            /**
             * Tratamento de Situação
            */
            while(line.contains("<bdi>Situação</bdi>") == false){
                line = br.readLine();
            }

            line = remove(line, "Situação");
            
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
            while(line.contains("Orçamento") == false){
                line = br.readLine();
            }
            if(line.contains("-")){
                setOrcamento(0);
            } else{
                line = remove(line, "Orçamento");            
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
    public String elemento;    // o conteÃºdo Ã© um inteiro positivo
    public No esq, dir;     // Filhos da esquerda e da direita
    public boolean cor;

    public No() {  
    }

    public No(String elemento) {
        this(elemento, null, null, false);
    }

    public No(String elemento, boolean cor) {
        this(elemento, null, null, cor);
    }

    public No(String elemento, No esq, No dir, boolean cor) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
        this.cor = cor;
    }
}

class Alvinegra {
    private No raiz;   // Raiz da Ãrvore
    public int compara = 0;
    /**
     * Construtor da classe
     */
    public Alvinegra () {
		raiz = null;
	}

    /*************** MÃ‰TODOS DE INSERÃ‡ÃƒO ***************/
	/**
	 * Metodo privado recursivo para inserir elemento.
	 * @param x Elemento a ser inserido.
	 * @param i No em analise.
	 * @return No em analise, alterado ou nao.
	 * @throws Exception Se o elemento existir.
	 */
	public void inserir(String x) throws Exception {
        // Se a arvore estiver vazia
        if (raiz == null) {
            raiz = new No(x);
        // Senao, se a arvore tiver um elemento
        } else if (raiz.esq == null && raiz.dir == null){
            if (x.compareTo(raiz.elemento) < 0) {
                raiz.esq = new No(x); 
            } else {
                raiz.dir = new No(x);   
            }
        
        // Senao, se a arvore tiver dois elementos (raiz e dir)
        } else if (raiz.esq == null) {
            if (x.compareTo(raiz.elemento) < 0) {
                raiz.esq = new No(x);
            
            } else if (x.compareTo(raiz.dir.elemento) < 0) {
                raiz.esq = new No(raiz.elemento);
                raiz.elemento = x;
            } else {
                raiz.esq = new No(raiz.elemento);
                raiz.elemento = raiz.dir.elemento;
                raiz.dir.elemento = x;
            }
            
            raiz.esq.cor = raiz.dir.cor = false;
        
        // Senao, se a arvore tiver dois elementos (raiz e esq)
        } else if (raiz.dir == null) {
            if (x.compareTo(raiz.elemento) > 0) {
                raiz.dir = new No(x);
            
            } else if (x.compareTo(raiz.esq.elemento) > 0) {
                raiz.dir = new No(raiz.elemento);
                raiz.elemento = x;
            } else {
                raiz.dir = new No(raiz.elemento);
                raiz.elemento = raiz.esq.elemento;
                raiz.esq.elemento = x;
            }
            
            raiz.esq.cor = raiz.dir.cor = false;
        } else { 
            inserir(x, null, null, null, raiz);
        }

        raiz.cor = false;
	}
    
    private void balancear(No bisavo, No avo, No pai, No i) {
        // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
        if (pai.cor == true) {
            // 4 tipos de reequilibrios e acoplamento
            if (pai.elemento.compareTo(avo.elemento) > 0) { // rotacao a esquerda ou direita-esquerda
                if (i.elemento.compareTo(pai.elemento) > 0) {
                    avo = rotacaoEsq(avo);
                } else {
                    avo = rotacaoDirEsq(avo);
                }
            } else { // rotacao a direita ou esquerda-direita
                if (i.elemento.compareTo(pai.elemento) < 0) {
                    avo = rotacaoDir(avo);
                } else {
                    avo = rotacaoEsqDir(avo);
                }
            }
            
            if (bisavo == null) {
                raiz = avo;
            } else if (avo.elemento.compareTo(bisavo.elemento) < 0) {
                bisavo.esq = avo;
            } else {
                bisavo.dir = avo;
            }
            
            // reestabelecer as cores apos a rotacao
            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;
        }
    }

    private void inserir(String elemento, No bisavo, No avo, No pai, No i) throws Exception {
        if (i == null) {
            if (elemento.compareTo(pai.elemento) < 0) {
                i = pai.esq = new No(elemento, true);
            } else {
                i = pai.dir = new No(elemento, true);
            }
            if (pai.cor == true) {
                balancear(bisavo, avo, pai, i);
            }
        } else {
            // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
            if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
                i.cor = true;
                i.esq.cor = i.dir.cor = false;
                
                if (i == raiz) {
                    i.cor = false;
                } else if (pai.cor == true) {
                    balancear(bisavo, avo, pai, i);
                }
            }
            if (elemento.compareTo(i.elemento) < 0) {
                inserir(elemento, avo, pai, i, i.esq);
            } else if (elemento.compareTo(i.elemento) > 0) {
                inserir(elemento, avo, pai, i, i.dir);
            }
        }
    }

    /*************** METODOS DE ROTACOES ***************/
    private No rotacaoDir(No no) {
        No noEsq = no.esq;
        No noEsqDir = noEsq.dir;
  
        noEsq.dir = no;
        no.esq = noEsqDir;
  
        return noEsq;
    }
  
    private No rotacaoEsq(No no) {
        No noDir = no.dir;
        No noDirEsq = noDir.esq;
  
        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
    }
  
    private No rotacaoDirEsq(No no) {
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
    }
  
    private No rotacaoEsqDir(No no) {
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
    }

    /*************** MÃ‰TODOS DE PESQUISA ***************/
    /**
	 * Metodo publico iterativo para pesquisar elemento.
	 * @param x Elemento que sera procurado.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	public void pesquisar(String x) {
        System.out.print(x + "\nraiz ");
        if(pesquisar(x, raiz)){
            System.out.println("SIM");
        } else {
            System.out.println("NAO");
        }
		
	}

    /**
	 * Metodo privado recursivo para pesquisar elemento.
	 * @param x Elemento que sera procurado.
	 * @param i No em analise.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	private boolean pesquisar(String x, No i) {
        boolean resp;
          if (i == null) {
           resp = false;
        } else if (x.compareTo(i.elemento) == 0) {
           resp = true;
        } else if (x.compareTo(i.elemento) < 0) {
            System.out.print("esq ");
           resp = pesquisar(x, i.esq);
        } else {
            System.out.print("dir ");
           resp = pesquisar(x, i.dir);
        }
        return resp;
    }
}

public class TP0404{
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
        Arq.openWriteClose("689172_Alvinegra.txt", "ISO-8859-1", str);
    }
    
    /**
     * (MAIN) receives an input from keyborad and starts the program
     * @param String[] args
     * @throws ParseException
     * @return
    */
    public static void main (String[] args) throws Exception{
        // tempo inicial
        double inicio = new Date().getTime();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "ISO-8859-1"));
        String[] in = new String[1000];
        int count = 0;
        String line; 

        // Leitura dos endereÃ§os url
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

        
        // InÃ­cio da Ã¡rvore
        Alvinegra arvore = new Alvinegra();
        for(int i = 0; i < count; i++){
            arvore.inserir(f[i].getTituloOriginal());
        }

        // Pesquisa na Ãrvore
        while(isFim (line = br.readLine()) == false){
            arvore.pesquisar(line);
        }
        
        // tempo final e criaÃ§Ã£o do arquivo log
        double fim = new Date().getTime();
        log(fim - inicio, arvore.compara);
    } // ending main
} // ending class