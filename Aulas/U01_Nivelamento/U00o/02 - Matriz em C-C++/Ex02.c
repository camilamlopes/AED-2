/**
 * Matriz em C
 * @author Camila Moreira Lopes
 */

#include <stdio.h>

int main(){
   //Declaracao de variaveis
   int n = scanf ("%i", &n);
   int matriz[n][n];
   int soma[n];

   //Leitura dos elementos
   for(int i = 0; i < n; i++){
      for(int j = 0; j < n; j++){
         scanf ("%i", &matriz[i][j]);
      }
   }

   //Soma das colunas
   for(int i = 0; i < n; i++){
      soma[i] = 0;
      for(int j = 0; j < n; j++){
         soma[i] += matriz[j][i];
      }
   }

   //Escrever saida
   for(int j = 0; j < n; j++){
      printf("%i ", soma[j]);
   }
}