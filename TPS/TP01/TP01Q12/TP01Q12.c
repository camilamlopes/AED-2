/* Bibliotecas Usadas*/
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <locale.h>

/* MÉTODO RESPONSÁVEL POR FIM */
int isFim(char *str){
    if (strlen(str) == 3 && str[0] == 'F' && str[1] == 'I' && str[2] == 'M') return 1;
    return 0;
}

/* MÉTODO É PALÍNDROMO OU NÃO */
int isPalindromo(char *str, int pos){
    if(pos == (strlen(str)/2)) return 1;
    else if(str[pos] != str[strlen(str) - 1 - pos]){
        return 0;
    } else return isPalindromo(str, pos + 1);
}

/* MÉTODO PRINCIPAL*/
int main(){
    setlocale(LC_ALL, "");
    char linha[1000];
    int num = 0;
    
    //primeira leitura
    fgets(linha, sizeof linha, stdin);
    linha[strlen(linha) - 1] = 0; // retira o \n

    while(isFim(linha) == 0){
        if(isPalindromo(linha, 0)){
            printf("SIM\n");
        }
        else{
            printf("NAO\n");
        }

        num++;
        setbuf(stdin, NULL);
        fgets(linha, sizeof linha, stdin);
        linha[strlen(linha) - 1] = 0;
    }

    return 0;
}