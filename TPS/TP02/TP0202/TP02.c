/**
 * @file TP02.c
 * @author Camila Moreira Lopes
 * @brief 
 * @version 0.1
 * 
 * @copyright Copyright (c) 2022
 * 
 */

/*************** BIBLIOTECAS INCLUIDAS ***************/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

/**************** VARIÁVEIS DE TAMANHO ****************/
#define MAX_CHAR    100
#define MAX_LINE    2000
#define MAX_KEY     50
#define MAX_MOVIES  200
#define FDR_PREFIX  "/tmp/filmes/"

/********************* ESTRUTURAS *********************/
// --------------------------------------------------------------- //
typedef struct date {
    int day,
        month,
        year;
} Date;

//---------------- estrutura do filme ----------------//
typedef struct movie {
    char name[MAX_CHAR];
    char genre[MAX_CHAR];
    char situation[MAX_CHAR];
    char original_title[MAX_CHAR];
    char original_language[MAX_CHAR];
    char keywords[MAX_KEY][MAX_CHAR];

    Date release_date;

    int duration;
    int count_keywords;
    float budget;
} Movie;

/***************** VARIÁVEIS GLOBAIS *****************/
Movie movies[MAX_MOVIES];
int count_movies = 0;

/*******************  FUNÇÕES *******************/
bool isFim(char *str){ return (strlen(str) == 3 && str[0] == 'F' && str[1] == 'I' && str[2] == 'M'); }

// --------------------------------------------------------------- //

char *remove_line_break(char *line) {
    while (*line != '\r' && *line != '\n') line++;
    *line = '\0';
    return line;
}
char *freadline(char *line, int max_size, FILE *file) { return remove_line_break(fgets(line, max_size, file)); }

// --------------------------------------------------------------- //
bool contains(register char *str, char *subStr){
    register char *a, 
                  *b = subStr;

    if(*b == 0){
        return true;
    }

    for(;*str != 0; str += 1){
        if(*str != *b) continue;
        a = str;
        while(1) {
            if(*b == 0) {
                return true;
            } else if(*a++ != *b++){
                break;
            }
        }

        b = subStr;
    }
    return false;
}

void *removeTags(char *str, char *text) {
    char *start = text;
    int count = 0;
    while (*str != '\0') {
        if (*str == '<') count++;
        else if (*str == '>') count--;
        else if (count == 0 && *str != '"') {
            if (*str == '&'){
                while(*(++str) != ';');
            }
            else if (*str != '\r' && *str != '\n') *text++ = *str;
        }
        str++;
    }
    *text = '\0';
}

bool hasSpace(char c) { return (c == '\t' || c == '\n' || c == '\v' || c == '\f' || c == '\r' || c == ' ' ? true : false); }

char *trim(char *str) {
    int len = strlen(str);
    char *back = str + len;

    while (hasSpace(*str)) str++;

    for(len - 1; (hasSpace(str[len])); len--);
    str[len+1] = '\0';

    return str;
}

char *removeString(char *str, const char *sub){
    size_t len = strlen(sub);
    if (len > 0) {
        char *p = str;
        while ((p = strstr(p, sub)) != NULL) {
            memmove(p, p + len, strlen(p + len) + 1);
        }
    }
    return str;
}

/*********  FUNÇÕES DA ESTRUTURA MOVIE *********/
void print_movie(Movie *m){
    printf("%s ", m->name);
    printf("%s ", m->original_title);
    printf("%02i/%02i/%04i ", m->release_date.day, m->release_date.month, m->release_date.year);
    printf("%i ", m->duration);
    printf("%s ", m->genre);
    printf("%s ", m->original_language);
    printf("%s ", m->situation);
    printf("%g ", m->budget);
    
    if(m->count_keywords > 0){
        int i = 0;
        printf("[");
        for(; i < m->count_keywords - 1; i++){
            printf("%s, ", m->keywords[i]);
        } 
        printf("%s]\n", m->keywords[i]);
    } else printf("[]\n");
}

void read_movie(char *url){
    FILE *url_file = fopen(url, "r");
    char line[MAX_LINE];

    // ----------------------------------------------------------- //
    if(url_file == NULL) {
        perror("ERRO NA ABERTURA DO ARQUIVO: ");
        system("pause");
    }

    // ----------------------------------------------------------- //
    char name[MAX_CHAR],
         original_title[MAX_CHAR],
         genre[MAX_CHAR],
         original_language[MAX_CHAR],
         situation[MAX_CHAR],
         keywords[MAX_CHAR];

    Date release_date;
    release_date.day = 0;

    int duration = -1;
    float budget = -1;
    // ----------------------------------------------------------- //
    //--------------------- tratamento de nome --------------------//
    do {
        freadline(line, MAX_LINE, url_file);
    } while (!contains(line, "<h2"));
    freadline(line, MAX_LINE, url_file);
    
    removeTags(line,name);
    strcpy(movies[count_movies].name, trim(name));

    // ----------------------------------------------------------- //
    //-------------- tratamento de data de lançamentp ------------ //
    do {
        freadline(line, MAX_LINE, url_file);
    } while (!contains(line, "<span class=\"release\">"));
    freadline(line, MAX_LINE, url_file);

    if(contains(line, "(BR)"))
        removeString(line, "(BR)");
    else if (contains(line, "(US)"))
        removeString(line, "(US)");
    trim(line);
    
    printf("%s\n", trim(line));
    
}

/************************  FUNÇÃO PRINCIPAL ************************/
int main (){
    size_t prefix_size = strlen(FDR_PREFIX);
    char in[MAX_LINE];

    // ----------------------------------------------------------- //
    strcpy(in, FDR_PREFIX);
    freadline(in + prefix_size, MAX_LINE, stdin);

    while(isFim(in + prefix_size) == false){
        read_movie(in);
        //print_movie(&movies[count_movies++]);
        freadline(in + prefix_size, MAX_LINE, stdin);
    }

    return 0;
}