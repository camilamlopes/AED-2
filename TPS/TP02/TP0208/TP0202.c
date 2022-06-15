/* BIBLIOTECAS USADAS */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

/* GLOBAL VARIABLES */
#define MAX_CHAR 1000
#define MAX_QNT 100


/* ESTRUTURA DE FILME */
typedef struct filme{
    char nome[MAX_CHAR];
    char tituloOriginal[MAX_CHAR];
    char genero[MAX_CHAR];
    char idiomaOriginal[MAX_CHAR];
    char situacao[MAX_CHAR];    
    int dataDia, dataMes, dataAno;

    int duracao;
    float orcamento;

    char palavraChave[MAX_QNT][MAX_QNT];
    int tamPalavraChave;
} Filme;

/*Construtor*/
Filme new_filme(){
    Filme f;
    strcpy(f.nome,"");
    strcpy(f.tituloOriginal,"");
    strcpy(f.genero,"");
    strcpy(f.idiomaOriginal,"");
    strcpy(f.situacao,"");

    f.dataDia = 0;
    f.dataMes = 0;
    f.dataAno = 0;

    f.duracao = 0;
    f.orcamento = 0.0;
    
    return f;
} 


/*--------------------- GETTERS ---------------------*/
char *getNome(Filme *filme){
    return filme -> nome;
}
int getDuracao(Filme *filme){
    return filme -> duracao;
}
char *getGenero(Filme *filme){
    return filme -> genero;
}
char *getSituacao(Filme *filme){
    return filme -> situacao;
}
float getOrcamento(Filme *filme){
    return filme -> orcamento;
}

int getDataDia(Filme *filme){
    return filme -> dataDia;
}
int getDataMes(Filme *filme){
    return filme -> dataMes;
}
int getDataAno(Filme *filme){
    return filme -> dataAno;
}

char *getTituloOriginal(Filme *filme){
    return filme -> tituloOriginal;
}
char *getIdiomaOriginal(Filme *filme){
    return filme -> idiomaOriginal;
}

/*--------------------- SETTERS ---------------------*/

void setNome(char *nome, Filme *filme){
    memcpy(filme->nome, nome, sizeof(filme->nome));
}
void setDuracao(int duracao, Filme *filme){
    filme -> duracao = duracao;
}
void setGenero(char *genero, Filme *filme){
    memcpy(filme->genero, genero, sizeof(filme->genero));
}
void setSituacao(char *situacao, Filme *filme){
    memcpy(filme->situacao, situacao, sizeof(filme->situacao));
}
void setOrcamento(float orcamento, Filme *filme){
    filme -> orcamento = orcamento;
}

void setDataDia(int dataDia ,Filme *filme){
    filme -> dataDia = dataDia;
}
void setDataMes(int dataMes ,Filme *filme){
    filme -> dataMes = dataMes;
}
void setDataAno(int dataAno ,Filme *filme){
    filme -> dataAno = dataAno;
}
void setTituloOriginal(char *tituloOriginal, Filme *filme){
    strcpy(filme->tituloOriginal, tituloOriginal);
}
void setIdiomaOriginal(char *idiomaOriginal, Filme *filme){
    strcpy(filme->idiomaOriginal, idiomaOriginal);
}


/* RESUMO DAS FUNÇÕES CRIADAS */
int convertMinutes(char str[]);
int *formatDate(char str[]);
char *removeString(char str[], char remove[]);
char *trim(char *str);
bool contains(char *str, char *subStr);
char *removeTags(char *str);
Filme ler(char *url);
void imprimir(Filme *filme);

/**
 * Retorna um valor booleano se a linha em análise é FIM
 * @param str
 */
bool isFim(char *str){ return (strlen(str) == 3 && str[0] == 'F' && str[1] == 'I' && str[2] == 'M'); }

int main(){
    int count = 0;
    char url[MAX_QNT][MAX_QNT*2];

    // estrutura para as leituras de url
    fgets(url[count], sizeof(url[count]), stdin);
    url[count][strcspn(url[count], "\n")] = '\0';

    while(isFim(url[count]) == false){
        count++;
        // leitura do endereço do site
        fgets(url[count], sizeof(url[count]), stdin);
        url[count][strcspn(url[count], "\n")] = '\0';
    }

    // estrutura para leitura e definição de um array
    Filme filme[count];
    for(int i = 0; i < count; i++){
        filme[i] = ler(url[i]);
    }

    for(int i = 0; i < count; i++){
        printf("[%i] ", i);
        imprimir(filme);
    }

    return 0;
}

int convertMinutes(char str[]){
    int time = 0, j = 0;
    char tmp[strlen(str)];

    for(int i = 0; i < strlen(str); i++){
        if(str[i] == 'h'){
            time = atoi(tmp) * 60;
            tmp[0] = '\0';
            j = 0;
        } else if(str[i] == 'm'){
            time += atoi(tmp);
            i = strlen(str);
        } else tmp[j++] = str[i];
    }

    return time;
}

