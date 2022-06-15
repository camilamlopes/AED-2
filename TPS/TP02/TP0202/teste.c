#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

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

void *substring (char *str){
    removeString(str, "/");
    int index = 0;
    int 


}

int main() {
    
    char line[2000], resp[2000];
    fgets(line, 2000, stdin);

    removeString(line, "(BR)");
    formatDate(line);
    printf("%s\n", line);
    
    return 0;
}