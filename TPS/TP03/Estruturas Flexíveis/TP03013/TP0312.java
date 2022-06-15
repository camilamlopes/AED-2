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
        } else{
            if(str.charAt(1) == 'h'){
                time += 60 * Integer.parseInt(splitStr[0]);
            } else {
                time += Integer.parseInt(splitStr[0]);
            }
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

class Aloca {
    public Filme elemento;
    public Aloca prox;

    /**
     * Construtor da classe
     */
    public Aloca () {
        this.elemento = null;
        this.prox = null;
    }

    public Aloca (Filme elemento) {
        this.elemento = elemento.clonar();
        this.prox = null;
    }
}

class Fila{
    private Aloca primeiro;
    private Aloca ultimo;
    private int qnt;
    private float tempo;

    /**
     * Construtor da classe que cria uma fila sem elementos
     */
    public Fila (){
        primeiro = new Aloca();
        ultimo = primeiro;
        qnt = 0;
        tempo = 0;
    }
    

    public void enfileirar (Filme film) throws Exception{
        if(qnt == 5){
            desenfileirar();
        } 
        ultimo.prox = new Aloca(film);
        ultimo = ultimo.prox;
        qnt++;


        tempo += film.getDuracao();
        System.out.println(Math.round(tempo/qnt));
    }

    public Filme desenfileirar() throws Exception{
        if (primeiro == ultimo) {
			throw new Exception("Erro ao remover!");
		}

        Aloca tmp = primeiro;
        primeiro = primeiro.prox;
        
        Filme resp = primeiro.elemento;
        
        tmp.prox = null;
        tmp = null;
		
        tempo -= resp.getDuracao();
        qnt--;

        return resp;
	}

    public void show (){
        int j = 0;

        for(Aloca i = primeiro.prox; i != null; i = i.prox, j++){
            System.out.print("[" + j + "] ");
            i.elemento.imprimir();
        }
    }
}

public class TP0312{
    /**
     * Receives a string and checks if it's equals to "FIM"
     * @param str
     * @return
     */
    public static boolean isFim (String str){
        return (str.length() == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M');
    } // ending method

    /**
     * (MAIN) receives an input from keyborad and starts the program
     * @param String[] args
     * @throws ParseException
     * @return
    */
    public static void main (String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
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

        
        Fila queue = new Fila();
        for(int i = 0; i < count; i++){
            queue.enfileirar(f[i]);
        }

        Filme tmp = new Filme();
        int qntI = Integer.parseInt(br.readLine());
        String[] str = new String[3];

        for(int i = 0; i < qntI; i++){
            line = br.readLine();
            
            if(line.contains("I")){
                str = line.split(" ", 2);

                tmp.ler(str[1]);

                queue.enfileirar(tmp);

            } else if(line.contains("R")){
                tmp = queue.desenfileirar();
                System.out.println("(R) " + tmp.getNome());
            } 
        }

        queue.show();
        
    } // ending main
} // ending class