#include "View.h"


void View::drawAxis(float dim){

    glMatrixMode(GL_MODELVIEW);
    glBegin(GL_LINES);
    // draw line for x axis
    glColor3f(1, 0.0, 0.0);
    glVertex3f(dim/2.0,0.0, 0.0);
    glVertex3f(-dim/2.0,0.0, 0.0);

    // draw line for y axis
    glColor3f(0.0,1, 0.0);
    glVertex3f(0.0,dim/2.0, 0.0);
    glVertex3f(0.0,-dim/2.0, 0.0);

    // draw line for z axis
    glColor3f(0.0,0.0,1);
    glVertex3f(0.0,0.0, dim/2.0);
    glVertex3f(0.0,0.0, -dim/2.0);

    glEnd();

    glColor3f(1.0,1.0,1.0);
}



void View::markPoints(float dim){

        glColor3f(1.0,0.0,0.0);

        glRasterPos3fv(origin);
        glutBitmapCharacter(GLUT_BITMAP_HELVETICA_18,'O');

        glRasterPos3f(dim/2.0,0.0,0.0);
        glutBitmapCharacter(GLUT_BITMAP_HELVETICA_18,'X');
        glRasterPos3f(0.0,dim/2.0,0.0);
        glutBitmapCharacter(GLUT_BITMAP_HELVETICA_18,'Y');
        glRasterPos3f(0.0,0.0,dim/2.0);
        glutBitmapCharacter(GLUT_BITMAP_HELVETICA_18,'Z');

        glColor3f(1.0,1.0,1.0);
}
