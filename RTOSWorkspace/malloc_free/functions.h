#ifndef functions_h
#define functions_h
#include <unistd.h>
#include <stddef.h>
void * my_calloc(size_t n,size_t size);
void * my_malloc(size_t size);
void  my_free(void *ptr);
struct meta{
    struct meta * next;
    int free;
    size_t size;
};
typedef struct meta metadata;
#endif
