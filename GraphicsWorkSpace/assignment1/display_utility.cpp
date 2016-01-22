
#include "display_utility.h"
void drawDot(Point p) {
    glBegin(GL_POINTS);
    glVertex3f(p.x,p.y,0);
    glEnd();
    glFlush();
}

void drawLineStrip(Point p[],int n) {
    glBegin(GL_LINE_STRIP);

      for(int i=0;i<n;i++)
          glVertex3f(p[i].x,p[i].y,0.0);

    glEnd();

}

void drawAxis(int SCREEN_WIDTH,int SCREEN_HEIGHT)
{

    glPushMatrix();
    glLoadIdentity();
    gluOrtho2D(0.0,SCREEN_WIDTH,0.0,SCREEN_HEIGHT);

    glBegin(GL_LINES);
    // draw line for x axis
    glColor3f(1.0, 0.0, 0.0);
    glVertex3f(0.0,SCREEN_HEIGHT/2.0, 0.0);
    glVertex3f(SCREEN_WIDTH,SCREEN_HEIGHT/2.0, 0.0);
    // draw line for y axis
    glColor3f(0.0,1.0, 0.0);
    glVertex3f(SCREEN_WIDTH/2.0,0.0, 0.0);
    glVertex3f(SCREEN_WIDTH/2.0,SCREEN_HEIGHT, 0.0);


    glEnd();

    glPopMatrix();
    glFlush();
    glColor3f(0.0,0.0,0.0);
}
