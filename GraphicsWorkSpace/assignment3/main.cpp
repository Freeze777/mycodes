#include "Controller.h"
#include "Model.h"

using namespace std;


void myReshape(int w, int h);
void myKeyBoard(unsigned char key,int x, int y);
void myMouse(int button, int state, int x, int y);
void myDisplay(void);
void specialKeys(int key,int x,int y);
void myMouseMotion(int x, int y) ;

Controller *controller;
Model *model;
View *view;
float SCREEN_WIDTH=1000;
float SCREEN_HEIGHT=650;

GLuint texture[2];

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

void specialKeys(int key,int x,int y)
{

    controller->keyboard_special_callback(key,x,y);

}

void myKeyBoard(unsigned char key,int x,int y)
{
    controller->keyboard_callback(key,x,y);

}


int main(int argc, char *argv[]) {
    model=new Model();
    float s1[3]={0.07,0.0,0.0};
    float s2[3]={-0.06,0.0,0.0};
    if(argc!=3)
    {
        printf("Insufficient arguments..!!\n");
        exit(0);
    }
    model->readModelsFromFile(argv[1],argv[2]);
    model->setShifts(s1,s2);
    model->plymodel1->readTexture2Buffer("textures/checker.bmp",64,64);
    model->plymodel2->readTexture2Buffer("textures/checker.bmp",64,64);
    glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
/*
  glGenTextures(2, texture);
  glBindTexture(GL_TEXTURE_2D, texture[0]);
  glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_LINEAR); //scale linearly when image bigger than texture
  glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR); //scale linearly when image smalled than texture
  glTexImage2D(GL_TEXTURE_2D, 0, 3, image1.sizeX, image1.sizeY, 0,
  GL_RGB, GL_UNSIGNED_BYTE, image1.data);
  glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_DECAL);
  glBindTexture(GL_TEXTURE_2D, texture[1]);
  glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP);
  glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
  glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
  glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
  glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_DECAL);
  glTexImage2D(GL_TEXTURE_2D, 0, 3, 64,64, 0, GL_RGB, GL_UNSIGNED_BYTE,&model2->plymodel->texture.checkImage[0][0][0]);*/

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
    glutSpecialFunc(specialKeys);

    glutMouseFunc(myMouse);
    glutMotionFunc(myMouseMotion);


    view->initialize(controller->dim,controller->fov);
    glutMainLoop();

    return 0;
}






