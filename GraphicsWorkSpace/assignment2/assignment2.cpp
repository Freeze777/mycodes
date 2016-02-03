#include <iostream>
#include <limits.h>
#include <stdio.h>

#include "rply.h"
#include "display_utility.h"
#include "curves.h"
#include "transformations.h"
using namespace std;

Point points[50],shift[2],bezier[51], lagrange[51];
int cnt=0,clicks = 0,SCREEN_WIDTH = 1000,SCREEN_HEIGHT = 650;

//Cube vertices
float vertA[3] = { 0.5, 0.5, 0.5};
float vertB[3] = {-0.5, 0.5, 0.5};
float vertC[3] = {-0.5,-0.5, 0.5};
float vertD[3] = { 0.5,-0.5, 0.5};
float vertE[3] = { 0.5, 0.5,-0.5};
float vertF[3] = {-0.5, 0.5,-0.5};
float vertG[3] = {-0.5,-0.5,-0.5};
float vertH[3] = { 0.5,-0.5,-0.5};


/* 0-get user points*/
/*1- add/delete*/
int MODE=0;



/*  Globals */
double dim=2.0; /* dimension of orthogonal box */
int th = 0;   /* azimuth of view angle */
int ph = 0;   /* elevation of view angle */
int fov = 55; /* field of view for perspective */
int asp = 1; /*aspect ratio*/

void drawShape()
{
  /* Cube */
  glBegin(GL_QUADS);
  /* front => ABCD yellow */
  glColor3f(1.0,1.0,0.0);
  glVertex3fv(vertA);
  glVertex3fv(vertB);
  glVertex3fv(vertC);
  glVertex3fv(vertD);
  /* back => FEHG red */
  glColor3f(1.0,0.0,0.0);
  glVertex3fv(vertF);
  glVertex3fv(vertE);
  glVertex3fv(vertH);
  glVertex3fv(vertG);
  /* right => EADH green */
  glColor3f(0.0,1.0,0.0);
  glVertex3fv(vertE);
  glVertex3fv(vertA);
  glVertex3fv(vertD);
  glVertex3fv(vertH);
  /* left => BFGC blue */
  glColor3f(0.0,0.0,1.0);
  glVertex3fv(vertB);
  glVertex3fv(vertF);
  glVertex3fv(vertG);
  glVertex3fv(vertC);
  /* top => EFBA turquoise */
  glColor3f(0.0,1.0,1.0);
  glVertex3fv(vertE);
  glVertex3fv(vertF);
  glVertex3fv(vertB);
  glVertex3fv(vertA);
  /* bottom => DCGH pink */
  glColor3f(1.0,0.0,1.0);
  glVertex3fv(vertD);
  glVertex3fv(vertC);
  glVertex3fv(vertG);
  glVertex3fv(vertH);
  glEnd();
}


void myInit() {

    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();

    gluPerspective(fov,asp,dim/4,4*dim);

    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();

}

void displayUserPoints()
{
    glColor3f(0.0,0.0,0.0);
    for(int k=0;k<clicks;k++)
        drawDot(points[k]);


    glColor3f(1.0,1.0,0.0);
    drawLineStrip(points,clicks);
}

void myReshape(int w,int h)
{
    SCREEN_WIDTH=w;
    SCREEN_HEIGHT=h;
    asp = (height>0) ? (double)SCREEN_WIDTH/SCREEN_HEIGHT : 1;
    glViewport(0,0, SCREEN_WIDTH,SCREEN_HEIGHT);
    myInit();


}

void myKeyBoard(unsigned char key,int x, int y){
    // cout<<(int)key<<"\n";
    switch ( key )
    {
    case 13: // Enter key

        if(MODE==1)MODE=0;
        glClear(GL_COLOR_BUFFER_BIT);

        computeBezierCurvePoints(points,clicks,bezier);
        computeLagrangeCurvePoints(points,clicks,lagrange);
        glColor3f(0.0,0.0,0.0);
        for(int k=0;k<clicks;k++)
            drawDot(points[k]);

        glutPostRedisplay();
        break;


    case 43:
        zoom(2.0,points[0]);
        break;

    case 45:
        zoom(0.5,points[0]);
        break;

    case 114:
        MODE=1;
        glClear(GL_COLOR_BUFFER_BIT);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluOrtho2D(0.0,SCREEN_WIDTH,0.0,SCREEN_HEIGHT);
        drawAxis(SCREEN_WIDTH,SCREEN_HEIGHT);
        for(int k=0;k<clicks;k++)
            drawDot(points[k]);

        glFlush();
        break;

    case 27: // Escape key
        glutDestroyWindow (glutGetWindow());
        exit (0);
        break;
    }


}

