#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
typedef struct point{
   long long int x; long long int y; 
   
   
}Point;


 long long int sign (Point p2, Point p3)
{
    return (0 - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (0 - p3.y);
}

bool PointTriangle (Point v1, Point v2, Point v3)
{
    bool b1, b2, b3;
    Point pt;
    pt.x=pt.y=0;

    b1 = sign( v1, v2) <= 0;
    b2 = sign( v2, v3) <= 0;
    b3 = sign( v3, v1) <= 0;

    return ((b1 == b2) && (b2 == b3));
}
int main() {

   int tst;
   int count=0;
  	Point a,b,c;
  	scanf("%d",&tst);
  	while(tst>0){
  	scanf("%lld%ld%lld%lld%lld%lld",&a.x,&a.y,&b.x,&b.y,&c.x,&c.y);
  	if(PointTriangle(a,b,c))
  		count++;
  	tst--;
  	}
   printf("%lld",count);
    return 0;
}