#include "functions.h"
#include <stdio.h>
int main()
{


   int* arr=(int *)my_malloc(5*sizeof(int));
   for (int var = 0; var < 5; ++var) {
       arr[var]=var*10;
   }
   for (int var = 0; var < 5; ++var) {
       printf("%d ",arr[var]);
   }
printf("\n");
    int* num=(int *)my_malloc(2*sizeof(int));
    char* ch=(char *)my_malloc(10*sizeof(char));
    for (int var = 0; var < 10; ++var) {
        ch[var]=65+var;
    }

   my_free(arr);

   int* a=(int *)my_malloc(2*sizeof(int));
   a[0]=1000;
   a[1]=200;


for (int var = 0; var < 10; ++var) {
    printf("%d ",arr[var]);
}
printf("\n");
for (int var = 0; var < 10; ++var) {
    printf("%c ",ch[var]);
}
   return 0;
}
