public class ex01{
    public static boolean isIn (int[] array, int x){
        for(int i = 0; i < 10; i++){
            if(x == array[i]){
                return true;
            }
        }
        return false;
    }

    public static boolean isInCrescente (int[] array, int x){
        if(x > array[array.length/2]){
            for(int i = 4; i < 10; i++){
                if(x == array[i]){
                    return true;
                }
            }
            return false;
        } else{
            for(int i = 0; i < 5; i++){
                if(x == array[i]){
                    return true;
                }
            }
            return false;
        }
    }

    public static void main (String[] args){
        int[] arrayInt = new int[10];
        int x = 0;

        for(int i = 0; i < 10; i++){
            arrayInt[i] = MyIO.readInt();
        }

        x = MyIO.readInt();

        //primeira parte
        if(isIn(arrayInt, x) == true){
            System.out.println("O NUMERO ESTA CONTIDO NO ARRAY");
        } else{
            System.out.println("O NUMERO NAO ESTA CONTIDO NO ARRAY");
        }

        //segunda parte
        if(isInCrescente(arrayInt, x) == true){
            System.out.println("O NUMERO ESTA CONTIDO NO ARRAY");
        } else{
            System.out.println("O NUMERO NAO ESTA CONTIDO NO ARRAY");
        }
    }
}