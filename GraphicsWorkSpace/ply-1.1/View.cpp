#include "View.h"


void View::drawAxis(float dim){

    glMatrixMode(GL_MODELVIEW);
    glBegin(GL_LINES);
    // draw line for x axis
    glColor3f(1, 0.0, 0.0);
    glVertex3f(dim/2,0.0, 0.0);
    glVertex3f(-dim/2,0.0, 0.0);

    // draw line for y axis
    glColor3f(0.0,1, 0.0);
    glVertex3f(0.0,dim/2, 0.0);
    glVertex3f(0.0,-dim/2, 0.0);

    // draw line for z axis
    glColor3f(0.0,0.0,1);
    glVertex3f(0.0,0.0, dim/2);
    glVertex3f(0.0,0.0, -dim/2);

    glEnd();

    glColor3f(1.0,1.0,1.0);
}



void View::originTest(void){

        glColor3f(1.0,0.0,0.0);

        glRasterPos3fv(origin);
        glutBitmapCharacter(GLUT_BITMAP_HELVETICA_18,'O');

        glColor3f(1.0,1.0,1.0);
}
