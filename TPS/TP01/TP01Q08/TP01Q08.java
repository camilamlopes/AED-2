import java.io.*;
import java.net.*;

public class TP01Q08 {
    // função que prevê se a linha possui a leitura FIM
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static String importHtml(String site){
        URL url;
        InputStream is = null;
        BufferedReader br;
        String resp = "", line;

        try{
            url = new URL(site);
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));
   
            while ((line = br.readLine()) != null){
               resp += line + "\n";
            }
        } catch (MalformedURLException mue){
            mue.printStackTrace();
        } catch (IOException ioe){
            ioe.printStackTrace();
        } 
   
        try{
            is.close();
        } catch (IOException ioe){
            // nothing to see here
   
        }
   
        return resp;
    }

    public static  int[] contador(String site){
        int[] x = new int[25];
        
        //para zerar todos os contadores
        for(int i = 0; i < 25; i++){
            x[i] = 0;
        }

        for(int i = 0; i < site.length(); i++){
            if(site.charAt(i) == '<'){
                if(site.charAt(i + 1) == 'b' && site.charAt(i + 2) == 'r' && site.charAt(i + 3) == '>'){
                    x[23]++;
                    i += 3;
                } if(site.charAt(i + 1) == 't' && site.charAt(i + 2) == 'a' && site.charAt(i + 6) == '>'){
                    x[24]++;
                    i+= 6;
                }
            } else if(site.charAt(i) == 'a') x[0]++;
            else if (site.charAt(i) == 'e') x[1]++;
            else if (site.charAt(i) == 'i') x[2]++;
            else if (site.charAt(i) == 'o') x[3]++;
            else if (site.charAt(i) == 'u') x[4]++;
            else if (site.charAt(i) == '\u00E1') x[5]++;     // á
            else if (site.charAt(i) == '\u00E9') x[6]++;     // é
            else if (site.charAt(i) == '\u00ED') x[7]++;     // í
            else if (site.charAt(i) == '\u00F3') x[8]++;     // ó
            else if (site.charAt(i) == '\u00FA') x[9]++;     // ú
            else if (site.charAt(i) == '\u00E0') x[10]++;    // à
            else if (site.charAt(i) == '\u00E8') x[11]++;    // è
            else if (site.charAt(i) == '\u00EC') x[12]++;    // ì
            else if (site.charAt(i) == '\u00F2') x[13]++;    // ò
            else if (site.charAt(i) == '\u00F9') x[14]++;    // ù
            else if (site.charAt(i) == '\u00E3') x[15]++;    // ã
            else if (site.charAt(i) == '\u00F5') x[16]++;    // ô
            else if (site.charAt(i) == '\u00E2') x[17]++;    // â
            else if (site.charAt(i) == '\u00EA') x[18]++;    // ê
            else if (site.charAt(i) == '\u00EE') x[19]++;    // î
            else if (site.charAt(i) == '\u00F4') x[20]++;    // ô
            else if (site.charAt(i) == '\u00FB') x[21]++;    // û
            else if (site.charAt(i) > 'a' && site.charAt(i) <= 'z') x[22]++;
        }

        return x;
    }

    public static void imprime(int[] X, String nome){
        MyIO.println("a(" + X[0] + ") e(" + X[1] + ") i(" + X[2] + ") o(" + X[3] + ") u(" + X[4] + ")"
                + " \u00E1(" + X[5] + ") \u00E9(" + X[6] + ") \u00ED(" + X[7] + ") \u00F3(" + X[8] + ") \u00FA(" + X[9] + ")" 
                + " \u00E0(" + X[10] + ") \u00E8(" + X[11] + ") \u00EC(" + X[12] + ") \u00F2(" + X[13] + ") \u00F9(" + X[14] + ")" 
                + " \u00E3(" + X[15] + ") \u00F5(" + X[16] + ")"
                + " \u00E2(" + X[17] + ") \u00EA(" + X[18] + ") \u00EE(" + X[19] + ")" + " \u00F4(" + X[20] + ") \u00FB("+ X[21] + ")"
                + " consoante(" + X[22] + ") <br>(" + X[23] + ") <table>(" + X[24] + ") " + nome);
    }

    /* MÉTODO PRINCIPAL */
    public static void main(String[] args){
        MyIO.setCharset("utf-8");
        String nome = new String();
        String site = new String();

        int[] qnt = new int[25];

        nome = MyIO.readLine();
        while(isFim(nome) == false){
            site = MyIO.readLine();
            site = importHtml(site);
            qnt = contador(site);
            imprime(qnt, nome);
            nome = MyIO.readLine();
        }
    }
}
