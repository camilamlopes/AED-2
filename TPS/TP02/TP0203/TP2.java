/**
 * @author: Camila Moreira Lopes
 */

// Libraries used
import java.util.Date;
import java.io.*;

class Filme{
    /* Atributos Privados */
    private String nome;

    /* Construtores */
    public Filme(){
        this.nome = "";      
    }

    public Filme(String nome){
        this.nome = nome;
    }

    /* Getters */
    public String getNome() { 
        return nome; 
    }
      
    /* Setters */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    /**
     * Clone creating a new Filme variable and copying it
     * @return Filme type
     */
    public Filme clonar(){
        Filme clone = new Filme();
        clone.setNome(nome);
        return clone;
    }

    /**
     * Receives a line and removes tags
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

            // Tratamento do nome
            do{
                line = br.readLine(); 
            } while(line.contains("<h2") == false);

            line = br.readLine(); 
            setNome(removeTags(line).trim());

            br.close();
        } catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }

    }
}


class Lista{
    public Filme[] array = new Filme[200];
    public int count;
    public int compara;
    
    /**
     * Construtor da classe
     * @param count
     * @param array
     */
    public Lista (int count, Filme[] array){
        this.array = array;
        this.count = count;
        this.compara = 0;
    }

    /**
     * Procura elemento e retorna se ele existe
     * @param str
     * @return
     */
    public boolean pesquisaSeq(String str){
        boolean retorno = false;
        String nome;
        for(int i = 0; i < count && retorno == false; i++){
            nome = array[i].getNome();
            retorno = (str.equals(nome));
            compara++; // compara o elemento pesquisado com o nome + se o i
        }
        return retorno;
    }
}


public class TP2{
    /**
     * Receives a string and checks if it's equals to "FIM"
     * @param str
     * @return
     */
    public static boolean isFim (String str){
        return (str.length() == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M');
    } // ending method

    public static void log(double tempo, int compara){
        Arq.openWrite("689172_binaria.txt");
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
        int count_f = 0;
        String line; 

        // leitura dos endereços url
        while(isFim (line = br.readLine()) == false){
            in[count_f] = line;
            count_f++;
        }
        
        // Instanciamento dos objetos
        Filme f[] = new Filme[count_f];
        for(int i = 0; i < count_f; i++){
            f[i] = new Filme();
            f[i].ler(in[i]);
        }

        // Leitura dos filmes a serem pesquisados
        String[] pesquisa = new String[200];
        int count_p = 0;
        while(isFim (line = br.readLine()) == false){
            pesquisa[count_p] = line;
            count_p++;
        }

        // Pesquisa Sequencial
        Lista list = new Lista(count_f, f);
        for(int i = 0; i < count_p; i++){
            if(list.pesquisaSeq(pesquisa[i])){
                System.out.println("SIM");
            } else{
                System.out.println("NAO");
            }
        }

        double fim = new Date().getTime();
        log(fim - inicio, list.compara);
    } // ending main
} // ending class