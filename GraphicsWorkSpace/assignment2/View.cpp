#include "View.h"


View::View(float width, float height){
    this->screenHeight=height;
    this->screenWidth=width;


}
void View::set_width_height(float width,float height)
{

    this->screenWidth=width;
    this->screenHeight=height;


}
void  View::createWindow(char *windowName){



    glutInitDisplayMode(GLUT_SINGLE|GLUT_RGB|GLUT_DEPTH);
    glutInitWindowSize(screenWidth,screenHeight);
    glutInitWindowPosition(0,0);
    glutCreateWindow(windowName);
    glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    glColor3f(0.0,1.0,1.0);

}
void  View::initialize(double dim,int fov){


    glViewport(0,0,screenWidth,screenHeight);


    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluPerspective(fov,screenWidth/screenHeight,dim,4*dim);


    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
    gluLookAt(0,0,2.5*dim, 0,0,0 , 0,1,0);

    GLfloat light_position[] = { 0.0,0.0,10.0, 0.0 };

    GLfloat light_diffuse[] = {0.75,0.68,0.5,0.0};

    glClearColor(0.0,0.0,0.0,0.0);
    glEnable(GL_NORMALIZE);
    glShadeModel (GL_SMOOTH);
    glLightfv(GL_LIGHT0, GL_DIFFUSE, light_diffuse);
    glLightfv(GL_LIGHT0, GL_POSITION, light_position);

    GLfloat mat_ambient[] = { 1.0, 1.0, 1.0, 0.0 };
    GLfloat mat_shininess[] = { 100.0 };
    glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient);
    glMaterialfv(GL_FRONT, GL_SHININESS, mat_shininess);

    glEnable(GL_LIGHTING);
    glEnable(GL_LIGHT0);
    glEnable(GL_DEPTH_TEST);

}
