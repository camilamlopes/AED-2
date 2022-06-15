/*-----------------------------------------------------------------------------------------------------*/
// @author Camila Moreira Lopes
// 1/2022
/*-----------------------------------------------------------------------------------------------------*/

/* BIBLIOTECAS USADAS */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

/*-----------------------------------------------------------------------------------------------------*/
// Definitions
#define MAX_FilmeS          100
#define MAX_FIELD_SIZE      100
#define MAX_keywords        20
#define MAX_LINE_SIZE       250
#define FDR_PREFIX          "/tmp/filmes/"

/*-----------------------------------------------------------------------------------------------------*/

/* ESTRUTURA DE FILME */
// Structs
typedef struct {
    int ano,
    mes,
    dia;
} Date; 

typedef struct filme{
    char nome[MAX_FIELD_SIZE],
        titulo_original[MAX_FIELD_SIZE],
        genero[MAX_FIELD_SIZE], 
        lingua_original[MAX_FIELD_SIZE], 
        situacao[MAX_FIELD_SIZE],
        keywords[MAX_keywords][MAX_FIELD_SIZE];
    Date lancamento;
    int duration, count_keywords;
    float budget;
} Filme;

/*-----------------------------------------------------------------------------------------------------*/

Filme filmes[MAX_FilmeS];
int count_filmes = 0;

/*-----------------------------------------------------------------------------------------------------*/
long int indexOf(char *str, char *search);
char *substring(char *string, int position, int length);
void str_replace(char *target, const char *needle, const char *replacement);
int firstDigit(const char *str, int start);
char *extractOnlyText(char *html, char *text);
void filme_print(Filme *filme);
void filme_read(char *filenome);
/*-----------------------------------------------------------------------------------------------------*/
// Functions
bool isFim(char *str) { return str[0] == 'F' && str[1] == 'I' && str[2] == 'M'; }

char *remove_line_break(char *line) {
    while (*line != '\r' && *line != '\n') line++;
    *line = '\0';
    return line;
}

char *freadline(char *line, int max_size, FILE *file) { return remove_line_break(fgets(line, max_size, file)); }
char *readline(char *line, int max_size) { return freadline(line, max_size, stdin); }

int main() {
    size_t prefix_size = strlen(FDR_PREFIX);
    char line[MAX_LINE_SIZE];
    strcpy(line, FDR_PREFIX);
    readline(line + prefix_size, MAX_LINE_SIZE);
    
    while(!isFim(line + prefix_size)) {
        filme_read(line);
        filme_print(&filmes[count_filmes++]);
        readline(line + prefix_size, MAX_LINE_SIZE);
    }
    return 0;
}

/*-----------------------------------------------------------------------------------------------------*/

long int indexOf(char *str, char *search) {
    long int pos = strcspn(str, search);
    return pos == strlen(str) ? -1 : pos;
}

char *substring(char *string, int position, int length) {
    char *p;
    int c;
    p = malloc(length+1);
    if(p == NULL) {
        printf("Unable to allocate memory.\n");
        exit(1);
    }
    for(c = 0; c < length; c++) {
        *(p+c) = *(string+position-1);      
        string++;  
    }
    *(p+c) = '\0';
    return p;
}

void str_replace(char *target, const char *needle, const char *replacement) {
    char buffer[1024] = { 0 };
    char *insert_point = &buffer[0];
    const char *tmp = target;
    size_t needle_len = strlen(needle);
    size_t repl_len = strlen(replacement);

    while(1) {
        const char *p = strstr(tmp, needle);
        if(p == NULL) {
            strcpy(insert_point, tmp);
            break;
        }
        memcpy(insert_point, tmp, p - tmp);
        insert_point += p - tmp;
        memcpy(insert_point, replacement, repl_len);
        insert_point += repl_len;
        tmp = p + needle_len;
    }
    strcpy(target, buffer);
}

int firstDigit(const char *str, int start) {
    for(int i = start; i != strlen(str); i++) if(str[i] >= '0' && str[i] <= '9') return i;
    return -1;
}

