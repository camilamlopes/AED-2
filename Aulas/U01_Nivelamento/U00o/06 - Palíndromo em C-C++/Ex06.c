/**
 * Palíndromo em C
 * @author Camila Moreira Lopes
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

bool isPalindromo(char str[]){
    for(int i = 0; i < strlen(str)/2; i++){
        if(str[i] != str[strlen(str) - 1 - i]){
            return false;
        }
    }
    return true;
}

/* MÉTODO QUE TESTA SE É FIM */
bool isFim(char str[]){
    return (strlen(str) == 3 && str[0] == 'F' && str[1] == 'I' && str[2] == 'M');
}

/* MÉTODO PRINCIPAL */
int main(){
    char in[1000][100];
    int num_in = 0;
  
    do{
        fgets(in[num_in], sizeof(in[num_in]), stdin);
        in[num_in][strcspn(in[num_in],"\n")] = '\0'; //retirar o enter
    } while(isFim(in[num_in++]) == false);
    num_in--; // é necessário desconsiderar a última linha que contém a palavra isFim

    for(int i = 0; i < num_in; i++){
        if(isPalindromo(in[i])){
            printf("SIM\n");
        } else printf("NAO\n");
    }

    return 0;
}