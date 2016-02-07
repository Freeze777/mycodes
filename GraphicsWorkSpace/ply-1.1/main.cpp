#include <stdio.h>
#include <iostream>
#include <math.h>
#include <string.h>
#include "PlyUtility.h"
#include "View.h"
using namespace std;

#define PI 3.1415926535898
#define COS(th) cos(PI/180*(th))
#define SIN(th) sin(PI/180*(th))
void drawModel(void);
void drawBoundingBox(void);
void myReshape(int w, int h);
void myKeyBoard(unsigned char key,int x, int y);
void myMouse(int button, int state, int x, int y);
void myDisplay(void);
void myInit(void);
void windowSpecial(int key,int x,int y);

View view;
PlyUtility ply;


double dim=2.0;
int th = 0;   /* azimuth of view angle */
int ph = 0;   /* elevation of view angle */
int fov = 45; /* field of view for perspective */
int asp = 1;  /* aspect ratio */

float SCREEN_WIDTH = 1000,SCREEN_HEIGHT = 650;
GLfloat centroid[3]={0.02,-0.09 ,-0.015};



GLfloat v[8][3];

void myMouse(int button, int state, int x, int y) {


    if(state==GLUT_DOWN && button==GLUT_LEFT)
    {
        printf("\n screen cordinates: (%d,%d)",x,y);
        GLdouble viewMatrix[16];
        GLdouble projMatrix[16];
        GLint viewport[4];

        glGetDoublev (GL_MODELVIEW_MATRIX, viewMatrix);
        glGetDoublev (GL_PROJECTION_MATRIX, projMatrix);
        glGetIntegerv(GL_VIEWPORT, viewport);
        // glReadPixels( x, int(winY), 1, 1, GL_DEPTH_COMPONENT, GL_FLOAT, &winZ );
        double ox,oy,oz;
        gluUnProject((double)x,(double)(SCREEN_HEIGHT-y),0.0,viewMatrix,projMatrix,viewport,&ox,&oy,&oz);
        printf("\n screen cordinates: (%f,%f,%f)",ox,oy,oz);
    }


}
void myDisplay() {

    /*init called first and then reshape gets called*/

    glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);

    myInit();
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
    double Ex = -2*dim*SIN(th)*COS(ph);
    double Ey = +2*dim        *SIN(ph);
    double Ez = +2*dim*COS(th)*COS(ph);
    /* camera/eye position, aim of camera lens, up-vector */
    gluLookAt(Ex,Ey,Ez , 0,0,0 , 0,COS(ph),0);
    //   gluLookAt(0,0,1,0,0,0,0,1,0);
    drawBoundingBox();
    drawModel();
    view.drawAxis(dim);
   view.originTest();
    glFlush();
}


void myInit() {
    glEnable(GL_DEPTH_TEST);
    glViewport(0,0, SCREEN_WIDTH,SCREEN_HEIGHT);

    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
   // gluPerspective( /* field of view in degree */ fov,/* aspect ratio */ SCREEN_WIDTH/SCREEN_HEIGHT,/* Z near */ -dim/2, /* Z far */ dim/2);
 gluPerspective( /* field of view in degree */ fov,/* aspect ratio */ SCREEN_WIDTH/SCREEN_HEIGHT,/* Z near */ dim, /* Z far */4*dim);
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();

}
void drawBoundingBox(void){
    v[0][0] = v[1][0] = v[2][0] = v[3][0] = -dim/2;
    v[4][0] = v[5][0] = v[6][0] = v[7][0] = dim/2;
    v[0][1] = v[1][1] = v[4][1] = v[5][1] = -dim/2;
    v[2][1] = v[3][1] = v[6][1] = v[7][1] = dim/2;
    v[0][2] = v[3][2] = v[4][2] = v[7][2] = dim/2;
    v[1][2] = v[2][2] = v[5][2] = v[6][2] = -dim/2;

    glMatrixMode(GL_MODELVIEW);

    glColor3f(1.0,1.0,1.0);


    for (int i = 0; i < 4 ; i++) {
        glBegin(GL_LINES);

        glVertex3f(v[i][0],v[i][1],v[i][2]);
        glVertex3f(v[(i+1)%4][0],v[(i+1)%4][1],v[(i+1)%4][2]);

        glVertex3f(v[i][0],v[i][1],v[i][2]);
        glVertex3f(v[i+4][0],v[i+4][1],v[i+4][2]);

        glVertex3f(v[i+4][0],v[i+4][1],v[i+4][2]);
        if((i+5)%8!=0)
            glVertex3f(v[(i+5)%8][0],v[(i+5)%8][1],v[(i+5)%8][2]);
        else
            glVertex3f(v[4][0],v[4][1],v[4][2]);

        glEnd();
    }


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

void drawModel(void)
{


    glMatrixMode(GL_MODELVIEW);
    glPushMatrix();

    glScalef(10,10,10);
    glTranslatef(centroid[0],centroid[1],centroid[2]);

    glColor3f(0.75,0.68,0.204);

    int fcount=ply.getFaceCount();
    PlyUtility::Face ** fl=ply.getFaceList();
    PlyUtility::Vertex ** vl=ply.getVertexList();
    for (int var = 0; var < fcount ; var++) {
        glBegin(GL_TRIANGLES);

        for (int t = 0; t < fl[var]->nverts; t++)
        {
            int vIndex=fl[var]->verts[t];
            glVertex3f(vl[vIndex]->x,vl[vIndex]->y,vl[vIndex]->z);
        }

        glEnd();
    }



   glPopMatrix();



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


