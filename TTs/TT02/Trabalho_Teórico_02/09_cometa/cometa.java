public class cometa {
    public static void main(String[] args){
        int ano = MyIO.readInt();
        while(ano != 0){       
            if((ano - 1986) % 76 == 0) System.out.println(ano + 76);
            else{
                int aux = 2062;
                while(aux < ano) aux += 76;
                System.out.println(aux);
            }
            ano = MyIO.readInt();
        }
    }
}
