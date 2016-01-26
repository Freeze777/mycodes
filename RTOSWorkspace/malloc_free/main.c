#include "functions.h"
#include <stdio.h>
int main()
{


    int* arr=(int *)my_malloc(10*sizeof(int));
    print_memory_contents();
    printf("\n");

    int* num=(int *)my_malloc(5*sizeof(int));
    print_memory_contents();
    printf("\n");

    my_free(arr);
    print_memory_contents();
    printf("\n");

    char* ch=(char *)my_malloc(10*sizeof(char));
    print_memory_contents();
    printf("\n");

    int* t=(int *)my_malloc(4*sizeof(int));
    print_memory_contents();
    printf("\n");

    my_free(num);
    print_memory_contents();
    printf("\n");

    my_free(ch);
    print_memory_contents();
    printf("\n");

    int* a=(int *)my_malloc(50*sizeof(int));
    print_memory_contents();
    printf("\n");

    int* b=(int *)my_malloc(10*sizeof(int));
    print_memory_contents();
    printf("\n");

    my_free(a);
    print_memory_contents();
    printf("\n");

    int* c=(int *)my_malloc(10*sizeof(int));
    print_memory_contents();
    printf("\n");

    my_free(c);
    print_memory_contents();
    printf("\n");

    my_free(t);
    print_memory_contents();
    printf("\n");

    my_free(b);
    print_memory_contents();
    printf("\n");

    int * e=(int *)my_malloc(348);
    print_memory_contents();
    printf("\n");

    int * d=(int *)my_malloc(400);
    print_memory_contents();
    printf("\n");

    my_free(d);
    print_memory_contents();
    printf("\n");

    my_free(e);
    print_memory_contents();
    printf("\n");

    int * g= my_calloc(5,100);
    print_memory_contents();
    printf("\n");

    g= my_realloc(g,1000);
    print_memory_contents();
    printf("\n");

    g= my_realloc(g,200);
    print_memory_contents();
    printf("\n");

    g= my_realloc(g,300);
    print_memory_contents();
    printf("\n");


   int*y= my_malloc(500);
    print_memory_contents();
    printf("\n");

    y= my_realloc(y,600);
    print_memory_contents();
    printf("\n");

   int *z= my_calloc(5,100);
   print_memory_contents();
   printf("\n");

   y= my_realloc(y,200);
   print_memory_contents();
   printf("\n");

   my_free(y);
   print_memory_contents();
   printf("\n");

   return 0;
}
