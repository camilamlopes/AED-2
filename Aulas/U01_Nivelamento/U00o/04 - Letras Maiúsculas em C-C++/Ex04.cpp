/**
 * Letras Maiúsculas C
 * @author Camila Moreira Lopes
 */

#include <iostream>
#include <stdbool.h>
#include <string.h>

using namespace std;

#define NUMLINHA    1000
#define TAMPALAVRA  100

/* MÉTODO QUE TESTA SE É MAIÚSCULA */
bool isMaiusculo(char c){
    return (c >= 'A' && c <= 'Z');
}

/* MÉTODO CONTADOR */
int countMaiusculas (char in[]){
    int answ = 0;
    for(int i = 0; i < strlen(in); i++){
        if(isMaiusculo(in[i]) == true){
            answ++;
        }
    }
    return answ;
}

/* MÉTODO QUE TESTA SE É FIM */
bool isFim(char str[]){
    return (strlen(str) == 3 && str[0] == 'F' && str[1] == 'I' && str[2] == 'M');
}

/* MÉTODO PRINCIPAL */
int main(){
    char in[NUMLINHA][TAMPALAVRA];
    int num_in = 0;
  
    do{
        cin >> in[num_in];
    } while(isFim(in[num_in++]) == false);
    num_in--; // é necessário desconsiderar a última linha que contém a palavra isFim

    for(int i = 0; i < num_in; i++){
        cout << countMaiusculas(in[i]) << endl;
    }

    return 0;
}