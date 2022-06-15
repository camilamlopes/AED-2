/**
 * @author Camila Moreira Lopes
 * Faça um programa que leia n números inteiros, armazene-os em um arquivo, leia-os do arquivo e mostre-os na tela
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>


void main(int argc, char** argv){
    int n = scanf("%i", &n);
    int vetor[n];
    for(int i = 0; i < n; i++){
        scanf("%i", &vetor[i]);
    }
    
    FILE *p = fopen("ex01.txt", "wd");
    for(int i = 0; i < n; i++){
        fwrite(&vetor[i], sizeof(int),1,p);
    }
    fclose(p);

}