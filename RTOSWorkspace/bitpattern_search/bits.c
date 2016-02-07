#include "bits.h"


static unsigned char * shifted_patterns;
static int length;


void process_pattern(unsigned char pattern,int plength)
{	
    length=plength;
    shifted_patterns=(unsigned char *)malloc(length*sizeof(char));
    unsigned long i=0;
    unsigned long bit_mask=0x00000000000000ff;

    for (int var = 1; var < BYTE_SIZE; ++var)
        i=(i|pattern)<<length;

    i=i|pattern;  //byte_size*length bits of repeated pattern

    for (int var = 0; var < length; ++var) {
        shifted_patterns[length-1-var]=(i & bit_mask);//last 8 bits stored in unsigned char
        i=i>>BYTE_SIZE;
    }
    /* for (int var = 0; var < length; ++var) {
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
        count%=length;
    }

    return start_addr-1;

}
void fill_pattern(unsigned char *start_addr, unsigned char *end_addr){
    unsigned int count=0;
    while(start_addr<=end_addr)
    {
        *start_addr=shifted_patterns[count++];
        start_addr++;
        count%=length;

    }

}
void init_zero (unsigned char * start_addr,unsigned char* end_addr){

while(start_addr<=end_addr)
{
    *start_addr=0;
    start_addr++;
}

}
