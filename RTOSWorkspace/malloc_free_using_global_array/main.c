/** @file main.c
 *  @brief Driver program for dynamic memory allocations functions.
 *  A mixture of calls to function are made
 *  and after each call the memory layout is printed in the format Block-size v/s free-status(0-not free 1-free) 
 *  The amount of free space left is also printed
 *  @author Freeze Francis
 *  
 */
#include "functions.h"

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

    my_free(9859);
    print_memory_contents();
    printf("free space:%lu\n",free_space_in_my_heap());

    return 0;
}

/*
//MENU DRIVEN 
 int choice;
    int size,n,temp;
    metadata * ptrs[50];
    int i=1;
    do

    {

        printf("1 -malloc");
        printf("\n2 -calloc");
        printf("\n3 -realloc");
        printf("\n4 -free");
        printf("\n5 -print_memory_contents");
        printf("\n6 -Quit");

        printf("\nEnter your choice: ");

        scanf("%d", &choice);
        switch(choice)
        {
        case 1: printf("\nEnter size: ");
            scanf("%d", &size);
            ptrs[i++]=my_malloc(size);
            break;

        case 2: printf("\nEnter block_size and #blocks: ");
            scanf("%d %d", &size,&n);
            ptrs[i++]=my_calloc(size,n);
            break;


        case 3: printf("\nEnter  previous_choice_number  and new_size: ");
            scanf("%d %d",&temp,&size);
            ptrs[temp]=my_realloc(ptrs[temp],size);
            break;

        case 4: printf("\nEnter  previous_choice_number to be freed : ");
            scanf("%d",&temp);
            my_free(ptrs[temp]);
            break;

        case 5:
            print_memory_contents();
            break;

        case 6:
            exit(0);
            break;

        }

    } while (1);

*/
