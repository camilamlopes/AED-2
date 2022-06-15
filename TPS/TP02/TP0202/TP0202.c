/* RESUMO DAS FUNÇÕES CRIADAS */
int convertMinutes(char str[]);
int *formatDate(char str[]);
char *removeString(char str[], char remove[]);



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

    return point
}
void setDataLancamento(int *dataLancamento, Filme *filme){
    memcpy(filme->dataLancamento, dataLancamento, sizeof(filme->dataLancamento));
}
void setTituloOriginal(Filme *filme){

}
void setIdiomaOriginal(char *idiomaOriginal, Filme *filme){
    filme -> idiomaOriginal[0] = idiomaOriginal[0];
    filme -> idiomaOriginal[1] = idiomaOriginal[1];
    filme -> idiomaOriginal[2] = idiomaOriginal[2];

}

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

<<<<<<< HEAD
    for(int i = st; i < len; i++){
        clear[i-st] += str[i];
    }
    return clear;
=======
    if((st > 0) || (len < strlen(str))){
        char *clear;
        return strncpy(clear, str + st, len - st);
    } else return str;
>>>>>>> 211b6656bf1febd8f2aa3702a8cc560d8c79bfd5
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
<<<<<<< HEAD
    char *clear = (char *)malloc(MAX_CHAR*10);
=======
    char *clear;
>>>>>>> 211b6656bf1febd8f2aa3702a8cc560d8c79bfd5
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

void ler(char *url, Filme *filme){
<<<<<<< HEAD
    char line[MAX_CHAR*2];             //modificar o max char pra 2k
    char aux_line[MAX_CHAR*2];
=======
    char line[1000];

>>>>>>> 211b6656bf1febd8f2aa3702a8cc560d8c79bfd5
    /*char *str = "/tmp/filmes/" + url;
    FILE *arq = fopen(str, "r");*/
    FILE *arq = fopen(url, "r");

    if(arq == NULL){
        perror("ERRO NA ABERTURA DO ARQUIVO: ");
        system("pause");
    }
<<<<<<< HEAD
    
    /*************************** TRATAMENTO DO NOME ***************************/
    do{
        fgets(line, sizeof(line) ,arq);
    } while(contains(line, "<h2") == false);
    fgets(line, sizeof(line) ,arq);
    
    strcpy(aux_line, removeTags(line));         //retira as tags
    strcpy(line, trim(aux_line));               //retira os espaços extras
=======


    /* TRATAMENTO DO NOME */
    do{
        fgets(line, sizeof(line) ,arq);
    } while(contains(line, "<h2") == false);

     fgets(line, sizeof(line) ,arq);

     memcpy(line, removeTags(line), sizeof(line));
     setNome(trim(line), filme);
>>>>>>> 211b6656bf1febd8f2aa3702a8cc560d8c79bfd5

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




