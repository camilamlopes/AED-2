/* Bibliotecas Usadas*/
#include <stdlib.h>
#include <stdio.h>

/* MÉTODO PRINCIPAL */
int main(){
    int idadeM, idadeV, idadeN[2];
    scanf("%d", &idadeM);
    while(idadeM != 0){
        scanf("%d %d", &idadeN[0], &idadeN[1]);
        idadeV = idadeM - idadeN[0] - idadeN[1];

        if(idadeV > idadeN[0] && idadeV > idadeN[1]) printf("%d\n", idadeV);
        else if(idadeN[0] > idadeN[1]) printf("%d\n", idadeN[0]);
        else printf("%d\n", idadeN[1]);

        scanf("%d", &idadeM);
        }
    return 0;
}