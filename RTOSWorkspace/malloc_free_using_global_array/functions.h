#ifndef functions_h
#define functions_h
#include <unistd.h>
#include <stddef.h>
#include <stdio.h>
#include<string.h>
void * my_calloc(size_t n,size_t size);
void * my_malloc(size_t size);
void * my_realloc(void *ptr,size_t size);
void  my_free(void *ptr);
size_t free_space_in_my_heap (void);
void print_memory_contents(void);
struct meta{
    struct meta * next; //8B
    int free;           //4B
    size_t size;        //8B
};

//sizeof(metadata)=24 :after padding
//sizeof(pointer)=8
typedef struct meta metadata;


#define THRESHOLD sizeof(int)+sizeof(metadata)
#define BUFF_SIZE 1000000
#endif
