/* BIBLIOTECAS USADAS */
#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#include <string.h>

/* MÉTODO RESPONSÁVEL POR FIM */
int isFim(char *str){
    if (strlen(str) == 3 && str[0] == 'F' && str[1] == 'I' && str[2] == 'M') return 1;
    return 0;
}

int isVogal(char *str){
    for(int i = 0; i < strlen(str); i++){
        if(str[i] == 'A' || str[i] == 'E' || str[i] == 'I' || str[i] == 'O' || str[i] == 'U'
        || str[i] == 'a' || str[i] == 'e' || str[i] == 'i' || str[i] == 'o' || str[i] == 'u'){
            continue;
        } else return 0;
    }
    return 1;
}

int isConsoante(char *str){
    for(int i = 0; i < strlen(str); i++){
        if((str[i] > 64 && str[i] < 91) || (str[i] > 96 && str[i] < 123)){
            if(str[i] == 'A' || str[i] == 'E' || str[i] == 'I' || str[i] == 'O' || str[i] == 'U'
            || str[i] == 'a' || str[i] == 'e' || str[i] == 'i' || str[i] == 'o' || str[i] == 'u'){
                return 0;
            }  
        } else return 0;
        
    }
    return 1;
}

int isInt(char *str){
    for(int i = 0; i < strlen(str); i++){
        if(str[i] > 47 && str[i] < 58){
            continue;
        } else return 0;
    }
    return 1;
}

int isFloat(char *str){
    int countP = 0;
    for(int i = 0; i < strlen(str); i++){
        if(str[i] > 48 && str[i] < 58 || str[i] == '.' || str[i] == ','){
            if(str[i] == '.' || str[i] == ','){
                if(countP == 1) return 0;
                else countP++;
            }
        } else return 0;
    }
    return 1;
}

int main(){
    setlocale(LC_ALL, "");
    char linha[1000];
    int num = 0;
    
    //primeira leitura
    fgets(linha, sizeof linha, stdin);
    linha[strlen(linha) - 1] = 0; // retira o \n

    while(isFim(linha) == 0){
        if(isVogal(linha)){
            printf("SIM ");
        } else printf("NAO ");
        
        if(isConsoante(linha)){
            printf("SIM ");
        } else printf("NAO ");

        if(isInt(linha)){
            printf("SIM ");
        } else printf("NAO ");
        
        if(isFloat(linha)){
            printf("SIM\n");
        } else printf("NAO\n");


        num++;
        setbuf(stdin, NULL);
        fgets(linha, sizeof linha, stdin);
        linha[strlen(linha) - 1] = 0;
    }
    return 0;
}