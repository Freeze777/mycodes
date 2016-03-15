#include "Controller.h"


void Controller::mouse_callback(int button, int state, int x, int y) {


    if(state==GLUT_DOWN && button==GLUT_LEFT)
    {
        arcball_on = true;
        arcball->set_current_xy(x,y);
        arcball->set_last_xy(x,y);

    }else {
        arcball_on = false;
    }

}
void  Controller::mouse_motion_callback(int x, int y) {


    if (arcball_on) {  // if left button is pressed
        arcball->set_current_xy(x,y);
     }
    glMatrixMode(GL_MODELVIEW);
    arcball->rotateModelvthMouse(dim);
    glutPostRedisplay();


}

void  Controller::display_callback() {

  
  

    

    glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
    glLightfv(GL_LIGHT2,GL_SPOT_DIRECTION,spotDir2);
    model->setScaleFactor(dim);
    model->drawScene(dim);

    glFlush();
}


void  Controller::reshape_callback(int w,int h)
{

    SCREEN_WIDTH=w;
    SCREEN_HEIGHT=h;

    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluPerspective(fov,SCREEN_WIDTH/SCREEN_HEIGHT,dim,5*dim);
    glViewport(0,0, SCREEN_WIDTH,SCREEN_HEIGHT);

    arcball->set_width_height(SCREEN_WIDTH,SCREEN_HEIGHT);
    view->set_width_height(SCREEN_WIDTH,SCREEN_HEIGHT);


}

void  Controller::keyboard_special_callback(int key,int x,int y)
{

    if (key == GLUT_KEY_RIGHT) {th =0.1;ph=0.0;}
    else if (key == GLUT_KEY_LEFT) {th =-0.1;ph=0.0;}
    else if (key == GLUT_KEY_UP) {th=0.0;ph =0.1;}
    else if (key == GLUT_KEY_DOWN){ th=0.0 ;ph =-0.1;}
    else return;
    
    glTranslatef(th,ph,0.0);
    glutPostRedisplay();
}

void  Controller::keyboard_callback(unsigned char key,int x,int y)
{   	
	 if (key == 27) exit(0);
    else if (key == '-' && key>1) zoomfactor=0.9;
    else if (key == '+' && key<179) zoomfactor=1.1;
    else if (key == 'a'||key == 'A') toggle1=!toggle1;
    else if (key == 's'||key == 'S') toggle2=!toggle2;
    else if (key == 'd'||key == 'D') toggle3=!toggle3;
    else return;
    (toggle1)?glDisable(GL_LIGHT0):glEnable(GL_LIGHT0);
    (toggle2)?glDisable(GL_LIGHT1):glEnable(GL_LIGHT1);
    (toggle3)?glDisable(GL_LIGHT2):glEnable(GL_LIGHT2);
    glScalef(zoomfactor,zoomfactor,zoomfactor);
    zoomfactor=1.0;
    glutPostRedisplay();

}


