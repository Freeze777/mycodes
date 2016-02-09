/** @file functions.h
 *  @brief Function prototypes for dynamic memory management.
 *
 *  This contains the prototypes for the dynamic memory 
 *  allocations and deallocations including macros,structure declarations.
 *
 *  @author Freeze Francis
 * 
 */



#ifndef functions_h
#define functions_h
#include <unistd.h>
#include <stddef.h>
#include <stdio.h>
#include<string.h>

#define THRESHOLD sizeof(int)+sizeof(metadata)
#define BUFF_SIZE 1000000

/** @brief Allocates requested bytes of memory from the available global array.
 *  
 *  @param size The number of bytes to allocated.
 *  @return The pointer to the allocated memory.
 */
void * my_malloc(size_t size);

/** @brief Allocates requested blocks of memory from the available global array
 *  and initializes its contents to 0.
 *
 *  @param n The number of blocks to allocated.
 *  @param size The size of each block in bytes.
 *  @return The pointer to the allocated memory.
 */

void * my_calloc(size_t n,size_t size);

/** @brief Reallocates the current memory to a new memory location according to requested size.
 *  Expands or shrinks the allocated memory by the give size.  
 * 
 *  @param ptr The pointer the memory to be reallocated.
 *  @param size The new size.
 *  @return The pointer to the reallocated memory.
 */

void * my_realloc(void *ptr,size_t size);

/** @brief Frees the memory pointed to by ptr. 
 *  
 *  @param ptr The pointer the memory to be freed.
 *  @return void
 */

void  my_free(void *ptr);

/** @brief Defragments the global array by fusing adjacent free blocks.
 *  
 *  @param ptr The pointer the current memory.
 *  @param size The new size.
 *  @return The pointer to the reallocated memory.
 */
void deframent_my_heap (void);

/** @brief Computes the available free space in the global array.
 *  
 *  @param void
 *  @return The available free space in bytes.
 */
size_t free_space_in_my_heap (void);

/** @brief Prints the state of the global array with memory block size and associated status (free or not free).
 *  Used for verification of testcases.
 *
 *  @param void
 *  @return void
 */
void print_memory_contents(void);

/** @brief Structure declaration of the metadata block used for memory allocations.
 *  Used internally to implement linked list of allocated memory blocks.
 * 
 */
struct meta{
    struct meta * next; // < Pointer to the next allocated block
    int free;           // < Status to indicate free or not
    size_t size;        // < The size of the allocated block
};


typedef struct meta metadata;



#endif
