#include "bits.h"


int main()
{
unsigned char A[50];

A[0]=0b11011110;
A[1]=0b11110111;
A[2]=0b10111101;
A[3]=0b11101111;
A[4]=0b01111011;
A[5]=0b11011110;
A[6]=0b11110111;
A[7]=0b10111101;
A[8]=0x00;

unsigned char pattern=0b00111011;
process_pattern(pattern,6);
printf("%p \n",&A[0]);
unsigned char * ptr=check_bit_pattern(&A[0]);
printf("%p \n",ptr);


for (int var = 0; var < 9; ++var)
    printf("%d ",A[var]);


fill_pattern(&A[0],&A[8]);

printf("\n");

for (int var = 0; var < 9; ++var)
    printf("%d ",A[var]);

printf("\n");
init_zero(&A[2],&A[6]);


for (int var = 0; var < 9; ++var)
    printf("%d ",A[var]);

    return 0;
}
