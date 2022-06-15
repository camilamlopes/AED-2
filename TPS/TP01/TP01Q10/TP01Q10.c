/* BIBLIOTECAS USADAS */
#include <stdio.h>
#include <stdlib.h>

/* MÉTODO DE LEITURA E ESCRITA DO ARQUIVO ALEATÓRIO */
void readFile(int n){
    FILE *arq;
    arq = fopen("pub.in", "w");

    if(arq == NULL){
        printf("ERRO NA ABERTURA DO ARQUIVO\n");
        system("pause");
        exit(1);
    }

    double num = 0.0;
    for(int i = 0; i < n; i++){
        scanf("%lf", &num);
        fprintf(arq, "%lf\n", num);
    }

    fclose(arq); 
}

/* MÉTODO DE ESCRITA DO ARQUIVO AO CONTRÁRIO */
void printInverse(int n){
    FILE *arq;
    arq = fopen("pub.in", "rb");

    if(arq == NULL){
        printf("ERRO NA ABERTURA DO ARQUIVO\n");
        system("pause");
        exit(1);
    }
    
    double real = 0.0;

    for(int i = n; i > 0; i--){
        fseek(arq, 0, SEEK_SET);

        for(int j = 0; j < i; j++){
            fscanf(arq, "%lf\n", &real);
        }

        if(real != (int)real) printf("%g\n", real);
        else printf("%i\n", (int)real);
    }

    fclose(arq);
}

/* MÉTODO PRINCIPAL */
int main(){
    int numN;
    scanf("%d", &numN);

    readFile(numN);
    printInverse(numN);
    return 0;
}