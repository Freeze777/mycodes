#include <stdlib.h>

#ifdef __APPLE__
#include <OpenGL/OpenGL.h>
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif


static double yVal=1.0;

int spot(double a, double b, double c, double d, double e, double f){

    /*

    a, b and c -- x, y and z co-ordinates for light position

    d, e and f -- x, y and z co-ordinates for spot light position

    */

    GLfloat mat_specular[] = { 0.3, 1.0, 0.3, 1.0 };

    GLfloat mat_shininess[] = { 50.0 };

    GLfloat light_position[] = { a,b,c, 1.0 };

    GLfloat spotDir[] = { d,e,f };

    glClearColor (0.5, 0.5, 0.5, 0.0);

    glShadeModel (GL_SMOOTH);

    glLightfv(GL_LIGHT0,GL_SPECULAR,mat_specular);

    glLightfv(GL_LIGHT0,GL_POSITION,light_position);

    glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular);

    glMaterialfv(GL_FRONT, GL_SHININESS, mat_shininess);

    // Definig spotlight attributes

    glLightf(GL_LIGHT0,GL_SPOT_CUTOFF,95.0);

    glLightf(GL_LIGHT0,GL_SPOT_EXPONENT,2.0);

    glLightfv(GL_LIGHT0,GL_SPOT_DIRECTION,spotDir);

    glEnable(GL_COLOR_MATERIAL);

    glEnable(GL_DEPTH_TEST);

    return 0;

}

void drawSphere(){

    GLUquadricObj* cyl;

    glClearColor (0.5, 0.5, 0.5, 0.0);

    GLfloat light_position[] = { 50.0, 50.0 , 0.0, 1.0 };

    GLfloat mat_specular[] = { 0.3, 0.3, 1.0, 1.0 };

    GLfloat mat_shininess[] = { 100.0 };

    GLfloat spotDir[] = { 50.0, 30.0, 0.0 };

    glShadeModel (GL_SMOOTH);

    spot( yVal,5.0,1.5,10.0,1.0,10.0);

    glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular);

    glMaterialfv(GL_FRONT, GL_SHININESS, mat_shininess);

    glMatrixMode (GL_PROJECTION);

    glLoadIdentity();

    gluPerspective(35.0, 1.0, 1.0, 100.0);

    glMatrixMode (GL_MODELVIEW);

    glLoadIdentity();

    gluLookAt (30.0, 0.0, 10.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0);

    cyl = gluNewQuadric();

    gluQuadricDrawStyle(cyl, GLU_FILL);

    glPushMatrix();

    gluSphere(cyl, 4.0, 1000, 1000);

    glPopMatrix();

    glFlush();

}

void display(void){

    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

    drawSphere();

    glFlush ();

}

void init (void){

    glClearColor (0, 0, 0, 0.0);

    glEnable(GL_DEPTH_TEST); //enabling z-buffer

    /* initialize viewing values */

    glMatrixMode (GL_PROJECTION);

    glLoadIdentity();
aa
    gluPerspective(35.0, 1.0, 1.0, 100.0);

    glMatrixMode (GL_MODELVIEW);

    glLoadIdentity();

    gluLookAt (30.0, 0.0, 10.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0);

    glEnable(GL_LIGHTING);

    glEnable(GL_LIGHT0);

}

void keyboard(unsigned char key, int x, int y){

    switch (key) {

        case 27: // “esc” on keyboard

        exit(0);

        break;

        case 97: // “a” on keyboard

        yVal++;

        glutPostRedisplay();

        break;

    }

}

int main(int argc, char** argv){

    glutInit(&argc, argv);

    glutInitDisplayMode (GLUT_SINGLE | GLUT_RGB);

    glutInitWindowSize (600, 600);

    glutInitWindowPosition (100, 100);

    glutCreateWindow ("Spot Light - Programming Techniques");

    init ();

    glutDisplayFunc(display);

    glutKeyboardFunc(keyboard);

    glutMainLoop();

    return 0;

}
