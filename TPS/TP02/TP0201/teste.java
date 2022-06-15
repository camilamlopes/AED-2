import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.Date;

public class teste {

    public static String remove(String str, String remove){
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

        System.out.println(clean);
        return clean;
    }

    public static String removeString(String str, String remove){
        String clear = "";
        int i = 0, j = 0;
  
        for(; j < remove.length(); i++){
            if(str.charAt(i) != remove.charAt(j)){
                clear += str.charAt(i);
            } else j++;
        }


        for(; i < str.length(); i++){
            clear += str.charAt(i);
        }

        return clear;
    }

    public static String removeComma(String str){
        String clean = "";

        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) != ',') clean += str.charAt(i);
        }

        return clean;
    }

    public static int convertMinute(String str){
        int time = 0, i = 0;
        String tmp = "";

        if(str.contains("h")){
            while(str.charAt(i) != 'h'){
                tmp += str.charAt(i);
                i++;
            }

            i += 2; // retirar h e o espaço se houver minutos
            time = Integer.parseInt(tmp) * 60;
        } 

        if(str.contains("m")){
            tmp = "";
            while(str.charAt(i) != 'm'){
                tmp += str.charAt(i);
                i++;
            }
            time += Integer.parseInt(tmp);
        }

        return time;
    }
    
    public static String removeAngleB(String line){
        String clean = "";

        for(int i = 0; i < line.length(); i++){
            if(line.charAt(i) == '<'){
                while(line.charAt(i) != '>'){
                    i++;
                }

                if(i + 1 < line.length() && line.charAt(i + 1) == ' ') i++; // se a frase iniciar com espaço o mesmo é retirado

            } else if(line.charAt(i) == '&'){           // retirar characteres especiais do html até o próximo <
                while(line.charAt(i) != '<'){
                    i++;
                }
                i--;
            } else{
                clean += line.charAt(i);
            }
        }

        return clean;
    }
    
    // dataLancamento [palavrasChave]
    public static void main (String[] args) throws ParseException {
        String line = "";
        try{
            FileReader fr = new FileReader("007 - Sem Tempo para Morrer.html");
            BufferedReader br = new BufferedReader(fr);
            
            while(line.contains("Orçamento") == false){
                line = br.readLine();
            }

            if(line.contains("-")){
                MyIO.println(0);
            } else{
                line = remove(line, "Orçamento");            
                line = remove(line, "$");
                line = remove(line, ",");

                
                MyIO.println(Float.parseFloat(removeAngleB(line.trim())));
            }            

            br.close();

        } catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }

    }
}
