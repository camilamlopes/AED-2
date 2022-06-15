import java.io.IOException;
import java.io.*;
import java.nio.charset.*;

class Medalhas{
    String[] pais;
    int ouro,
        prata,
        bronze;
        
    
}


public class Quadro {
    public static void main (String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));

        String qntIn;
        int qnt;


        

        while((qntIn = in.readLine()) != null){
            qnt = Integer.parseInt(qntIn);
            Medalhas m[] = new Medalhas[qnt];

            for(int i = 0; i < qnt; i++){

            }
        }
    }
}