// Remove tags 
char *extractOnlyText(char *html, char *text) {
    char *start = text;
    
    int pos = 0;
    
    while (*html != '\0') {
        if (*html == '<') {
            if (
                (*(html + 1) == 'p') ||
                (*(html + 1) == 'b' && *(html + 2) == 'r') ||
                (*(html + 1) == '/' && *(html + 2) == 'h' && *(html + 3) == '1') ||
                (*(html + 1) == '/' && *(html + 2) == 't' && *(html + 3) == 'h') ||
                (*(html + 1) == '/' && *(html + 2) == 't' && *(html + 3) == 'd')
            ) break;
            else contagem++;
        }
        else if (*html == '>') contagem--;
        else if (contagem == 0 && *html != '"') {
            if (*html == '&') html = strchr(html, ';');
            else if (*html != '\r' && *html != '\n') *text++ = *html;
        }

        html++;
    }
    *text = '\0';
    return *start == ' ' ? start + 1 : start;
}

// -------------------------------------------------------------------------------- //
// Class filme functions
void filme_print(Filme *filme) {
    printf("%s %s %02i/%02i/%04i %i %s %s %s %g [",
    filme -> nome,
    filme -> titulo_original,
    filme -> lancamento.dia, filme -> lancamento.mes, filme -> lancamento.ano,
    filme -> duration,
    filme -> genero,
    filme -> lingua_original,
    filme -> situacao,
    filme -> budget);
    for(int i = 0; i < filme -> count_keywords; i++) {
        if(i == filme -> count_keywords - 1) printf("%s]\n", filme -> keywords[i]);
        else printf("%s, ", filme -> keywords[i]);
    }
    if(filme -> count_keywords == 0) printf("]\n");
}

