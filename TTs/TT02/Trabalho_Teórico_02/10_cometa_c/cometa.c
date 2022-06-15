/* Bibliotecas Usadas*/
#include <stdlib.h>
#include <stdio.h>

int main(){
    int ano;
    scanf("%d", &ano);
    
    while(ano != 0){
        if((ano - 1986) % 76 == 0) printf("%d\n", ano + 76);
        else{
            int aux = 2062;
            while(aux < ano) aux += 76;
            printf("%d\n",aux);
        }

        scanf("%d", &ano);
    }
    return 0;
}