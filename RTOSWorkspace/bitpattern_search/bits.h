/** @file bits.h
 *  @brief Function prototypes for bit pattern check and fill.
 *
 *  This contains the prototypes for checking whether the memory is continuous repetition of given bitpattern
 *  and also to fill memory with the bitpattern.
 *
 *  @author Freeze Francis
 */
#ifndef bits_h
#define bits_h

#include <stdio.h>
#include <stdlib.h>
/** 
 *  @brief Checks whether the memory contains continuos repetition of the bit pattern from the given start address.
 *
 *  @param start_addr pointer to the starting address
 *  @return pointer to the point where the bit pattern repetition fails
 */
unsigned char * check_bit_pattern(unsigned char * start_addr);

/** 
 *  @brief Fills the memory with continuos repetition of the bit pattern from start address to end address.
 *
 *  @param start_addr pointer to the starting address
 *   @param end_addr pointer to the end address
 *  @return void
 */
void fill_pattern(unsigned char * start_addr,unsigned char * end_addr);

/** 
 *  @brief Initializes the memory with  0 from start address to end address.
 *
 *  @param start_addr pointer to the starting address
 *   @param end_addr pointer to the end address
 *  @return void
 */
void init_zero (unsigned char * start_addr,unsigned char* end_addr);
/** 
 *  @brief Computes and stores the byte sequences that repeat using the given pattern.
 *
 *  @param pattern the bit pattern to be processed
 *   @param plength length of the pattern in bits
 *  @return void
 */
void process_pattern(unsigned char pattern,int plength);

#define BYTE_SIZE 8

#endif
