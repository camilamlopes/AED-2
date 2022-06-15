class globalVariable{
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

public class teste {
    public static String retiraEspaco(String str){
        String replace = new String();
        for(int i = 0; i < str.length(); i++){
            if((int)str.charAt(i) != 32) replace += str.charAt(i);
        }
        return replace;
    }

    public static void posicaoCerta(String str, globalVariable gv){
        while(str.charAt(gv.getPosicao()) != ')'){
            gv.posicaoSum(1);
            if(str.charAt(gv.getPosicao()) == '(') posicaoCerta(str, gv);
        }
    }

    public static void main(String[] args){
        String str = "and(not(A),not(B))";
        globalVariable gv = new globalVariable();

        gv.setPosicao(10);
        posicaoCerta(str, gv);
        System.out.println(str.charAt(gv.getPosicao()));
        
    }
}
