
#include <GL/glu.h>
#include <GL/gl.h>
#include <GL/glut.h>
#include <math.h>

#ifndef point_h
#define point_h
class Point {
public:
    float x, y;
    void setxy(float x2, float y2)
    {
        x = x2; y = y2;
    }
    //operator overloading for '=' sign
    const Point & operator=(const Point &rPoint)
    {
        x = rPoint.x;
        y = rPoint.y;
        return *this;
    }
 //operator overloading for '+' sign
    Point operator+(const Point &b)
    {
        Point sum;
        sum.x = this->x+ b.x;
        sum.y = this->y+ b.y;

        return sum;
    }

};

void drawLineStrip(Point p[],int n);
void drawDot(Point p);
void drawAxis(int SCREEN_WIDTH,int SCREEN_HEIGHT);

#endif

