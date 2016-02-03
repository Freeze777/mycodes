
#include "curves.h"
#include <math.h>
float binomial_coff(float n,float k) {
    if(k == 0)
        return 1;
    if(k > n/2)
        return binomial_coff(n,n-k);
    return (n * binomial_coff(n-1,k-1)) / k;
}

Point bezierHelper(Point p[],int size,double t) {
    Point P;
    P.x = 0; P.y = 0;
    for (int i = 0; i<size; i++)
    {
        P.x = P.x + binomial_coff((float)(size - 1), (float)i) * pow(t, (double)i) * pow((1 - t), (size - 1 - i)) * p[i].x;
        P.y = P.y + binomial_coff((float)(size - 1), (float)i) * pow(t, (double)i) * pow((1 - t), (size - 1 - i)) * p[i].y;
    }

    return P;
}
void computeBezierCurvePoints(Point p[],int size,Point bz[])
{


    /* Draw each segment of the curve.Make t increment in smaller amounts for a more detailed curve.*/
    int i=1;
    bz[0]=p[0];
    for(double t = 0.0;t <= 1.0; t += 0.02)
    {
        Point p2 = bezierHelper(p,size,t);
        bz[i++]=p2;
    }

    bz[i-1]=p[size-1];




}

void computeLagrangeCurvePoints(Point p[],int size,Point lg[])
{
    glColor3f(1.0,0.0,1.0);
    float stepSize=(p[size-1].x-p[0].x)/50.0;

    lg[0]=p[0];
    int i=1;
    for(float x=p[0].x;i<=50;x+=stepSize)
    {

        float y=0;
        for(int j=0;j<size;j++)
        {
            float l=1;
            for(int m=0;m<size;m++)
            {
                if(m!=j)
                {
                    l*=(x-p[m].x)/(p[j].x -p[m].x);
                }

            }
            y+=l*p[j].y;
        }
        Point p;
        p.x=x;
        p.y=y;
        lg[i++]=p;


    }
    lg[i-1]=p[size-1];

}

