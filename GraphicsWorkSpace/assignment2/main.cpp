#include "Controller.h"
#include "Model.h"

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
Model *model;
View *view;
float SCREEN_WIDTH=1000;
float SCREEN_HEIGHT=650;


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


int main(int argc, char *argv[]) {
    model=new Model();
    model->readModelFromFile("bunny");

    view=new View(SCREEN_WIDTH,SCREEN_HEIGHT);
    controller=new Controller(view,model);

    controller->SCREEN_HEIGHT=SCREEN_HEIGHT;
    controller->SCREEN_WIDTH=SCREEN_WIDTH;


    glutInit(&argc, argv);
    view->createWindow("3D Rendering");


    //register callbacks
    glutDisplayFunc(myDisplay);
    glutReshapeFunc(myReshape);
    glutKeyboardFunc(myKeyBoard);
    glutMouseFunc(myMouse);
    glutSpecialFunc(windowSpecial);
    glutMotionFunc(myMouseMotion);


    view->initialize(controller->dim,controller->fov);
    glutMainLoop();

    return 0;
}






