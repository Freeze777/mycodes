#include <stdio.h>
#include <iostream>
#include <math.h>
#include <string.h>
#include "PlyUtility.h"
#include "View.h"
#include "PlyModel.h"
#include "BoundingBox.h"
#include "Quaternions.h"
#include "Vector.h"
using namespace std;

#define PI 3.1415926535898
#define COS(th) cos(PI/180*(th))
#define SIN(th) sin(PI/180*(th))

void myReshape(int w, int h);
void myKeyBoard(unsigned char key,int x, int y);
void myMouse(int button, int state, int x, int y);
void myDisplay(void);
void myInit(void);
void windowSpecial(int key,int x,int y);
void myMouseMotion(int x, int y) ;
Vector get_arcball_vector(int x, int y) ;
void rotateModelvthMouse(void);

View view;
PlyUtility ply;
PlyModel plymodel;
BoundingBox box;

int last_mx = 0, last_my = 0, cur_mx = 0, cur_my = 0;
int arcball_on = false;

double dim=2.0;
int th = 0;   /* azimuth of view angle */
int ph = 0;   /* elevation of view angle */
int fov = 45; /* field of view for perspective */
int asp = 1;  /* aspect ratio */

float SCREEN_WIDTH = 1000,SCREEN_HEIGHT = 650;





void myMouse(int button, int state, int x, int y) {


    if(state==GLUT_DOWN && button==GLUT_LEFT)
    {
        printf("\n screen cordinates: (%d,%d)",x,y);
        /* GLdouble viewMatrix[16];
        GLdouble projMatrix[16];
        GLint viewport[4];

        glGetDoublev (GL_MODELVIEW_MATRIX, viewMatrix);
        glGetDoublev (GL_PROJECTION_MATRIX, projMatrix);
        glGetIntegerv(GL_VIEWPORT, viewport);
        // glReadPixels( x, int(winY), 1, 1, GL_DEPTH_COMPONENT, GL_FLOAT, &winZ );

        printf("\n screen cordinates: (%f,%f,%f)",ox,oy,oz);*/

        arcball_on = true;
        last_mx = cur_mx = x;
        last_my = cur_my = y;

    }else {
        arcball_on = false;
    }

}

void rotateModelvthMouse(void){

    if (cur_mx != last_mx || cur_my != last_my) {
        Vector va = get_arcball_vector(last_mx, last_my);
        Vector vb = get_arcball_vector( cur_mx,  cur_my);
        double angle = (180.0 / PI )*acos(min(1.0, dot(va, vb)));//in degrees
        Vector axis_in_camera_coord = cross(va, vb);

        GLdouble viewMatrix[16];
        GLdouble projMatrix[16];
        GLint viewport[4];
        glGetDoublev (GL_MODELVIEW_MATRIX, viewMatrix);
        glGetDoublev (GL_PROJECTION_MATRIX, projMatrix);
        glGetIntegerv(GL_VIEWPORT, viewport);
       // double axis_in_object_coord[3];
       double axis_in_object_coord[3]={-axis_in_camera_coord.x(),-axis_in_camera_coord.y(),-axis_in_camera_coord.z()};

       //gluUnProject(-axis_in_camera_coord.x(),-axis_in_camera_coord.y(),-axis_in_camera_coord.z(),
           //        viewMatrix,projMatrix,viewport,&axis_in_object_coord[0],&axis_in_object_coord[1],&axis_in_object_coord[2]);
        Quaternion quat(angle,axis_in_object_coord);
        glMatrixMode(GL_MODELVIEW);
        glMultMatrixd(quat.rotationMatrix());

        quat.~Quaternion();
        last_mx = cur_mx;
        last_my = cur_my;

    }
}

void myMouseMotion(int x, int y) {


    if (arcball_on) {  // if left button is pressed
        cur_mx = x;
        cur_my = y;
    }
 glutPostRedisplay();


}

Vector get_arcball_vector(int x, int y) {
    Vector P = Vector(1.0*x/SCREEN_WIDTH*2 - 1.0,
                      1.0*y/SCREEN_HEIGHT*2 - 1.0,
                      0);
    P.y(- P.y());

    float OP_squared = P.x() * P.x() + P.y() * P.y();
    if (OP_squared <= 1*1)
        P.z(sqrt(1*1 - OP_squared));  // Pythagore
    else
        P.normalize();  // nearest point

    return P;
}
void myDisplay() {

    /*init called first and then reshape gets called*/

    glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);

 /*  myInit();//sets up proj matrix and set modelview matrix to identity

  //  double Ex = -2*dim*SIN(th)*COS(ph);
  //  double Ey = +2*dim        *SIN(ph);
  //  double Ez = +2*dim*COS(th)*COS(ph);
   camera/eye position, aim of camera lens, up-vector */


    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluPerspective(fov, SCREEN_WIDTH/SCREEN_HEIGHT, dim,4*dim);

    gluLookAt(0,0,5, 0,0,0 , 0,1,0);

    /*  double axis[]={1.0,1.0,1.0};
    Quaternion quat(180,axis);
    const GLdouble *matrix=quat.rotationMatrix();
    glMultMatrixd(matrix);*/
    rotateModelvthMouse();
    box.draw(dim);
    plymodel.draw(ply);
    view.drawAxis(dim);
    view.originTest();
    glFlush();
}


void myInit() {
    glEnable(GL_DEPTH_TEST);
    glViewport(0,0, SCREEN_WIDTH,SCREEN_HEIGHT);

    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluPerspective(fov,SCREEN_WIDTH/SCREEN_HEIGHT,dim,4*dim);
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();

}


int main(int argc, char *argv[]) {

    ply.readPlyFile("bunny");
    glutInit(&argc, argv);

    glutInitDisplayMode(GLUT_SINGLE|GLUT_RGB);//use single buffer and RGB color schemes
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



void myReshape(int w,int h)
{

    SCREEN_WIDTH=w;
    SCREEN_HEIGHT=h;

    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    //  gluPerspective( /* field of view in degree */ fov,/* aspect ratio */ SCREEN_WIDTH/SCREEN_HEIGHT,/* Z near */ -dim/2, /* Z far */ dim/2);
    gluPerspective( /* field of view in degree */ fov,/* aspect ratio */ SCREEN_WIDTH/SCREEN_HEIGHT,/* Z near */ dim, /* Z far */ 4*dim);
    glViewport(0,0, SCREEN_WIDTH,SCREEN_HEIGHT);



}

void windowSpecial(int key,int x,int y)
{
    /*  Right arrow key - increase azimuth by 5 degrees */
    if (key == GLUT_KEY_RIGHT) th += 5;
    /*  Left arrow key - decrease azimuth by 5 degrees */
    else if (key == GLUT_KEY_LEFT) th -= 5;
    /*  Up arrow key - increase elevation by 5 degrees */
    else if (key == GLUT_KEY_UP) ph += 5;
    /*  Down arrow key - decrease elevation by 5 degrees */
    else if (key == GLUT_KEY_DOWN) ph -= 5;

    /*  Keep angles to +/-360 degrees */
    th %= 360;
    ph %= 360;

    glutPostRedisplay();
}

void myKeyBoard(unsigned char key,int x,int y)
{
    /*  Exit on ESC */
    if (key == 27) exit(0);
    /*  Change field of view angle */
    else if (key == '-' && key>1) fov++;
    else if (key == '+' && key<179) fov--;
    /*  Change dimensions */
    else if (key == 'D') dim += 0.1;
    else if (key == 'd' && dim>1) dim -= 0.1;
    glutPostRedisplay();

}


