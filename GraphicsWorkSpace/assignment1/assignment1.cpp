#include <iostream>
#include <limits.h>

#include "display_utility.h"
#include "curves.h"
#include "transformations.h"
using namespace std;

Point points[20],shift[2],bezier[51], lagrange[51];
int cnt=0,clicks = 0,SCREEN_WIDTH = 1000,SCREEN_HEIGHT = 650;

/* 0-get user points*/
/*1- add/delete*/
int MODE=0;



void myInit() {

    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(0.0,SCREEN_WIDTH,0.0,SCREEN_HEIGHT);

}

void displayUserPoints()
{
    glColor3f(0.0,0.0,0.0);
    for(int k=0;k<clicks;k++)
        drawDot(points[k]);


    glColor3f(1.0,1.0,0.0);
    drawLineStrip(points,clicks);
}
void displayBezierCurve(){

    glColor3f(0.0,0.0,1.0);
     drawLineStrip(bezier,51);


}

void displayLagrangeCurve(){

    glColor3f(1.0,0.0,1.0);
    drawLineStrip(lagrange,51);
}
//Calculate the bezier point [generalized]


void myReshape(int w,int h)
{
    SCREEN_WIDTH=w;
    SCREEN_HEIGHT=h;

    glViewport(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    glMatrixMode(GL_PROJECTION);// choose projection matrix
    glLoadIdentity();//clear projection matrix
    gluOrtho2D(0.0,SCREEN_WIDTH,0.0,SCREEN_HEIGHT);//use orthographic projection->projection matrix will be updated

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

    glClear(GL_COLOR_BUFFER_BIT);

    drawAxis(SCREEN_WIDTH,SCREEN_HEIGHT);
    displayUserPoints();
    displayBezierCurve();
    displayLagrangeCurve();

    glFlush();
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
int main(int argc, char *argv[]) {
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
