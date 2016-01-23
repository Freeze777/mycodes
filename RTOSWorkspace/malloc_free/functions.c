#include "functions.h"

metadata * heap_base=NULL;
metadata * last=NULL;
static metadata * search_freespace(size_t size);

void * my_malloc(size_t size)
{
    metadata * block=NULL;
    if(heap_base!=NULL)
    {
        block=search_freespace(size);

        if(block==NULL)
        {
            block=sbrk(size+sizeof(metadata));
            last->next=block;
            last=block;
            block->next=NULL;
        }

    }
    else
    {
        block=sbrk(size+sizeof(metadata));
        heap_base=block;
        last=block;
          block->next=NULL;

    }

    block->size=size;
    block->free=0;


    return block+1;

}
void * my_calloc(size_t n,size_t size){
    size_t total=n*size;
    char * temp=(char *)my_malloc(total);

    for (int var = 0; var < total ; ++var)
        temp[var]=0;

    return (void*)temp;
}

void my_free(void *ptr)
{   if(ptr==NULL)
        return;

    metadata * to_free=(metadata *)ptr - 1;
    to_free->free=1;
}



static metadata * search_freespace(size_t size){
    metadata * temp=heap_base;
    while((temp!=NULL)&&!(temp->free && temp->size >= size))
    {
        temp=temp->next;

    }
    return temp;
}