int *formatDate(char str[]){
    int date[3], index[2];
    int* pointerDate = date;
    char aux[15] = "";
    
    for(int i = 0, j = 0; i < strlen(str); i++){
        if(str[i] == '/') index[j++] = i;
    }
    
    memset(aux, '\0', sizeof(aux));
    strncpy(aux, str, index[0] + 1);

    date[0] = atoi(aux);

    memset(aux, '\0', sizeof(aux));
    strncpy(aux, str + index[0] + 1, index[1] - index[0]);

    date[1] = atoi(aux);

    memset(aux, '\0', sizeof(aux));
    strncpy(aux, str + index[1] + 1, strlen(str) - index[1]);

    date[2] = atoi(aux);

    return pointerDate;
}

char *removeString(char str[], char remove[]){
    char *point = str;
    bool isRemoved;
    
    for(int i = 0; i < strlen(str); i++){
        if(str[i] == remove[0]){
            isRemoved = true;

            for(int j = 0; j < strlen(remove); j++){
                if(str[i+j] != remove[j]){
                    isRemoved = false;
                }
            }

            if(isRemoved){
                for(int j = 0; j < strlen(remove); j++){
                    for(int k = i; k < strlen(str); k++){
                        str[k] = str[k+1];
                    }
                }

                str[strlen(str) - 1] = '\0';
                i--;
            }
        }
    }

    return point;
}

/**
 * 
 * @param str 
 * @return string 
 */
char *trim(char *str){
    char *clear = (char *)malloc(MAX_CHAR*10);
    int len = strlen(str);
    int st = 0;

    while ((st < len) && (str[st] <= ' ')) {
        st++;
    }

    while ((st < len) && (str[len - 1] <= ' ')) {
        len--;
    }

    for(int i = st; i < len; i++){
        clear[i-st] += str[i];
    }
    return clear;
}

/**
 * See if str contains substring
 * @param str
 * @param subStr
 * @return true/false
 */
bool contains(char *str, char *subStr){
    bool isContido = false;

    for(int i = 0; i < strlen(str); i++){               //percorre str

        if(str[i] == subStr[0]){
            isContido = true;

            for(int j = 0; j < strlen(subStr); j++){    // se tivera primeira letra da subStr começa a comparar cada posição
                if(str[i+j] != subStr[j]){              // se alguma das letras forem diferentes é encerrado a comparação
                    j = strlen(subStr);
                    isContido = false;
                }
            }

            if(isContido) i = strlen(str);              // se isContido for verdadeiro é encerrado o loop maior;
        }
    }
    return isContido;
}

/**
 * Remove everything between the tags
 * @param str
 * @return clear string
 */
char *removeTags(char *str){
    char *clear = (char *)malloc(MAX_CHAR*10);

    int pos = 0;

    for(int i = 0; i < strlen(str); i++){
        if(str[i] == '<'){
            while(str[i] != '>') i++;
            if(i+1 < strlen(str) && str[i+1] == ' ') i++; // se a frase iniciar com espaço o mesmo é retirado
        } else if(str[i] == '&'){
            while(str[i] != ';') i++;
        } else{
            clear[pos++] += str[i];
        }
    }
    return clear;
}

