#include "functions.h"

static char buffer[BUFF_SIZE]={0};
static  metadata * heap_base=(metadata *)buffer;
static metadata * last=NULL;
static metadata * search_freespace(size_t size);
static void fuse(metadata * ptr);
static void split(metadata * ptr,size_t size);
static size_t freespace=BUFF_SIZE;
static int first=1;




void * my_malloc(size_t size)
{
    printf("malloc for %lu\n",size);
    if(size==0)
        return NULL;

    metadata * block=NULL;


    if(!first){
        block=search_freespace(size);
        if(block==NULL)
        {
            block=(metadata *)(last->size+(char*)(last+1));
            last->next=block;
            last=block;
            block->next=NULL;
            block->size=size;
            freespace-=(size+sizeof(metadata));
        }else
        {
            if(block->size >= size+THRESHOLD)
            {   freespace-=block->size;
                split(block,size);
                freespace+=block->next->size;
            }else{
                freespace-=size;
            }
        }

    }else{

        first=0;
        block=heap_base;
        block->size=size;
        block->next=NULL;
        last=block;
        freespace-=(size+sizeof(metadata));


    }


    
    block->free=0;


    return block+1;

}
void * my_calloc(size_t n,size_t size){
    printf("calloc for %lu\n",n*size);
    size_t total=n*size;
    char * temp=(char *)my_malloc(total);

    for (int var = 0; var < total ; ++var)
        temp[var]=0;

    return (void*)temp;
}

void my_free(void *ptr)
{

    if(ptr==NULL)
        return;

    metadata * to_free=(metadata *)ptr - 1;
    printf("free for %lu \n",to_free->size);
    to_free->free=1;
    freespace+=to_free->size;
    fuse(to_free);
}

void * my_realloc(void *ptr,size_t size){


    if(ptr==NULL)
        return my_malloc(size);

    if(size==0)
    {
        my_free(ptr);
        return NULL;
    }

    metadata * block=(metadata *)ptr -1;
    printf("realloc from blocksize: %lu  to size: %lu\n",block->size,size);

    if(block->size < size)//expand
    {
        if(block->next->free==1 && block->next->size >= ( size - block->size -sizeof(metadata)))
        {
            block->size+=sizeof(metadata)+block->next->size;
            freespace-=block->next->size;

            if(block->next->next==NULL)
                last=block;

            block->next=block->next->next;
            //print_memory_contents();

            if(block->size >= size+THRESHOLD)
            {    split(block,size);
                freespace+=block->next->size;
            }
            return block+1;

        }
        char * chunk=(char *)my_malloc(size);
        memcpy(chunk,block+1,block->size);
        my_free(block+1);

        return chunk;

    }else if (block->size > size)//shrink
    {
        if(block->size >= size+THRESHOLD)
        {
            split(block,size);
            freespace+=block->next->size;
            return block+1;
        }

        char * chunk=(char *)my_malloc(size);
        memcpy(chunk,block+1,block->size);
        my_free(block+1);

        return chunk;

    }

}


static metadata * search_freespace(size_t size){
    metadata * temp=heap_base;
    while((temp!=NULL)&&!(temp->free && temp->size >= size))
    {
        temp=temp->next;

    }
    return temp;
}
static void fuse(metadata * ptr){


    while((ptr->next!=NULL) && ptr->next->free==1)
    {
        printf("fusing %lu & %lu\n",ptr->size,ptr->next->size);
        ptr->size+=ptr->next->size+sizeof(metadata);
        ptr->next=ptr->next->next;
        freespace+=sizeof(metadata);
    }
    if(ptr->next==NULL)
    {
        last=ptr;
    }

}
static void split(metadata *ptr,size_t size)
{
    char * temp=(char *) ptr;
    temp+=(sizeof(metadata)+size);
    metadata * chunk=(metadata *) temp;

    chunk->next=ptr->next;
    ptr->next=chunk;

    chunk->free=1;
    chunk->size=(ptr->size)-(size+sizeof(metadata));

    ptr->size=size;

    if(chunk->next==NULL)
    {
        last=chunk;
    }

}


size_t free_space_in_my_heap(void){

    return freespace;

}

void print_memory_contents(void){

    metadata * temp=heap_base;
    while(temp!=NULL)
    {
        printf("%lu %d\n",temp->size,temp->free);
        temp=temp->next;
    }

}
