class globalVariables{
    private int posicaoGlobal;
    
    public void setPosicao(int i){
        this.posicaoGlobal = i;
    }

    public int getPosicao(){
        return posicaoGlobal;
    }

    public void posicaoSum(int i) {
        this.posicaoGlobal = posicaoGlobal + i;
    }
}

public class TP01Q14{
    /* FUNÇÃO QUE RETIRA ESPAÇOS */
    public static String retiraEspaco(String str){
        String replace = new String();
        for(int i = 0; i < str.length(); i++){
            if((int)str.charAt(i) != 32) replace += str.charAt(i);
        }
        return replace;
    }

    public static void posicaoCerta(String str, globalVariables gv){
        gv.posicaoSum(1);
        while(str.charAt(gv.getPosicao()) != ')'){
            if(str.charAt(gv.getPosicao()) == '(') posicaoCerta(str, gv);
            gv.posicaoSum(1);
        }
    }

    /* FUNÇÃO DE LEITURA DE ALGEBRA BOOLEANA */
    public static int algebraB(String str, int[] en, globalVariables gv){       
        int pos = gv.getPosicao();

        if(str.charAt(pos) == 'A') return en[0];
        else if(str.charAt(pos) == 'B') return en[1];
        else if (str.charAt(pos) == 'C') return en[2];
        else if(str.charAt(pos) == 'n'){
            gv.posicaoSum(4);
            if(algebraB(str, en, gv) == 0){
                gv.posicaoSum(1);
                return 1;
            }
            else {
                gv.posicaoSum(1);
                return 0;   
            }

        } else if(str.charAt(pos) == 'a'){
            gv.posicaoSum(4);

            if(algebraB(str, en, gv) == 1){
                gv.posicaoSum(1);

                while(str.charAt(gv.getPosicao()) == ','){
                    gv.posicaoSum(1);

                    if(algebraB(str, en, gv) == 0){
                        posicaoCerta(str, gv);          //coloca a posição na posição do fechamento da expressão + 1
                        return 0;
                    } else gv.posicaoSum(1);
                }            
                return 1;

            } else if(algebraB(str, en, gv) == 0){
                posicaoCerta(str, gv);                  //coloca a posição na posição do fechamento da expressão + 1 
                return 0;
            }
            
        } else if(str.charAt(pos) == 'o'){
            gv.posicaoSum(3);

            if(algebraB(str, en, gv) == 1){
                posicaoCerta(str, gv); 
                return 1;     
            }

            else if(algebraB(str, en, gv) == 0){
                gv.posicaoSum(1);
                while(str.charAt(gv.getPosicao()) == ','){
                    gv.posicaoSum(1);

                    if(algebraB(str, en, gv) == 1){
                        
                        posicaoCerta(str, gv);          //coloca a posição na posição do fechamento da expressão + 1
                        return 1;
                    } else gv.posicaoSum(1);
                }
                return 0;
            }
        }
        return 0;
    }


    /* MÉTODO PRINCIPAL */
    public static void main(String[] args){
        globalVariables gv = new globalVariables();
        int numEntradas = 0;

        numEntradas = MyIO.readInt();
        while(numEntradas != 0){
            // leitura dos valores de entrada
            int[] entrada = new int[numEntradas];
            for(int i = 0; i < numEntradas; i++){
                entrada[i] = MyIO.readInt();
            }

            // retirar espaços desnecessários
            String expressao = new String();
            expressao = MyIO.readLine();
            expressao = retiraEspaco(expressao);

            gv.setPosicao(0);

            System.out.println(algebraB(expressao, entrada, gv));

            numEntradas = MyIO.readInt();
        }
    }
}
