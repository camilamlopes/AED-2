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
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // formato aderido para data

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
        int time = 0, i = 0;
        String tmp = "";

        for(; i < str.length(); i++){ 
            if(str.charAt(i) == 'h'){
                time = Integer.parseInt(tmp) * 60;
                tmp = "";
                i++;
            } else if(str.charAt(i) == 'm'){
                time += Integer.parseInt(tmp);
                i = str.length();
            } else tmp += str.charAt(i);
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

class Quicksort{
    public Filme[] array;
    public int tam;
    public int compara;
    
    /**
     * Construtor
     */
    public Quicksort(){
        this.array = new Filme[200];
        this.tam = array.length;
        this.compara = 0;
    }

    /**
     * Construtor
     * @param tam
     * @param array
     */
    public Quicksort(int tam, Filme[] array){
        this.array = array.clone();
        this.tam = tam;
        this.compara = 0;
    }

    public void swap(int i, int j){
        Filme tmp = array[i].clonar();
        array[i] = array[j].clonar();
        array[j] = tmp;
    }

    public Filme[] sort(){
        quicksort(0, tam-1);
        desempate();
        return array;
    }

    public void quicksort(int esq, int dir){
        int i = esq, j = dir;
        Filme pivo = array[(dir+esq)/2];

        while (i <= j) {
            while ((array[i].getSituacao()).compareTo(pivo.getSituacao()) < 0){
                compara++;
                i++;
            } compara++;

            while ((array[i].getSituacao()).compareTo(pivo.getSituacao()) > 0){
                compara++;
                j--;
            } compara++;
            
            if(i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }

        if(esq < j) quicksort(esq, j);
        if(i < dir) quicksort(i, dir);
    }

    public void desempate () {
        for(int i = 0; i < tam; i++){
            for(int j = i + 1; j < tam; j++){
                if((array[i].getSituacao()).compareTo(array[j].getSituacao()) == 0){
                    if((array[i].getNome()).compareTo(array[j].getNome()) > 0) {
                        swap(i, j);
                    }
                } else j = tam;
            }
        }
    }
}


public class TP0306{
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
        Arq.openWrite("689172_quicksort.txt");
        Arq.println("689172\t" + tempo + "\t" + compara);
        Arq.close();
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
        
        // variáveis
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        String in[] = new String[1000]; // array de String que recebe o endereço
        int count = 0;
        String line; 

        // leitura dos endereços url
        while(isFim (line = br.readLine()) == false){
            in[count] = line;
            count++;
        }
        
        // Instanciamento dos objetos
        Filme f[] = new Filme[count];
        for(int i = 0; i < count; i++){
            f[i] = new Filme();
            f[i].ler(in[i]);
        }

        /********** Ordenação por Seleção **********/        
        Quicksort quick = new Quicksort(count, f);
        f = quick.sort();

        // Impressão
        for(int i = 0; i < count; i++){
            f[i].imprimir();
        }

        double fim = new Date().getTime();
        log(fim - inicio, quick.compara);
    } // ending main
} // ending class