Filme ler(char *url){
    Filme *filme;
    char line[MAX_CHAR*2];             //modificar o max char pra 2k
    char aux_line[MAX_CHAR*2];

    char *str = strcat("/tmp/filmes/", url);
    FILE *arq = fopen(str, "r");

    if(arq == NULL){
        perror("ERRO NA ABERTURA DO ARQUIVO: ");
        system("pause");
    }
    
    /*************************** TRATAMENTO DO NOME ***************************/
    do{
        fgets(line, sizeof(line) ,arq);
    } while(contains(line, "<h2") == false);
    fgets(line, sizeof(line) ,arq);
    
    strcpy(aux_line, removeTags(line));         //retira as tags
    strcpy(line, trim(aux_line));               //retira os espaços extras

    setNome(line, filme);   


    /********************* TRATAMENTO DATA DE LANÇAMENTO *********************/      
    while(contains(line, "<span class=\"release\">") == false){
        fgets(line, sizeof(line) ,arq);
    }       
    fgets(line, sizeof(line) ,arq);
    
    if(contains(line, "(BR)")) strcpy(line, removeString(line, "(BR)"));
    else if(contains(line, "(US)")) strcpy(line, removeString(line, "(US)"));

    strcpy(line, trim(line));

    int* filterDate = formatDate(line);

    setDataDia(filterDate[0], filme);
    setDataMes(filterDate[1], filme);
    setDataAno(filterDate[2], filme);


    /************************* TRATAMENTO DO GÊNERO ***************************/
    while(contains(line, "<span class=\"genres\">") == false){
        fgets(line, sizeof(line) ,arq);
    } 
    fgets(line, sizeof(line) ,arq);
    fgets(line, sizeof(line) ,arq);

    strcpy(aux_line, removeTags(line));         //retira as tags
    strcpy(line, trim(aux_line));               //retira os espaços extras

    setGenero(line, filme);


    /************************ TRATAMENTO DE DURAÇÃO **************************/
    while(contains(line, "runtime") == false){
        fgets(line, sizeof(line) ,arq);
    } 
    fgets(line, sizeof(line) ,arq);
    fgets(line, sizeof(line) ,arq);
    
    strcpy(aux_line, trim(line));               //retira os espaços extras

    setDuracao(convertMinutes(aux_line), filme);


    /************************ TRATAMENTO DE TÍTULO ORIGINAL **************************/
    while(contains(line, "Título original") == false && contains(line, "<bdi>Situação</bdi>") == false){
        fgets(line, sizeof(line) ,arq);
    }

    if(contains(line, "<bdi>Situação</bdi>") == false){
        strcpy(aux_line, removeString(line, "Título original"));
        strcpy(line, removeTags(aux_line));         //retira as tags
        strcpy(aux_line, trim(line));               //retira os espaços extras

        setTituloOriginal(aux_line, filme);
    } else setTituloOriginal(getNome(filme), filme);


    /************************ TRATAMENTO DE SITUAÇÃO **************************/
    while(contains(line, "<bdi>Situação</bdi>") == false){
        fgets(line, sizeof(line) ,arq);
    }
    
    strcpy(aux_line, removeString(line, "Situação"));
    strcpy(line, removeTags(aux_line));         //retira as tags
    strcpy(aux_line, trim(line));               //retira os espaços extras

    setSituacao(aux_line, filme);


    /************************ TRATAMENTO DE IDIOMA ORIGINAL **************************/
    while(contains(line, "Idioma original") == false){
        fgets(line, sizeof(line) ,arq);
    }

    strcpy(aux_line, removeString(line, "Idioma original"));
    strcpy(line, removeTags(aux_line));         //retira as tags
    strcpy(aux_line, trim(line));               //retira os espaços extras

    setIdiomaOriginal(aux_line, filme);


    /************************ TRATAMENTO DE IDIOMA ORIGINAL **************************/
    while(contains(line, "Orçamento") == false){
        fgets(line, sizeof(line) ,arq);
    }

    if(contains(line, "-")){
        setOrcamento(0, filme);
    } else{
        strcpy(aux_line, removeString(line, "Orçamento"));
        strcpy(line, removeString(aux_line, "$"));
        strcpy(aux_line, removeString(line, ","));
        strcpy(line, removeTags(aux_line));         //retira as tags
        strcpy(aux_line, trim(line));               //retira os espaços extras

        setOrcamento(atof(aux_line), filme);
    }


    /************************ TRATAMENTO DE PALAVRAS-CHAVE **************************/
    int count = 0;
    char tmp[MAX_QNT][MAX_QNT];
    while(contains(line, "Palavras-chave") == false){
        fgets(line, sizeof(line) ,arq);
    }

    do{
        fgets(line, sizeof(line) ,arq);

        if(contains(line, "li")){
            strcpy(aux_line, removeTags(line));
            strcpy(tmp[count], trim(aux_line));
            count++;
        }
    } while(contains(line, "</section>") == false);

    filme->tamPalavraChave = count;
    if(count > 0){
        for(int i = 0; i < count; i++){
            strcpy(filme->palavraChave[i], tmp[i]);
        }        
    }
}

void imprimir(Filme *filme){
    printf("%s ", getNome(filme));
    printf("%s ", getTituloOriginal(filme));

    if(getDataDia(filme) < 10){
        if(getDataMes(filme) < 10){
            printf("0%i/0%i/%i", getDataDia(filme), getDataMes(filme), getDataAno(filme));
        } else{
            printf("0%i/%i/%i", getDataDia(filme), getDataMes(filme), getDataAno(filme));
        }
    } else{
        if(getDataMes(filme) < 10){
            printf("%i/0%i/%i ", getDataDia(filme), getDataMes(filme), getDataAno(filme));
        } else{
            printf("%i/%i/%i ", getDataDia(filme), getDataMes(filme), getDataAno(filme));
        }
    }
    
    // 
    printf("%i ", getDuracao(filme));
    printf("%s ", getGenero(filme));
    printf("%s ", getIdiomaOriginal(filme));
    printf("%s ", getSituacao(filme));
    printf("%.1f ", getOrcamento(filme));
    
    if(filme->tamPalavraChave > 0){
        int i = 0;
        printf("[");
        for(; i < filme->tamPalavraChave - 1; i++){
            printf("%s, ", filme->palavraChave[i]);
        }
        printf("%s]\n", filme->palavraChave[i]);
    } else printf("[]\n");
}





