public class donaMonica {

    
    public static void main(String[] args){
        int idadeM = MyIO.readInt(),
            idadeV = 0;
        int[] idadeN = new int[2];

        while(idadeM != 0){
            idadeN[0] = MyIO.readInt();
            idadeN[1] = MyIO.readInt();

            idadeV = idadeM - (idadeN[0] + idadeN[1]);
            if(idadeV > idadeN[0] && idadeV > idadeN[1]) System.out.println(idadeV);
            else if(idadeN[0] > idadeN[1]) System.out.println(idadeN[0]);
            else System.out.println(idadeN[1]);

            idadeM = MyIO.readInt();
        }
    }
}