void myDisplay() {



    glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
    /*  Enable Z-buffering in OpenGL */
    glEnable(GL_DEPTH_TEST);

    /*  Reset previous transforms */
    glLoadIdentity();

    /*  Perspective - set eye position */
    if (toggleMode) {
      double Ex = -2*dim*Sin(th)*Cos(ph);
      double Ey = +2*dim        *Sin(ph);
      double Ez = +2*dim*Cos(th)*Cos(ph);
      /* camera/eye position, aim of camera lens, up-vector */
      gluLookAt(Ex,Ey,Ez , 0,0,0 , 0,Cos(ph),0);
    }
    /*  Orthogonal - set world orientation */
    else {
      glRotatef(ph,1,0,0);
      glRotatef(th,0,1,0);
    }

    /*  Draw  */
    drawAxis(SCREEN_WIDTH,SCREEN_HEIGHT);
    drawShape();

    /*  Flush and swap */
    glFlush();
    glutSwapBuffers();
}
void myMouse(int button, int state, int x, int y) {
    // If left button was clicked

    if(button == GLUT_LEFT_BUTTON && state == GLUT_DOWN) {
        // Store where mouse was clicked, Y is backwards.
        glColor3f(0.0,0.0,0.0);
        if(MODE==1)
        {
            Point newp;
            int index=-1;
            int closest=-1;
            newp.setxy((float)x,(float)(SCREEN_HEIGHT - y));
            float threshold=20.0,min=INT_MAX;
            for(int k=0;k<clicks;k++)
            {
                float currDist=sqrt(pow(points[k].x-newp.x,2)+(pow(points[k].y-newp.y,2)));

                if(currDist<min)
                {
                    min=currDist;
                    closest=k;

                }

                if(currDist<=threshold)
                {
                    threshold=currDist;
                    index=k;

                }

            }

            if(index!=-1){
                for(;index<clicks-1;index++)
                    points[index]=points[index+1];

                clicks--;

            }else{

                if(newp.x>points[closest].x)
                    closest++;


                for( int i =clicks-1; i >= closest ; i-- )
                    points[i+1] = points[i];


                points[closest]=newp;
                clicks++;


            }

            glClear(GL_COLOR_BUFFER_BIT);

            for(int k=0;k<clicks;k++)
                drawDot(points[k]);

            drawAxis(SCREEN_WIDTH,SCREEN_HEIGHT);

        }else if(MODE==0)
        {
            points[clicks].setxy((float)x,(float)(SCREEN_HEIGHT - y));

            drawDot(points[clicks]);
            clicks++;

        }

    }
    if(button == GLUT_RIGHT_BUTTON && state == GLUT_DOWN) {

        glPushMatrix();
        glLoadIdentity();
        glColor3f(0.0,0.0,0.0);
        gluOrtho2D(0.0,SCREEN_WIDTH,0.0,SCREEN_HEIGHT);



        shift[cnt].setxy((float)x,(float)(SCREEN_HEIGHT - y));
        drawDot(shift[cnt]);
        cnt++;
        glPopMatrix();

        if(cnt==2)
        {
            float dist=sqrt(pow(shift[0].x-shift[1].x,2)+(pow(shift[0].y-shift[1].y,2)));
            translate(dist);

            cnt=0;
        }

    }

}
void mySpecialInput(int key, int x, int y)
{
    switch(key)
    {
    case GLUT_KEY_LEFT:
        rotate(90.0,points[0]);

        break;
    case GLUT_KEY_RIGHT:
        rotate(-90.0,points[0]);

        break;
    }
}


static int vertex_cb(p_ply_argument argument) {
    long eol;
    ply_get_argument_user_data(argument, NULL, &eol);
    printf("%g", ply_get_argument_value(argument));
    if (eol) printf("\n");
    else printf(" ");
    return 1;
}

static int face_cb(p_ply_argument argument) {
    long length, value_index;
    ply_get_argument_property(argument, NULL, &length, &value_index);
    switch (value_index) {
    case 0:
    case 1:
        printf("%g ", ply_get_argument_value(argument));
        break;
    case 2:
        printf("%g\n", ply_get_argument_value(argument));
        break;
    default:
        break;
    }
    return 1;
}
int readPlyFile()
{
    long nvertices, ntriangles;
    p_ply ply = ply_open("input.ply", NULL, 0, NULL);
    if (!ply) return 1;
    if (!ply_read_header(ply)) return 1;
    nvertices = ply_set_read_cb(ply, "vertex", "x", vertex_cb, NULL, 0);
    ply_set_read_cb(ply, "vertex", "y", vertex_cb, NULL, 0);
    ply_set_read_cb(ply, "vertex", "z", vertex_cb, NULL, 1);
    ntriangles = ply_set_read_cb(ply, "face", "vertex_indices", face_cb, NULL, 0);
    printf("%ld\n%ld\n", nvertices, ntriangles);
    if (!ply_read(ply)) return 1;
    ply_close(ply);

}
int main(int argc, char *argv[]) {

    readPlyFile();


    glutInit(&argc, argv);
    myInit();
    glutInitDisplayMode(GLUT_SINGLE|GLUT_RGB);//use single buffer and RGB color schemes
    glutInitWindowSize(SCREEN_WIDTH,SCREEN_HEIGHT);
    glutInitWindowPosition(0,0);
    glutCreateWindow("Curves");

    //register callbacks
    glutMouseFunc(myMouse);
    glutKeyboardFunc(myKeyBoard);
    glutDisplayFunc(myDisplay);
    glutReshapeFunc(myReshape);
    glutSpecialFunc(mySpecialInput);

    glClearColor(1.0,1.0,1.0,0.0);//white background canvas
    glColor3f(0.0,0.0,0.0);//use black for further draws
    glPointSize(4);
    glLineWidth(1.5);
    glutMainLoop();

    return 0;
}
