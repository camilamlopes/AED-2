public class ex02 {
    public static void maiorMenor(int[] array){
        int maior = array[0], menor = array[0];
        for(int i = 1; i < 10; i++){
            if(maior < array[i]){
                maior = array[i];
            } else if(menor > array[i]){
                menor = array[i];
            }
        }
        System.out.println("Maior = " + maior + "\nMenor = " + menor);
    }


    public static void main (String[] args){
        int[] arrayInt = new int[10];

        for(int i = 0; i < 10; i++){
            arrayInt[i] = MyIO.readInt();
        }

        //primeira parte
        maiorMenor(arrayInt);

        //segunda parte
        
    }
}
