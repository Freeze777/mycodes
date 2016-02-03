
#ifndef bits_h
#define bits_h

#include <stdio.h>
unsigned char * check_bit_pattern(unsigned char * start_addr);
void fill_pattern(unsigned char * start_addr,unsigned char * end_addr);
void init_zero (unsigned char * start_addr,unsigned char* end_addr);
void process_pattern(unsigned char pattern);
#define PATTERN_LEN 5
#define BYTE_SIZE 8

#endif
