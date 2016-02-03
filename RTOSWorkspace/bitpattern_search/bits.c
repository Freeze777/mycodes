#include "bits.h"


static unsigned char shifted_patterns[PATTERN_LEN];


void process_pattern(unsigned char pattern)
{
    unsigned long i=0;
    unsigned long bit_mask=0x00000000000000ff;
    for (int var = 1; var <= BYTE_SIZE; ++var)
        i=(i|pattern)<<PATTERN_LEN;

    i=i>>PATTERN_LEN;  //byte_size*pattern_len bits of repeated pattern

    for (int var = 0; var < PATTERN_LEN; ++var) {
        shifted_patterns[PATTERN_LEN-1-var]=(i & bit_mask);//last 8 bits stored in unsigned char
        i=i>>BYTE_SIZE;
    }
    /* for (int var = 0; var < PATTERN_LEN; ++var) {
        printf("%d ",(int)shifted_patterns[var]);
    }
*/

}
unsigned char * check_bit_pattern(unsigned char * start_addr){
    unsigned int count=0;
    unsigned char flag=1;
    while(flag)
    {
        flag=flag & (((*start_addr)^shifted_patterns[count++])==0);
        start_addr++;
        count%=PATTERN_LEN;
    }

    return start_addr-1;

}
void fill_pattern(unsigned char *start_addr, unsigned char *end_addr){
    unsigned int count=0;
    while(start_addr<=end_addr)
    {
        *start_addr=shifted_patterns[count++];
        start_addr++;
        count%=PATTERN_LEN;

    }

}
void init_zero (unsigned char * start_addr,unsigned char* end_addr){

while(start_addr<=end_addr)
{
    *start_addr=0;
    start_addr++;
}

}
