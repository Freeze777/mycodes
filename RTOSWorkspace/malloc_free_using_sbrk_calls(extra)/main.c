#include "functions.h"
#include <stdio.h>
int main()
{


    int* arr=(int *)my_malloc(10*sizeof(int));
    print_memory_contents();
    printf("free space:%lu\n",free_space_in_my_heap());

    int* num=(int *)my_malloc(5*sizeof(int));
    print_memory_contents();
    printf("free space:%lu\n",free_space_in_my_heap());

    my_free(arr);
    print_memory_contents();
    printf("free space:%lu\n",free_space_in_my_heap());

    char* ch=(char *)my_malloc(10*sizeof(char));
    print_memory_contents();
    printf("free space:%lu\n",free_space_in_my_heap());

    int* t=(int *)my_malloc(4*sizeof(int));
    print_memory_contents();
    printf("free space:%lu\n",free_space_in_my_heap());

    my_free(num);
    print_memory_contents();
    printf("free space:%lu\n",free_space_in_my_heap());

    my_free(ch);
    print_memory_contents();
    printf("free space:%lu\n",free_space_in_my_heap());

    int* a=(int *)my_malloc(50*sizeof(int));
    print_memory_contents();
    printf("free space:%lu\n",free_space_in_my_heap());

    int* b=(int *)my_malloc(10*sizeof(int));
    print_memory_contents();
    printf("free space:%lu\n",free_space_in_my_heap());

    my_free(a);
    print_memory_contents();
    printf("free space:%lu\n",free_space_in_my_heap());

    int* c=(int *)my_malloc(10*sizeof(int));
    print_memory_contents();
    printf("free space:%lu\n",free_space_in_my_heap());

    my_free(c);
    print_memory_contents();
    printf("free space:%lu\n",free_space_in_my_heap());

    my_free(t);
    print_memory_contents();
    printf("free space:%lu\n",free_space_in_my_heap());

    my_free(b);
    print_memory_contents();
    printf("free space:%lu\n",free_space_in_my_heap());

    int * e=(int *)my_malloc(348);
    print_memory_contents();
    printf("free space:%lu\n",free_space_in_my_heap());

    int * d=(int *)my_malloc(400);
    print_memory_contents();
    printf("free space:%lu\n",free_space_in_my_heap());

    my_free(d);
    print_memory_contents();
    printf("free space:%lu\n",free_space_in_my_heap());

    my_free(e);
    print_memory_contents();
    printf("free space:%lu\n",free_space_in_my_heap());

    int * g= my_calloc(5,100);
    print_memory_contents();
    printf("free space:%lu\n",free_space_in_my_heap());

    g= my_realloc(g,1000);
    print_memory_contents();
    printf("free space:%lu\n",free_space_in_my_heap());

    g= my_realloc(g,200);
    print_memory_contents();
    printf("free space:%lu\n",free_space_in_my_heap());

    g= my_realloc(g,300);
    print_memory_contents();
    printf("free space:%lu\n",free_space_in_my_heap());


   int*y= my_malloc(500);
    print_memory_contents();
    printf("free space:%lu\n",free_space_in_my_heap());

    y= my_realloc(y,600);
    print_memory_contents();
    printf("free space:%lu\n",free_space_in_my_heap());

   int *z= my_calloc(5,100);
   print_memory_contents();
   printf("free space:%lu\n",free_space_in_my_heap());

   y= my_realloc(y,200);
   print_memory_contents();
   printf("free space:%lu\n",free_space_in_my_heap());

   my_free(y);
   print_memory_contents();
   printf("free space:%lu\n",free_space_in_my_heap());

   return 0;
}