void filme_read(char *filenome) {
    FILE *html_file;
    char *line_html = NULL;
    size_t len = 0;
    ssize_t read;

    html_file = fopen(filenome, "r");

    if(html_file == NULL) exit(EXIT_FAILURE);

    // ------------------------------------ //

    // Creating Filme variables
    char *nome = NULL, 
    *titulo_original = NULL,
    *genero = NULL,
    *lingua_original = NULL,
    *situacao = NULL,
    *keywords = NULL;

    Date lancamento;

    lancamento.dia = 0;
    int duration = -1;
    float budget = -1;

    // ------------------------------------ //
    
    // Read HTML line by line
    while((read = getline(&line_html, &len, html_file)) != -1) {

        // --------------------------- //
        // Find Filme nome
        if(nome == NULL) {
            if(strstr(line_html, "<title>") != NULL) {
                nome = strstr(line_html, "<title>") + 7;
                strcpy(filmes[count_filmes].nome, nome);
                str_replace(filmes[count_filmes].nome, "&#8212;", "—");
                filmes[count_filmes].nome[strlen(filmes[count_filmes].nome) - 46] = '\0';
            }
        }

        // --------------------------- //
        // Find Filme original title
        if(titulo_original == NULL) {
            if(strstr(line_html, "<p class=\"wrap\">") != NULL) {
                titulo_original = strstr(line_html, "</strong> ") + 10;
                titulo_original[strlen(titulo_original) - 5] = '\0';
                strcpy(filmes[count_filmes].titulo_original, titulo_original);
            }
        }

        // --------------------------- //
        // Find Filme release date
        if(lancamento.dia == 0) {
            if(strstr(line_html, "<span class=\"release\">") != NULL) {
                // Skip one line
                read = getline(&line_html, &len, html_file);
                char *dia, *mes, *ano;
                dia = substring(line_html, 9, 2);
                mes = substring(line_html, 12, 2);
                ano = substring(line_html, 15, 4);
                filmes[count_filmes].lancamento.dia = atoi(dia);
                filmes[count_filmes].lancamento.mes = atoi(mes);
                filmes[count_filmes].lancamento.ano = atoi(ano);
            }
        }

        // --------------------------- //
        // Find Filme duration
        if(duration == -1) {
            if(strstr(line_html, "<span class=\"runtime\">") != NULL) {
                // Skip two lines
                read = getline(&line_html, &len, html_file);
                read = getline(&line_html, &len, html_file);
                int h_pos = indexOf(line_html, "h"),
                    hours = 0,
                    minutes = 0;
                if(h_pos != -1) hours = atoi(substring(line_html, firstDigit(line_html, 0), h_pos));
                minutes = atoi(substring(line_html, firstDigit(line_html, h_pos == -1 ? 0 : h_pos), strlen(line_html) - 1));
                duration = (hours * 60) + minutes;
                filmes[count_filmes].duration = duration;
            }
        }

        // -------------------------- //
        // Find Filme generos
        if(genero == NULL) {
            if(strstr(line_html, "<span class=\"generos\">") != NULL) {
                // Skip two lines
                read = getline(&line_html, &len, html_file);
                read = getline(&line_html, &len, html_file);
                extractOnlyText(line_html, filmes[count_filmes].genero);
                genero = substring(filmes[count_filmes].genero, 7, strlen(filmes[count_filmes].genero));
                strcpy(filmes[count_filmes].genero, genero);
            }
        }

        // --------------------------- //
        // Find Filme original language
        if(lingua_original == NULL) {
            if(strstr(line_html, "<bdi>Idioma original</bdi>") != NULL) {
                strcpy(filmes[count_filmes].lingua_original, line_html);
                lingua_original = substring(filmes[count_filmes].lingua_original, 50, strlen(line_html) - 54);
                strcpy(filmes[count_filmes].lingua_original, lingua_original);
            }
        }

        // --------------------------- //
        // Find Filme situacao
        if(situacao == NULL) {
            if(strstr(line_html, "<bdi>Situação</bdi>") != NULL) {
                strcpy(filmes[count_filmes].situacao, line_html);
                situacao = substring(filmes[count_filmes].situacao, 44, strlen(line_html) - 44);
                strcpy(filmes[count_filmes].situacao, situacao);
            }
        }

        // --------------------------- //
        // Find Filme budget
        if(budget == -1) {
            if(strstr(line_html, "<bdi>Orçamento</bdi>") != NULL) {
                char *p_budget, e_budget[strlen(line_html)];
                strcpy(e_budget, line_html);
                p_budget = substring(e_budget, 45, strlen(line_html) - 49);
                if(!strcmp(p_budget, "-")) filmes[count_filmes].budget = 0;
                else{
                    strcpy(e_budget, p_budget);
                    str_replace(e_budget, "$", "");
                    str_replace(e_budget, ",", "");
                    filmes[count_filmes].budget = atof(e_budget);
                }
            }
        }

        // --------------------------- //
        // Find Filme keywords
        if(keywords == NULL) {
            if(strstr(line_html, "<h4><bdi>Palavras-chave</bdi></h4>") != NULL) {
                // Skip two lines until keywords starts
                for(int i = 0; i < 2; i++) read = getline(&line_html, &len, html_file);
                char tmp_line[strlen(line_html)];
                strcpy(tmp_line, line_html);
                keywords = substring(tmp_line, 5, strlen(line_html) - 5);

                if(strcmp(keywords, "<p><bdi>Nenhuma palavra-chave foi adicionada.</bdi></p>")) {
                    // Skip more two lines until keywords starts
                    for(int x = 0; x < 2; x++) read = getline(&line_html, &len, html_file);
                    while(true) {
                        if(strstr(line_html, "</ul>") != NULL) break;
                        if(strstr(line_html, "<li>") != NULL){
                            extractOnlyText(line_html, tmp_line);
                            keywords = substring(tmp_line, 9, strlen(line_html) - 8);
                            strcpy(filmes[count_filmes].keywords[filmes[count_filmes].count_keywords++], keywords);
                        }
                        read = getline(&line_html, &len, html_file);
                    }
                }
            }
        }

        // ------------------------------------ //
        // Verify variables still "null"
        if(titulo_original == NULL) strcpy(filmes[count_filmes].titulo_original, filmes[count_filmes].nome);
    }

    // ------------------------------------ //
    fclose(html_file);
    if(line_html) free(line_html);
}

// -------------------------------------------------------------------------------- //
