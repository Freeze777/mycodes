#include "Controller.h"

using namespace std;

#define PI 3.1415926535898
#define COS(th) cos(PI/180*(th))
#define SIN(th) sin(PI/180*(th))



void myInit(void);
void myReshape(int w, int h);
void myKeyBoard(unsigned char key,int x, int y);
void myMouse(int button, int state, int x, int y);
void myDisplay(void);
void windowSpecial(int key,int x,int y);
void myMouseMotion(int x, int y) ;

Controller *controller;
PlyUtility ply;
PlyModel plymodel;
float SCREEN_WIDTH;
float SCREEN_HEIGHT;


void myMouse(int button, int state, int x, int y) {

    controller->mouse_callback(button,state,x,y);

}
void myMouseMotion(int x, int y) {

    controller->mouse_motion_callback(x,y);

}

void myDisplay() {

    controller->display_callback();

}

void myReshape(int w,int h)
{

    SCREEN_WIDTH=w;
    SCREEN_HEIGHT=h;
    controller->reshape_callback(w,h);
}

void windowSpecial(int key,int x,int y)
{

    controller->keyboard_special_callback(key,x,y);

}

void myKeyBoard(unsigned char key,int x,int y)
{
    controller->keyboard_callback(key,x,y);

}

void myInit() {


    glViewport(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);


    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluPerspective(controller->fov,SCREEN_WIDTH/SCREEN_HEIGHT,controller->dim,4*controller->dim);


    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
    gluLookAt(0,0,2.5*controller->dim, 0,0,0 , 0,1,0);

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


int main(int argc, char *argv[]) {
    controller=new Controller(&ply,&plymodel);
    SCREEN_HEIGHT=controller->SCREEN_HEIGHT;
    SCREEN_WIDTH=controller->SCREEN_WIDTH;
    ply.readPlyFile("bunny");
    plymodel.computeNormal(&ply);
    plymodel.computeCentroid(&ply);
    glutInit(&argc, argv);

    glutInitDisplayMode(GLUT_SINGLE|GLUT_RGB|GLUT_DEPTH);//use single buffer and RGB color schemes
    glutInitWindowSize(SCREEN_WIDTH,SCREEN_HEIGHT);
    glutInitWindowPosition(0,0);
    glutCreateWindow("3D MADNESS");
    glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    glColor3f(0.0,1.0,1.0);

    //register callbacks
    glutDisplayFunc(myDisplay);
    glutReshapeFunc(myReshape);
    glutKeyboardFunc(myKeyBoard);
    glutMouseFunc(myMouse);
    glutSpecialFunc(windowSpecial);
    glutMotionFunc(myMouseMotion);


    myInit();
    glutMainLoop();

    return 0;
}






