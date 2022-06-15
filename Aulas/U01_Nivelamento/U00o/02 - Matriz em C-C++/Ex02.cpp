/**
 * Matriz em C++
 * @author Camila Moreira Lopes
 */

#include <iostream>
using namespace std;

int main(){
   //Declaracao de variaveis
   int n;
   cin >> n;
   int matriz[n][n];
   int soma[n];

   //Leitura dos elementos
   for(int i = 0; i < n; i++){
      for(int j = 0; j < n; j++){
         cin >> matriz[i][j];
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
   for(int i = 0; i < n; i++){
      cout << soma[i] << " ";
   }
}