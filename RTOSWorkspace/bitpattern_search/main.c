/** @file main.c
 *  @brief Driver program for bit pattern search and fill functionalities
 *  An char array of size 20 is used and a mixture of calls to function are made
 *  and output is printed in the format address v/s value 
 *  @author Freeze Francis
 *  
 */



#include "bits.h"
void print_array_contents(unsigned char*, unsigned char*);

int main()
{
unsigned char A[20];
int length=20;

unsigned char pattern=0b00011011;
int pattern_lenght=5;

process_pattern(pattern,pattern_lenght);
unsigned char * ptr=NULL;
printf("Array base address:%p \n",&A[0]);

init_zero(A,A+(length-1));
print_array_contents(A,A+(length-1));

ptr=check_bit_pattern(A);
printf("Bit pattern check failed at:%p \n",ptr);


fill_pattern(A,A+(length/2));
print_array_contents(A,A+(length-1));


ptr=check_bit_pattern(A);
printf("Bit pattern check failed at:%p \n",ptr);

init_zero(A,A+4);
print_array_contents(A,A+(length-1));

ptr=check_bit_pattern(A+5);
printf("Bit pattern check failed at:%p \n",ptr);

fill_pattern(A,A+(length-2));
print_array_contents(A,A+(length-1));

ptr=check_bit_pattern(A);
printf("Bit pattern check failed at:%p \n",ptr);

    return 0;
}


void print_array_contents(unsigned char* start, unsigned char* end){


while(start<=end)
{printf("%p	:%d \n",start,*start);
start++;
}

printf("\n");

}
